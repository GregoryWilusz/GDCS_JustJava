/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int coffeePrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText enteredName = (EditText) findViewById(R.id.name_field);
        String customerName = enteredName.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        String emailContent = createOrderSummary(customerName, coffeePrice, hasWhippedCream, hasChocolate);
        String emailSubject = getString(R.string.subject, customerName);
        composeEmailWithOrderSummary(emailContent, emailSubject);
    }

    /**
     * This method invokes an common email intent and creates an email's subject and contens
     *
     * @param text is the content of an email message
     * @param subject of an email
     */
    public void composeEmailWithOrderSummary(String text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) { // checks if there is activity on the device to
            startActivity(intent);                                 // handle this intent
        }
    }


    /**
     * This method creates an order summary
     *
     * @param coffeePrice of the order
     * @param name entered by the customer
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants chocolate topping
     * @return order text summary
     */
    private String createOrderSummary(String name, int coffeePrice, boolean hasWhippedCream, boolean hasChocolate) {
        int price = calculatePrice(coffeePrice, hasWhippedCream, hasChocolate);
        String orderSummary = getString(R.string.name, name) +
                "\n" + getString(R.string.add_whipped_cream, hasWhippedCream) +
                "\n" + getString(R.string.add_chocolate, hasChocolate) +
                "\n" + getString(R.string.quantity_text, quantity) +
                "\n" + getString(R.string.total, NumberFormat.getCurrencyInstance().format(price)) + "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

    /**
     * Calculates the price of the order.
     *
     * @param coffeePrice of one cup of coffee
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants whipped cream topping
     * @return total order price
     */
    private int calculatePrice(int coffeePrice, boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = coffeePrice;
        int whippedCreamPrice = 1;
        int chocolatePrize = 2;

        if (hasWhippedCream) {
            basePrice += whippedCreamPrice;
        }

        if (hasChocolate) {
            basePrice += chocolatePrize;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the increment quantity button is clicked.
     */
    public void incrementQuantity(View view) {
        if (quantity == 10) {
            Toast.makeText(this, getString(R.string.toast_max_ordered), Toast.LENGTH_SHORT).show();
            return; // keyword 'return' is used to break from a method
        }
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement quantity button is clicked.
     */
    public void decrementQuantity(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.toast_min_ordered), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }
}
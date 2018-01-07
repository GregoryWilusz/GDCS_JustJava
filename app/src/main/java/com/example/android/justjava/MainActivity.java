/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

        displayMessage(createOrderSummary(customerName, coffeePrice, hasWhippedCream, hasChocolate));
    }

    /**
     * This method creates an order summary
     *
     * @param coffeePrice of the order
     * @param name entered by the customer
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name, int coffeePrice, boolean hasWhippedCream, boolean hasChocolate) {
        int price = calculatePrice(coffeePrice, hasWhippedCream, hasChocolate);
        String orderSummary = "Name: " + name +
                "\nAdd whipped cream? " + hasWhippedCream +
                "\nAdd chocolate? " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\nThank you!";
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

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence messageTooMany = "You cannot order more than 10 cups of coffee.";
        CharSequence messageTooFew = "You cannot order less than 1 cup of coffee.";

        if (number >= 10) {
            Toast.makeText(context, messageTooMany, duration).show();
        }

        if (number < 2) {
            Toast.makeText(context, messageTooFew, duration).show();
        }
    }

    /**
     * This method is called when the increment quantity button is clicked.
     */
    public void incrementQuantity(View view) {
        if (quantity >= 10) {
            return; // keyword 'return' is used to break from a method
        }

        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement quantity button is clicked.
     */
    public void decrementQuantity(View view) {
        if (quantity < 2) {
            return;
        }

        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
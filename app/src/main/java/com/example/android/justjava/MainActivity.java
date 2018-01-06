/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(7, hasWhippedCream));

    }


    private String createOrderSummary(int price, boolean withWhippedCream) {
        String orderSummary = "Name: Lyla the Labyrinth\nQuantity: " + quantity +
                "\nAdd whipped cream? " + withWhippedCream +
                "\nQuantity: " + quantity +
                "\nTotal: $" + calculatePrice(price) + "\nThank you!";
        return orderSummary;
    }

    public void isTopping(View view) {
        hasWhippedCream = !hasWhippedCream;
    }

    /**
     * Calculates the price of the order.
     *
     * @param pricePerCup is the price of one cup of coffee
     * @return total price
     */
    private int calculatePrice(int pricePerCup) {
        return quantity * pricePerCup;
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
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement quantity button is clicked.
     */
    public void decrementQuantity(View view) {
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
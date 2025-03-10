package com.example.cashier;

import com.example.cashier.calculator.rules.ProductRule;

public class DefaultRule extends ProductRule {


    /**
     * Constructs a DiscountRule instance for a given product with specified discount rules.
     *
     * @param comment          A description or additional comments for this rule.
     * @param minPurchaseItems The minimum number of items required to be eligible for the discount.
     * @throws IllegalArgumentException If the discount is not within the range 0.01 to 1.0.
     */
    public DefaultRule(String comment, int minPurchaseItems) {
        super(comment, minPurchaseItems);
    }

    @Override
    public boolean isApplicable(int quantity) {
        return true;
    }

}

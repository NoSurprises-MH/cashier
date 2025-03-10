package com.example.cashier.calculator.rules;

public class DiscountRule extends ProductRule {


    private final int maxPurchase;
    private final double discount;

    /**
     * Constructs a DiscountRule instance for a given product with specified discount rules.
     *
     * @param comment          A description or additional comments for this rule.
     * @param minPurchaseItems The minimum number of items required to be eligible for the discount.
     * @param maxPurchase      The maximum number of items allowed for the discount; use -1 for no limit.
     * @param discount         The discount rate expressed as a fraction (e.g., 0.5 for 50%). Must be between 0.01 and 1.0.
     * @throws IllegalArgumentException If the discount is not within the range 0.01 to 1.0.
     */
    public DiscountRule(String comment, int minPurchaseItems, int maxPurchase, double discount) {
        super(comment, minPurchaseItems);
        if (discount > 1.0 || discount < 0.01) {
            throw new IllegalArgumentException("Discount must be between 0.01 and 1.0");
        }
        this.maxPurchase = maxPurchase;
        this.discount = discount;
    }

    public double getDiscount() {
        return 1 - discount;
    }

    @Override
    public boolean isApplicable(int quantity) {
        return quantity >= minPurchase() && (quantity <= maxPurchase || maxPurchase == -1);
    }
}

package com.example.cashier.calculator.rules;

public class LimitationRule extends ProductRule {
    private final int maxPurchase;

    public LimitationRule(String comment, int minPurchaseItems, int maxPurchase) {
        super(comment, minPurchaseItems);
        this.maxPurchase = maxPurchase;
        if (maxPurchase < 1) {
            throw new IllegalArgumentException("MaxPurchase must be set for Limitation Rules");
        }
    }

    public int getMaxPurchase() {
        return maxPurchase;
    }

    @Override
    public boolean isApplicable(int quantity) {
        return quantity > maxPurchase;
    }
}

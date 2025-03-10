package com.example.cashier.calculator.rules;

public abstract class ProductRule {
    private final String comment;
    private final int minPurchaseItems;

    public ProductRule(String comment, int minPurchaseItems) {
        this.comment = comment;
        this.minPurchaseItems = minPurchaseItems;
    }

    public String comment() {
        return comment;
    }

    public int minPurchase() {
        return minPurchaseItems;
    }


    public abstract boolean isApplicable(int quantity);


}

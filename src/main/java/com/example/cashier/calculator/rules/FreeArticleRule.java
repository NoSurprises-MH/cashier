package com.example.cashier.calculator.rules;

import com.example.cashier.Product;

public class FreeArticleRule extends ProductRule {
    private final Product freeProduct;
    private final int freeItemQuantity;

    public FreeArticleRule(String comment, int minPurchaseItems, Product freeProduct, int freeItemQuantity) {
        super(comment, minPurchaseItems);
        this.freeProduct = freeProduct;
        this.freeItemQuantity = freeItemQuantity;
    }

    public int getFreeItemQuantity() {
        return freeItemQuantity;
    }

    public Product getFreeProduct() {
        return freeProduct;
    }

    @Override
    public boolean isApplicable(int quantity) {
        return quantity >= minPurchase();
    }

}

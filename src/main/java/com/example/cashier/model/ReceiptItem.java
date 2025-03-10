package com.example.cashier.model;

import com.example.cashier.Product;

public record ReceiptItem(int quantity, Product product, double sum, String comment) {
}

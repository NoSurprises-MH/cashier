package com.example.cashier.model;

import com.example.cashier.Product;

public record CartItem(int quantity, Product product) {
}

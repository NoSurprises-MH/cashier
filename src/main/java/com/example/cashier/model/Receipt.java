package com.example.cashier.model;

import java.util.List;

public record Receipt(List<ReceiptItem> receiptItems, double total) {
}

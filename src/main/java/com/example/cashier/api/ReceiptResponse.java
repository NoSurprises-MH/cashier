package com.example.cashier.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Response object containing details of the processed checkout")
public record ReceiptResponse(
        @Schema(description = "List of generated receipt items based on the checkout request", requiredMode = REQUIRED)
        List<ReceiptResponseItem> receiptResponseItems,
        @Schema(description = "Total sum", requiredMode = REQUIRED)
        double totalSum
) {
}
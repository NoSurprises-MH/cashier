package com.example.cashier.api;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Itemized detail of the receipt generated for the checkout")
public record ReceiptResponseItem(
        @Schema(description = "Quantity of the product in this receipt item", example = "2", requiredMode = REQUIRED)
        int quantity,

        @Schema(description = "Product associated with this receipt item", requiredMode = REQUIRED)
        String product,

        @Schema(description = "Total cost for this receipt item", example = "140.00", requiredMode = REQUIRED)
        double sum,

        @Schema(description = "Additional details or comments regarding this receipt item", example = "3 free Article 5 when purchasing Article 7")
        String comment
) {}
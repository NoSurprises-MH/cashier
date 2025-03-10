package com.example.cashier.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Object representing a product order")
public record CartItemRequest(
        @Schema(description = "Quantity of the product being ordered", example = "2", requiredMode = REQUIRED)
        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity,

        @Schema(description = "Identifier of the product being ordered", requiredMode = REQUIRED, allowableValues = {"ARTICLE_1", "ARTICLE_2", "ARTICLE_3", "ARTICLE_4", "ARTICLE_5", "ARTICLE_6", "ARTICLE_7", "ARTICLE_8", "ARTICLE_9", "ARTICLE_10"})
        @NotNull(message = "Product is required")
        ProductRequest product
) {
}
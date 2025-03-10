package com.example.cashier.api;

import com.example.cashier.model.CartItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Request object for initiating a checkout")
public record CheckoutCartRequest(
        @Schema(description = "List of product orders included in the checkout request", requiredMode = REQUIRED)
        List<CartItemRequest> cartItems
) {}
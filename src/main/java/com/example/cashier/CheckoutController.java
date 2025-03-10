package com.example.cashier;

import com.example.cashier.api.CheckoutCartRequest;
import com.example.cashier.api.ReceiptResponse;
import com.example.cashier.api.ReceiptResponseItem;
import com.example.cashier.model.CartItem;
import com.example.cashier.model.CheckoutCart;
import com.example.cashier.model.Receipt;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api/v1")
public class CheckoutController {

    CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Operation(
            summary = "Process checkout and generate receipt",
            description = "Submits a shopping cart with items and quantities to calculate the total bill, apply discounts, and return a receipt."
    )
    @PostMapping(value = "/checkout")
    public ReceiptResponse checkout(@Valid @RequestBody CheckoutCartRequest checkoutCartRequest) {


        CheckoutCart checkoutCart = new CheckoutCart(convertCartItemRequestToModel(checkoutCartRequest));
        Receipt receipt = checkoutService.handleRequest(checkoutCart);
        List<ReceiptResponseItem> list = convertToReceiptResponseItems(receipt);
        return new ReceiptResponse(list, receipt.total());
    }

    private static List<ReceiptResponseItem> convertToReceiptResponseItems(Receipt receipt) {
        return receipt.receiptItems().stream().map(x -> new ReceiptResponseItem(x.quantity(), x.product().name(), x.sum(), x.comment())).toList();
    }

    private static List<CartItem> convertCartItemRequestToModel(CheckoutCartRequest checkoutCartRequest) {
        return checkoutCartRequest.cartItems().stream().map(x -> new CartItem(x.quantity(), Product.valueOf(x.product().name()))).toList();
    }
}

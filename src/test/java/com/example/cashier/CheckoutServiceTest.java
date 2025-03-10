package com.example.cashier;

import com.example.cashier.model.CheckoutCart;
import com.example.cashier.model.CartItem;
import com.example.cashier.model.Receipt;
import com.example.cashier.model.ReceiptItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class CheckoutServiceTest {

    @Autowired
    private CheckoutService checkoutService;

    @Test
    void testOrderArticle7ShouldGiveFreeItems() {
        // Arrange
        CartItem cartItem = new CartItem(1, Product.ARTICLE_7);
        CheckoutCart request = new CheckoutCart(List.of(cartItem));


        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(2);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(new ReceiptItem(1, Product.ARTICLE_7,70,null),
                new ReceiptItem(3, Product.ARTICLE_5,0,"3 free Article 5 when purchasing Article 7")));
    }


    @Test
    void testOrder5Article9ShouldGiveError() {
        // Arrange
        CartItem cartItem = new CartItem(5, Product.ARTICLE_9);
        CheckoutCart request = new CheckoutCart(List.of(cartItem));


        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(1);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(new ReceiptItem(0, Product.ARTICLE_9,0,"You can only buy 4 of Article 9")));
    }

    @Test
    void testOrder1ShouldHandleFreeItemsAndRegularItemsCombination() {
        // Arrange
        CartItem order1 = new CartItem(1, Product.ARTICLE_6);
        CartItem order2 = new CartItem(3, Product.ARTICLE_9); // Includes 5 free ARTICLE_5
        CartItem order3 = new CartItem(2, Product.ARTICLE_7); // Includes 5 free ARTICLE_5
        CheckoutCart request = new CheckoutCart(List.of(order1, order2,order3));

        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(5);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(
                new ReceiptItem(2, Product.ARTICLE_7, 140, null), // 2 * 70
                new ReceiptItem(1, Product.ARTICLE_6, 22, null), // 1 * 22
                new ReceiptItem(3, Product.ARTICLE_9, 240, null), // 3 * 80
                new ReceiptItem(3, Product.ARTICLE_5, 0, "3 free Article 5 when purchasing Article 7"),
                new ReceiptItem(3, Product.ARTICLE_5, 0, "3 free Article 5 when purchasing Article 7")
        ));
    }

    @Test
    void testOrder2ShouldHandleFreeItemsAndDiscounts() {
        // Arrange
        CartItem order1 = new CartItem(12, Product.ARTICLE_5);
        CartItem order3 = new CartItem(1, Product.ARTICLE_7);
        CheckoutCart request = new CheckoutCart(List.of(order1, order3));

        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(4);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(
                new ReceiptItem(12, Product.ARTICLE_5, 192, null), // 12 * 16
                new ReceiptItem(1, Product.ARTICLE_6, 0, "1 free Article 6 when purchasing 10 of Article 5"),
                new ReceiptItem(1, Product.ARTICLE_7, 70, null), // 1 * 70
                new ReceiptItem(3, Product.ARTICLE_5, 0, "3 free Article 5 when purchasing Article 7")
        ));
    }

    @Test
    void testOrder3ShouldGiveDiscountedOrder6() {
        // Arrange
        CartItem order1 = new CartItem(7, Product.ARTICLE_6); // With a 75% discount
        CartItem order2 = new CartItem(2, Product.ARTICLE_1);
        CheckoutCart request = new CheckoutCart(List.of(order1, order2));

        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(2);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(
                new ReceiptItem(7, Product.ARTICLE_6, 38.5, "Discount 75% on Article 6"), // 7 * 22 * 0.25
                new ReceiptItem(2, Product.ARTICLE_1, 20, null) // 2 * 10
        ));
    }

    @Test
    void testOrder4ShouldHandleMaxArticle9QuantityAndDiscountOnArticle6() {
        // Arrange
        CartItem order1 = new CartItem(3, Product.ARTICLE_6); // With a 50% discount
        CartItem order2 = new CartItem(5, Product.ARTICLE_9); // Exceeds max quantity
        CheckoutCart request = new CheckoutCart(List.of(order1, order2));

        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(2);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(
                new ReceiptItem(3, Product.ARTICLE_6, 33, "Discount 50% on Article 6"), // 3 * 22 * 0.50
                new ReceiptItem(0, Product.ARTICLE_9, 0, "You can only buy 4 of Article 9") // Error message
        ));
    }

    @Test
    void testOrder5ShouldHandleFreeItemsAndDiscountsForArticle6() {
        // Arrange
        CartItem order1 = new CartItem(10, Product.ARTICLE_5); // Includes free ARTICLE_6
        CartItem order2 = new CartItem(2, Product.ARTICLE_6); // Includes 50% discount
        CheckoutCart request = new CheckoutCart(List.of(order1, order2));

        // Act
        Receipt receipt = checkoutService.handleRequest(request);

        // Assert
        assertThat(receipt.receiptItems()).hasSize(3);
        assertThat(receipt.receiptItems()).containsExactlyInAnyOrderElementsOf(List.of(
                new ReceiptItem(10, Product.ARTICLE_5, 160, null), // 10 * 16
                new ReceiptItem(2, Product.ARTICLE_6, 22, "Discount 50% on Article 6") ,// 2 * 22 * 0.50
                new ReceiptItem(1, Product.ARTICLE_6, 0, "1 free Article 6 when purchasing 10 of Article 5") // Free with article 5
        ));
    }


}
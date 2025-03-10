package com.example.cashier;

import com.example.cashier.model.CheckoutCart;
import com.example.cashier.model.CartItem;
import com.example.cashier.model.Receipt;
import com.example.cashier.model.ReceiptItem;
import com.example.cashier.calculator.Calculator;
import com.example.cashier.calculator.rules.ProductRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.cashier.calculator.RuleCatalog.PRODUCTS_TO_RULES;

@Service
public class CheckoutService {

    private final Calculator calculator;

    public CheckoutService(Calculator calculator) {
        this.calculator = calculator;
    }


    public Receipt handleRequest(CheckoutCart request) {
        var items = new ArrayList<ReceiptItem>();
        for (CartItem cartItem : request.cartItems()) {
            List<ProductRule> productRules = PRODUCTS_TO_RULES.getOrDefault(cartItem.product(), List.of());

            boolean anyRuleApplicable = false;
            for (ProductRule productRule : productRules) {
                if (productRule.isApplicable(cartItem.quantity())) {
                    anyRuleApplicable = true;
                    items.addAll(calculator.calculateForRule(productRule, cartItem));
                }
            }
            if (!anyRuleApplicable) {
                items.add(calculator.calculateDefault(cartItem));
            }
        }
        return new Receipt(items, calculator.getTotal(items));
    }
}

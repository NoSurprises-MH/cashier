package com.example.cashier.calculator;

import com.example.cashier.model.CartItem;
import com.example.cashier.model.ReceiptItem;
import com.example.cashier.calculator.rules.DiscountRule;
import com.example.cashier.calculator.rules.FreeArticleRule;
import com.example.cashier.calculator.rules.LimitationRule;
import com.example.cashier.calculator.rules.ProductRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Calculator {

    public List<ReceiptItem> calculateForRule(ProductRule rule, CartItem cartItem) {
        List<ReceiptItem> receiptItemList = new ArrayList<>();
        if (rule instanceof FreeArticleRule ruleInstance) {
            var applyNumTimes = cartItem.quantity() / ruleInstance.minPurchase();
            for (int i = 0; i < applyNumTimes; i++) {
                receiptItemList.add(new ReceiptItem(ruleInstance.getFreeItemQuantity(), ruleInstance.getFreeProduct(), 0, ruleInstance.comment()));
            }
            receiptItemList.add(new ReceiptItem(cartItem.quantity(), cartItem.product(), cartItem.quantity() * cartItem.product().getPrice(), null));
        } else if (rule instanceof LimitationRule ruleInstance) {
            receiptItemList.add(new ReceiptItem(0, cartItem.product(), 0, ruleInstance.comment()));
        } else if (rule instanceof DiscountRule ruleInstance) {
            receiptItemList.add(new ReceiptItem(cartItem.quantity(), cartItem.product(), cartItem.quantity() * cartItem.product().getPrice() * ruleInstance.getDiscount(), ruleInstance.comment()));
        }
        return receiptItemList;
    }

    public ReceiptItem calculateDefault(CartItem cartItem) {
        return new ReceiptItem(cartItem.quantity(), cartItem.product(), cartItem.quantity() * cartItem.product().getPrice(), null);
    }

    public double getTotal(ArrayList<ReceiptItem> items) {
        return items.stream().map(ReceiptItem::sum).reduce(0.0, Double::sum);
    }
}

package com.example.cashier.calculator;

import com.example.cashier.Product;
import com.example.cashier.calculator.rules.DiscountRule;
import com.example.cashier.calculator.rules.FreeArticleRule;
import com.example.cashier.calculator.rules.LimitationRule;
import com.example.cashier.calculator.rules.ProductRule;

import java.util.List;
import java.util.Map;

import static com.example.cashier.Product.*;

public class RuleCatalog {

    private static final FreeArticleRule RULE_1 = new FreeArticleRule(
            "3 free Article 5 when purchasing Article 7",
            1,
            Product.ARTICLE_5,
            3);

    private static final FreeArticleRule RULE_2 = new FreeArticleRule(
            "1 free Article 6 when purchasing 10 of Article 5",
            10,
            Product.ARTICLE_6,
            1);

    private static final LimitationRule RULE_3 = new LimitationRule(
            "You can only buy 4 of Article 9",
            0,
            4);

    private static final DiscountRule RULE_4 = new DiscountRule(
            "Discount 50% on Article 6",
            2,
            4,
            0.5);

    private static final DiscountRule RULE_5 = new DiscountRule(
            "Discount 75% on Article 6",
            5,
            -1,
            0.75);


    /**
     * A mapping between products and their associated list of applicable rules.
     * Each entry in the map corresponds to a product and the list of rules that apply to it.
     * The rules can include discounts, limitations, or free article offers depending on the rule definition.
     */
    public static final Map<Product, List<ProductRule>> PRODUCTS_TO_RULES = Map.of(
            ARTICLE_7, List.of(RULE_1),
            ARTICLE_5, List.of(RULE_2),
            ARTICLE_9, List.of(RULE_3),
            ARTICLE_6, List.of(RULE_4, RULE_5));

    private RuleCatalog() {
    }
}

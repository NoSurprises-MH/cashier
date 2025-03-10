package com.example.cashier;

public enum Product {
    ARTICLE_1(10),
    ARTICLE_2(20),
    ARTICLE_3(30),
    ARTICLE_4(44),
    ARTICLE_5(16),
    ARTICLE_6(22),
    ARTICLE_7(70),
    ARTICLE_8(89),
    ARTICLE_9(80);

    private final int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}


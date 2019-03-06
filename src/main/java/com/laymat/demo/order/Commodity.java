package com.laymat.demo.order;

import java.math.BigDecimal;

/**
 * 商品类
 */
public class Commodity {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private String name;
    private BigDecimal price;
}

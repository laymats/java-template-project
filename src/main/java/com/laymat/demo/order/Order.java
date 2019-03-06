package com.laymat.demo.order;

import java.math.BigDecimal;

/**
 * 订单类
 */
public class Order {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private String name;
    private BigDecimal amount;
    private int time;
}

package com.laymat.demo.order;

import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

public interface OrderHandle {
    boolean initService(List<Commodity> commodityList);
    boolean placeOrder(Order order);
    boolean replenishment(List<Commodity> commodityList);
    int commodityCount();
    Hashtable<Commodity,Order> placeDetail();
}

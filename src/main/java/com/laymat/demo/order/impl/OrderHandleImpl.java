package com.laymat.demo.order.impl;

import com.laymat.demo.order.Commodity;
import com.laymat.demo.order.Order;
import com.laymat.demo.order.OrderHandle;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service("orderHandle")
public class OrderHandleImpl implements OrderHandle {
    private static ConcurrentHashMap<Commodity, Order> commodityOrderConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public boolean initService(List<Commodity> commodityList) {
        for (var commodity : commodityList) {
            var order = new Order();
            order.setName(null);
            commodityOrderConcurrentHashMap.put(commodity, order);
        }
        return true;
    }

    @Override
    public boolean placeOrder(Order order) {
        for (var entry : commodityOrderConcurrentHashMap.entrySet()) {
            if (entry.getValue().getName() == null) {
                entry.setValue(order);
                //处理订单后续流程（可异步）
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replenishment(List<Commodity> commodityList) {
        for (var commodity : commodityList) {
            commodityOrderConcurrentHashMap.put(commodity, null);
        }
        return true;
    }

    @Override
    public int commodityCount() {
        return commodityOrderConcurrentHashMap.size();
    }

    @Override
    public Hashtable<Commodity, Order> placeDetail() {
        var table = new Hashtable<Commodity, Order>();
        for (var map : commodityOrderConcurrentHashMap.entrySet()) {
            if (map.getValue().getName() != null)
                table.put(map.getKey(), map.getValue());
        }
        return table;
    }
}

package com.laymat.demo;

import cn.hutool.core.date.DateUtil;
import com.laymat.demo.order.Commodity;
import com.laymat.demo.order.Order;
import com.laymat.demo.order.OrderHandle;
import com.laymat.demo.order.impl.OrderHandleImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {
    @Resource
    OrderHandle orderHandle;

    public class ServletInitializer extends SpringBootServletInitializer {
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(DemoApplication.class);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }
    @RequestMapping("/init")
    public boolean Init() {
        var list = new ArrayList<Commodity>();
        for (var i = 0; i < 1000; i++) {
            var commodity = new Commodity();
            commodity.setName("iphone x");
            commodity.setPrice(new BigDecimal("1000"));
            list.add(commodity);
        }
        //初始化订单抢购服务
        return orderHandle.initService(list);
    }

    @RequestMapping("/test")
    public Map TestOrder() {
        var order = new Order();
        order.setAmount(new BigDecimal(1000));
        order.setName("张三");
        order.setTime((int) DateUtil.currentSeconds());
        var map = new HashMap<>();
        map.put("state", orderHandle.placeOrder(order));
        return map;
    }

    @RequestMapping("/count")
    public int CommodityCount() {
        return orderHandle.commodityCount();
    }

    @RequestMapping("/detail")
    public Hashtable<Commodity, Order> CommodityDetail() {
        return orderHandle.placeDetail();
    }
}

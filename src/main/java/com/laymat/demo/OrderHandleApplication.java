package com.laymat.demo;

import com.laymat.demo.order.Commodity;
import com.laymat.demo.order.impl.OrderHandleImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@SpringBootApplication
//@RestController
//public class OrderHandleApplication {
//    public class ServletInitializer extends SpringBootServletInitializer {
//        @Override
//        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//            return application.sources(OrderHandleApplication.class);
//        }
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(OrderHandleApplication.class, args);
//
//
//    }
//
//}
//


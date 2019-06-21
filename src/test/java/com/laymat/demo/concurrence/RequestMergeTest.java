package com.laymat.demo.concurrence;

import cn.hutool.core.util.RandomUtil;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestMergeTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RequestMerge requestMerge;

    @Test
    public void doRequest() {

        var fixedThreadPool = Executors.newFixedThreadPool(1000);
        for (var i = 0; i <= 1000; i++) {

            fixedThreadPool.execute(() -> {
                String data = String.valueOf(RandomUtil.randomString(10));
                try {
                    var result = requestMerge.doRequest(String.valueOf(data));
                    logger.info("id:[{}] result:[{}]", data, result);
                    if (result) {
                        showSuccess();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    Object lockObj = new Object();
    Integer count = 0;

    void showSuccess() {
        synchronized (lockObj) {
            count++;
            logger.info("success:[{}]", count);
        }
    }
}
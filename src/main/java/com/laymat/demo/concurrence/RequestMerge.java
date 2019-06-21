package com.laymat.demo.concurrence;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.concurrent.*;

@Service
public class RequestMerge {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected LinkedBlockingQueue<SimpleRequest> simpleRequestBlockingDeque = new LinkedBlockingQueue<>();

    @Data
    protected class SimpleRequest {
        private String id;
        private CompletableFuture<Boolean> future;
    }

    @PostConstruct
    protected void init() {
        var scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            var size = simpleRequestBlockingDeque.size();
            if (size == 0) return;

            /**
             * 获取等待处理的队列数据，处理思路示例
             * 1、根据算法模型，每次取出指定数据大小，列如100/1000/10000，再根据算法权重去计算哪一个id符合条件（适合控制抽奖活动）
             * 2、取出所有数据，并将id进行组合提交到后台查询，然后再将组合查询的结果拆分之后返回给客户端（适合海量请求处理）
             */
            var requestList = new ArrayList<SimpleRequest>();
            for (var i = 0; i < size; i++) {
                var request = simpleRequestBlockingDeque.poll();

                //根据算法返回结果
                if (i % 5 == 0) {
                    request.future.complete(true);
                } else {
                    request.future.complete(false);
                }

                //根据查询结果返回
                //requestList.add(request);
            }
            logger.info("已处理:" + size);

            //后台查询
            //selectRequestBatch(requestList)

//            for (var request : requestList) {
//                request.future.complete(true);
//            }

        }, 0, 10, TimeUnit.MILLISECONDS);
    }


    public boolean doRequest(String id) throws ExecutionException, InterruptedException {
        /**
         * 包装异步处理对象
         */
        var request = new SimpleRequest();
        request.id = id;
        request.future = new CompletableFuture<>();
        /**
         * 添加到异步处理队列
         */
        simpleRequestBlockingDeque.add(request);
        /**
         * 等待异步返回
         */
        return request.future.get();
    }
}

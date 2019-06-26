package com.laymat.demo;

import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DemoApplicationTests {
    private Object lock = new Object();
    private Integer count = 0;
    private Integer total = 0;

    @Test
    public void contextLoads() throws IOException, InterruptedException {
        var file = new InputStreamReader(new FileInputStream("E:\\Program Files (x86)\\Tencent\\WeChat\\webak\\WeChat Files\\smailook\\FileStorage\\File\\2019-06\\sf.txt"));
        BufferedReader br = new BufferedReader(file);

        var newRule = new ArrayList<String>();
        String lineTxt = null;
        while ((lineTxt = br.readLine()) != null) {
            //(.*://dev.tg.wan.360.cn/.*)

            String finalLineTxt = lineTxt;
            Thread t = new Thread(() -> {
                try {
                    total++;
                    var data = HttpUtil.get("http://" + finalLineTxt, 3000);
                    if (data != null) {
                        newRule.add(String.format("(.*://%s/.*)", finalLineTxt));
                    }
                } catch (Exception ex) {
                } finally {
                    synchronized (lock) {
                        count++;
                    }
                }
            });
            t.start();


        }
        br.close();

        while (true) {
            System.out.println("共计:" + total + "已完成:" + count);
            var text = "^" + String.join("|", newRule.toArray(new String[newRule.size()]));
            //System.out.println(text);


            var newFile = new OutputStreamWriter(new FileOutputStream("C:\\Users\\dell\\Desktop\\temp.txt"));
            newFile.write(text, 0, text.length());
            newFile.close();
            Thread.sleep(100);

            if (total <= count)
                break;
        }
    }

}

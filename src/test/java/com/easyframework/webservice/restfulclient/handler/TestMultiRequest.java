package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.ResponseAdapter;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by xgt on 2016/1/19.
 */
public class TestMultiRequest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-pooling-test.xml");
        final Client client = (Client) context.getBean("easyHttpClient");
                Date start = new Date();
        final ResponseAdapter<String> adapter = new ResponseAdapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        };
        for(int i=0; i<1000; i++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Request request = new BaiduRequest();
                    String result = client.doRequest(request, adapter);
//                                    System.out.println("======="+result);
                }
            });
            thread.start();
//            thread.join();
//            System.out.println(i);
        }
        while (true)
        {
            if ( Thread.activeCount() == 2 ) break;
        }
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());
    }
}

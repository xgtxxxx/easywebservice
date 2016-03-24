package com.easyframework.webservice.restfulclient.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.adapters.SimpleStringAdapter;
import com.easyframework.webservice.restfulclient.handler.BaiduRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-pooling-test.xml"})
public class ExampleSpring {
    @Autowired
    private Client client;

    @Test
    public void test(){
        Request request = new BaiduRequest();
        String html = client.doRequest(request,new SimpleStringAdapter());
        System.out.println(html);
    }
}

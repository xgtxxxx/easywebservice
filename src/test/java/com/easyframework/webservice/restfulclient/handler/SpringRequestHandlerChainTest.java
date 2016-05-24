package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.adapters.responseadapter.ResponseAdapter;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-test.xml"})
public class SpringRequestHandlerChainTest {
    @Autowired
    private Client client;

    @Test
    public void testBaidu(){
        Request request = new BaiduRequest();
        String result = client.doRequest(request, new ResponseAdapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        });

        System.out.println(result);
    }

    @Test
    public void testRequest100(){
        Date start = new Date();
        for(int i=0; i<100; i++){
            Request request = new BaiduRequest();
            String result = client.doRequest(request, new ResponseAdapter<String>() {
                public String convertTo(ResponseInfo f) {
                    return StringUtils.toString(f.getBody());
                }
            });
        }
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());
    }

    @Test
    public void testMultiRequest() throws InterruptedException {
        Date start = new Date();
        final ResponseAdapter<String> adapter = new ResponseAdapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        };
        for(int i=0; i<500; i++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Request request = new BaiduRequest();
                    String result = client.doRequest(request, adapter);
                }
            });
            thread.start();
            System.out.println(Thread.activeCount());
        }
        while (true)
        {
            if ( Thread.activeCount() == 2 ) break;
        }
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());
    }
}

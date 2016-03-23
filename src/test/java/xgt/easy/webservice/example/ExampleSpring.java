package xgt.easy.webservice.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.adapters.SimpleStringAdapter;
import xgt.easy.webservice.handler.BaiduRequest;

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

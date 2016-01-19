package xgt.easy.webservice.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.model.ResponseInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.util.Date;

/**
 * Created by xgt on 2016/1/19.
 */
public class TestMultiRequest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-pooling-test.xml");
        final Client client = (Client) context.getBean("easyHttpClient");
                Date start = new Date();
        final Adapter<String> adapter = new Adapter<String>() {
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

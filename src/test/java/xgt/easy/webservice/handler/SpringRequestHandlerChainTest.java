package xgt.easy.webservice.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.client.EasyHttpClient;
import xgt.easy.webservice.client.SimpleClient;
import xgt.easy.webservice.model.ResponseInfo;
import xgt.easy.webservice.request.SimpleRequest;
import xgt.easy.webservice.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-test.xml"})
public class SpringRequestHandlerChainTest {
    @Autowired
    private Client client;

    @Test
    public void testBaidu(){
        SimpleRequest request = new BaiduRequest();
        request.setHost("http://www.baidu.com");
        request.setCtx("s");

        String result = client.doRequest(request, new Adapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        });

        System.out.println(result);
    }
}

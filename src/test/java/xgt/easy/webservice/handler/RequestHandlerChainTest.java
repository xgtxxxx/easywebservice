package xgt.easy.webservice.handler;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.request.GetRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RequestHandlerChainTest {
    @Test
     public void testHandleGet(){
        RequestHandlerChain chain = new RequestHandlerChain();
        chain.buildChain();

        GetRequest request = new GetRequest();
        request.setStart(10);
        request.setTerm("hello 中国");
        try {
            RequestInfo info = chain.handle(request);
            System.out.println(JSON.toJSON(info));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

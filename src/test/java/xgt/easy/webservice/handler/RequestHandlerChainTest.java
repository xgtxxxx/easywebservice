package xgt.easy.webservice.handler;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.request.GetRequest;
import xgt.easy.webservice.request.PostRequest;

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

    @Test
    public void testPost() throws UnsupportedEncodingException, IllegalAccessException {
        RequestHandlerChain chain = new RequestHandlerChain();
        chain.buildChain();
        PostRequest post = new PostRequest();
        post.setBook("book");
        post.setName("gavin");
        post.setPassword("123456");
        post.setTitle("good");
        post.setIsCache(true);
        System.out.println(JSON.toJSON(chain.handle(post)));
    }

}
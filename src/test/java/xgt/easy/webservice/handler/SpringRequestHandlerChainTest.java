package xgt.easy.webservice.handler;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.request.GetRequest;
import xgt.easy.webservice.request.PostRequest;

import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-test.xml"})
public class SpringRequestHandlerChainTest {
    @Autowired
    private RequestHandlerChain chain;
    @Test
     public void testHandleGet(){
        GetRequest request = new GetRequest();
        request.setStart(10);
        request.setTerm("hello&中国");
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
        PostRequest post = new PostRequest();
        post.setBook("book/book");
        post.setName("gavin");
        post.setPassword("123456");
        post.setTitle("good&good");
        post.setIsCache(true);
        System.out.println(JSON.toJSON(chain.handle(post)));
    }

}

package com.easyframework.webservice.restfulclient.springtemplete.client;

import com.easyframework.webservice.restfulclient.Factory;
import com.easyframework.webservice.restfulclient.adapters.requestadapter.PostBodyAdapter;
import com.easyframework.webservice.restfulclient.client.AbstractSimpleClient;
import com.easyframework.webservice.restfulclient.client.SimpleClient;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.handler.annotationhandler.RequestHandler;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import com.easyframework.webservice.restfulclient.model.RequestInfo;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.springtemplete.SpringRestfulBodyAdapter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SpringRestfulClient extends SimpleClient {

    private PostBodyAdapter<Object> entityAdapter;

    private Object lock = new Object();

    private boolean isReady = false;

    private RestTemplate restTemplate;

    @Override
    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
        ResponseInfo response = new ResponseInfo();
        final HttpEntity<Object> entity = new HttpEntity<Object>(initHeaders(info.getHeaders()));
        response.setBody(restTemplate.exchange(info.getRequestUrl(), org.springframework.http.HttpMethod.GET, entity, String.class));
        return response;
    }

    @Override
    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
        ResponseInfo response = new ResponseInfo();
        final HttpEntity<Object> entity = new HttpEntity<Object>(initBody(info),initHeaders(info.getHeaders()));
        response.setBody(restTemplate.exchange(info.getRequestUrl(), HttpMethod.POST, entity, String.class));
        return response;
    }

    private synchronized Object initBody(RequestInfo info) {
        if(entityAdapter==null){
            entityAdapter = new SpringRestfulBodyAdapter();
        }
        return entityAdapter.convertTo(info);
    }

    private HttpHeaders initHeaders(List<ParameterPair> headers){
        final HttpHeaders hs = new HttpHeaders();
        for (final ParameterPair item : headers) {
            hs.add(item.getKey(), item.getStringValue());
        }
        return hs;
    }

    private RestTemplate getDefaultClient(){
        if(restTemplate==null){
            synchronized (lock){
                if(!isReady){
                    restTemplate = new RestTemplate();
                    isReady = true;
                }
            }
        }
        return restTemplate;
    }

}

package com.easyframework.webservice.restfulclient.httpclient;

import com.easyframework.webservice.restfulclient.adapters.requestadapter.PostBodyAdapter;
import com.easyframework.webservice.restfulclient.handler.PostBodyHandler;
import com.easyframework.webservice.restfulclient.httpclient.handler.FormDataEntityHandler;
import com.easyframework.webservice.restfulclient.httpclient.handler.JsonEntityHandler;
import com.easyframework.webservice.restfulclient.model.PostContentType;
import org.apache.http.HttpEntity;

public class HttpPostBodyAdapter extends PostBodyAdapter<HttpEntity> {

    @Override
    public PostBodyHandler<HttpEntity> getDefaultHandler(String key) {
        {
            if(PostContentType.JSON.equals(key)){
                return (PostBodyHandler<HttpEntity>)new JsonEntityHandler();
            } else {
                return (PostBodyHandler<HttpEntity>)new FormDataEntityHandler();
            }
        }
    }
}

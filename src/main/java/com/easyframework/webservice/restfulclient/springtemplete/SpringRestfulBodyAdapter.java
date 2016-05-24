package com.easyframework.webservice.restfulclient.springtemplete;

import com.easyframework.webservice.restfulclient.adapters.requestadapter.PostBodyAdapter;
import com.easyframework.webservice.restfulclient.handler.PostBodyHandler;
import com.easyframework.webservice.restfulclient.model.PostContentType;
import com.easyframework.webservice.restfulclient.springtemplete.handler.FormDataEntityHandler;
import com.easyframework.webservice.restfulclient.springtemplete.handler.JsonEntityHandler;
import org.apache.http.HttpEntity;

public class SpringRestfulBodyAdapter extends PostBodyAdapter<Object> {
    @Override
    public PostBodyHandler<Object> getDefaultHandler(String key) {
        {
            if(PostContentType.JSON.equals(key)){
                return new JsonEntityHandler();
            } else {
                return new FormDataEntityHandler();
            }
        }
    }
}

package com.easyframework.webservice.restfulclient.adapters.responseadapter;

import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.JsonObjectAdapter;
import com.easyframework.webservice.restfulclient.utils.JsonUtils;
import com.easyframework.webservice.restfulclient.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SimpleJsonObjectAdapter<T> implements JsonObjectAdapter<ResponseInfo,T> {

    private ObjectMapper objectMapper;

    public T convertTo(ResponseInfo from, Class<T> clazz) throws IOException {
        if(objectMapper==null){
            objectMapper = JsonUtils.getObjectMapper();
        }
        return objectMapper.readValue(StringUtils.toString(from.getBody()),clazz);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}

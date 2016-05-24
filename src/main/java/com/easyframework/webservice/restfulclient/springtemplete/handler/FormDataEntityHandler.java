package com.easyframework.webservice.restfulclient.springtemplete.handler;

import com.easyframework.webservice.restfulclient.handler.PostBodyHandler;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import com.easyframework.webservice.restfulclient.model.PostContentType;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class FormDataEntityHandler extends PostBodyHandler<Object> {
    public MultiValueMap convertTo(List<ParameterPair> from) {
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<String, Object>();
        if(from!=null&&!from.isEmpty()){
            for(ParameterPair pp: from){
                body.add(pp.getKey(),pp.getValue());
            }
        }
        return body;
    }
}

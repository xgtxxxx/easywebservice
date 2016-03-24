package com.easyframework.webservice.restfulclient.httpclient.handler;

import com.easyframework.webservice.restfulclient.model.PostContentType;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import com.easyframework.webservice.restfulclient.model.ParameterPair;

import java.util.List;

public class FormDataEntityHandler extends RequestInfoHandler {
    public HttpEntity convertTo(List<ParameterPair> from) {
        StringBuffer sb = new StringBuffer();
        for(ParameterPair parameter: from){
            if(sb.length()>0){
                sb.append('&');
            }
            sb.append(parameter.getKey()).append('=').append(parameter.getStringValue());
        }
        return new StringEntity(sb.toString(), ContentType.create(PostContentType.FORM_DATA, "utf-8"));
    }
}

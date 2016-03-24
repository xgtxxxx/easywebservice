package com.easyframework.webservice.restfulclient.httpclient.handler;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.easyframework.webservice.restfulclient.model.ParameterPair;

import java.util.List;

public class JsonEntityHandler extends RequestInfoHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonEntityHandler.class);
    public HttpEntity convertTo(final List<ParameterPair> from) {
        final StringBuffer sb = new StringBuffer("{");
        if(from!=null){
            int index = 0;
            for(final ParameterPair pp : from){
                if(index++>0){
                    sb.append(",");
                }
                sb.append("\"").append(pp.getKey()).append("\"").append(":");
                sb.append(pp.getJsonValue());
            }
        }
        sb.append("}");
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("Request json body : {}",sb.toString());
        }
        return new StringEntity(sb.toString(), ContentType.APPLICATION_JSON);
    }
}

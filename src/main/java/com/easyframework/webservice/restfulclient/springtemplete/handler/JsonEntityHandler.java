package com.easyframework.webservice.restfulclient.springtemplete.handler;

import com.easyframework.webservice.restfulclient.handler.PostBodyHandler;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonEntityHandler extends PostBodyHandler<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonEntityHandler.class);
    public String convertTo(final List<ParameterPair> from) {
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
        return sb.toString();
    }
}

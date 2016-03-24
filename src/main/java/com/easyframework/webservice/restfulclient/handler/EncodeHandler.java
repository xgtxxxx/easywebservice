package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.model.FieldInfo;
import com.easyframework.webservice.restfulclient.Handler;
import com.easyframework.webservice.restfulclient.annotation.Encode;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeHandler extends Handler {
    @Override
    public FieldInfo handle(Object request) throws EasyWebserviceException {
        if(request instanceof FieldInfo){
            final FieldInfo info = (FieldInfo)request;
            boolean needEncode = false;
            if(info.getField().getDeclaringClass().getAnnotation(Encode.class)!=null){
                needEncode = true;
            }
            if(!needEncode){
                needEncode = info.getField().getAnnotation(Encode.class)!=null;
            }
            if(needEncode){
                if(info.getValue()!=null){
                    String value = StringUtils.toString(info.getValue());
                    try {
                        info.setValue(URLEncoder.encode(value, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        getLogger().error(e.getMessage());
                        throw new EasyWebserviceException(e);
                    }
                }
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in EncodeHandler should be type of FieldInfo");
        }
    }
}

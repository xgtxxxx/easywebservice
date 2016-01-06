package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.annotation.Encode;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeHandler extends Handler {
    @Override
    public Object handle(Object request) throws IllegalAccessException, UnsupportedEncodingException {
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
                    info.setValue(URLEncoder.encode(value, "utf-8"));
                }
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in EncodeHandler should be type of FieldInfo");
        }
    }
}

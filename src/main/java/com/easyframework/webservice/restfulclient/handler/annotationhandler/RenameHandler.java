package com.easyframework.webservice.restfulclient.handler.annotationhandler;

import com.easyframework.webservice.restfulclient.model.FieldInfo;
import com.easyframework.webservice.restfulclient.Handler;
import com.easyframework.webservice.restfulclient.annotation.Rename;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;

public class RenameHandler extends Handler {
    @Override
    public FieldInfo handle(Object request) throws EasyWebserviceException {
        if(request instanceof FieldInfo){
            final FieldInfo info = (FieldInfo)request;
            final Rename rename = info.getField().getAnnotation(Rename.class);
            if(rename!=null){
               info.setKey(rename.value());
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in SkipHandler should be type of FieldInfo");
        }
    }
}

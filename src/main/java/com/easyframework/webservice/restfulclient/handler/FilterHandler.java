package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.annotation.Filter;
import com.easyframework.webservice.restfulclient.model.FieldInfo;
import com.easyframework.webservice.restfulclient.Handler;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.model.Action;

public abstract class FilterHandler extends Handler {
    @Override
    public FieldInfo handle(Object request) throws EasyWebserviceException {
        if(request instanceof FieldInfo){
            FieldInfo info = (FieldInfo)request;
            Filter filter = info.getField().getDeclaringClass().getAnnotation(Filter.class);
            Filter fieldFilter = info.getField().getAnnotation(Filter.class);
            if(filter==null){
                filter = fieldFilter;
            }else{
                if(fieldFilter!=null&&fieldFilter.value()!=filter.value()){
                    filter = fieldFilter;
                }
            }
            if(filter!=null){
                if(filter.value()== Action.BEFORE_ENCODE){
                    beforeEncode(info);
                }else{
                    afterEncode(info);
                }
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in SkipHandler should be type of FieldInfo");
        }
    }

    public abstract void beforeEncode(final FieldInfo info);

    public abstract void afterEncode(final FieldInfo info);
}

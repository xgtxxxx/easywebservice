package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.annotation.Filter;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.model.Action;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.model.RequestInfo;

import java.io.UnsupportedEncodingException;

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

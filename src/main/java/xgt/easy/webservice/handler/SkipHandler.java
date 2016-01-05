package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.annotation.Skip;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkipHandler extends Handler {
    @Override
    public Object handle(Object request) throws IllegalAccessException, UnsupportedEncodingException {
        if(request instanceof FieldInfo){
            final FieldInfo info = (FieldInfo)request;
            final Skip skip = info.getField().getAnnotation(Skip.class);
            if(skip!=null){
                boolean isSkip = isExist(info,skip.skipIfExist());
                if(isSkip){
                    info.setSkip(true);
                }else{
                    info.setSkip(isMatch(info,skip.skipIfMatch()));
                }
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in SkipHandler should be type of FieldInfo");
        }
    }

    private boolean isMatch(final FieldInfo info,final String s) {
        if(StringUtils.isNotEmpty(s)){
            final Object obj = info.getValue();
            if(obj!=null){
                final String value = String.valueOf(obj);
                final Pattern reg = Pattern.compile(s);
                final Matcher m = reg.matcher(value);
                return m.matches();
            }
        }
        return false;
    }

    private boolean isExist(final FieldInfo info,final String filterString){
        if(StringUtils.isNotEmpty(filterString)){
            final Object obj = info.getValue();
            if(obj!=null){
                final String value = String.valueOf(obj);
                if(value.indexOf(filterString)!=-1){
                    return true;
                }
            }
        }
        return false;
    }
}

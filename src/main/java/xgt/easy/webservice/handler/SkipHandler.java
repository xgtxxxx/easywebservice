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
            Skip skip = info.getField().getDeclaringClass().getAnnotation(Skip.class);
            if(skip==null){
                skip = info.getField().getAnnotation(Skip.class);
            }
            if(skip!=null){
                skipIfEmpty(info, skip.skipIfEmpty());
                skipIfExist(info,skip.skipIfExist());
                skipIfMatch(info, skip.skipIfMatch());
            }
            return fireNext(info);
        }else{
            throw new IllegalAccessError("Parameter of method handle in SkipHandler should be type of FieldInfo");
        }
    }

    private void skipIfEmpty(final FieldInfo info, final boolean isSkip) {
        if(isSkip){
            if(StringUtils.isEmpty(StringUtils.toString(info.getValue()))){
                info.setSkip(true);
            }
        }
    }

    private void skipIfMatch(final FieldInfo info,final String s) {
        if(!info.isSkip()) {
            if (StringUtils.isNotEmpty(s)) {
                final Object obj = info.getValue();
                if (obj != null) {
                    final String value = StringUtils.toString(obj);
                    final Pattern reg = Pattern.compile(s);
                    final Matcher m = reg.matcher(value);
                    info.setSkip(m.matches());
                }
            }
        }
    }

    private void skipIfExist(final FieldInfo info,final String filterString){
        if(!info.isSkip()){
            if(StringUtils.isNotEmpty(filterString)){
                final Object obj = info.getValue();
                if(obj!=null){
                    final String value = StringUtils.toString(obj);
                    if(value.indexOf(filterString)!=-1){
                        info.setSkip(true);
                    }
                }
            }
        }
    }
}

package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.annotation.Rename;
import xgt.easy.webservice.annotation.Skip;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenameHandler extends Handler {
    @Override
    public Object handle(Object request) throws IllegalAccessException, UnsupportedEncodingException {
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

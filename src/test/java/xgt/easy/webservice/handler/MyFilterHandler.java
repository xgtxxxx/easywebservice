package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.UnsupportedEncodingException;

public class MyFilterHandler extends FilterHandler {
    @Override
    public void beforeEncode(FieldInfo info) {
        String value = StringUtils.toString(info.getValue());
        if(StringUtils.isNotEmpty(value)){
            if(value.indexOf("&")!=-1){
                info.setValue(value.replaceAll("&","%2t"));
            }
        }
    }

    @Override
    public void afterEncode(FieldInfo info) {
        String value = StringUtils.toString(info.getValue());
        if(StringUtils.isNotEmpty(value)){
            if(value.indexOf("/")!=-1){
                info.setValue(value.replaceAll("/","%2f"));
            }
        }
    }
}

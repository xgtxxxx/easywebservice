package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.handler.annotationhandler.FilterHandler;
import com.easyframework.webservice.restfulclient.model.FieldInfo;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

public class ActionFilter extends FilterHandler {
    @Override
    public void beforeEncode(FieldInfo info) {
    }

    @Override
    public void afterEncode(FieldInfo info) {
        String s = StringUtils.toString(info.getValue()).replaceAll("-","_");
        info.setValue(s);
    }
}

package xgt.easy.webservice.handler;

import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.utils.StringUtils;

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

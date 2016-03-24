package com.easyframework.webservice.restfulclient.model;

import com.easyframework.webservice.restfulclient.utils.JsonUtils;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

/**
 * Created by xgt on 2016/1/5.
 */
public class ParameterPair {
    private String key;
    private Object value;

    public ParameterPair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String getStringValue(){
        return StringUtils.toString(value);
    }

    public String getJsonValue(){
        return JsonUtils.toJson(value);
    }
}

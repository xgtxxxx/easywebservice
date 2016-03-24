package com.easyframework.webservice.restfulclient.adapters;

import com.easyframework.webservice.restfulclient.ResponseAdapter;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

public class SimpleStringAdapter implements ResponseAdapter<String> {
    public String convertTo(final ResponseInfo f) {
        return StringUtils.toString(f.getBody());
    }
}

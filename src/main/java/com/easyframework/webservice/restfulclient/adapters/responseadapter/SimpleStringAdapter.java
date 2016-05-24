package com.easyframework.webservice.restfulclient.adapters.responseadapter;

import com.easyframework.webservice.restfulclient.adapters.responseadapter.ResponseAdapter;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

public class SimpleStringAdapter implements ResponseAdapter<String> {

    public String convertTo(final ResponseInfo f) {
        return StringUtils.toString(f.getBody());
    }

}

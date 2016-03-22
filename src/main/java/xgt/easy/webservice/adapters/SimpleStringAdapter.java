package xgt.easy.webservice.adapters;

import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.ResponseAdapter;
import xgt.easy.webservice.model.ResponseInfo;
import xgt.easy.webservice.utils.StringUtils;

public class SimpleStringAdapter implements ResponseAdapter<String> {
    public String convertTo(final ResponseInfo f) {
        return StringUtils.toString(f.getBody());
    }
}

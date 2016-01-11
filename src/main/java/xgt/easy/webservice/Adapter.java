package xgt.easy.webservice;

import xgt.easy.webservice.model.ResponseInfo;

public interface Adapter<T> {
    public T convertTo(final ResponseInfo f);
}

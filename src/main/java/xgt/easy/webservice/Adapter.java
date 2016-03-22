package xgt.easy.webservice;

import xgt.easy.webservice.model.ResponseInfo;

public interface Adapter<F,T> {
    public T convertTo(final F from);
}

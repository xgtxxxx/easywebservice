package com.easyframework.webservice.restfulclient;

public interface Adapter<F,T> {
    public T convertTo(final F from);
}

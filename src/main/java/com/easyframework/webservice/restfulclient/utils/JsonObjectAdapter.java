package com.easyframework.webservice.restfulclient.utils;

import java.io.IOException;

public interface JsonObjectAdapter<F,T> {
    public T convertTo(final F from,Class<T> clazz) throws IOException;
}

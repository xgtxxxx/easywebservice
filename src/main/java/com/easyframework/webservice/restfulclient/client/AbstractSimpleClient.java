package com.easyframework.webservice.restfulclient.client;

import com.easyframework.webservice.restfulclient.Factory;

public abstract class AbstractSimpleClient<T> extends SimpleClient {
    protected Factory<T> clientFactory;

    public Factory<T> getClientFactory() {
        return clientFactory;
    }

    public void setClientFactory(Factory<T> clientFactory) {
        this.clientFactory = clientFactory;
    }
}

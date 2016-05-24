package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.Adapter;
import com.easyframework.webservice.restfulclient.model.ParameterPair;

import java.util.List;

public abstract class PostBodyHandler<T> implements Adapter<List<ParameterPair>,T> {
}

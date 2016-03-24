package com.easyframework.webservice.restfulclient.httpclient.handler;

import org.apache.http.HttpEntity;
import com.easyframework.webservice.restfulclient.Adapter;
import com.easyframework.webservice.restfulclient.model.ParameterPair;

import java.util.List;

public abstract class RequestInfoHandler implements Adapter<List<ParameterPair>,HttpEntity> {

}

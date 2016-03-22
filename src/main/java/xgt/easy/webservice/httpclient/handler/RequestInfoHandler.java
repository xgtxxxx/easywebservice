package xgt.easy.webservice.httpclient.handler;

import org.apache.http.HttpEntity;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.model.ParameterPair;

import java.util.List;

public abstract class RequestInfoHandler implements Adapter<List<ParameterPair>,HttpEntity> {

}

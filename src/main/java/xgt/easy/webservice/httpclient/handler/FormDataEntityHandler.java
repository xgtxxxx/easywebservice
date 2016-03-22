package xgt.easy.webservice.httpclient.handler;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import xgt.easy.webservice.httpclient.EntityAdapter.EntityAdapter;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.PostContentType;
import xgt.easy.webservice.model.RequestInfo;

import java.util.List;

public class FormDataEntityHandler extends RequestInfoHandler {
    public HttpEntity convertTo(List<ParameterPair> from) {
        StringBuffer sb = new StringBuffer();
        for(ParameterPair parameter: from){
            if(sb.length()>0){
                sb.append('&');
            }
            sb.append(parameter.getKey()).append('=').append(parameter.getStringValue());
        }
        return new StringEntity(sb.toString(), ContentType.create(PostContentType.FORM_DATA, "utf-8"));
    }
}

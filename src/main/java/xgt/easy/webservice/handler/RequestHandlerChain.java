package xgt.easy.webservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xgt.easy.webservice.Handler;
import xgt.easy.webservice.annotation.SupperAvailable;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.model.RequestInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestHandlerChain implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger(RequestHandlerChain.class);
    private List<Handler> handlers;

    private Handler next;

    private void buildChain(){
    }

    public RequestInfo handle(final Object request) {

        return null;
    }

    private List<FieldInfo> getFields(final Object obj){
        final List<Field> fields = new ArrayList<Field>();
        Class clazz = obj.getClass();
        final Annotation available = clazz.getAnnotation(SupperAvailable.class);
        while (clazz!=null){
            fields.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
            if(available!=null){
                clazz = clazz.getSuperclass();
            }else{
                clazz = null;
            }
        }

        for (final Field field:fields){

        }

        return fields;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}

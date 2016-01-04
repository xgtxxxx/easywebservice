package xgt.easy.webservice.request;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xgt on 2016/1/3.
 */
@SupperAvailable
public class SimpleRequest extends Request {

    @Override
    public HttpMethod getHttpMethod() {
        return null;
    }
}

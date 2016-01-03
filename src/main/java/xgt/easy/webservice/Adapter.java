package xgt.easy.webservice;

/**
 * Created by xgt on 2016/1/3.
 */
public interface Adapter<F,T> {
    public T convertTo(F f);
}

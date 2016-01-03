package xgt.easy.webservice;

/**
 * Created by xgt on 2015/12/30.
 */
public interface Handler {
    public <T> T handle(final Object request);
}

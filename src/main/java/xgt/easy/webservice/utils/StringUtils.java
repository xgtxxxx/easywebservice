package xgt.easy.webservice.utils;

public final class StringUtils {
    public static final boolean isEmpty(final String string){
        return string==null||"".equals(string);
    }
    public static final boolean isNotEmpty(final String string){
        return string!=null&&!"".equals(string);
    }
}

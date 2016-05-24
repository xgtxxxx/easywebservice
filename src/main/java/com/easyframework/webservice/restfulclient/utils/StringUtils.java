package com.easyframework.webservice.restfulclient.utils;

public final class StringUtils {
    public static final boolean isEmpty(final String string){
        return string==null||"".equals(string);
    }
    public static final boolean isNotEmpty(final String string){
        return string!=null&&!"".equals(string);
    }

    public static final boolean isNotEmpty(final Object obj){
        String s = toString(obj);
        return isNotEmpty(s);
    }

    public static final String toString(Object obj){
        if(obj==null){
            return null;
        }else{
            if(obj instanceof String){
                return (String)obj;
            }
            return String.valueOf(obj);
        }

    }
}

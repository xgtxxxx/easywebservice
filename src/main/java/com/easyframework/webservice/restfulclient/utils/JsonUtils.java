package com.easyframework.webservice.restfulclient.utils;

import com.google.gson.Gson;

public final class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJson(final Object obj){
        return gson.toJson(obj);
    }
}

package com.easyframework.webservice.restfulclient.model;

public enum PathType {
    PATH("/"),HOST("");
    private String append;

    private PathType(String s){
        this.append = s;
    }

    public String toString(){
        return append;
    }
}

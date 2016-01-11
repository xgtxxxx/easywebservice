package xgt.easy.webservice.model;

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

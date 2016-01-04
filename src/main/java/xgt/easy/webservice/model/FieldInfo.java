package xgt.easy.webservice.model;

import java.lang.reflect.Field;

public class FieldInfo {
    private Field field;
    private int order = 10000;
    private String key;
    private String value;

    public static FieldInfo newInstance(final Field f){
        final FieldInfo info = new FieldInfo();
        info.setField(f);
        info.setKey(f.getName());
        info.setValue(f.get);
        return info;
    }

    public Field getField() {
        return field;
    }

    public void setField(final Field field) {
        this.field = field;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}

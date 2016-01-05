package xgt.easy.webservice.model;

import java.lang.reflect.Field;

public class FieldInfo {
    private Field field;
    private int order = 10000;
    private String key;
    private Object value;
    private boolean skip;

    private FieldType fieldType;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}

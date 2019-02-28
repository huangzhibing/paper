package com.hqu.modules.craw.core.entity;

public class Identity {
    private String value;
    private String equalsTo;
    private Integer metaType;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEqualsTo() {
        return equalsTo;
    }

    public void setEqualsTo(String equalsTo) {
        this.equalsTo = equalsTo;
    }

    public Integer getMetaType() {
        return metaType;
    }

    public void setMetaType(Integer metaType) {
        this.metaType = metaType;
    }
}

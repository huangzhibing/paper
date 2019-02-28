package com.hqu.modules.craw.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TargetData {
    private String name;
    private String value;
    private String displayName;
    private Integer metaType;
    private Identity identity;
    private String ajaxPath; //Ajax请求地址
    private String jsonObjectField;

    public String getAjaxPath() {
        return ajaxPath;
    }

    public void setAjaxPath(String ajaxPath) {
        this.ajaxPath = ajaxPath;
    }

    public String getJsonObjectField() {
        return jsonObjectField;
    }

    public void setJsonObjectField(String jsonObjectField) {
        this.jsonObjectField = jsonObjectField;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getMetaType() {
        return metaType;
    }

    public void setMetaType(Integer metaType) {
        this.metaType = metaType;
    }
}

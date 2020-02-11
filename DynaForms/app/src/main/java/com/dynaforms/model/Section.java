
package com.dynaforms.model;

import java.util.List;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section implements Parent<ChildList> {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private List<Object> value = null;
    @SerializedName("childList")
    @Expose
    private List<ChildList> childList = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }

    public List<ChildList> getChildList() {
        return childList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setChildList(List<ChildList> childList) {
        this.childList = childList;
    }

}

package com.roof.advisory.area.entity;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

public class AreaTreeSelect {
    private String key;
    private String value;
    private String label;
    private List<AreaTreeSelect> children;

    public AreaTreeSelect() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<AreaTreeSelect> getChildren() {
        return this.children;
    }

    public void setChildren(List<AreaTreeSelect> children) {
        this.children = children;
    }

    public void addChildren(AreaTreeSelect area) {
        if(children == null){
            this.children = Lists.newArrayList();
            this.children.add(area);
        }else {
            this.children.add(area);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaTreeSelect that = (AreaTreeSelect) o;
        return Objects.equals(value, that.value);

    }


}

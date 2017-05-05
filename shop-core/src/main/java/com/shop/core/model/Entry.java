package com.shop.core.model;

import java.io.Serializable;

/**
 * Created by TW on 2017/5/1.
 */
public class Entry implements Serializable {
    private String name;
    private String value;


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
}

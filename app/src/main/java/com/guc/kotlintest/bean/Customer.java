package com.guc.kotlintest.bean;

/**
 * Created by guc on 2019/11/6.
 * 描述：
 */
public class Customer {
    private String name;

    public Customer(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

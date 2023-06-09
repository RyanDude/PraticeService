package com.example.saga.Factory.domain;

public class Mac implements Laptop{
    @Override
    public String create() {
        return "create apple mac";
    }
}

package com.example.saga.Factory.domain;

public class IPhone implements Phone{
    @Override
    public String create() {
        return "create iphone";
    }
}

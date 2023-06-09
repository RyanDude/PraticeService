package com.example.saga.Factory.domain;

public class GooglePhone implements Phone{
    @Override
    public String create() {
        return "create google phone";
    }
}

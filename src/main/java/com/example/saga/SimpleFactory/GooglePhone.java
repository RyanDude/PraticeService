package com.example.saga.SimpleFactory;

public class GooglePhone implements Phone{
    public GooglePhone(){
//        this.make();
    }

    @Override
    public String make(){
        return "make a Google Phone";
    }
}

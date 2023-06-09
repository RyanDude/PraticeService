package com.example.saga.SimpleFactory;

public class ApplePhone implements Phone{
    public ApplePhone(){
//        this.make();
    }
    @Override
    public String make(){
        return "make an iphone";
    }
}

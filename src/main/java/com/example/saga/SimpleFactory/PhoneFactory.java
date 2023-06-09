package com.example.saga.SimpleFactory;

import org.springframework.stereotype.Component;
@Component
public class PhoneFactory {
    public Phone makePhone(String type){
        if(type.equalsIgnoreCase("apple")){
            return new ApplePhone();
        }
        else if (type.equalsIgnoreCase("google")){
            return new GooglePhone();
        }
        return null;
    }
}

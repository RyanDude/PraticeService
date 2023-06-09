package com.example.saga.Factory.Impl;

import com.example.saga.Factory.Product;
import com.example.saga.Factory.ProductFactory;
import com.example.saga.Factory.domain.IPhone;
import com.example.saga.Factory.domain.Laptop;
import com.example.saga.Factory.domain.Mac;
import com.example.saga.SimpleFactory.ApplePhone;
import com.example.saga.SimpleFactory.Phone;
import org.springframework.stereotype.Component;

@Component
public class AppleFactory implements ProductFactory {
    @Override
    public Phone makePhone() {
        return new ApplePhone();
    }

    @Override
    public Laptop makeLaptop() {
        return new Mac();
    }
}

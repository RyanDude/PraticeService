package com.example.saga.Factory.Impl;

import com.example.saga.Factory.Product;
import com.example.saga.Factory.ProductFactory;
import com.example.saga.Factory.domain.GoogleLaptop;
import com.example.saga.Factory.domain.Laptop;
import com.example.saga.SimpleFactory.GooglePhone;
import com.example.saga.SimpleFactory.Phone;
import org.springframework.stereotype.Component;

@Component
public class GoogleFactory implements ProductFactory {
    @Override
    public Phone makePhone() {
        return new GooglePhone();
    }

    @Override
    public Laptop makeLaptop() {
        return new GoogleLaptop();
    }
}

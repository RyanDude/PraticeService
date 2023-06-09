package com.example.saga.Factory;

import com.example.saga.Factory.domain.Laptop;
import com.example.saga.SimpleFactory.Phone;

public interface ProductFactory {
    public Phone makePhone();
    public Laptop makeLaptop();
}

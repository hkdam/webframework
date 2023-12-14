package com.example.webframework.testandstudy.proxy.Impl;

import com.example.webframework.testandstudy.proxy.Person;

public class Renter implements Person {

    @Override
    public void rentHouse(){
        System.out.println("rent done");
    }
}

package com.example.webframework.testandstudy.proxy.Impl;

import com.example.webframework.testandstudy.proxy.Person;

public class RenterProxy implements Person {

    private Person person;

    public RenterProxy(Person person){
        this.person = person;
    }

    @Override
    public void rentHouse() {
        System.out.println("media start");
        person.rentHouse();
        System.out.println("media end");
    }
}

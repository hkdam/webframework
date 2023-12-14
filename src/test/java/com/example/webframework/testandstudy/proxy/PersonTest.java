package com.example.webframework.testandstudy.proxy;

import com.example.webframework.testandstudy.proxy.Impl.Renter;
import com.example.webframework.testandstudy.proxy.Impl.RenterProxy;
import com.example.webframework.testandstudy.proxy.Impl.RenterProxyJdk;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class PersonTest extends TestCase {

    @Test
    public void test1(){

//        Person renter = new Renter();
//        RenterProxy renterProxy = new RenterProxy(renter);
//        renterProxy.rentHouse();

//        Person renter = new RenterProxy(new Renter());
//        renter.rentHouse();

        Person renterProxyJDK = new RenterProxyJdk(new Renter()).getProxy();
        renterProxyJDK.rentHouse();
    }

}
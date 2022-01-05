package com.dy.thrift.service.impl;

import com.dy.thrift.service.HelloWorldService;
import org.apache.thrift.TException;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "Hello World: " + username;
    }
}

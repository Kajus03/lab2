package com.example.lab2.bean;

import com.example.lab2.cdi.Hello;
import com.example.lab2.cdi.Logged;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@Logged
public class CdiDemoBean {
    @Inject
    private Hello hello;
    private String output;
    public void callHello() {
        output = hello.hello();
    }
    public String getOutput() {
        return output;
    }
}

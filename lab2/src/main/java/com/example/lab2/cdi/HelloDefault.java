package com.example.lab2.cdi;

import javax.enterprise.context.Dependent;

@Dependent
public class HelloDefault implements Hello {
    public String hello() {
        return "Hello Default!";
    }
}

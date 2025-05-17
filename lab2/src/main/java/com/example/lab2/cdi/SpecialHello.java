package com.example.lab2.cdi;

import javax.enterprise.inject.Specializes;
import javax.enterprise.context.Dependent;

@Specializes
@Dependent
public class SpecialHello extends HelloDefault {
    public String hello() {
        return "Hello Specialized!";
    }
}

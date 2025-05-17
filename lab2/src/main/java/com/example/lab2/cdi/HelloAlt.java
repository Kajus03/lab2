package com.example.lab2.cdi;

import javax.enterprise.inject.Alternative;
import javax.enterprise.context.Dependent;

@Alternative
@Dependent
public class HelloAlt implements Hello {
    public String hello() {
        return "Hello Alternative!";
    }
}

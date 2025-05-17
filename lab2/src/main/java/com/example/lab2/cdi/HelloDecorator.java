package com.example.lab2.cdi;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;

@Decorator
@Dependent
public class HelloDecorator implements Hello {
    @Inject
    @Delegate
    private Hello delegate;

    public String hello() {
        return delegate.hello() + " (decorated)";
    }
}

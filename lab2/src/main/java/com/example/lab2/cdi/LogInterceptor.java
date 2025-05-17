package com.example.lab2.cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
public class LogInterceptor {
    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        System.out.println("Entering: " + ctx.getMethod());
        Object result = ctx.proceed();
        System.out.println("Exiting: " + ctx.getMethod());
        return result;
    }
}

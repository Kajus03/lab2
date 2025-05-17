package com.example.lab2.async;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

@Stateless
public class CalcBean {
    private ConcurrentHashMap<String, Integer> store = new ConcurrentHashMap<>();

    @Asynchronous
    public Future<Integer> longCalc(String key, int input) {
        store.put(key, input);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int result = input * 3;
        store.put(key, result);
        return CompletableFuture.completedFuture(result);
    }

    public int check(String key) {
        return store.getOrDefault(key, 0);
    }
}
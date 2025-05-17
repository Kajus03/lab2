package com.example.lab2.bean;

import com.example.lab2.async.CalcBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AsyncBean implements Serializable {
    @EJB
    private CalcBean calc;

    private int number;
    private String key;
    private int result;

    public void start() {
        key = "k" + System.currentTimeMillis();
        calc.longCalc(key, number);
    }

    public void check() {
        result = calc.check(key);
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }
}

package com.thinkinginjava.type_information.example.chapter14_7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: XueYing.Cao
 * @date: 2019/1/29
 * @description:
 */
class MethodSeletor implements InvocationHandler {
    private Object proxied;
    public MethodSeletor(Object proxied) { this.proxied = proxied; }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("interesting"))
            System.out.println("**** Proxy detected the interesting method");
        return method.invoke(proxied, args);
    }
}

interface SomeMethods {
    void boring1();
    void boring2();
    void boring3();
    void interesting(String arg);
}

class Implementation implements SomeMethods {
    @Override
    public void boring1() { System.out.println("boring1"); }
    @Override
    public void boring2() { System.out.println("boring2"); }
    @Override
    public void boring3() { System.out.println("boring3"); }
    @Override
    public void interesting(String arg) { System.out.println("interesting " + arg); }
}

public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods)Proxy.newProxyInstance(SomeMethods.class.getClassLoader(),
                new Class[]{SomeMethods.class},
                new MethodSeletor(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
}
/**output:
 * boring1
 * boring2
 * **** Proxy detected the interesting method
 * interesting bonobo
 * boring3
 */
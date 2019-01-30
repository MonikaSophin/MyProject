package com.thinkinginjava.type_information.example.chapter14_9;

/**
 * @author: XueYing.Cao
 * @date: 2019/1/30
 * @description:
 *  Method #setAccessible(true);使用反射可以到达并调用所有方法。（包括private修饰的方法）
 * 私有静态内部类，也是如此。
 */
class InnerA {
    private static class C implements A {
        @Override
        public void f() { System.out.println("public C.f()"); }
        public void g() { System.out.println("public C.g()"); }
        void u(){ System.out.println("package C.u()"); }
        protected void v() { System.out.println("protected C.v()"); }
        private void w() { System.out.println("private C.w()"); }
    }
    public static A makeA() { return new C(); }
}

public class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
/**output:
 * public C.f()
 * com.thinkinginjava.type_information.example.chapter14_9.InnerA$C
 * public C.g()
 * package C.u()
 * protected C.v()
 * private C.w()
 */
package com.company;

public class A {

    private int a;

    class B {//внутренние классы по сути, еще один член окружающего класса
        void b(){
            System.out.println(a);//доступны даже приватные члены окружающего класса
        }
    }

    static class C {
        void c(){
            System.out.println(new A().a);//члены окружающего класса доступны только через экземпляр класса
        }
    }

    public void test() {
        new B();
        new C();
    }
}

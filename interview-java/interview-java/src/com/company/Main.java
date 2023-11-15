package com.company;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //test_console_input();
        //test_executors();
        //test_overriding();
        //test_nested_and_inner_class();
        //race_condition();
        //test_iterator();
        //System.out.println(ForkJoinPool.getCommonPoolParallelism());

        var a = new ArrayList<Integer>();
        ((ArrayList)a).add("a");
        ((ArrayList)a).add("b");
        System.out.println(a);


    }

    private static void test_console_input() throws IOException {
        System.out.println("Enter line:");
        String input = new Scanner(System.in).next();
        System.out.printf("You entered: %s%n", input);
    }

    private static void test_iterator() {
        var iterator=List.of(1,2,3).iterator();
        System.out.println("one");
        iterator.forEachRemaining(integer -> System.out.println(integer));
        System.out.println("two");
        iterator.forEachRemaining(integer -> System.out.println(integer));//ничего не выведет, так как итератор пройден
    }

    static int count = 0;//volatile не поможет

    //static synchronized void increment() {
    static void increment() {
        count++;
    }

    private static void race_condition() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 100000).forEach(i -> executorService.submit(Main::increment));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(count);
        executorService.shutdown();
    }

    private static void test_nested_and_inner_class() {
        var a = new A();
        var b = a.new B();//create instance of inner class
        var c = new A.C();//create instance of static nested class
    }

    static void test_overriding() {
        class Animal {
            public void voice() {
                System.out.println("Animal");
            }
        }

        class Cat extends Animal {
            public void voice() {
                System.out.println("Cat");
            }
        }

        class Dog extends Animal {
        }

        Cat cat = new Cat();
        Dog dog = new Dog();
        cat.voice();
        ((Animal) cat).voice();
        dog.voice();
    }

    static void test_executors() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
            TimeUnit.SECONDS.sleep(1);
            return 666;
        });

//        if (result.isDone()) {
//            System.out.println(result.get());
//        }

        System.out.println(result.get());
        executorService.shutdown();
        System.out.printf("Finish");
    }
}

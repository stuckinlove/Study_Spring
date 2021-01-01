package com.study.springasync.dispatach;

import java.util.Arrays;
import java.util.List;

public class About_Dispatch {

    static class Service{
        void run(){
            System.out.println("run");
        }
        void run(String str){
            System.out.println("run :" + str);
        }
    }

    static abstract class MyService{
        abstract void run();
    }

    static class MyServiceImpl1 extends MyService{
        @Override
        void run() {
            System.out.println("run1");
        }
    }
    static class MyServiceImpl2 extends MyService{
        @Override
        void run() {
            System.out.println("run2");
        }
    }

    public static void main(String[] args) {
        // dispatch: 어떤 메소드를 호출할지 결정하는 과정

        // static dispatch : (프로그래밍 실행 시점이 되지 않아도)컴파일 시점에 어느 메소드가 불릴지 알고 있는 것. 정적인 디스패치
        new Service().run();
        new Service().run("str");

        // dynamic dispatch : 어떤 메소드를 호출할지는 런타임시에 결정됨.
        MyService myServiceImpl1 = new MyServiceImpl1();
        myServiceImpl1.run(); // 컴파일 시점에 결정되어 있지 않음. 컴파일 시점에는 추상클래스의 메소드로 정의됨. -> 다이나믹 디스패치.

        List<MyService> myServices = Arrays.asList(new MyServiceImpl1(), new MyServiceImpl2());
        myServices.forEach(s -> s.run()); //myServices.forEach(MyService::run);
    }


}

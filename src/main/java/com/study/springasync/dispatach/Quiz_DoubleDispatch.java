package com.study.springasync.dispatach;

import java.util.Arrays;
import java.util.List;

public class Quiz_DoubleDispatch {

    // double dispatch
    interface Post { void postOn(SNS sns); }
    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            System.out.println("text -> " + sns.getClass().getSimpleName());
        }
    }
    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            System.out.println("picture -> " + sns.getClass().getSimpleName());
        }
    }

    interface SNS {}
    static class Facebook implements SNS{
    }
    static class Twitter implements SNS{
    }

    public static void main(String[] args) {
        List<Post> postList = Arrays.asList(new Text(), new Picture());
        List<SNS> snsList = Arrays.asList(new Facebook(), new Twitter());

        postList.forEach(post -> snsList.forEach(sns -> post.postOn(sns))); // dispatch 발생이 컴파일 시점이 아닌 런타임 시점

        // 새로운 서비스(Instagram) 추가된 경우? 어떻게 추가할 것인가.
        // 다이나믹 디스패치를 두 번 발생시키는 방법으로 의존성을 파괴하지 않도록 할 수 있다.

        // 여기까지는 문제 없지만, 서비스가 늘었을 경우, 서비스마다 처리되어야하는 방식이 다른 경우. 클래스 if문으로 instance of로 타입을 구분하게 되는데/
        // 안티패턴이 된다.

    }
}

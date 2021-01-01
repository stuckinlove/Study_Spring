package com.study.springasync.dispatach;

import java.util.Arrays;
import java.util.List;

public class Answer_DoubleDispatch {
    interface Post { void postOn(SNS sns); }
    static class Text implements Post {
        public void postOn(SNS sns) {
            sns.post(this); // 두번째 다이나믹 디스패치 발생.
        }
    }
    static class Picture implements Post {
        public void postOn(SNS sns) {
            sns.post(this);
        }
    }

    interface SNS {
        void post(Text text);
        void post(Picture picture);
    }
    static class Facebook implements SNS {
        public void post(Text text) { System.out.println("text - facebook"); }
        public void post(Picture picture) { System.out.println("picture - facebook"); }
    }
    static class Twitter implements SNS {
        public void post(Text text) { System.out.println("text - Twitter"); }
        public void post(Picture picture) { System.out.println("picture - Twitter"); }
    }
    static class Instagram implements SNS {
        public void post(Text text) { System.out.println("text - Instagram"); }
        public void post(Picture picture) { System.out.println("picture - Instagram"); }
    }

    public static void main(String[] args) {
        List<Post> postList = Arrays.asList(new Text(), new Picture());
        List<SNS> snsList = Arrays.asList(new Facebook(), new Twitter(), new Instagram());

        postList.forEach(post -> snsList.forEach(sns -> post.postOn(sns))); // 첫번째 다이나믹 디스패치 발생
    }
}

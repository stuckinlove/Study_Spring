package com.study.springasync.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;


@SpringBootApplication
@Slf4j
@EnableAsync
public class Web_SpringAsync_Callable {

    @RestController
    static class MyController{

        /**
         * Callable로 서블릿 스레드의 비동기 처리.
         *
         * 설명: NIO Connector에서 요청을 받아 서블릿 스레드가 생성되면
         * 내부 처리가 끝나기를 대기하지 않고 바로 서블릿 스레드 풀에 반환한다.
         * 이후, 작업 스레드가 작업을 완료하면 서블릿 스레드 풀에서 스레드를 새로이 할당받아 NIO Connector에 돌려준다.
         * 정리하자면, 적은 서블릿 스레드 양 (예로 톰캣 서블릿)으로 많은 처리를 소화할 수 있다.
         * 단점으로는 그만큼 작업 스레드를 부담시키는 결과가 된다.
         */
        @GetMapping("/async")
        public Callable<String> async() throws InterruptedException {
            log.info("controller method");
            Thread.sleep(2000);

            return (() -> {
                log.info("async");
                Thread.sleep(1000);
                return "hello";
            });
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Web_SpringAsync_Callable.class, args);
    }
}

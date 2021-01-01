package com.study.springasync.async;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 옛날 자바 비동기 방법
 */
@Slf4j
public class JavaAsyncFuture {
    public static void main(String[] args) throws Exception {
        // create thread pool
        ExecutorService es = Executors.newCachedThreadPool();

        // Future를 이용한 예제 (blocking방식)
        // Future을 사용해서 다른 스레드에서 실행된 값을 메인 스레드로 가져옴
        Future<String> f = es.submit(() -> { // 새로운 스레드 발행해서 실행
            Thread.sleep(2000);
            log.info("Async"); // 새로운 스레드에서 실행됨 // 2
            return "Complete";
        });

        log.info(String.valueOf(f.isDone())); // 1
        Thread.sleep(2100);
        log.info("Exit"); // 3
        log.info(String.valueOf(f.isDone())); // 4
        log.info(f.get()); // 5
        // future get은 블록킹 방식. 블록킹 방식이란, 특정 작업의 결과를 가져올 때까지 메소드의 호출 리턴이 대기상태에 있으면 블록킹.
        // 결과값을 기다리지 않으면 Non-Blocking, 결과값을 기다리면 BLocking


        // 2. Futuretask을 이용한 방법
        FutureTask<String> ft = new FutureTask<String>(() -> {
            Thread.sleep(2000);
            log.info("futuretask: " + "Async"); // 새로운 스레드에서 실행됨 // 2
            return "futuretask: Complete";
        }) {
            @Override
            protected void done() {
                try { System.out.println(get()); } catch (Exception e) {}
            }
        };
        es.execute(ft);

        log.info(String.valueOf("futuretask: " + f.isDone()));
        Thread.sleep(2100);
        log.info("futuretask: " + "Exit");
        log.info(String.valueOf("futuretask: " + f.isDone()));
        log.info("futuretask: " + f.get());


        // 3. FutureTask의 상속방법 + 에러 캐치 후 원래 스레드로 가져오기 (2.의 리팩토링)
        CallbackFutureTask cbf = new CallbackFutureTask(() ->
                { Thread.sleep(2000);
//                if (1==1) throw new RuntimeException("error!!!!"); // 에러가 발생해도 리턴은 됨.
                    log.info("CallbackFutureTask: " + "Async");
                    return "CallbackFutureTask: Complete"; }
                , s -> System.out.println("result is " + s)
                , e -> System.out.println("Error is " + e.getMessage())
                );

        // execute
        es.execute(cbf);

        log.info(String.valueOf("CallbackFutureTask: " + f.isDone()));
        Thread.sleep(2100);
        log.info("CallbackFutureTask: " + "Exit");
        log.info(String.valueOf("CallbackFutureTask: " + f.isDone()));
        log.info("CallbackFutureTask: " + f.get());

        // shutdown
        es.shutdown();

        // callback을 이용한 방식 (Non-Blocking방식)

    }

    interface SuccessCallback{
        void onSuccess(String result);
    }
    interface ErrorCallback{
        void onError(Throwable throwable);
    }

    static class CallbackFutureTask extends FutureTask<String>{
        SuccessCallback sc;
        ErrorCallback ec;
        public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ErrorCallback ec) {
            super(callable);
            this.sc = Objects.requireNonNull(sc);
            this.ec = Objects.requireNonNull(ec);
        }

        @Override
        protected void done() {
            try {
                sc.onSuccess(get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                ec.onError(e.getCause());
            }
        }
    }
}

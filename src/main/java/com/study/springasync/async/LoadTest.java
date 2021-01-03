package com.study.springasync.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LoadTest {

    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ec = Executors.newCachedThreadPool();

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/async";

        StopWatch main = new StopWatch();
        main.start();

        for (int i = 0; i < 100; i++) {
            ec.execute(() -> {

                int idx = LoadTest.counter.addAndGet(1);
                log.info("Thread {}", idx);

                StopWatch st = new StopWatch();

                st.start();
                rt.getForEntity(url, String.class);
                st.stop();
                log.info("Elapsed: {} , {}", idx, st.getTotalTimeSeconds());

            });
        }

        ec.shutdown();
        ec.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();
        log.info("Total: {} ", main.getTotalTimeSeconds());

    }
}

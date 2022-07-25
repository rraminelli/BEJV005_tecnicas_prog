package br.com.rraminelli.threads;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadsScheduled {

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Processamento thread 1");
            }
        }, 2, TimeUnit.SECONDS);

        executor.schedule(() -> System.out.println("Processamento thread 2"), 1, TimeUnit.SECONDS);

        executor.schedule(() -> System.out.println("Processamento thread 3"), 1, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> System.out.println("Processamento thread 4 " + LocalDateTime.now()), 3, 1, TimeUnit.SECONDS);

        //executor.shutdown();

    }

}

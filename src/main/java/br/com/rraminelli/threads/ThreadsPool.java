package br.com.rraminelli.threads;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadsPool {

    public static void main(String[] args) {

        //ExecutorService executor = Executors.newFixedThreadPool(10);
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Processamento thread 1");
        });

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Processamento thread 2");
        });

        executor.execute(() -> {
            for (int i = 0; i<100; i++) {
                System.out.println("thread 3 " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Processamento thread 3");
        });

        executor.shutdown();

        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                List<Runnable> notExecutedTasks = executor.shutdownNow();
                for (Runnable r : notExecutedTasks) {
                    System.out.println(r);
                }
            }
        } catch (InterruptedException e) {
            List<Runnable> notExecutedTasks = executor.shutdownNow();
            for (Runnable r : notExecutedTasks) {
                System.out.println(r);
            }
        }



        System.out.println(executor.isShutdown());

        System.out.println("Fim processo main");
    }

}

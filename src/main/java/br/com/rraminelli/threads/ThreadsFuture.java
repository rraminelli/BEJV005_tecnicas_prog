package br.com.rraminelli.threads;

import java.util.concurrent.*;

public class ThreadsFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);
            return "Resultado processo 1";
        });

        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(1000);
            throw new RuntimeException("erro");
            //return "Resultado processo 2";
        });

        executor.shutdown();

        /*
            ... outras regras
         */

        try {
            System.out.println(future.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("time out");
            throw new RuntimeException(e);
        }

        System.out.println(future2.get());

        System.out.println("fim main");

    }

    class RetornoProcessamento implements Callable<String> {

        Future<String> wait;
        RetornoProcessamento(Future<String> wait) {
            this.wait = wait;
        }

        @Override
        public String call() throws Exception {
            return "RetornoProcessamento";
        }
    }
}

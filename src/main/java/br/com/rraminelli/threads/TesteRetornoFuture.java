package br.com.rraminelli.threads;

import java.util.concurrent.*;

public class TesteRetornoFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new TesteRetornoFuture().exec();
    }

    void exec() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);
            return "Resultado processo 1";
        });

        Future<String> futureRetornoProcessamento = executor.submit(new RetornoProcessamento(future));

        executor.shutdown();

        System.out.println(futureRetornoProcessamento.get());

    }

    class RetornoProcessamento implements Callable<String> {
        Future<String> wait;
        RetornoProcessamento(Future<String> wait) {
            this.wait = wait;
        }
        @Override
        public String call() throws Exception {
            return "RetornoProcessamento " + wait.get();
        }
    }

}

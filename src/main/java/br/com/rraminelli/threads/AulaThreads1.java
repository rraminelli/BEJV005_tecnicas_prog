package br.com.rraminelli.threads;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AulaThreads1 {

    public static void main(String[] args) {
        new AulaThreads1().execute();
        System.out.println("Fim!!!");
    }

    void execute() {

        Incremento incremento = new Incremento();

        /*Thread somaThread = new IncrementoThread(incremento);
        somaThread.start();*/

        /*Thread somaRunnable = new Thread(new IncrementoRunnable(incremento));
        somaRunnable.start();*/

        for (int i = 0; i < 10; i++) {
            Thread somaRunnable = new Thread(new IncrementoRunnable(incremento));
            somaRunnable.setName("Thread " + i);
            somaRunnable.start();
        }



        //thread lambda


        System.out.println("Fim programa!");
    }

    class IncrementoThread extends Thread {
        Incremento incremento;
        public IncrementoThread(Incremento incremento) {
            this.incremento = incremento;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            incremento.incrementar();
        }
    }

    class IncrementoRunnable implements Runnable {
        Incremento incremento;
        public IncrementoRunnable(Incremento incremento) {
            this.incremento = incremento;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


                incremento.incrementar();


        }
    }

    class Incremento {
        //Atomic

        private AtomicInteger valorIncrementado = new AtomicInteger();
        public void incrementar() {
                int valor = valorIncrementado.incrementAndGet();
                System.out.println(Thread.currentThread().getName() +
                        " Valor incrementado: " + valor);
        }

    }

}

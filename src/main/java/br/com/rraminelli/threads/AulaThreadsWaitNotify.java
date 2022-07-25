package br.com.rraminelli.threads;

import br.com.rraminelli.model.Carro;

public class AulaThreadsWaitNotify {

    public static void main(String[] args) {
        new AulaThreadsWaitNotify().execute();
    }

    void execute() {
        Thread thread2 = new Thread(new Processo2());
        new Thread(new Processo1(thread2)).start();
    }


    class Processo1 implements Runnable {

        Thread processo2 = null;
        Processo1(Thread processo2) {
            this.processo2 = processo2;
        }

        public void run() {
            System.out.println("inicio processo 1");
            processo2.start();
            synchronized (processo2) {
                try {
                    processo2.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("fim processo 1");
        }
    }

    class Processo2 implements Runnable {
        public void run() {
            synchronized (this) {
                System.out.println("inicio processo 2");
                for (int i = 0; i<10; i++) {
                    System.out.println(i);
                }
                System.out.println("fim processo 2");

                notify();

                //....


            }
        }


    }

}

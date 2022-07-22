package br.com.rraminelli.threads;

public class AulaThreadsWaitNotify {

    public static void main(String[] args) {
        new AulaThreadsWaitNotify().execute();
    }

    void execute() {
        new Thread(new Processo1()).start();
    }


    class Processo1 implements Runnable {
        public void run() {
            Thread thread2 = new Thread(new Processo2());
            thread2.start();

            System.out.println("fim processo 1");

        }
    }

    class Processo2 implements Runnable {
        public void run() {

                for (int i = 0; i<10; i++) {
                    System.out.println(i);
                }

                System.out.println("fim processo 2");


        }


    }

}

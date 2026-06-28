package de.spring.elolink_spring.test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStarter {


    public static void main(String[] args) {
        PrimeThread pt1 = new PrimeThread();
        SecondThread pt2 = new SecondThread();
        pt1.start();
        pt2.start();
    }

    static synchronized void printSynchronizedOutput(String s) {
        System.out.println("Synchronized Output form " + s);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    static class PrimeThread extends Thread {
        @Override
        public void run(){
            int i = 0;
            while (i < 100){
                System.out.println("Running PrimeThread " + i);

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                printSynchronizedOutput("FROM PRIME "+ i);
                i++;
            }

        }
    }

    static class SecondThread extends Thread {
        @Override
        public void run(){

            int i = 0;
            while (i < 100){
                System.out.println("Running SecondThread " + i);

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                printSynchronizedOutput("FROM SECOND "+ i);
                i++;
            }
        }
    }


}

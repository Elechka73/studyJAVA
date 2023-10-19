package org.example;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    public static Car[] winners = new Car[3];
    private static AtomicInteger position = new AtomicInteger(0);


    static {
        CARS_COUNT = 0;
    }

    private CyclicBarrier cb;
    private CountDownLatch cdl;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            cdl.countDown();
            CheckWin(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CheckWin(Car car) {
        int myPosition = position.getAndIncrement();
        if (myPosition < 3) {
            winners[myPosition] = car;
        }
    }

    public static void printWinners() {
        System.out.println("Победители:");
        for (int i = 0; i < winners.length; i++) {
            System.out.println((i + 1) + " МЕСТО: " + winners[i].getName());
        }
    }
}
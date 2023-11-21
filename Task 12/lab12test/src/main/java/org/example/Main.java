package org.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.OptimisticLockException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;


public class Main {
    public static final int THREAD_COUNT = 8;
    static SessionFactory configuration = new Configuration()
            .addAnnotatedClass(Items.class)
            .configure("hibernate.cfg.xml").buildSessionFactory();

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Session session = configuration.openSession();
        try {
            for (int i = 0; i < 40; i++) {
                session.beginTransaction();
                Items item = new Items();
                session.persist(item);
                session.getTransaction().commit();
            }
            threadTest();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            configuration.close();
            session.close();
        }
        Date date = new Date(System.currentTimeMillis() - time);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        System.out.println(dateFormatted);
    }

    public static void threadTest() {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int ii = i + 1;
            threads[i] = new Thread(() -> {
                System.out.println("Thread #" + ii + " start");
                for (int k = 0; k < 20000; k++) {
                    boolean upd = false;
                    while (!upd) {
                        Session session = configuration.getCurrentSession();
                        Long rndRow = (long) ((Math.random() * 40) + 1);
                        try {
                            session.beginTransaction();
                            Items items = session.get(Items.class, rndRow);
                            items.setVal(items.getVal() + 1);
                            sleep(5);
                            session.save(items);
                            session.getTransaction().commit();
                            upd = true;
                        } catch (OptimisticLockException | HibernateException e) {
                            session.getTransaction().rollback();
                        }
                        session.close();
                    }
                }
                countDownLatch.countDown();
                System.out.println("Thread #" + ii + " end");
            });
            threads[i].start();
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
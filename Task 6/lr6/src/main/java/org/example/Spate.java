package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
public class Spate {
    static final int size = 60000000;
    static final int half = size / 2;

    public void method1() {
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);
        long time = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(array[0]);
        System.out.println(array[array.length - 1]);
        System.out.println("Время выполнения первого метода: " + (System.currentTimeMillis() - time));
    }

public void method2() {
    float[] array = new float[size];
    float[] firstHalf = new float[half];
    float[] secondHalf = new float[half];
    Arrays.fill(array, 1.0f);
    long time = System.currentTimeMillis();
    System.arraycopy(array, 0, firstHalf, 0, half);
    System.arraycopy(array, half,  secondHalf, 0, half);

    Thread threadOne = new Thread(() -> {
        for (int i = 0; i < firstHalf.length; i++) {
            firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.arraycopy(firstHalf, 0, array, 0, firstHalf.length);
    });
    Thread threadTwo = new Thread(() -> {
        for(int i = 0; i < secondHalf.length; i++) {
            secondHalf[i] = (float)(secondHalf[i] * Math.sin(0.2f + (half + i) / 5) * Math.cos(0.2f + (half + i) / 5) * Math.cos(0.4f + (half + i) / 2));
        }
        System.arraycopy(secondHalf, 0, array, half, secondHalf.length);
    });
    threadOne.start();
    threadTwo.start();
    try {
        threadOne.join();
        threadTwo.join();
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println(array[0]);
    System.out.println(array[array.length - 1]);
    System.out.println("Время выполнения второго метода: " + (System.currentTimeMillis() - time));
}
    public void method3(int n) {
        float[] array = new float[size];
        System.out.println("количество потоков "+ n);
        double d = size%n;
        if (d != 0) n++;
        System.out.println("остаток " + d + " количество потоков "+ n);
        Arrays.fill(array, 1.0f);
        long time = System.currentTimeMillis();
        Thread[] threads = new Thread[n];
        float[][] results = new float[n][];
        int partSize = size / n;
        for (int i = 0; i < n; i++) {
            final int startIndex = i * partSize;
            final int endIndex = (i == n - 1) ? size : (i + 1) * partSize;
            final int index = i;
            threads[i] = new Thread(() -> {
                float[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
                for (int j = 0; j < subArray.length; j++) {
                    subArray[j] = (float)(subArray[j] * Math.sin(0.2f + (startIndex + j) / 5) * Math.cos(0.2f + (startIndex + j) / 5) * Math.cos(0.4f + (startIndex + j) / 2));
                }
                results[index] = subArray;
            });
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(results[i], 0, array, i * partSize, results[i].length);
        }

        System.out.println(array[0]);
        System.out.println(array[array.length - 1]);
        System.out.println("Время выполнения третьего метода: " + (System.currentTimeMillis() - time));
    }
}
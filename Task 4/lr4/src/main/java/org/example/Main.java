package org.example;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("1 задание");
        Integer[] arr1 = {51, 13, 0, 7, 3, 16};
        String[] arr2 = {"яблоко", "кошка", "электромобиль","архитектура", "ямб"};
        System.out.println(Arrays.toString(arr1));
        swapElements(arr1, 1,4);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        swapElements(arr2, 2,3);
        System.out.println(Arrays.toString(arr2));
        System.out.println("2 задание");
        List<String> list = convertToList(arr2);
        System.out.println(list);
        System.out.println("3 задание");
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        BananaBox<Banana> bananaBox = new BananaBox<>();
        BananaBox<Banana> bananaBox1 = new BananaBox<>();
        appleBox1.add(new Apple(),new Apple());
        appleBox2.add(new Apple());
        orangeBox.add(new Orange());
        bananaBox.add(new Banana(), new Banana());
        bananaBox.printBox();
        appleBox1.moveTo(bananaBox);
        bananaBox.moveTo(appleBox1);

        bananaBox.moveTo(bananaBox);
        bananaBox.moveTo(bananaBox1);

        appleBox1.moveTo(appleBox2);
        appleBox2.printBox();

//        appleBox1.moveTo(orangeBox);
//        orangeBox.moveTo(bananaBox);

//        Apple[] apples = {
//                new Apple(), new Apple()
//        };
//        Orange[] oranges = {
//                new Apple(), new Apple(), new Apple()
//        };

//        for(int i =0;i<17;i++) {
//            orangeBox.add(new Orange());
//        }
//        for(int i = 0; i< 15;i++) {
//            appleBox1.add(new Apple());
//        }
//        for (int i = 0; i<15;i++){
//            appleBox2.add(new Apple());
//        }
//        int a = 1 + (int) (Math.random()*17);
//        for (int i = 1; i<=a; i++){
//            bananaBox.add(new Banana());}
//        orangeBox.info();
//        appleBox1.info();
//        appleBox2.info();
//        bananaBox.info();
//        float orangeWeight = orangeBox.getWeight();
//        float apple1Weight = appleBox1.getWeight();
//        float apple2Weight = appleBox2.getWeight();
//        float bananaWeight = bananaBox.getWeight();
//        System.out.println("Вес коробки с апельсинами: " + orangeWeight);
//        System.out.println("Вес первой коробки с яблоками: " + apple1Weight);
//        System.out.println("Вес второй коробки с яблоками: " + apple2Weight);
//        System.out.println("Сравнение веса коробки с апельсинами с весом первой коробки с яблоками: " + orangeBox.compare(appleBox1));
//        System.out.println("Сравнение веса коробки с апельсинами с весом второй коробки с яблоками: " + orangeBox.compare(appleBox2));
//        System.out.println("Сравнение веса первой коробки с яблоками с весом второй коробки с яблоками: " + appleBox1.compare(appleBox2));
////
//        try {
//            orangeBox.moveTo(orangeBox);
//        } catch (MoveException e) {
//            e.printStackTrace();
//        }
//        if ()
//
//        int b = 1 + (int) (Math.random()*6);
//        for (int i = 1; i<=b; i++){
//            bananaBox.add(new Banana());}
//        bananaBox.info();
//
//        appleBox1.printBox();
//        appleBox2.printBox();
//        orangeBox.printBox();
//        bananaBox.printBox();
    }
    private static <T> void swapElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    private static <V> List<V> convertToList(V[] array){
        return Arrays.asList(array);
    }
}
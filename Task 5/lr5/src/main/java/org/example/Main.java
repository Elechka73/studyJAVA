package org.example;
import java.util.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        String[] arrayWord = {"Авто", "Собака", "Авто", "Холодильник","Очки","Телефон", "Собака", "Магнитофон", "Собака"};
//        HashMap<String, Integer> listWord = new HashMap<>();
//        for (String s: arrayWord) {
//            listWord.merge(s, 1, Integer::sum);
//        }
//        System.out.println(listWord.entrySet());
        Synonim map= new Synonim();

        map.add("уважение", "признание");
        map.add("увядание", "угасание");
        map.add("уважение", "престиж");
        map.add("праведный", "святой"); //
        map.add("увядание", "иссыхание");
        map.add("уважение", "почёт");
        map.add("праведный", "справедливый");
        map.add("праведный", "святой"); //

        System.out.println("==========");
        System.out.println(map.get("уважение"));
        System.out.println("==========");
        System.out.println(map.get("праведный"));
        map.printAll();;
    }
}


package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1 задание ===");
        List<String> list = new ArrayList<>(Arrays.asList("Пион", "Слово", "Ночь", "Гладь", "Лопасть", "Ночь", "Слово", "Грязь", "Метро", "Грязь"));

        var stringStream = list.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .collect(groupingBy(stringLongEntry -> stringLongEntry.getValue(), mapping(Map.Entry::getKey, toCollection(TreeSet::new))))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(longTreeSetEntry -> longTreeSetEntry.getValue().stream().sorted(Comparator.comparing(String::length)).collect(joining(", ")))
                .orElse("");

        System.out.println(stringStream);

        System.out.println("=== 2 задание ===");
        List<Stuff> stuffs = new ArrayList<>(Arrays.asList(
                new Stuff("Ivan", 24, 30000, Stuff.post.ENGINEER, "M"),
                new Stuff("Nikolay", 43, 50000, Stuff.post.ENGINEER, "M"),
                new Stuff("Olga", 18, 18000, Stuff.post.CLEANER, "W"),
                new Stuff("Boris", 50, 100000, Stuff.post.MANAGER, "M"),
                new Stuff("Sara", 46, 32000, Stuff.post.CLEANER, "W"),
                new Stuff("Bor", 50, 100, Stuff.post.MANAGER, "M")
        ));
        printOldest(stuffs, 5);
    }
    public static void printOldest(List<Stuff> stuffs, int n) {
        System.out.println(stuffs.stream().filter(l -> l.getGender() == "M")
                .limit(n).sorted(Comparator.comparingInt(Stuff::getSalary)
                .reversed())
                .sorted(Comparator.comparing(Stuff::getName)).map(Stuff::getName)
                .collect(Collectors.joining( " , " ,n+" самых низкооплачиваемых сотрудников зовут: " , ";" )));
    }
}
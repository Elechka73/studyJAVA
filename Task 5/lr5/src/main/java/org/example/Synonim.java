package org.example;
//import jdk.internal.icu.impl.Trie2;

import java.util.*;

public class Synonim {
    public Synonim() {
        map = new HashMap<>();
    }

    Map<String, LinkedHashSet<String>> map;
    public void add(String key, String val) {
        LinkedHashSet<String> synonyms = map.get(key);

        if (synonyms != null) {
            if (!synonyms.contains(val)) {
                synonyms.add(val);
            } else {
                System.out.println("Значение '" + val + "' уже есть уже у " + key);
            }
        } else {
            LinkedHashSet<String> newSet = new LinkedHashSet<>();
            newSet.add(val);
            map.put(key, newSet);
        }
    }
    public String get(String word) {
        LinkedHashSet<String> sinonims = map.get(word);
        if (sinonims != null && !sinonims.isEmpty()) {
            return String.join(", ", sinonims);
        } else {
            return "Синонимы не найдены";
        }
    }
    public void printAll() {
        System.out.println(map.entrySet());
    }
}
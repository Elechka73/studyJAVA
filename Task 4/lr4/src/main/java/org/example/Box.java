package org.example;

import java.util.*;

public class Box<L extends Fruit> {
    public List<L> getList() {
        return list;
    }

    public List<L> list;

    public Box() {
        list = new ArrayList<>();
    }

    //    void add(L[] array) {
//        list.addAll(Arrays.asList(array));
//        //list.addAll((Collection<? extends L>) list1);
//    }
    @SafeVarargs
    final void add(L... obj) {
        Collections.addAll(list, obj);
    }

//    void add(L obj) {
//        list.add(obj);
//    }

    void moveTo(Box<L> box) {
        if(!box.getList().equals(list)) {
            box.getList().addAll(list);
            list.clear();
        }
    }
//    void move(List<L> list, Box<? extends AvoidBanana> box) {
//        List<L> temp = new ArrayList<L>();
//        for (L item : list) {
//            box.getList().add(item);
//        }
//    }
//
    void info() {
        if (list.isEmpty()) {
            System.out.println("Коробка пуста");
        } else {
            System.out.println("В коробке находятся " + list.get(0).toString() + " в количестве " + list.size());
        }
    }

    float getWeight() {
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.size() * list.get(0).getWeight();
        }
    }

    boolean compare(Box<? extends AvoidBanana> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void printBox() {
        if (list.isEmpty()) {
            System.out.println("Коробка пуста");
        } else {
            for (L l : list) {
                System.out.println(l);
            }
        }
    }
}

package org.example;

public  class Cat implements Participant{
    int maxHeight;
    int maxLenght;
    String name;
    static int countSuperJamp = 2;

    public Cat(int maxHeight, int maxLenght, String name) {
        this.maxHeight = maxHeight;
        this.maxLenght = maxLenght;
        this.name = name;
    }

    @Override
    public boolean run(int dist) {
        if (dist <= maxLenght) {
            System.out.println("Кот " + this.name + " пробежал " + dist + " м");
            return true;
        }
        else {
            System.out.println("Кот " +this.name + " не смог пробежать " + dist + " м");
            return false;
            }
    }
    @Override
    public boolean jump(int height) {
        if (height<= maxHeight) {
            System.out.println("Кот "+ this.name+ " перепрыгнул " + height  +" м");
            return  true;}
        else if (countSuperJamp !=0) {
            if (height > maxHeight) countSuperJamp--;
            System.out.println("Кот "+ this.name+ " перепрыгнул с супершансом " + height  +" м");

            return true;
        }
        else {
            System.out.println("Кот "+ this.name+ " не смог перепрыгнуть " + height  +" м");
            return false;
        }
    }
}

package org.example;

public class Robot implements Participant{
    int maxHeight;
    int maxLenght;
    String name;

    public Robot(int maxHeight, int maxLenght, String name) {
        this.maxHeight = maxHeight;
        this.maxLenght = maxLenght;
        this.name = name;
    }
    public boolean run(int dist) {
        if (dist <= maxLenght) {System.out.println("Робот " +this.name + " пробежал " + dist + " м");
            return true;}
        else {System.out.println("Робот " +this.name + " не смог пробежать " + dist + " м");
            return false;}
    }
    public boolean jump(int height) {
        if (height<= maxHeight) {
            System.out.println("Робот "+ this.name+ " перепрыгнул " + height  +" м");
            return true;
        }
        else {
            System.out.println("Робот "+ this.name+ " не смог перепрыгнуть " + height  +" м");
            return false;
        }
    }
}

package org.example;

public class Human implements Participant{
    private int maxHeight;
   private int maxLenght;
    String name;

    public Human(int maxHeight, int maxLenght, String name) {
        this.maxHeight = maxHeight;
        this.maxLenght = maxLenght;
        this.name = name;
    }

    @Override
    public boolean run(int dist) {
        if (dist <= maxLenght) {
            System.out.println(this.name + " пробежал " + dist + " м");
            return true;
        } else {
            System.out.println(this.name + " не смог пробежать " + dist + " м");
            return false;
        }
    }
        @Override
        public boolean jump ( int height) {
            if (height <= maxHeight) {
                System.out.println(this.name + " перепрыгнул " + height + " м");
                return true;
            } else {
                System.out.println(this.name + " не смог перепрыгнуть " + height + " м");
                return false;
            }
        }
}


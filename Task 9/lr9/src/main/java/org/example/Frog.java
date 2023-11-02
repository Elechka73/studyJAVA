package org.example;
@Table(title = "Frog1")
public class Frog {
    public enum Color{
        BLACK, RED, GREEN, SPOTTED;
    }
    @Column
    private String name;
    @Column
    private String name1;

    @Column
    private int age;

    @Column
    private Color color;

    public Frog(String name, String name1, int age, Color color) {
        this.name = name;
        this.name1 = name1;
        this.age = age;
        this.color = color;
    }
}

package org.example;

public class Stuff {
    private final String name;
    private final int age;
    private final int salary;
    private final String gender;
    public post work;
    public enum post{
        ENGINEER, MANAGER, CLEANER
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }

    public Stuff(String name, int age, int salary, post work, String gender) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.work = work;
        this.gender = gender;
    }
}
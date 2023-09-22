package org.example;

public class MyArrayDataException extends Exception{
    private int i;
    private int j;
    private String r;
    public MyArrayDataException(int i,int j, String r) {
        super("Неверное значение в массиве " + r + " Ошибка в ячейке " + (i + 1) + " x " + (j + 1));
        this.i = i;
        this.j = j;
        this.r = r;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

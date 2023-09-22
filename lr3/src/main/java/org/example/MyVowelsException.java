package org.example;

public class MyVowelsException extends RuntimeException{
    private int i;
    private int j;
    private String r;
    public MyVowelsException(int i, int j, String r) {
        super("В ячейке массива гласная буква " + r + " Ошибка в ячейке " + (i + 1) + " x " + (j + 1));
        this.i = i;
        this.j = j;
        this.r = r;
    }
}

package org.example;


public class Main {
    public static void main(String[] args) throws Exception {
        Frog frog = new Frog("Plam", "1", 2, Frog.Color.BLACK);
        Frog frog1 = new Frog("Nyn", "2",3, Frog.Color.GREEN);
        Frog frog2 = new Frog("Raf", "3",1, Frog.Color.SPOTTED);
        AnnotationProcessor.createTable(frog);
        AnnotationProcessor.insertIntoTable(frog);
        AnnotationProcessor.insertIntoTable(frog1);
        AnnotationProcessor.insertIntoTable(frog2);
    }
}
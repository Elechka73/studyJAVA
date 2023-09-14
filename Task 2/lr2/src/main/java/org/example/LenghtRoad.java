package org.example;

public enum LenghtRoad {
        SHORT("короткая дистанция",50), AVERANGE("средняя дистанция", 100), LONG("длинная дистанция", 250);
        private int lenghtRoad;
        private String title;
    LenghtRoad (String title, int lenghtRoad) {
            this.lenghtRoad= lenghtRoad;
            this.title= title;}

    public int getLenghtRoad() {
        return lenghtRoad;
    }

    public String getTitle() {
        return title;
    }
}



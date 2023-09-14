package org.example;

public class Main {

    public static void main(String[] args) {
       Participant[] participants = {
               new Human(2, 350, "Jesus"),
               new Cat(1, 100, "Fet"),
               new Robot(3,400,"CyberBull"),
               new Human(0,10,"Max"),
               new Cat(2, 90, "Bulgakov")
       };
      Challenge[] challenges = {
              new Wall(WallHeight.LOW),
              new RunningRoad(LenghtRoad.SHORT),
              new Wall (WallHeight.AVERANGE),
              new Wall (WallHeight.HIGH),
              new RunningRoad(LenghtRoad.AVERANGE),
              new RunningRoad(LenghtRoad.LONG)
      };
      for(Participant p: participants) {
          for (Challenge t: challenges) {
              if(!t.isCan(p)) break;
          }
      }
    }
}
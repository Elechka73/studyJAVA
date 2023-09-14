package org.example;

public class RunningRoad implements Challenge{
    private int roadLength;

    public RunningRoad(LenghtRoad lenghtRoad) {
        this.roadLength = lenghtRoad.getLenghtRoad();
    }
    @Override
    public boolean isCan(Participant participant){
        if (participant.run(roadLength)){
            return true;
        }
        else return false;
    }
}

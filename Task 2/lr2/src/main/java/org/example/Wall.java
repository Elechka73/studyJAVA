package org.example;

public class Wall implements Challenge{
    private int wallHeight;

    public Wall(WallHeight wallHeight) {
        this.wallHeight = wallHeight.getWallHeight();
    }
    @Override
    public boolean isCan(Participant participant){
        if (participant.jump(wallHeight)){
            return true;
        }
        else return false;
    }
}

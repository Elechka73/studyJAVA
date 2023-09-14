package org.example;

public enum WallHeight {
    LOW("низкая стена",0), AVERANGE("средняя стена", 2), HIGH("высокая стена", 3);
    private int wallHeight;
    private String title;
    WallHeight (String title, int wallHeight) {
        this.wallHeight= wallHeight;
        this.title= title;}

    public int getWallHeight() {
        return wallHeight;
    }

    public String getTitle() {
        return title;
    }
}

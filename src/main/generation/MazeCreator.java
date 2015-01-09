package main.generation;

import main.models.Graph;

public abstract class MazeCreator {

    protected Graph floorAndWalls;
    protected Graph paths;

    protected int length;
    protected int width;
    protected int height;

    public Graph getFloorAndWalls() {
        return floorAndWalls;
    }

    public void setFloorAndWalls(Graph floorsAndWalls) {
        this.floorAndWalls = floorsAndWalls;
    }

    public Graph getPaths() {
        return paths;
    }

    public void setPaths(Graph paths) {
        this.paths = paths;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void printMaze() {
        System.out.print("|");
        int length = width * height;
        for (int i = 0; i < length; i++) {

        }
    }
}

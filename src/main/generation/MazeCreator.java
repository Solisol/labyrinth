package main.generation;

import main.models.Graph;

public abstract class MazeCreator {

    private Graph maze;

    private int width;
    private int height;

    public Graph getMaze() {
        return maze;
    }

    public void setMaze(Graph maze) {
        this.maze = maze;
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
}

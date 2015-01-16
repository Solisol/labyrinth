package main.creator;

import main.models.Graph;

public class NoMazeCreator extends MazeCreator {

    public NoMazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
        this.length = width*height;
    }

    @Override
    public void generateMaze() {
        paths = Graph.getFullGridGraph(width, height);
    }


}

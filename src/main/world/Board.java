package main.world;

import main.creator.MazeCreator;
import main.creator.RecursiveBacktrackerMazeCreator;

import java.awt.*;

public class Board extends Frame {

    private MazeCreator maze;

    public void draw() {
        maze = new RecursiveBacktrackerMazeCreator(10, 10);
        maze.generateMaze();
    }

}

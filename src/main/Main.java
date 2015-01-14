package main;

import main.creator.RecursiveBacktrackerMazeCreator;

public class Main {

    public static void main(String[] args) {
        RecursiveBacktrackerMazeCreator maze = new RecursiveBacktrackerMazeCreator(10, 10);
        maze.generateMaze();
        maze.printMaze();
    }
}

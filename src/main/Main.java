package main;

import main.generation.RecursiveBacktrackerMazeCreator;

public class Main {

    public static void main(String[] args) {
        RecursiveBacktrackerMazeCreator maze = new RecursiveBacktrackerMazeCreator(14, 20);
        maze.generateMaze();
        maze.printMaze();
    }
}

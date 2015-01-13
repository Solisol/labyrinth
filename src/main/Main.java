package main;

import main.generation.RecursiveBacktrackerMazeCreator;

public class Main {

    public static void main(String[] args) {
        RecursiveBacktrackerMazeCreator maze = new RecursiveBacktrackerMazeCreator(10, 10);
        maze.generateMaze();
        maze.printMaze();
    }
}

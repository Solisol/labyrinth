package main.models;

import main.creator.MazeCreator;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class AstarPath extends Path {

    private int goalX;
    private int goalY;
    private MazeCreator maze;

    public AstarPath(int goal, MazeCreator maze) {
        super();
        this.maze = maze;
        this.goalX = goal % this.maze.getWidth();
        this.goalY = goal / this.maze.getWidth();
    }

    public AstarPath(int goalX, int goalY, MazeCreator maze, LinkedList<Vertex> path) {
        super(path);
        this.maze = maze;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    @Override
    public double getLength() {
        return getNodes().size() + getFlyDistanceToGoal();
//        return getFlyDistanceToGoal();
    }

    private double getFlyDistanceToGoal() {
        Vertex last = getNodes().getLast();
        int x = last.getIndex() % maze.getWidth();
        int y = last.getIndex() / maze.getWidth();
        return Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    @Override
    public Path clone() {
        return new AstarPath(goalX, goalY, maze, new LinkedList<Vertex>(getNodes()));
    }
}

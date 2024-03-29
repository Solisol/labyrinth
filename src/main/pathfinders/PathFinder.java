package main.pathfinders;

import main.creator.MazeCreator;
import main.models.Vertex;

import java.util.LinkedList;
import java.util.List;

public abstract class PathFinder {

    private List<PathFinderListener> listenerList;

    protected MazeCreator maze;

    public PathFinder() {
        listenerList = new LinkedList<PathFinderListener>();
    }

    public void addListener(PathFinderListener listener) {
        listenerList.add(listener);
    }

    public boolean removeListener(PathFinderListener listener) {
        return listenerList.remove(listener);
    }

    protected void raiseNodeGetsPickeable(int index) {
        for (PathFinderListener listener : listenerList) {
            listener.nodeGetsPickeable(index);
        }
    }

    protected void raiseTakenNode(int index) {
        for (PathFinderListener listener : listenerList) {
            listener.takenNode(index);
        }
    }

    protected void raiseGoalFound(List<Vertex> path) {
        for (PathFinderListener listener : listenerList) {
            listener.goalFound(path);
        }
    }

    public void setMaze(MazeCreator maze) {
        this.maze = maze;
    }

    public abstract void start();
}

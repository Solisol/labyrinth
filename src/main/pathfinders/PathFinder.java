package main.pathfinders;

import java.util.LinkedList;
import java.util.List;

public abstract class PathFinder {

    private List<PathFinderListener> listenerList;

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

    public abstract void start();
}

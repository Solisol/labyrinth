package main.pathfinders;

public interface PathFinderListener {

    void nodeGetsPickeable(int index);

    void takenNode(int index);

    void goalFound(int index);

}

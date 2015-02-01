package main.pathfinders;

import main.models.Vertex;

import java.util.List;

public interface PathFinderListener {

    void nodeGetsPickeable(int index);

    void takenNode(int index);

    void goalFound(List<Vertex> path);

}

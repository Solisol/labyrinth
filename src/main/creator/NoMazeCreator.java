package main.creator;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;

public class NoMazeCreator extends MazeCreator {

    public NoMazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
        this.length = width*height;
    }

    @Override
    public void generateMaze() {
        paths = getPathToAllNeighbours();
    }

    private Graph getPathToAllNeighbours() {
        Graph graph = new Graph(length);
        for (int i = 0; i < length; i++) {
            Vertex node = graph.getNodeInPosition(i);
            node.setOutNodes(getAllNeighbours(node, graph));
            graph.updateNode(node);
        }
        return graph;
    }

    private ArrayList<Vertex> getAllNeighbours(Vertex node, Graph graph) {
        ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

        int index = node.getIndex();
        if (index % width != 0) {
            neighbours.add(graph.getNodeInPosition(index - 1));
        }
        if (index >= width) {
            neighbours.add(graph.getNodeInPosition(index - width));
        }
        if (index  < length - width) {
            neighbours.add(graph.getNodeInPosition(index + width));
        }
        if (index % width != (width - 1)) {
            neighbours.add(graph.getNodeInPosition(index + 1));
        }
        return neighbours;
    }
}

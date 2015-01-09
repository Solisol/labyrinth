package main.generation;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class RecursiveBacktrackerMazeCreator extends MazeCreator {

    private LinkedList<Integer> stack = new LinkedList<Integer>();
    private static int length;
    private Graph graph;
    private Random random = new Random();
    private ArrayList<Boolean> visited;

    protected void generateMaze() {
        length = getHeight() * getWidth();
        int width = getWidth();
        graph = new Graph();
        resetVisited();

        //Get start node
        Vertex node = graph.getNodeInPosition(0);

        visited.set(node.getIndex(), true);
        node.setOutNodes(getPossibleNeighbours(node, graph, width));
        graph.updateNodeInPosition(node.getIndex(), node);

        //pick new random possible node and put self on stack
            //Do something about the path between new node and old
        //if no possible node, pop stack
        //do while stack not empty

    }

    public void resetVisited() {
        visited = new ArrayList<Boolean>();
        for (int i = 0; i < length; i++) {
            visited.add(false);
        }
    }

    public ArrayList<Vertex> getPossibleNeighbours(Vertex node, Graph graph, int width) {
        ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

        int index = node.getIndex();
        if (index % width != 0 && !visited.get(index - 1)) {
             neighbours.add(graph.getNodeInPosition(index - 1));
        }
        if (index >= width && !visited.get(index - width)) {
            neighbours.add(graph.getNodeInPosition(index - width));
        }
        if (index  < length - width && !visited.get(index + width)) {
            neighbours.add(graph.getNodeInPosition(index + width));
        }
        if (index % width != 2 && !visited.get(index + 1)) {
            neighbours.add(graph.getNodeInPosition(index + 1));
        }
        return neighbours;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

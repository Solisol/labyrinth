package main.generation;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RecursiveBacktrackerMazeCreator extends MazeCreator {

    private LinkedList<Vertex> stack = new LinkedList<Vertex>();
    private static int length;
    private Graph floorAndWalls;
    private Graph paths;
    private Random random = new Random();
    private ArrayList<Boolean> visited;

    protected void generateMaze() {
        length = getHeight() * getWidth();
        int width = getWidth();
        floorAndWalls = new Graph(length);
        paths = new Graph(length);
        resetVisited();

        //Get start node
        Vertex node = floorAndWalls.getNodeInPosition(0);
        stack.push(node);

        while (stack.size() > 0) {
            node = stack.pop();
            visited.set(node.getIndex(), true);
            node.setOutNodes(getPossibleNeighbours(node, floorAndWalls, width));
            //floorAndWalls.updateNodeInPosition(node.getIndex(), node);

            //pick new random possible node and put self on stack
            if (node.getOutNodes().size() > 0) {
                ArrayList<Vertex> outNodes = node.getOutNodes();
                //pick random neighbour
                Vertex randomNeighbour = outNodes.get(random.nextInt(outNodes.size()));
                //Do something about the path between new node and old
                outNodes.remove(randomNeighbour.getIndex());
                node.setOutNodes(outNodes);
                floorAndWalls.updateNodeInPosition(node);
                Vertex pathNode = paths.getNodeInPosition(node.getIndex());
                ArrayList<Vertex> pathOutNodes = pathNode.getOutNodes();
                pathOutNodes.add(randomNeighbour);
                pathNode.setOutNodes(pathOutNodes);
                paths.updateNodeInPosition(pathNode);
                stack.push(node);
                stack.push(randomNeighbour);
            }
        }

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

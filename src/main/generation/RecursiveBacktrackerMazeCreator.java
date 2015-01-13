package main.generation;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RecursiveBacktrackerMazeCreator extends MazeCreator {

    private LinkedList<Vertex> stack = new LinkedList<Vertex>();
    private Random random = new Random();
    private ArrayList<Boolean> visited;

    public RecursiveBacktrackerMazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void generateMaze() {
        length = height * width;
        floorAndWalls = new Graph(length);
        paths = new Graph(length);
        resetVisited();

        //Get start node
        Vertex node = floorAndWalls.getNodeInPosition(0);
        stack.push(node);

        while (stack.size() > 0) {
            node = stack.pop();
            visited.set(node.getIndex(), true);
            node.setOutNodes(getPossibleNeighbours(node, floorAndWalls));
            floorAndWalls.updateNode(node);

            //pick new random possible node and put self on stack
            if (node.getOutNodes().size() > 0) {
                ArrayList<Vertex> outNodes = node.getOutNodes();
                //pick random neighbour
                Vertex randomNeighbour = outNodes.get(random.nextInt(outNodes.size()));
                //Do something about the path between new node and old
                node.removeFromNodes(randomNeighbour);
                floorAndWalls.updateNode(node);
                Vertex pathNode = paths.getNodeInPosition(node.getIndex());
                pathNode.addToNodes(randomNeighbour);
                paths.updateNode(pathNode);
                Vertex pathRandomNode = paths.getNodeInPosition(randomNeighbour.getIndex());
                pathRandomNode.addToNodes(pathNode);
                paths.updateNode(pathRandomNode);
                //Push self to stack
                stack.push(node);
                //Push the new random neighbour to the stack
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

    public ArrayList<Vertex> getPossibleNeighbours(Vertex node, Graph graph) {
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
        if (index % width != (width - 1) && !visited.get(index + 1)) {
            neighbours.add(graph.getNodeInPosition(index + 1));
        }
        return neighbours;
    }

}

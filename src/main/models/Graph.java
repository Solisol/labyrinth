package main.models;

import java.util.ArrayList;

public class Graph {
    public Graph(int numberOfNodes) {
        ArrayList<Vertex> nodes = new ArrayList<Vertex>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Vertex(i));
        }
        this.nodes = nodes;
    }

    public int size() {
        return nodes.size();
    }

    private ArrayList<Vertex> nodes;

    public ArrayList<Vertex> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Vertex> nodes) {
        this.nodes = nodes;
    }

    public Vertex getNodeInPosition(int index) {
        return nodes.get(index);
    }

    public void updateNode(Vertex node) {
        nodes.set(node.getIndex(), node);
    }

    public String toString() {
        String res = "Graph[" + nodes.size() + "]: ";
        for (int i = 0; i < nodes.size(); i++) {
            res = res + nodes.get(i).toString() + ", ";
        }
        return res;
    }

    public static Graph getOpposite(Graph graph, int width, int height) {
        Graph opposite = getFullGridGraph(width, height);
        for (int i = 0; i < graph.size(); i++) {
            Vertex pathNode = graph.getNodeInPosition(i);
            ArrayList<Vertex> pathNeighbours = pathNode.getOutNodes();
            for (int j = 0; j < pathNeighbours.size(); j++) {
                Vertex pathNeighbour = pathNeighbours.get(j);
                Vertex oppositeNode = opposite.getNodeInPosition(i);
                oppositeNode.removeFromNodes(opposite.getNodeInPosition(pathNeighbour.getIndex()));
                opposite.updateNode(oppositeNode);
            }
        }
        return opposite;
    }

    public static Graph getFullGridGraph(int width, int height) {
        int length = width*height;
        Graph graph = new Graph(length);
        for (int i = 0; i < length; i++) {
            Vertex node = graph.getNodeInPosition(i);
            node.setOutNodes(getAllNeighbours(node, graph, width, length));
            graph.updateNode(node);
        }
        return graph;
    }

    private static ArrayList<Vertex> getAllNeighbours(Vertex node, Graph graph, int width, int length) {
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

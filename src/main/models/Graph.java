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

    public void updateNodeInPosition(Vertex node) {
        nodes.set(node.getIndex(), node);
    }

    public String toString() {
        String res = "Graph: ";
        for (int i = 0; i < nodes.size(); i++) {
            res = res + i + " " + nodes.get(i).toString() + ", ";
        }
        return res;
    }
}

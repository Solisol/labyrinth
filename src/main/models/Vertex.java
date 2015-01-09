package main.models;

import java.util.ArrayList;

public class Vertex {

    private ArrayList<Vertex> outNodes = new ArrayList<Vertex>();
    private int index;

    public Vertex(int index) {
        this.index = index;
    }

    public ArrayList<Vertex> getOutNodes() {
        return outNodes;
    }

    public void setOutNodes(ArrayList<Vertex> outNodes) {
        this.outNodes = outNodes;
    }

    private int getNumberOfNeighbours() {
        return outNodes.size();
    }

    public String toString() {
        String res = "Vertex: {";
        if (outNodes.size() == 0) {
            return res;
        }
        for (int i = 0; i < outNodes.size(); i++) {
            res = res + outNodes.get(i).getIndex() + " ";
        }
        return res + "}";
    }

    public int getIndex() {
        return index;
    }
}

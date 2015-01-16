package main.models;

import java.util.ArrayList;

public class Vertex {

    private ArrayList<Vertex> outNodes = new ArrayList<Vertex>();
    private int index;

    public Vertex(int index, ArrayList<Vertex> outNodes) {
        this.index = index;
        this.outNodes = outNodes;
    }

    public Vertex(int index) {
        this(index, new ArrayList<Vertex>());
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

    public void addToNodes(Vertex node) {
        outNodes.add(node);
    }

    public void removeFromNodes(Vertex node) {
        outNodes.remove(node);
    }

    public String toString() {
        String res = "Vertex(" + index + "): {";
        if (outNodes.size() == 0) {
            return res + "}";
        }
        for (int i = 0; i < outNodes.size(); i++) {
            res = res + outNodes.get(i).getIndex() + " ";
        }
        return res + "}";
    }

    public int getIndex() {
        return index;
    }

    public int neighbourSize() {
        return outNodes.size();
    }

    public boolean compareChildren(Vertex other) {
        if (index != other.getIndex()) {
            return false;
        }
        for (Vertex child : outNodes) {
            boolean isMatched = false;
            for (Vertex otherChild : other.getOutNodes()) {
                if (child.getIndex() == otherChild.getIndex()) {
                    isMatched = true;
                    break;
                }
            }
            if (!isMatched) {
                return isMatched;
            }
        }
        return true;
    }
}

package main.models;

import java.util.LinkedList;

/**
 *
 */
public class Path implements Comparable<Path>{
    private LinkedList<Vertex> nodes;

    public Path() {
        nodes = new LinkedList<Vertex>();
    }

    public Path(LinkedList<Vertex> nodes) {
        this.nodes = nodes;
    }

    public LinkedList<Vertex> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Vertex> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(Path path) {
        return nodes.size() - path.nodes.size();
    }

    public Path clone() {
        return new Path(new LinkedList<Vertex>(nodes));
    }

    public void add(Vertex node) {
        nodes.addLast(node);
    }
}

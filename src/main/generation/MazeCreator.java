package main.generation;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;

public abstract class MazeCreator {

    protected Graph floorAndWalls;
    protected Graph paths;

    protected int length;
    protected int width;
    protected int height;

    public Graph getFloorAndWalls() {
        return floorAndWalls;
    }

    public void setFloorAndWalls(Graph floorsAndWalls) {
        this.floorAndWalls = floorsAndWalls;
    }

    public Graph getPaths() {
        return paths;
    }

    public void setPaths(Graph paths) {
        this.paths = paths;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void printMaze() {
        ArrayList<Vertex> nodes = getPaths().getNodes();
        System.out.print(" ");
        for (int i = 0; i < width; i++) {
            System.out.print("_ ");
        }
        System.out.println("");
        for (int i = 0; i < length; i++) {
            Vertex node = nodes.get(i);
            if (isLeftEdge(node)) {
                System.out.print("|");
            }
            System.out.print(hasDown(node) ? " " : "_");
            System.out.print(hasRight(node) ? (!isRightEdge(node) ? " " : "") : "|");
            if(i % width == width - 1) {
                System.out.println("");
            }
        }
    }

    public boolean isLeftEdge(Vertex node) {
        return (node.getIndex() % width == 0);
    }

    public boolean isRightEdge(Vertex node) {
        return (node.getIndex() % width == width - 1);
    }

    public boolean hasUp(Vertex node) {
        int upIndex = node.getIndex() - width;
        return hasPathToIndex(node, upIndex);
    }

    public boolean hasLeft(Vertex node) {
        int leftIndex = node.getIndex() - 1;
        return hasPathToIndex(node, leftIndex);
    }

    public boolean hasRight(Vertex node) {
        int rightIndex = node.getIndex() + 1;
        return hasPathToIndex(node, rightIndex);
    }

    public boolean hasDown(Vertex node) {
        int downIndex = node.getIndex() + width;
        return hasPathToIndex(node, downIndex);
    }

    private boolean hasPathToIndex(Vertex node, int matchIndex) {
        ArrayList<Vertex> outNodes = node.getOutNodes();
        for (Vertex outNode : outNodes) {
            if (outNode.getIndex() == matchIndex) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

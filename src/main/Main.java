package main;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        main.Labyrinth lab = new main.Labyrinth();
//        lab.setSize(400, 300);
//        lab.setVisible(true);
        Graph graph = createGraph();
        System.out.println(graph.toString());
        for (int i = 0; i < 9; i++) {
            System.out.println(getPossibleNeighbours(i, 3, 3));
        }
    }

    private static Graph createGraph() {
        Graph graph = new Graph();
        ArrayList<Vertex> nodes = new ArrayList<Vertex>();

        Vertex node = new Vertex(0);
        Vertex node1 = new Vertex(1);
        Vertex node2 = new Vertex(2);
        Vertex node3 = new Vertex(3);
        Vertex node4 = new Vertex(4);
        Vertex node5 = new Vertex(5);
        Vertex node6 = new Vertex(6);
        Vertex node7 = new Vertex(7);
        Vertex node8 = new Vertex(8);

        node.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node1, node3)));
        node1.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node, node2, node4)));
        node2.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node1, node5)));
        node3.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node, node4, node6)));
        node4.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node1, node3, node5, node7)));
        node5.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node2, node4, node8)));
        node6.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node3, node7)));
        node7.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node4, node6, node8)));
        node8.setOutNodes(new ArrayList<Vertex>(Arrays.asList(node5, node7)));

        nodes.addAll(Arrays.asList(node, node1, node2, node3, node4, node5, node6, node7, node8));
        graph.setNodes(nodes);
        return graph;
    }

    private static ArrayList<Integer> getPossibleNeighbours(int node, int width, int height) {
        int length = width * height;
        ArrayList<Integer> neighbours = new ArrayList<Integer>();

        if (node % width != 0) {
            neighbours.add(node - 1);
        }
        if (node >= width) {
            neighbours.add(node - width);
        }
        if (node < length - width) {
            neighbours.add(node + width);
        }
        if (node % width != 2) {
            neighbours.add(node + 1);
        }
        return neighbours;
    }
}

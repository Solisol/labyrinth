package test.generation;

import main.generation.RecursiveBacktrackerMazeCreator;
import main.models.Graph;
import main.models.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class RecursiveBacktrackerMazeCreatorTest {

    private RecursiveBacktrackerMazeCreator maze;
    private int width = 3;
    private int height = 3;

    @Before
    public void setup() {
        maze = new RecursiveBacktrackerMazeCreator();
        maze.setHeight(height);
        maze.setWidth(width);
        maze.setLength(width * height);
        maze.resetVisited();
    }

    @Test
    public void testGenerateNeighbours() {
        Graph correctGraph = createFullGraph();
        ArrayList<Vertex> nodes = correctGraph.getNodes();

        for (Vertex node : nodes) {
            ArrayList<Vertex> testNeighbours = maze.getPossibleNeighbours(node, correctGraph);
            Vertex testNode = new Vertex(node.getIndex(), testNeighbours);
            assertTrue(node.compareChildren(testNode));
        }

    }
    private static Graph createFullGraph() {
        Graph graph = new Graph(9);
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
}

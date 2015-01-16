package test.generation;

import main.creator.RecursiveBacktrackerMazeCreator;
import main.models.Graph;
import main.models.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecursiveBacktrackerMazeCreatorTest {

    private RecursiveBacktrackerMazeCreator maze;
    private int width = 3;
    private int height = 3;

    @Before
    public void setup() {
        maze = new RecursiveBacktrackerMazeCreator(width, height);
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

    @Test
    public void testHasUp() {
        Graph fullGraph = createFullGraph();
        ArrayList<Vertex> fullNodes = fullGraph.getNodes();

        for (int i = 0; i < fullNodes.size(); i++) {
            if (i < width) {
                assertFalse(maze.hasUp(fullNodes.get(i)));
            } else {
                assertTrue(maze.hasUp(fullNodes.get(i)));
            }
        }
    }

    @Test
    public void testHasDown() {
        Graph fullGraph = createFullGraph();
        ArrayList<Vertex> fullNodes = fullGraph.getNodes();

        for (int i = 0; i < fullNodes.size(); i++) {
            if (i >= maze.getLength() - width) {
                assertFalse(maze.hasDown(fullNodes.get(i)));
            } else {
                assertTrue(maze.hasDown(fullNodes.get(i)));
            }
        }
    }

    @Test
    public void testHasLeft() {
        Graph fullGraph = createFullGraph();
        ArrayList<Vertex> fullNodes = fullGraph.getNodes();

        for (int i = 0; i < fullNodes.size(); i++) {
            if (i % width == 0) {
                assertFalse(maze.hasLeft(fullNodes.get(i)));
            } else {
                assertTrue(maze.hasLeft(fullNodes.get(i)));
            }
        }
    }

    @Test
    public void testHastRight() {
        Graph fullGraph = createFullGraph();
        ArrayList<Vertex> fullNodes = fullGraph.getNodes();

        for (int i = 0; i < fullNodes.size(); i++) {
            if (i % width == width - 1) {
                assertFalse(maze.hasRight(fullNodes.get(i)));
            } else {
                assertTrue(maze.hasRight(fullNodes.get(i)));
            }
        }
    }

    @Test
    public void testEmptyGraphHasNoNeighbours() {
        Graph emptyGraph = createEmptyGraph();
        ArrayList<Vertex> emptyNodes = emptyGraph.getNodes();

        for (Vertex node : emptyNodes) {
            assertFalse(maze.hasDown(node));
            assertFalse(maze.hasUp(node));
            assertFalse(maze.hasLeft(node));
            assertFalse(maze.hasRight(node));
        }
    }

    private static Graph createEmptyGraph() {
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

        nodes.addAll(Arrays.asList(node, node1, node2, node3, node4, node5, node6, node7, node8));
        graph.setNodes(nodes);
        return graph;
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

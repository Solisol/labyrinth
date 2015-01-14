package test.generation;

import main.creator.MazeCreator;
import main.creator.RecursiveBacktrackerMazeCreator;
import main.models.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MazeCreatorTest {

    private MazeCreator maze;
    private int width = 3;
    private int height = 3;

    @Before
    public void setup() {
        maze = new RecursiveBacktrackerMazeCreator(width, height);
        maze.setLength(width * height);
    }

    @Test
    public void testIsLeft() {
        assertTrue(maze.isLeftEdge(new Vertex(0)));
        assertTrue(maze.isLeftEdge(new Vertex(3)));
        assertTrue(maze.isLeftEdge(new Vertex(6)));
        assertFalse(maze.isLeftEdge(new Vertex(2)));
        assertFalse(maze.isLeftEdge(new Vertex(1)));
        assertFalse(maze.isLeftEdge(new Vertex(8)));
    }

    @Test
    public void testIsRight() {
        assertTrue(maze.isRightEdge(new Vertex(2)));
        assertTrue(maze.isRightEdge(new Vertex(5)));
        assertTrue(maze.isRightEdge(new Vertex(8)));
        assertFalse(maze.isRightEdge(new Vertex(0)));
        assertFalse(maze.isRightEdge(new Vertex(1)));
        assertFalse(maze.isRightEdge(new Vertex(3)));
        assertFalse(maze.isRightEdge(new Vertex(4)));
    }
}

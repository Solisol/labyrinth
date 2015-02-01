package main.world;

import main.creator.MazeCreator;
import main.creator.NoMazeCreator;
import main.creator.OpenMazeCreator;
import main.creator.RecursiveBacktrackerMazeCreator;
import main.models.Vertex;
import main.pathfinders.PathFinderListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Board extends JPanel implements PathFinderListener {

    private int width;
    private int height;
    private int length;

    private MazeCreator maze;

    private Color[] tiles;
    private int fade;

    public Board(MazeCreator maze) {
        setMaze(maze);
    }

    public void clearBoard() {
        tiles = new Color[length];
        Arrays.fill(tiles, Color.white);
        fade = 0;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.green);

        int tileWidth = getWidth() / width;
        int tileHeight = getHeight() / height;

        //Top line of maze
        g.drawLine(0, 0, tileWidth * width, 0);


        ArrayList<Vertex> nodes = maze.getPaths().getNodes();

        for (int i = 0; i < length; i++) {
            int x = i % width;
            int y = i / width;

            g.setColor(tiles[i]);
            g.fillRect(tileWidth * x, tileHeight * y, tileWidth, tileHeight);

        }

        g.setColor(Color.black);

        for (int i = 0; i < length; i++) {
            int x = i % width;
            int y = i / width;

            Vertex node = nodes.get(i);
            if (maze.isLeftEdge(node)) {
                g.drawLine(tileWidth * x, tileHeight * y, tileWidth * x, tileHeight * (y + 1));
            }
            if (!maze.hasDown(node)) {
                g.drawLine(tileWidth * x, tileHeight * (y + 1), tileWidth * (x + 1), tileHeight * (y + 1));
            }
            if (!maze.hasRight(node)) {
                g.drawLine(tileWidth * (x + 1), tileHeight * y, tileWidth * (x + 1), tileHeight * (y + 1));
            }
        }
    }

    @Override
    public void nodeGetsPickeable(int index) {
        tiles[index] = Color.green;
        this.repaint();
    }

    @Override
    public void takenNode(int index) {
        tiles[index] = new Color(1.0f, (float) fade/length, (float) fade/length);
        fade++;
        this.repaint();
        try {
            Thread.sleep(1);
        } catch (InterruptedException ie) {
            //dooooo naaaathing
        }
    }

    @Override
    public void goalFound(List<Vertex> path) {
        for (Vertex node : path) {
            tiles[node.getIndex()] = Color.blue;
        }
        this.repaint();
        System.out.println(String.format("Number of steps are %s", fade));
    }

    public MazeCreator getMaze() {
        return maze;
    }

    public void setMaze(MazeCreator maze) {
        this.maze = maze;
        this.width = maze.getWidth();
        this.height = maze.getHeight();
        length = width * height;
        clearBoard();
    }

    public int getFade() {
        return fade;
    }
}

package main.world;

import main.creator.MazeCreator;
import main.creator.RecursiveBacktrackerMazeCreator;
import main.models.Vertex;
import main.pathfinders.PathFinderListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends JPanel implements PathFinderListener {

    private final int width;
    private final int height;
    private final int length;
    private MazeCreator maze;
    private Color[] tiles;
    private int fade;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        length = width * height;
        maze = new RecursiveBacktrackerMazeCreator(width, height);
        maze.generateMaze();
        tiles = new Color[length];
        Arrays.fill(tiles, Color.white);
        fade = 0;
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
        tiles[index] = Color.blue;
        this.repaint();
    }

    @Override
    public void takenNode(int index) {
        tiles[index] = new Color(0f,(float) fade/length, 0f);
        fade++;
        this.repaint();
        try {
            Thread.sleep(2);
        } catch (InterruptedException ie) {
            //dooooo naaaathing
        }
    }

    public MazeCreator getMaze() {
        return maze;
    }
}

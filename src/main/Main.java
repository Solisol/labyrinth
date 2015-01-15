package main;

import main.creator.RecursiveBacktrackerMazeCreator;
import main.pathfinders.PathFinder;
import main.pathfinders.RandomPathFinder;
import main.world.Board;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));
        Board board = new Board(200, 200);
        frame.add(board);
        frame.setSize(801,801);
        frame.setTitle("Hello frameu laburinthu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        PathFinder pathFinder = new RandomPathFinder(board.getMaze());

        pathFinder.addListener(board);
        pathFinder.start();
    }
}

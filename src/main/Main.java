package main;

import main.creator.RecursiveBacktrackerMazeCreator;
import main.world.Board;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));
        frame.add(new Board(100, 100));
        frame.setSize(801,801);
        frame.setTitle("Hello frameu laburinthu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

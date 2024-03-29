package main;

import main.creator.MazeCreator;
import main.creator.NoMazeCreator;
import main.creator.OpenMazeCreator;
import main.creator.RecursiveBacktrackerMazeCreator;
import main.pathfinders.AstarManhattanPathFinder;
import main.pathfinders.DijkstraPathFinder;
import main.pathfinders.PathFinder;
import main.pathfinders.RandomPathFinder;
import main.world.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static MazeCreator maze;
    private static PathFinder pathFinder;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));

        final int width = 50;
        final int height = 50;

        maze = new NoMazeCreator(width, height);
        maze.generateMaze();
        pathFinder = new RandomPathFinder(maze);

        final Board board = new Board(maze);
        pathFinder.addListener(board);

        //Optionspanel that holds all options
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));

        //Panel for the different algorithms
        JPanel algorithmsPanel = new JPanel(new GridLayout(3, 1));

        ButtonGroup algorithmsGroup = new ButtonGroup();
        JRadioButton randomButton = new JRadioButton("Random", true);
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pathFinder = new RandomPathFinder(maze);
                pathFinder.addListener(board);
            }
        });
        JRadioButton dijkstraButton = new JRadioButton("Dijkstra", false);
        dijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pathFinder = new DijkstraPathFinder(maze);
                pathFinder.addListener(board);
            }
        });
        JRadioButton astarButton = new JRadioButton("A*", false);
        astarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pathFinder = new AstarManhattanPathFinder(maze);
                pathFinder.addListener(board);
            }
        });
        algorithmsGroup.add(randomButton);
        algorithmsGroup.add(dijkstraButton);
        algorithmsGroup.add(astarButton);

        algorithmsPanel.add(randomButton);
        algorithmsPanel.add(dijkstraButton);
        algorithmsPanel.add(astarButton);

        //Panel for different mazes
        JPanel mazePanel = new JPanel(new GridLayout(3, 1));
        final ButtonGroup mazeGroup = new ButtonGroup();

        JRadioButton noMazeButton = new JRadioButton("Empty grid", true);
        noMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze = new NoMazeCreator(width, height);
                maze.generateMaze();
                pathFinder.setMaze(maze);
                board.setMaze(maze);
            }
        });
        JRadioButton openMazeButton = new JRadioButton("Open maze", false);
        openMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze = new OpenMazeCreator(width, height);
                maze.generateMaze();
                pathFinder.setMaze(maze);
                board.setMaze(maze);
            }
        });
        JRadioButton mazeButton = new JRadioButton("Maze", false);
        mazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze = new RecursiveBacktrackerMazeCreator(width, height);
                maze.generateMaze();
                pathFinder.setMaze(maze);
                board.setMaze(maze);
            }
        });

        mazeGroup.add(noMazeButton);
        mazeGroup.add(openMazeButton);
        mazeGroup.add(mazeButton);

        mazePanel.add(noMazeButton);
        mazePanel.add(openMazeButton);
        mazePanel.add(mazeButton);

        //Buttons to start algorithm on current maze and to generate new maze
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

        final JTextArea numberOfSteps = new JTextArea("DAT 1337");

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        board.clearBoard();
                        pathFinder.start();
                        numberOfSteps.setText("Number of steps are: " + board.getFade());
                    }
                }).start();
            }
        });
        JButton newMazeButton = new JButton("New Maze");
        newMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                maze.generateMaze();
                board.clearBoard();
            }
        });

        buttonsPanel.add(startButton);
        buttonsPanel.add(newMazeButton);

        //Add to options panel
        optionsPanel.add(algorithmsPanel);
        optionsPanel.add(mazePanel);
        optionsPanel.add(buttonsPanel);
        optionsPanel.add(numberOfSteps);

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, optionsPanel);
        frame.add(splitter);
        frame.setSize(1201, 801);
        frame.setTitle("Hello frameu laburinthu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        splitter.setDividerLocation(0.75d);

    }
}

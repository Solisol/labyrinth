package main.pathfinders;

import main.creator.MazeCreator;
import main.models.Graph;
import main.models.Vertex;

import java.util.*;

public class RandomPathFinder extends PathFinder {

    private MazeCreator maze;

    public RandomPathFinder(MazeCreator maze) {
        this.maze = maze;
    }

    @Override
    public void start() {
        Graph paths = maze.getPaths();
        int goal = maze.getLength() - 1;
        int start = 0;

        List<Vertex> possibleNodes = new ArrayList<Vertex>();
        Set<Vertex> visited = new HashSet<Vertex>();

        possibleNodes.add(paths.getNodeInPosition(start));
        raiseNodeGetsPickeable(start);

        while (!possibleNodes.isEmpty()) {
            Collections.shuffle(possibleNodes);
            Vertex node = possibleNodes.remove(0);
            if (node.getIndex() == goal) {
                System.out.println("IS GOAL");
//                raiseGoalFound(node.getIndex());
                return;
            }
            if (!visited.contains(node)) {
                raiseTakenNode(node.getIndex());
                visited.add(node);
                for (Vertex child : node.getOutNodes()) {
                    if (!visited.contains(child) && !possibleNodes.contains(child)) {
                        raiseNodeGetsPickeable(child.getIndex());
                        possibleNodes.add(paths.getNodeInPosition(child.getIndex()));
                    }
                }
            } else {
                System.out.println("Already been at");
            }
        }

    }
}

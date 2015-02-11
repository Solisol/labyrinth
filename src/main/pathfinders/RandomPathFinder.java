package main.pathfinders;

import main.creator.MazeCreator;
import main.models.Graph;
import main.models.Path;
import main.models.Vertex;

import java.util.*;

public class RandomPathFinder extends PathFinder {

    public RandomPathFinder(MazeCreator maze) {
        this.maze = maze;
    }

    @Override
    public void start() {
        Graph paths = maze.getPaths();

        Random random = new Random();

        Set<Vertex> visited = new HashSet<Vertex>();

        ArrayList<Path> availablePaths = new ArrayList<Path>();

        int start = 0;
        int goal = maze.getLength() - 1;
        Vertex node = paths.getNodeInPosition(start);
        Path path = new Path();
        path.add(node);
        availablePaths.add(path);

        while (!availablePaths.isEmpty()) {
            Path current = availablePaths.get(random.nextInt(availablePaths.size()));
            Vertex last = current.getNodes().getLast();
            if (visited.contains(last)) {
                continue;
            }
            visited.add(last);
            raiseTakenNode(last.getIndex());
            if (last.getIndex() == goal) {
                raiseGoalFound(current.getNodes());
                return;
            }
            for (Vertex neighbour : last.getOutNodes()) {
                if (!visited.contains(neighbour)) {
                    Path additional = current.clone();
                    additional.add(paths.getNodeInPosition(neighbour.getIndex()));
                    availablePaths.add(additional);
                    raiseNodeGetsPickeable(neighbour.getIndex());
                }
            }
        }

    }
}

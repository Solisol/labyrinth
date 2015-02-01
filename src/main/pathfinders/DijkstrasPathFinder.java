package main.pathfinders;

import main.creator.MazeCreator;
import main.models.Graph;
import main.models.Path;
import main.models.Vertex;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 */
public class DijkstrasPathFinder extends PathFinder {

    private MazeCreator maze;

    public DijkstrasPathFinder(MazeCreator maze) {
        this.maze = maze;
    }

    @Override
    public void start() {
        Graph paths = maze.getPaths();

        Set<Vertex> visited = new HashSet<Vertex>();

        PriorityQueue<Path> availablePaths = new PriorityQueue<Path>();

        int start = 0;
        int goal = maze.getLength() - 1;
        Vertex node = paths.getNodeInPosition(start);
        Path path = new Path();
        path.add(node);
        availablePaths.add(path);

        while (!availablePaths.isEmpty()) {
            Path current = availablePaths.poll();
            Vertex last = current.getNodes().getLast();
            if (visited.contains(last)) {
                continue;
            }
            visited.add(last);
            raiseTakenNode(last.getIndex());
            if (last.getIndex() == goal) {
                //TODO return path
                raiseGoalFound(last.getIndex());
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

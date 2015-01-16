package main.creator;

import main.models.Graph;
import main.models.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class OpenMazeCreator extends MazeCreator {

    private final Random random;

    public OpenMazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
        this.length = width * height;
        random = new Random();
    }

    @Override
    public void generateMaze() {
        RecursiveBacktrackerMazeCreator core = new RecursiveBacktrackerMazeCreator(width, height);
        core.generateMaze();
        this.paths = core.getPaths();
        this.floorAndWalls = Graph.getOpposite(paths, width, height);
        int counter = (int) (length * 0.05d);
        while (counter > 0 && floorAndWalls.size() > 0) {
            counter--;
            //pick random node that has neigbours
            Vertex randomNode = getRandomNode();
            ArrayList<Vertex> randomNodeOutNodes = randomNode.getOutNodes();
            Vertex randomNeighbour = randomNodeOutNodes.get(random.nextInt(randomNode.neighbourSize()));
            //update randomNode in paths with randomNeighbour as neighbour
            Vertex node = paths.getNodeInPosition(randomNode.getIndex());
            node.addToNodes(paths.getNodeInPosition(randomNeighbour.getIndex()));
            paths.updateNode(node);
            //remove random neighbour from randomnode in floorsandwalls
            randomNode.removeFromNodes(randomNeighbour);
            floorAndWalls.updateNode(randomNode);
        }
    }

    private Vertex getRandomNode() {
        int randomInt = random.nextInt(floorAndWalls.size());
        Vertex node = floorAndWalls.getNodeInPosition(randomInt);
        while (node.neighbourSize() <= 0 && floorAndWalls.size() > 0) {
            randomInt = random.nextInt(floorAndWalls.size());
            node = floorAndWalls.getNodeInPosition(randomInt);
        }
        return node;
    }
}

import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> nodes;

    public ArrayList<Vertex> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Vertex> nodes) {
        this.nodes = nodes;
    }

    public String toString() {
        String res = "Graph: ";
        for (int i = 0; i < nodes.size(); i++) {
            res = res + i + " " + nodes.get(i).toString() + ", ";
        }
        return res;
    }
}

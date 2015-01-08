import java.util.ArrayList;

public class Vertex {

    private ArrayList<Vertex> outNodes = new ArrayList<Vertex>();

    public ArrayList<Vertex> getOutNodes() {
        return outNodes;
    }

    public void setOutNodes(ArrayList<Vertex> outNodes) {
        this.outNodes = outNodes;
    }

    private int getNumberOfNeighbours() {
        return outNodes.size();
    }

    public String toString() {
        String res = "Vertex: {";
        if (outNodes.size() == 0) {
            return res;
        }
        for (int i = 0; i < outNodes.size(); i++) {
            res = res + i +  " ";
        }
        return res + "}";
    }
}

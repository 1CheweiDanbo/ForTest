package code.graphSearch;

public class GraphEdge {
    private GraphNode nodeLeft;
    private GraphNode nodeRight;

    public GraphEdge(GraphNode nodeLeft, GraphNode nodeRight) {
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public GraphNode getNodeLeft() {
        return nodeLeft;
    }

    public GraphNode getNodeRight() {
        return nodeRight;
    }
}

package code.graphSearch;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    public List<GraphEdge> edgeList = null;
    private String label = "";
    public GraphNode(String label){
        this.label = label;
        if(edgeList != null)
            edgeList = new ArrayList<GraphEdge>();
    }

    public void addEdgeList(GraphEdge edge){
        edgeList.add(edge);
    }

    public String getLabel() {
        return label;
    }
}

package com.example._datastruc2_20102669;
import java.util.ArrayList;
import java.util.List;

public class GraphInfo<T> {
    public T nodeData;
    public List<LinkedGraph<T>> neighbour = new ArrayList<>();

    public GraphInfo(T nodeData) {
        this.nodeData = nodeData;
    }

    public void connectToNodeUndirected(GraphInfo<T> destNode, int cost) {
        neighbour.add(new LinkedGraph<>(destNode, cost));
        destNode.neighbour.add(new LinkedGraph<>(this, cost));
    }
}


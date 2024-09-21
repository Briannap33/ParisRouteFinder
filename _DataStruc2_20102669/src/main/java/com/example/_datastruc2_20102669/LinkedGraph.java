package com.example._datastruc2_20102669;

public class LinkedGraph<T> {
    public GraphInfo<T> destinNode;
    public int weight;

    public LinkedGraph(GraphInfo<T> destinNode, int weight) {
        this.destinNode = destinNode;
        this.weight = weight;
    }
}

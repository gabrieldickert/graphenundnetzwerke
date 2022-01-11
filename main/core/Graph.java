package main.core;

import java.util.ArrayList;

public class Graph {

    public ArrayList<Edge> EdgeList;
    public ArrayList<Node> NodeList;
    public boolean isWeighted;
    public boolean isUndirected;

    public Graph( boolean isWeighted,boolean isUndirected) {

        EdgeList = new ArrayList<Edge>();
        NodeList = new ArrayList<Node>();

        this.isWeighted = isWeighted;
        this.isUndirected = isUndirected;

 
    }
    
}

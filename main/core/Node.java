package main.core;

import java.util.ArrayList;
import java.util.LinkedList;

public class Node {

    public int NodeIndex;
    public ArrayList<Node> NeighbourList;
    public LinkedList<Edge> EdgeList;
    // DFS props
    boolean visited = false;
    int discoverTime = 0;
    int finishedTime = 0;
    Node preNode = null;
    // Init Single-Source props
    public int d = 10000;
    Node preNodeShortestPath = null;

    public Node(int NodeIndex) {
        this.NodeIndex = NodeIndex;
        NeighbourList = new ArrayList<>();
        EdgeList = new LinkedList<>();
    }

}

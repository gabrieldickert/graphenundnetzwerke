package main.core;

import java.util.ArrayList;

public class Node {

    public int NodeIndex;
    public ArrayList<Node> NeighbourList;
    //DFS props
    boolean visited = false;
    int discoverTime = 0;
    int finishedTime = 0;
    Node preNode = null;

    public Node(int NodeIndex) {
        this.NodeIndex = NodeIndex;
        NeighbourList = new ArrayList<>();
    }

}

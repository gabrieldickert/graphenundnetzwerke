package main.core;

import java.util.ArrayList;

public class Node {

    public int NodeIndex;
    public ArrayList<Node> NeighbourList;

    public Node(int NodeIndex) {
        this.NodeIndex = NodeIndex;
        NeighbourList = new ArrayList<>();
    }

}

package main.core;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * simple Node class for a Graph
 */
public class Node {
    /**
     * Index of the given Node
     */
    public int NodeIndex;
    /**
     * List of the Neighbour Nodes.
     */
    public ArrayList<Node> NeighbourList;
    /**
     * List of the Edges containing this Node.
     */
    public LinkedList<Edge> EdgeList;
    // DFS props
    boolean visited = false;
    int discoverTime = 0;
    int finishedTime = 0;
    Node preNode = null;
    // Init Single-Source props
    public int d = 10000;
    Node preNodeShortestPath = null;

    /**
     * Constructor for a Node.
     * 
     * @param NodeIndex Index of the given Node.
     */
    public Node(int NodeIndex) {

        this.NodeIndex = NodeIndex;
        NeighbourList = new ArrayList<>();
        EdgeList = new LinkedList<>();
    }

}

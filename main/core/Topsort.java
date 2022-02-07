package main.core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.*;

/**
 * Simple class for DFS.
 */

public class Topsort {
    /**
     * Total Time of the DFS Process.
     */
    public static int totalTime = 0;
    /**
     * List of the dfs Node
     */
    public static ArrayList<Node> dfsNodes = new ArrayList<>();

    /**
     * Performs the DFS Algorithm.
     * 
     * @param g Graph to perform DFS on.
     * @return List containing Nodes sorted by finished time ASC.
     */
    public static ArrayList<Node> printDFS(Graph g) {

        Stack<Node> nodeStack = new Stack<Node>();

        for (Node n : g.NodeList) {

            if (!n.visited) {
                DFSVist(n, nodeStack);
            }

        }

        while (!nodeStack.empty()) {

            dfsNodes.add(nodeStack.pop());
            // System.out.println( nodeStack.pop().NodeIndex);
        }
        // Reverse due nature of Stack
        Collections.reverse(dfsNodes);
        return dfsNodes;
    }

    /**
     * 
     * @param n         Node which is currently visted.
     * @param nodeStack Stack of the DFS Nodes.
     */
    public static void DFSVist(Node n, Stack<Node> nodeStack) {
        Topsort.totalTime++;

        n.discoverTime = Topsort.totalTime;

        n.visited = true;

        ArrayList<Node> nNeighborList = n.NeighbourList;

        for (Node neighbour : nNeighborList) {
            if (!neighbour.visited) {
                neighbour.preNode = n;
                DFSVist(neighbour, nodeStack);
            }
        }

        Topsort.totalTime++;
        n.finishedTime = Topsort.totalTime;
        nodeStack.push(n);

    }
}

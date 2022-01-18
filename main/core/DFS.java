package main.core;

import java.util.ArrayList;
import java.util.Stack;
import java.util.*;
public class DFS {

    public static int totalTime = 0;
    public static ArrayList<Node> dfsNodes = new ArrayList<>();


    public static ArrayList<Node> printDFS(Graph g) {
 
        Stack<Node> nodeStack = new Stack<Node>();

        for(Node n : g.NodeList) {

            if(!n.visited) {
                DFSVist(n,nodeStack);
            }

        }
        
        while (!nodeStack.empty()) {

            dfsNodes.add(nodeStack.pop());
            // System.out.println( nodeStack.pop().NodeIndex);
        }


        return dfsNodes;
    }

    public static void DFSVist(Node n,Stack<Node> nodeStack) {
        // System.out.println("Knoten: "+ n.NodeIndex);
     
      
        DFS.totalTime++;

        n.discoverTime = DFS.totalTime;
        // System.out.println("Discovertime von Knoten"+n.NodeIndex+" beträgt "+n.discoverTime);

        n.visited = true;
        nodeStack.push(n);

        ArrayList<Node> nNeighborList = n.NeighbourList;

        for(Node neighbour :  nNeighborList) {
            // System.out.println("innerer Knoten: "+ neighbour.NodeIndex + "von Knoten:" + n.NodeIndex);
            if(!neighbour.visited) {
                neighbour.preNode = n;
                DFSVist(neighbour, nodeStack);
            }
        }

        // nodeStack.push(n);
        DFS.totalTime++;
        n.finishedTime = DFS.totalTime;
        // System.out.println("Finish von Knoten"+n.NodeIndex+" beträgt "+n.finishedTime);

    }
}

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
        }


        return dfsNodes;
    }

    public static void DFSVist(Node n,Stack<Node> nodeStack) {

     
      
        DFS.totalTime++;

        n.discoverTime = DFS.totalTime;
        System.out.println("Discovertime von Knoten"+n.NodeIndex+" beträgt "+n.discoverTime);

        n.visited = true;

        ArrayList<Node> nNeighborList = n.NeighbourList;

        for(Node neighbour :  nNeighborList) {

            if(!neighbour.visited) {
                neighbour.preNode = n;
                DFSVist(neighbour, nodeStack);
            }
        }

        nodeStack.push(n);
        DFS.totalTime++;
        n.finishedTime = DFS.totalTime;
        System.out.println("Finish von Knoten"+n.NodeIndex+" beträgt "+n.finishedTime);





    }


}

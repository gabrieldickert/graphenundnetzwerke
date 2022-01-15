package main.core;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    public static int totalTime = 0;
    public static ArrayList<Node> dfsNodes = new ArrayList<>();

    public static ArrayList<Node> printDFS(Graph g) {

        Stack<Node> stack = new Stack<>();

        for (int i = g.NodeList.size() - 1; i >= 0; i--) {
            stack.push(g.NodeList.get(i));
        }

        stack.forEach(k -> {
            System.out.println("" + k.NodeIndex);
        });

        Node firstDFSnode = stack.lastElement();

        while (!stack.empty()) {

            DFSvisit(g, firstDFSnode, stack);
        }

        // DFS.dfsNodes.add(firstDFSnode);

        System.out.println("Total time: " + DFS.totalTime);

        return dfsNodes;
    }

    public static void DFSvisit(Graph g, Node n, Stack<Node> stack) {
        System.out.println(n.NodeIndex + " hat visited: " + n.visited);
        if (!n.visited) {
            n.visited = true;
            DFS.dfsNodes.add(n);
        }

        DFS.totalTime++;
        n.discoverTime = DFS.totalTime;

        ArrayList<Node> nNeighborList = g.adjacentList.get(g.NodeList.get(n.NodeIndex - 1));

        if (nNeighborList.size() == 0) {
            System.out.println(n.NodeIndex + " hat keine Nachbarn");
            System.out.println("gepopptes element: " + stack.pop().NodeIndex);
            if (!stack.empty()) {
                DFSvisit(g, stack.lastElement(), stack);
            }

        }

        for (Node nNeighbor : nNeighborList) {

            if (nNeighbor.visited == false) {

                nNeighbor.preNode = n;

                System.out.println("Node " + n.NodeIndex + " besucht " + nNeighbor.NodeIndex);
                n.discoverTime = DFS.totalTime;

                DFSvisit(g, nNeighbor, stack);
            }

        }

        DFS.totalTime++;

        // System.out.println("Node " + n.NodeIndex + " finished time " +
        // n.finishedTime = DFS.totalTime;

        // stack.pop();

    }
}

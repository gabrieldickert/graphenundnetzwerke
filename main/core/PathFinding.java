package main.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class for PathFinding Algorithm.
 */
public class PathFinding {

    /**
     * Prepare Nodes for Pathfinding Algorithm (e.g Dijkstra)
     * 
     * @param g          Graph
     * @param startPoint Node to start from.
     */
    public static void initSingleSource(Graph g, Node startPoint) {

        // Setting every distance for a Node to infinite aswell as setting prenode to
        // null.
        for (Node n : g.NodeList) {

            n.d = 10000; // "infinite"
            n.preNodeShortestPath = null;

        }
        // Startpoint gets distance 0
        startPoint.d = 0;

    }

    /**
     * Performs relax operation on given Edge.
     * 
     * @param e an weighted Edge.
     */
    public static void relax(Edge e) {

        if (e.b.d > e.a.d + (int) e.weight) {
            e.b.d = e.a.d + (int) e.weight;
            e.b.preNodeShortestPath = e.a;
            e.b.preNode = e.a;
        }
    }

    /**
     * Performs the Dijkstra Pathfinding Algorithm.
     * 
     * @param g Graph to perform Dijkstra onto.
     * @param s Startnode.
     * @return an Hashmap containing Nodes and the given Distance.
     */
    public static HashMap<Node, Integer> performDijkstra(Graph g, Node s) {

        HashMap<Node, Boolean> visitedList = new HashMap<Node, Boolean>();

        HashMap<Node, Integer> returnMap = new HashMap<Node, Integer>();

        initSingleSource(g, s);

        PriorityQueue<Node> waitqueue = new PriorityQueue<Node>(g.NodeList.size(), (a, b) -> a.d - b.d);

        for (Node node : g.NodeList) {
            waitqueue.add(node);
        }

        while (!waitqueue.isEmpty()) {

            Node u = extractMin(waitqueue);

            LinkedList<Edge> edgeList = u.EdgeList;

            returnMap.put(u, u.d);

            for (Edge e : edgeList) {

                relax(e);
            }

            edgeList.removeIf(e -> e.a.equals(u));

        }

        return returnMap;
    }

    /**
     * Extracts Node with lowest weight.
     * 
     * @param q Queue containg current Nodes
     * @return Node with lowest weight.
     */
    public static Node extractMin(Queue<Node> q) {

        Node polledNode = q.poll();

        if (q.size() > 0) {
            PriorityQueue<Node> newWaitQueue = new PriorityQueue<Node>(q.size(), (a, b) -> a.d - b.d);

            for (Node node : q) {
                newWaitQueue.add(node);
            }

            q = newWaitQueue;
        }

        return polledNode;

    }

}

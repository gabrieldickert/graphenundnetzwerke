package main.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class for PathFinding Algorithm.
 */
public class Dijkstra {

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
    public static void relax(Node u, Node v, Edge w) {
        

        if (v.d > u.d + (int) w.weight) {
            v.d = u.d + (int) w.weight;
            v.preNode = u;

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

        HashMap<Node, Integer> returnMap = new HashMap<Node, Integer>();

        initSingleSource(g, s);

        Comparator<Node> comp = (Node n1, Node n2) -> Integer.compare(n1.d, n2.d);

        PriorityQueue<Node> waitqueue = new PriorityQueue<Node>(g.NodeList.size(), comp);

        for (Node node : g.NodeList) {

            waitqueue.add(node);
        }

        while (!waitqueue.isEmpty()) {

            Node u = extractMin(waitqueue, g);

            for (Node v : u.NeighbourList) {

                for (Edge edge : u.EdgeList) {

                    //directed case
                    if(edge.a.NodeIndex == u.NodeIndex && edge.b.NodeIndex == v.NodeIndex && !g.isUndirected) {

                        relax(u, v, edge);
                    }
                    //undirected 
                    else if(edge.a.NodeIndex == u.NodeIndex && edge.b.NodeIndex == v.NodeIndex && g.isUndirected || edge.b.NodeIndex == u.NodeIndex && edge.a.NodeIndex == v.NodeIndex && g.isUndirected ) {

                        relax(u, v, edge);
                    }

                }

            }

            if (waitqueue.size() > 0) {
                PriorityQueue<Node> newWaitQueue = new PriorityQueue<Node>(waitqueue.size(), comp);

                g.NodeList.sort(comp);
                for (Node node : g.NodeList) {

                    if (waitqueue.contains(node)) {
                        newWaitQueue.add(node);
                    }

                }

                waitqueue = newWaitQueue;
            }

            returnMap.put(u, u.d);

        }

        return returnMap;
    }

    /**
     * Extracts Node with lowest weight.
     * 
     * @param q Queue containg current Nodes
     * @return Node with lowest weight.
     */
    public static Node extractMin(Queue<Node> q, Graph g) {

        Node polledNode = q.poll();

        return polledNode;

    }

}

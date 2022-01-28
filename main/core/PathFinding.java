package main.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinding {

    public static void initSingleSource(Graph g, Node startPoint) {

        for (Node n : g.NodeList) {

            n.d = 10000;
            n.preNodeShortestPath = null;

        }
        // Startpoint gets distance 0
        startPoint.d = 0;

    }

    public static void relax(Edge e) {

        if (e.b.d > e.a.d + (int) e.weight) {
            e.b.d = e.a.d + (int) e.weight;
            e.b.preNodeShortestPath = e.a;
        }
    }

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
                System.out.println("HIIIEEER " + e.a + " " + e.b.NodeIndex);
                relax(e);
            }

            edgeList.removeIf(e -> e.a.equals(u));

        }

        return returnMap;
    }

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

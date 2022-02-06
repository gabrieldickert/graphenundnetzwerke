package main.core;

import java.util.Comparator;
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
       // System.out.println("KANTE VON "+e.a.NodeIndex+" zu "+e.b.NodeIndex);
        if (e.b.d > e.a.d + (int) e.weight) {
            e.b.d = e.a.d + (int) e.weight;
            e.b.preNodeShortestPath = e.a;
            e.b.preNode = e.a;
          //  System.out.println("DISTANZ ANPASSEN von "+e.b.NodeIndex);
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
         
        for(Node n: g.NodeList) {

           n.EdgeList.removeIf(e-> e.b.equals(n));

        }

        HashMap<Node, Boolean> visitedList = new HashMap<Node, Boolean>();

        HashMap<Node, Integer> returnMap = new HashMap<Node, Integer>();

        initSingleSource(g, s);

        Comparator<Node> comp = (Node n1, Node n2) -> Integer.compare(n1.d, n2.d);

        PriorityQueue<Node> waitqueue = new PriorityQueue<Node>(g.NodeList.size(),comp);

        for (Node node : g.NodeList) {

            waitqueue.add(node);
        }

       /* for(Node node : waitqueue) {

            System.out.println("QUEUE REIHENFOLGE INIT:"+node.NodeIndex);
        }*/
        
        while (!waitqueue.isEmpty()) {

            Node u = extractMin(waitqueue,g);

           // System.out.println("Arbeite auf Knoten "+u.NodeIndex);

            LinkedList<Edge> edgeList = u.EdgeList;

            //System.out.println("Länge Kantenliste von Knoten"+u.NodeIndex+":"+edgeList.size());
     
            for (Edge e : edgeList) {

                relax(e);
            }

            edgeList.removeIf(e -> e.a.equals(u));

      /*      System.out.println("--------NACH RELAX------------");
        for(Node n: waitqueue) {

            System.out.println("NODE:"+n.NodeIndex+" mit DISTANZ"+n.d);
        }

       // System.out.print("---------NEUE QUEUE---------");*/

        if(waitqueue.size() > 0) {
            PriorityQueue<Node> newWaitQueue = new PriorityQueue<Node>(waitqueue.size(),comp);

            g.NodeList.sort(comp); 
            for (Node node : g.NodeList) {
    
                if(waitqueue.contains(node)) {  
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
    public static Node extractMin(Queue<Node> q,Graph g) {

       // System.out.println("--------VOR POLL------------");
       /* for(Node n: q) {

            System.out.println("NODE:"+n.NodeIndex+" mit DISTANZ"+n.d);
        }*/

        Node polledNode = q.poll();
      /*  System.out.println("--------NACH POLL------------");
        for(Node n: q) {

            System.out.println("NODE:"+n.NodeIndex+" mit DISTANZ"+n.d);
        }*/

        /*
        if(q.size() > 0) {
            Comparator<Node> comp = (Node n1, Node n2) -> Integer.compare(n1.d, n2.d);
            PriorityQueue<Node> newWaitQueue = new PriorityQueue<Node>(q.size(),comp);

            for (Node node : g.NodeList) {

                if(q.contains(node)) {

                    System.out.println("QUEUE hat NODE "+node.NodeIndex);
                
                    newWaitQueue.add(node);
                }
            
            }

            q = newWaitQueue;
        }*/
   

        System.out.println("Node wurde von Queue entfernt:"+polledNode.NodeIndex);

        return polledNode;

    }

}

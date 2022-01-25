package main.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinding {


    public static void initSingleSource(Graph g,Node startPoint) {

        for(Node n: g.NodeList) {

            n.d = Integer.MAX_VALUE;
            n.preNodeShortestPath = null;

        }
        //Startpoint gets distance 0
        startPoint.d = 0;


    }


    public static void Relax(Node u,Node v,float w) {


    }


    public static HashMap<Node,Integer> performDijkstra(Graph g, Node s) {

        HashMap<Node,Boolean> visitedList = new HashMap<Node,Boolean>();

        HashMap<Node,Integer> returnMap = new HashMap<Node,Integer>();

        initSingleSource(g,s);

        Queue<Node> waitqueue = new LinkedList<Node>(g.NodeList);

        while(!waitqueue.isEmpty()) {
            
            LinkedList<Edge> edgeList = s.EdgeList;

            for(Edge e : edgeList) {

                

            }

        }

        return returnMap;
    }

 /*   public static Edge extractMin(Graph g,Node a) {

    }*/


        

}

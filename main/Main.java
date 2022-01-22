package main;

import java.util.ArrayList;
import java.util.LinkedList;

import main.core.*;

public class Main {

    public static void main(String[] args) {

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/lordgraph.txt");

        GraphExporter.exportGraphToDOT(g, "test.dot");

        LinkedList<Edge> BAUM =  Kruskal.performKruskal(g);


        for(Edge e : BAUM) {

            System.out.println(""+e.a.NodeIndex+"--"+e.b.NodeIndex+" -> "+e.weight);
        }



        
        //GraphExporter.exportGraphToDOT(g, "test.dot");

        /*ArrayList<Node> dfsList = DFS.printDFS(g);
        System.out.print("TopSort: ");
        for (Node n : dfsList) {
            
            System.out.print(n.NodeIndex + " ");
        }*/

        // System.out.println(dfsList);
        // g = P.parseGraphFromInput("input/k3_3.txt");
        // g = P.parseGraphFromInput("input/petersen.txt");
        /*
         * g = P.parseGraphFromInput("input/spider.txt");
         * g = P.parseGraphFromInput("input/primkruskal.txt");
         */

    }

}

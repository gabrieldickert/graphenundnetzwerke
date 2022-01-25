package main;

import java.util.ArrayList;
import java.util.LinkedList;

import main.core.*;

public class Main {

    public static void main(String[] args) {

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/lordgraph.txt");
 


        for(int i = 0; i <1;i++) {

            for(int j = 0; j < g.NodeList.get(i).EdgeList.size();j++) {

                System.out.println("Knoten "+g.NodeList.get(i).NodeIndex+" hat Kante von "
                +g.NodeList.get(i).EdgeList.get(j).a.NodeIndex+" zu " 
                +g.NodeList.get(i).EdgeList.get(j).b.NodeIndex
                +" mit weight: "+g.NodeList.get(i).EdgeList.get(j).weight);
            }

   
        }

        GraphExporter.exportGraphToDOT(g, "test.dot");

      //  PathFinding.performDijkstra(g, g.NodeList.get(0));

        /*
        LinkedList<Edge> BAUM =  Kruskal.performKruskal(g);


        for(Edge e : BAUM) {

            System.out.println(""+e.a.NodeIndex+"--"+e.b.NodeIndex+" -> "+e.weight);
        }
*/


        
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

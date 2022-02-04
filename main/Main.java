package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import main.core.*;

public class Main {

    public static void performTopsort(String filename) {

        System.out.println("------------BEGIN BENCHMARK FOR TOPSORT------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt");

        ArrayList<Node> dfsList = DFS.printDFS(g);

        for(int i = 0; i < dfsList.size();i++) {

            Node n = dfsList.get(i);
            System.out.println(n.NodeIndex+" (Discovertime:"+n.discoverTime+" / Finishtime:"+n.finishedTime+")");
        }


        GraphExporter.exportGraphToDOT(g, filename + ".dot");

        System.out.println("\n------------BENCHMARK OVER TOPSORT------------");
    }

    public static void performKruskal(String filename) {

        System.out.println("------------BEGIN BENCHMARK FOR KRUSKAL------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt");

        LinkedList<Edge> tree = Kruskal.performKruskal(g);

        for (Edge e : tree) {

            System.out.println("" + e.a.NodeIndex + "--" + e.b.NodeIndex + " -> " + e.weight);
    
        }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");
        //Setting tree as Edgelist for better output
        g.EdgeList = new ArrayList<Edge>(tree);
        
        GraphExporter.exportGraphToDOT(g, filename + "_postkruskal.dot");

        System.out.println("\n------------BENCHMARK OVER KRUSKAL------------");
    }

    public static void performDijkstra(String filename,int nodeIndex) {
        System.out.println("------------BEGIN BENCHMARK FOR DIJKSTRA------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt");

         HashMap<Node, Integer> dijkstraResult = PathFinding.performDijkstra(g,g.NodeList.get(nodeIndex));
         for (Node n : dijkstraResult.keySet()) {
         System.out.println("Knoten: "+n.NodeIndex +" Distanz: "+ n.d+" vorheriger Knoten: "+(n.preNode == null ? "/" : n.preNode.NodeIndex));
         }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");

        System.out.println("\n------------BENCHMARK OVER DIJKSTRA------------");
    }

    public static void main(String[] args) {

        System.out.println(
                "Bitte Algorithmus auswählen: 1.Topsort 2.Kruskal 3.Dijkstra 4.Program beenden\nBitte Zahl eingeben");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println("Bitte Zahl eingeben:");
            int input = in.nextInt();
            if (input == 4) {
                System.out.println("Meddl off");
                break;
            }
            System.out.print("Name der Benchmark File eingeben (aus Input Ordern ohne .txt Endung):\n");
            String benchmarkName = in.next();
            switch (input) {
                case 1:
                    performTopsort(benchmarkName);
                    break;
                case 2:
                    performKruskal(benchmarkName);
                    break;
                  case 3:
                    performDijkstra(benchmarkName,0);
                    break;

            }

        }

    }

}

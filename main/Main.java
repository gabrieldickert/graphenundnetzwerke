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

        System.out.println("\n------------BENCHMARK OVER KRUSKAL------------");
    }

    public static void performDijkstra(String filename,int nodeIndex) {
        System.out.println("------------BEGIN BENCHMARK FOR DIJKSTRA------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt");

         HashMap<Node, Integer> dijkstraResult = PathFinding.performDijkstra(g,g.NodeList.get(nodeIndex));
         for (Node n : dijkstraResult.keySet()) {
         System.out.println(n.NodeIndex + " " + n.d);
         }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");

        System.out.println("\n------------BENCHMARK OVER DIJKSTRA------------");
    }

    public static void main(String[] args) {

        System.out.println(
                "Bitte Algorithmus auswählen: 1.Topsort 2.Kruskal 3.Djikstra 4.Program beenden\nBitte Zahl eingeben");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println("Bitte Zahl eingeben:");
            int input = in.nextInt();
            if (input == 4) {
                break;
            }
            System.out.print("Name der Benchmark File eingeben (aus Input ordern ohne .txt Endung):\n");
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

        // Parser P = new Parser();

        // Graph g = P.parseGraphFromInput("input/dijkstra.txt");

        /*
         * for (int i = 0; i < 1; i++) {
         * 
         * for (int j = 0; j < g.NodeList.get(i).EdgeList.size(); j++) {
         * 
         * System.out.println("Knoten " + g.NodeList.get(i).NodeIndex +
         * " hat Kante von "
         * + g.NodeList.get(i).EdgeList.get(j).a.NodeIndex + " zu "
         * + g.NodeList.get(i).EdgeList.get(j).b.NodeIndex
         * + " mit weight: " + g.NodeList.get(i).EdgeList.get(j).weight);
         * }
         * 
         * }
         */

        /*
         * GraphExporter.exportGraphToDOT(g, "test.dot");
         * 
         * HashMap<Node, Integer> dijkstraResult = PathFinding.performDijkstra(g,
         * g.NodeList.get(0));
         * 
         * for (Node n : dijkstraResult.keySet()) {
         * System.out.println(n.NodeIndex + " " + n.d);
         * }
         */

        /*
         * LinkedList<Edge> BAUM = Kruskal.performKruskal(g);
         * 
         * 
         * for(Edge e : BAUM) {
         * 
         * System.out.println(""+e.a.NodeIndex+"--"+e.b.NodeIndex+" -> "+e.weight);
         * }
         */

        // GraphExporter.exportGraphToDOT(g, "test.dot");

        /*
         * ArrayList<Node> dfsList = DFS.printDFS(g);
         * System.out.print("TopSort: ");
         * for (Node n : dfsList) {
         * 
         * System.out.print(n.NodeIndex + " ");
         * }
         */

        // System.out.println(dfsList);
        // g = P.parseGraphFromInput("input/k3_3.txt");
        // g = P.parseGraphFromInput("input/petersen.txt");
        /*
         * g = P.parseGraphFromInput("input/spider.txt");
         * g = P.parseGraphFromInput("input/primkruskal.txt");
         */

    }

}

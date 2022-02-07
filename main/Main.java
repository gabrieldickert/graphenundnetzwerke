package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import main.core.*;

/**
 * Simple Program for the Course "Graphen und Netzwerke" Wintersemester
 * 2021/2022 made by Gabriel Dickert, Julian Schuster and Steven Hufnagel.
 * This Program performs Topsort, Kruskal and Dijkstra Algorithm based on the
 * benchmark Graphs provided by Jörg Kreiker.
 * To use this Program, follow the Instructions on the Commandline.
 */
public class Main {

    /**
     * Performs the Topsort Algorithm.
     * 
     * @param filename File containing the Graph to perform Topsport onto.
     */
    public static void performTopsort(String filename,boolean isDirected) {

        System.out.println("------------BEGIN BENCHMARK FOR TOPSORT------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt",isDirected);

        g.isUndirected = !isDirected;

        ArrayList<Node> dfsList = Topsort.printDFS(g);

        for (int i = 0; i < dfsList.size(); i++) {

            Node n = dfsList.get(i);
            System.out.println(
                    n.NodeIndex + " (Discovertime:" + n.discoverTime + " / Finishtime:" + n.finishedTime + ")");
        }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");

        // Clear static dfs vals for multiple Usage
        Topsort.totalTime = 0;
        Topsort.dfsNodes = new ArrayList<>();

        System.out.println("\n------------BENCHMARK OVER TOPSORT------------");
    }

    /**
     * Performs the Kruskal Algorithm.
     * 
     * @param filename File containing the Graph to perform Kruskal onto.
     */
    public static void performKruskal(String filename,boolean isDirected) {

        System.out.println("------------BEGIN BENCHMARK FOR KRUSKAL------------");

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/" + filename + ".txt", isDirected);

        g.isUndirected =!isDirected;

        LinkedList<Edge> tree = Kruskal.performKruskal(g);

        for (Edge e : tree) {

            System.out.println("" + e.a.NodeIndex + "--" + e.b.NodeIndex + " -> " + e.weight);

        }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");
        // Setting tree as Edgelist for better output
        g.EdgeList = new ArrayList<Edge>(tree);

        GraphExporter.exportGraphToDOT(g, filename + "_postkruskal.dot");

        System.out.println("\n------------BENCHMARK OVER KRUSKAL------------");
    }

    /**
     * Performs the Dijkstra Algorithm.
     * 
     * @param filename  File containing the Graph to perform Dijkstra onto.
     * @param nodeIndex Node to start from (0 per Default but can be changed).
     */
    public static void performDijkstra(String filename, int nodeIndex,boolean isDirected) {
        System.out.println("------------BEGIN BENCHMARK FOR DIJKSTRA------------");

        Parser P = new Parser();
        // false heißt ungerichtet
        Graph g = P.parseGraphFromInput("input/" + filename + ".txt", isDirected);
        // true heißt ungerichtet
        g.isUndirected = !isDirected;

        LinkedList<Node> outputList = new LinkedList<>();
        HashMap<Node, Integer> dijkstraResult = Dijkstra.performDijkstra(g, g.NodeList.get(nodeIndex));
        for (Node n : dijkstraResult.keySet()) {
            outputList.add(n);
        }

        Comparator<Node> comp = (Node n1, Node n2) -> Integer.compare(n1.NodeIndex, n2.NodeIndex);

        outputList.sort(comp);

        for (Node n : outputList) {
            System.out.println("Node: " + n.NodeIndex + " Distance: " + n.d + " previousNode: "
                    + (n.preNode == null ? "/" : n.preNode.NodeIndex));
        }

        GraphExporter.exportGraphToDOT(g, filename + ".dot");

        System.out.println("\n------------BENCHMARK OVER DIJKSTRA------------");
    }

    public static void main(String[] args) {

        System.out.println(
                "Select Algorithm: 1.Topsort 2.Kruskal 3.Dijkstra 4.Exit Program \nPlease enter a number from 1-4");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            System.out.println("Please enter a number from 1-4:");
            int input = in.nextInt();
            if (input == 4) {
                System.out.println("Bye");
                break;
            }
            System.out.println("is the Graph directed?\n type 1 for yes, 2 for no or 3 for default implmeneted behaviour");
            int directed = in.nextInt();
            //Default behaviour is graph is directed
            boolean isDirected = true;
            switch(directed) {
                case 2: 
                isDirected = false;
                break;
            }

            System.out.println("Name of the Benchmark File (from the input directory without .txt ending):\n");
            String benchmarkName = in.next();
            switch (input) {
                case 1:
                    performTopsort(benchmarkName,isDirected);
                    break;
                case 2:
                    performKruskal(benchmarkName,isDirected);
                    break;
                case 3:
                    performDijkstra(benchmarkName, 0,isDirected);
                    break;

            }

        }

    }

}

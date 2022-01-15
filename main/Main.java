package main;

import main.core.*;

public class Main {

    public static void main(String[] args) {

        Parser P = new Parser();

        Graph g = P.parseGraphFromInput("input/k4.txt");

        GraphExporter.exportGraphToDOT(g, "k4.dot");
        // g = P.parseGraphFromInput("input/k3_3.txt");
        // g = P.parseGraphFromInput("input/petersen.txt");
        /*
         * g = P.parseGraphFromInput("input/spider.txt");
         * g = P.parseGraphFromInput("input/primkruskal.txt");
         */

    }

}

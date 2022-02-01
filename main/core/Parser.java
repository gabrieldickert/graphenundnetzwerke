package main.core;

import java.io.*;
import java.util.Scanner;

/**
 * Simple Parsing class for reading the benchmark Graphs and resolve them to the
 * given graph structure.
 */

public class Parser {

    /**
     * Default Constructor for Parser
     */
    public Parser() {

    }

    /**
     * Parses a Graph from the given file.
     * 
     * @param inputFile filepath to the given graphfile.
     * @return an instantiated graph containg the informations about the given
     *         graphfile.
     */
    public Graph parseGraphFromInput(String inputFile) {

        // We dont know if graph is weighted or directed in advance, we have to tailor
        // it to our usecase
        Graph g = new Graph(false, false);

        try {
            // Create Graphfile
            File graphFile = new File(inputFile);
            // Create Scanner
            Scanner graphFileReader = new Scanner(graphFile);
            // Init 0 Nodes
            int graphNodeCount = 0;
            // Counter for readed lines
            int lineCounter = 0;
            // Reading every line from the textfile containg the graph
            while (graphFileReader.hasNextLine()) {
                // Current data of the readed Line
                String data = graphFileReader.nextLine();
                // First line represents the Total Node Count
                if (lineCounter == 0) {
                    // Sets the total Nodecount for this graph.
                    graphNodeCount = Integer.parseInt(data);
                } else {
                    // Splitting Entry of line(row). Seperation of line done by Whitespace
                    String[] rowData = data.split("\\s+");
                    //a line at index > 0 always contains atleast two nodes. Therefore two Nodes are init with null in advance.
                    Node rowNode = null;
                    Node rowNode2 = null;
                    //a line can be "simply" an unweighted edge therefore the length of the line is only 2. 
                    if (rowData.length == 2) {
                        //Parsing Nodeinicies from the line
                        rowNode = new Node(Integer.parseInt(rowData[0]));
                        rowNode2 = new Node(Integer.parseInt(rowData[1]));

                    } 
                    //if the length of the current line is 3, the second Nodeindex is at index 2 of the splitted array.
                    else if (rowData.length == 3) {

                        rowNode = new Node(Integer.parseInt(rowData[0]));
                        rowNode2 = new Node(Integer.parseInt(rowData[2]));
                    }
                    //Flags for checking if Node is already part of the Nodelist of the internal graph representation.(Ensures avoiding duplicates with different mem adresses!)
                    boolean hasRowNode = false;
                    boolean hasRowNode2 = false;

                    //Checking Nodelist of the Graph for current row Nodes.
                    for (int i = 0; i < g.NodeList.size(); i++) {

                        if (g.NodeList.get(i).NodeIndex == rowNode.NodeIndex) {

                            hasRowNode = true;
                        }

                        if (g.NodeList.get(i).NodeIndex == rowNode2.NodeIndex) {

                            hasRowNode2 = true;
                        }
                        //Both rownodes have been found => Loop can be stopped here.
                        if (hasRowNode && hasRowNode2) {
                            break;
                        }

                    }
                    //If first Node isn´t already in the Nodelist of the Graph we can add it.
                    if (!hasRowNode) {
                        g.NodeList.add(rowNode);
                    }
                    //If second Node isn´t already in the Nodelist of the Graph we can add it.
                    if (!hasRowNode2) {

                        g.NodeList.add(rowNode2);
                    }

                    // Adding Neighbours of the current rownodes.
                    for (int i = 0; i < g.NodeList.size(); i++) {

                        if (rowNode.NodeIndex == g.NodeList.get(i).NodeIndex) {
                            // Vorrausgesetzt keine parallelkante und selbstkante
                            for (int j = 0; j < g.NodeList.size(); j++) {
                                if (g.NodeList.get(j).NodeIndex == rowNode2.NodeIndex) {
                                    g.NodeList.get(i).NeighbourList.add(g.NodeList.get(j));
                                    break;
                                }
                            }

                        }

                    }
                    //Every Line is basically an Edge containg two Nodes. Depending of the length of the line the edge is unweighted or weighted.
                    Edge e = null;
                    //unweighted Edge
                    if (rowData.length == 2) {
                        //Init with null since we only need the ref
                        Node n1 = null;
                        Node n2 = null;
                        //Finding reference of the Node Objects in the Nodelist of the graph.
                        for (Node n: g.NodeList) {

                            if (n.NodeIndex == rowNode.NodeIndex) {
                                n1 = n;

                            } else if (n.NodeIndex == rowNode2.NodeIndex) {

                                n2 = n;
                            }
                        }
                        //Creating a new Edge with the founded references of the Nodes.
                        if (n1 != null && n2 != null) {
                            e = new Edge(n1, n2);
                            n1.EdgeList.add(e);
                            n2.EdgeList.add(e);
                        }

                    } 
                    //TODO REVISTED LOGIC!!!. weighted Edge.
                    else if (rowData.length == 3) {

                        e = new Edge(rowNode, rowNode2, Float.parseFloat(rowData[1]));

                    }
                    //Adding Edge first to an Parsing Edge List.
                    g.ParsingEdgeList.add(e);

                }
                //Increment counter of line
                lineCounter++;
            }
            //System.out.println("Anzahl Kanten:" + g.EdgeList.size());
            //System.out.println("Anzahl Knoten:" + g.NodeList.size());
            //Sort Nodelist by Nodeindex ASC
            g.sortNodeList();
            //MAYBE NOT needed if doing same as unweighted edge???
            g.extractRealEdges();

        } catch (FileNotFoundException e) {
            System.out.println("Could not parse given Graph");
            e.printStackTrace();
        }

        // Returns a graph if parsing passed successfully.
        return g;
    }

}
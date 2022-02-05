package main.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * Class for representing a simple graph.
 */

public class Graph {
    // List containg all Edges of the Graph.
    public ArrayList<Edge> EdgeList;
    // List containg all Nodes of the Graph.
    public ArrayList<Node> NodeList;
    // Flag for Weighted Prop.
    public boolean isWeighted;
    // Flag for directed Prop.
    public boolean isUndirected;
    // 2D-Array containing the incident Matrix Representation.
    public int[][] incidentMat;
    // 2D-Array containing the adjacent Matrix Representation.
    public int[][] adjacentMat;
    // List Containg the AdjacentList of thsi Graph.
    public LinkedHashMap<Node, ArrayList<Node>> adjacentList;

    /**
     * Constructor for a simple Graph
     * 
     * @param isWeighted   set weighted property.
     * @param isUndirected set undirected property.
     */
    public Graph(boolean isWeighted, boolean isUndirected) {

        EdgeList = new ArrayList<Edge>();
        NodeList = new ArrayList<Node>();

        this.isWeighted = isWeighted;
        this.isUndirected = isUndirected;

    }

    /**
     * Returns the Index of a specific Node in the Nodelist of the Graph.
     * 
     * @param NodeIndex Nodeindex to find .
     * @return Index of the coressponding Node in the Nodelist.
     */
    public int getIndexOfNode(int NodeIndex) {

        for (int i = 0; i < NodeList.size(); i++) {

            if (NodeList.get(i).NodeIndex == NodeIndex) {
                return i;
            }
        }

        return -1;

    }

    /**
     * Prints the Incidient Matrix of the Graph.
     */
    public void printIncidentMatrix() {
        // Init Size of IncidentMat
        incidentMat = new int[NodeList.size()][EdgeList.size()];

        for (int i = 0; i < NodeList.size(); i++) {

            Node n = NodeList.get(i);
            for (int j = 0; j < EdgeList.size(); j++) {

                Edge e = EdgeList.get(j);
                if (e.a.NodeIndex == n.NodeIndex || e.b.NodeIndex == n.NodeIndex) {

                    incidentMat[i][j] = 1;
                }

                else {
                    incidentMat[i][j] = 0;
                }
            }
        }

        printMatrix(incidentMat);

    }

    /**
     * Prints Adjacent Matrix of the Graph.
     */
    public void printAdjacentMatrix() {

        adjacentMat = new int[NodeList.size()][NodeList.size()];

        for (int i = 0; i < NodeList.size(); i++) {
            Node n = NodeList.get(i);

            for (int j = 0; j < NodeList.size(); j++) {
                Node n2 = NodeList.get(j);

                boolean isOne = false;
                for (int k = 0; k < n2.NeighbourList.size(); k++) {
                    if (n.NodeIndex == n2.NeighbourList.get(k).NodeIndex) {
                        isOne = true;
                    }
                }

                if (isOne) {
                    adjacentMat[i][j] = 1;
                } else {
                    adjacentMat[i][j] = 0;
                }
            }
        }

        printMatrix(adjacentMat);

    }

    /**
     * Fills the AdjacentList
     */
    public void fillAdjacentList() {

        adjacentList = new LinkedHashMap<>();

        for (Node n : NodeList) {

            adjacentList.put(n, n.NeighbourList);
        }

        adjacentList.entrySet().forEach(entry -> {

            // System.out.println("DER Nodeindex" + entry.getKey().NodeIndex);

            for (int i = 0; i < entry.getValue().size(); i++) {

                Node lol = (Node) entry.getValue().get(i);
                // System.out.println("ENTHÄHLT KNOTEN" + lol.NodeIndex);

            }
        });
    }

    /**
     * Prints a 2D-Matrix
     * 
     * @param m M 2D-Array to print.
     */
    public void printMatrix(int[][] m) {
        try {
            int rows = m.length;
            int columns = m[0].length;
            String str = "|\t";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += m[i][j] + "\t";
                }
                System.out.println(str + "|");
                str = "|\t";
            }
        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
    }

    /**
     * Sorts the Nodelist of the Graph by Nodeindex ASC.
     */
    public void sortNodeList() {

        Comparator<Node> compareById = (Node o1, Node o2) -> Integer.compare(o1.NodeIndex, o2.NodeIndex);
        Collections.sort(NodeList, compareById);
    }

    /**
     * Sorts the Edgelist of the Graph by Edgeweight ASC.
     */
    public void sortEdgeList() {

        Comparator<Edge> compareByWeight = (Edge e1, Edge e2) -> Float.compare(e1.weight, e2.weight);
        Collections.sort(EdgeList, compareByWeight);
    }

}

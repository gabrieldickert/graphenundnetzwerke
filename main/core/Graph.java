package main.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class Graph {

    public ArrayList<Edge> EdgeList;
    public LinkedList<Edge> ParsingEdgeList;
    public ArrayList<Node> NodeList;
    public boolean isWeighted;
    public boolean isUndirected;
    public int[][] incidentMat;
    public int[][] adjacentMat;
    public LinkedHashMap<Node, ArrayList<Node>> adjacentList;

    public Graph(boolean isWeighted, boolean isUndirected) {

        ParsingEdgeList = new LinkedList<Edge>();
        EdgeList = new ArrayList<Edge>();
        NodeList = new ArrayList<Node>();

        this.isWeighted = isWeighted;
        this.isUndirected = isUndirected;

    }
    /*LEGACY
    public void extractRealEdges() {

        for(Edge e : ParsingEdgeList) {
            int nodeIndexA = getIndexOfNode(e.a.NodeIndex);
            int nodeIndexB = getIndexOfNode(e.b.NodeIndex);
            if( nodeIndexA!=-1 && nodeIndexB!=-1) {

                Node a  = NodeList.get(nodeIndexA);
                Node b =  NodeList.get(nodeIndexB);
                Edge realEdge = new Edge(a,b,e.weight);

                a.EdgeList.add(realEdge);
                b.EdgeList.add(realEdge);
                EdgeList.add(e);

            }

        }

    }*/

    public int getIndexOfNode(int NodeIndex) {

        for(int i = 0; i < NodeList.size();i++) {

            if(NodeList.get(i).NodeIndex == NodeIndex) {
                return i;
            }
        }

        return -1;
        
    
    }

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

    public void sortNodeList() {
        // Aufsteigende Sortierung der NodeList nach nodeIndex
        Comparator<Node> compareById = (Node o1, Node o2) -> Integer.compare(o1.NodeIndex, o2.NodeIndex);
        Collections.sort(NodeList, compareById);
    }

    public void sortEdgeList() {

        Comparator<Edge> compareByWeight = (Edge e1, Edge e2) -> Float.compare(e1.weight,e2.weight);
        Collections.sort(EdgeList, compareByWeight);
    }

}

package main.core;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

public class Graph {

    public ArrayList<Edge> EdgeList;
    public ArrayList<Node> NodeList;
    public boolean isWeighted;
    public boolean isUndirected;
    public int [][] incidentMat;

    public Graph( boolean isWeighted,boolean isUndirected) {

        EdgeList = new ArrayList<Edge>();
        NodeList = new ArrayList<Node>();

        this.isWeighted = isWeighted;
        this.isUndirected = isUndirected;

 
    }

    public void printIncidentMatrix() {
        //Init Size of IncidentMat
        incidentMat = new int[NodeList.size()][EdgeList.size()];

        for(int i = 0; i < NodeList.size();i++) {

            Node n = NodeList.get(i);
            for(int j = 0; j < EdgeList.size();j++) {
                
                Edge e = EdgeList.get(j);
                if(e.a.NodeIndex == n.NodeIndex || e.b.NodeIndex == n.NodeIndex) {

                    incidentMat[i][j] = 1;
                }

                else {
                    incidentMat[i][j] = 0;
                }
            }
        }

        printMatrix(incidentMat);

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
    
}

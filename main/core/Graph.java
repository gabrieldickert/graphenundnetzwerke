package main.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.w3c.dom.NodeList;

public class Graph {

    public ArrayList<Edge> EdgeList;
    public ArrayList<Node> NodeList;
    public boolean isWeighted;
    public boolean isUndirected;
    public int [][] incidentMat;
    public int [][] adjacentMat;

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

    public void printAdjacentMatrix(){

        adjacentMat = new int[NodeList.size()][NodeList.size()];

        for(int i = 0; i < NodeList.size(); i++){
            Node n = NodeList.get(i);

            for(int j = 0; j < NodeList.size(); j++){
                Node n2 = NodeList.get(j);
                
                boolean isOne = false;
                for(int k = 0; k < n2.NeighbourList.size(); k++){
                    if(n.NodeIndex == n2.NeighbourList.get(k).NodeIndex){
                        isOne = true;
                    }
                }

                if(isOne){
                    adjacentMat[i][j] = 1;
                } else {
                    adjacentMat[i][j] = 0;
                }
            }
        }

        printMatrix(adjacentMat);

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

    public void sortNodeList(){
        //Aufsteigende Sortierung der NodeList nach nodeIndex
        Comparator<Node> compareById = (Node o1, Node o2) ->
        Integer.compare(o1.NodeIndex, o2.NodeIndex);
        Collections.sort(NodeList, compareById);
    }
    
}

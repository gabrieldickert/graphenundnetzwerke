package main.core;

import java.io.*;
import java.util.Scanner;

public class Parser {

    public Parser() {

    }

    public Graph parseGraphFromInput(String inputFile) {

        System.out.print("Input von File" + inputFile + "\n");
        // We dont know if graph is weighted or directed in advance
        Graph g = new Graph(false, false);

        try {
            File myObj = new File(inputFile);
            Scanner myReader = new Scanner(myObj);
            int graphNodeCount = 0;
            int lineCounter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // First line represents the Total Node Count
                if (lineCounter == 0) {

                    graphNodeCount = Integer.parseInt(data);
                } else {
                    // Connection of Nodes. Seperation of Nodes done by Whitespace e.g 1 2
                    String[] rowData = data.split("\\s+");

                    // 1 -- 2 , 2 is neighbour of 1 but not other way around

                    Node rowNode = null;
                    Node rowNode2 = null;

                    if (rowData.length == 2) { 
                        rowNode = new Node(Integer.parseInt( rowData[0]));
                        rowNode2 = new Node(Integer.parseInt( rowData[1]));

                    }

                    else if (rowData.length == 3) {

                        rowNode = new Node(Integer.parseInt(rowData[0]));
                        rowNode2 = new Node(Integer.parseInt(rowData[2]));
                    }
                
                        boolean hasRowNode = false;
                        boolean hasRowNode2 = false;
                        for (int i = 0; i < g.NodeList.size(); i++) {

                            if (g.NodeList.get(i).NodeIndex == rowNode.NodeIndex) {

                                hasRowNode = true;
                            }

                            if (g.NodeList.get(i).NodeIndex == rowNode2.NodeIndex) {

                                hasRowNode2 = true;
                            }

                            if (hasRowNode && hasRowNode2) {
                                break;
                            }

                        }

                        if (!hasRowNode) {
                            g.NodeList.add(rowNode);
                        }

                        if (!hasRowNode2) {

                            g.NodeList.add(rowNode2);
                        }
                        
                        //Adding Neighbours 
                        for (int i = 0; i < g.NodeList.size(); i++) {

                            if (rowNode.NodeIndex == g.NodeList.get(i).NodeIndex) {
                                // Vorrausgesetzt keine parallelkante und selbstkante
                                g.NodeList.get(i).NeighbourList.add(rowNode2);
  
                            }

                            if(rowNode2.NodeIndex == g.NodeList.get(i).NodeIndex) {
                             
                                g.NodeList.get(i).NeighbourList.add(rowNode);
                            }
                        }

                        Edge e = null;

                        
                    if (rowData.length == 2) { 

                      e = new Edge(rowNode, rowNode2);


                    }

                    else if (rowData.length == 3) {

                        e = new Edge(rowNode, rowNode2,Float.parseFloat(rowData[1]));

                    }
                        g.EdgeList.add(e);

                    }

                

         
                lineCounter++;
            }
            System.out.println("Anzahl Kanten:" + g.EdgeList.size());
            System.out.println("Anzahl Knoten:" + g.NodeList.size());

            for (Node n : g.NodeList) {

                System.out.println("Node mit der ID:" + n.NodeIndex + "hat folgende Nachbarn:");

                for (int i = 0; i < n.NeighbourList.size(); i++) {

                    System.out.println("Index des Nachbarn:" + n.NeighbourList.get(i).NodeIndex);
                }
            }

            g.sortNodeList();
            g.printIncidentMatrix();
           // g.printAdjacentMatrix();

            /*
             * int graphNodeCount = 0;
             * int lineCounter = 0;
             * boolean isWeighted = false;
             * boolean isDirected = false;
             * while (myReader.hasNextLine()) {
             * String data = myReader.nextLine();
             * //First line represents the Total Node Count
             * if(lineCounter == 0) {
             * graphNodeCount = Integer.parseInt(data);
             * }
             * else {
             * //Connection of Nodes. Seperation of Nodes done by Whitespace e.g 1 2
             * String [] nodeArr = data.split("\\s+");
             * 
             * //Edges w/o weight
             * if(nodeArr.length == 2) {
             * Node rowNode = new Node(Integer.parseInt(nodeArr[0]));
             * Node rowNode2 = new Node(Integer.parseInt(nodeArr[1]));
             * rowNode.NeighbourList.add(rowNode2);
             * g.EdgeList.add(new Edge(rowNode, rowNode2));
             * 
             * }
             * //Edges with weight
             * else if(nodeArr.length == 3) {
             * //Setting Weight prop
             * if(!isWeighted) {
             * isWeighted = true;
             * }
             * Node rowNode = new Node(Integer.parseInt(nodeArr[0]));
             * float weight = Float.parseFloat(nodeArr[1]);
             * Node rowNode2 = new Node(Integer.parseInt(nodeArr[2]));
             * g.EdgeList.add(new Edge(rowNode, rowNode2,weight));
             * 
             * rowNode.NeighbourList.add(rowNode2);
             * 
             * }
             * 
             * 
             * }
             * lineCounter++;
             * 
             * }
             * myReader.close();
             * 
             * //Update Weight prop
             * g.isWeighted = isWeighted;
             * 
             * for(Edge e : g.EdgeList) {
             * 
             * boolean hasANode = false;
             * boolean hasBNode = false;
             * for(Node n : g.NodeList) {
             * 
             * if(n.NodeIndex == e.a.NodeIndex) {
             * hasANode = true;
             * 
             * 
             * }
             * 
             * if(n.NodeIndex == e.b.NodeIndex) {
             * 
             * hasBNode = true;
             * }
             * 
             * 
             * }
             * 
             * if(!hasANode) {
             * 
             * g.NodeList.add(e.a);
             * }
             * 
             * if(!hasBNode) {
             * g.NodeList.add(e.b);
             * }
             * 
             * 
             * }
             * 
             * 
             * System.out.println("Anzahl Kanten:"+g.EdgeList.size());
             * System.out.println("Anzahl Knoten:"+g.NodeList.size());
             * 
             * 
             * for(Node n: g.NodeList) {
             * 
             * System.out.println("Node mit der ID:"+n.NodeIndex+"hat folgende Nachbarn:");
             * 
             * 
             * for(int i = 0; i < n.NeighbourList.size();i++) {
             * 
             * System.out.println("Index des NAchbarn:"+n.NeighbourList.get(i).NodeIndex);
             * }
             * }
             * 
             * 
             */

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Return true if parsing was succesfull
        return g;
    }

}

package main.core;

public class Edge {


    public Node a;
    public Node b;
    public float weight;
    public Edge(Node a , Node b) {

    this.a = a;
    this.b = b;

        
    }

    public Edge(Node a, Node b,float weight) {

        this.a = a;
        this.b = b;
        this.weight = weight;
    }
    
}

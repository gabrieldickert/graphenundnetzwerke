package main.core;

/**
 * Simple Edge class
 */
public class Edge {

    /**
     * First Node of the Edge.
     */
    public Node a;
    /**
     * Second Node of the Edge.
     */
    public Node b;
    /**
     * Weight of the Edge.
     */
    public float weight;

    /**
     * Constructor for an unweighted Edge.
     * 
     * @param a
     * @param b
     */
    public Edge(Node a, Node b) {

        this.a = a;
        this.b = b;
     


    }

    /**
     * Constructor for an weighted Edge.
     * 
     * @param a      Node 1 of the Edge.
     * @param b      Node 2 of the Edge.
     * @param weight weight opf the Edge.
     */
    public Edge(Node a, Node b, float weight) {

        this.a = a;
        this.b = b;
        this.weight = weight;
    }

}

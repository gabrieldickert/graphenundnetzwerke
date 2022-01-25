package main.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashMap;
public class Kruskal {


    public static LinkedList<Edge> performKruskal(Graph g) {

        LinkedList<Edge> kruskalList = new LinkedList<>();

        HashMap<Integer, HashSet<Integer>> forest = new HashMap<>();

        HashSet<Float> weightSet = new HashSet<>();

        for(Node n : g.NodeList) {
            //Add Tree
            forest.put(n.NodeIndex, makeSet(n));

        }

        //Sort List by weight ASC
        g.sortEdgeList();

        for(Edge e: g.EdgeList) {

            weightSet.add(e.weight);
        }
        //Sorted Weights in Set
        TreeSet<Float> weightTreeSet= new TreeSet<Float>(weightSet);

        for(Edge e : g.EdgeList) {

                if(!findSet(forest, e.a).equals(findSet(forest, e.b))) {
                        kruskalList.add(e);
                        union(forest, e.a, e.b);

                }
        }

        
  
        return kruskalList;


    }

    public static HashSet<Integer> findSet( HashMap<Integer, HashSet<Integer>> forest,Node n) {

        for (int representative : forest.keySet()) {

            if(forest.get(representative).contains(n.NodeIndex)) {

                return forest.get(representative);
            }
		}
      return null;
    }

    public static HashSet<Integer> makeSet(Node n) {

        HashSet<Integer> nodeSet = new HashSet<Integer>(1);
        
        nodeSet.add(n.NodeIndex);

        return nodeSet;


    }

    public static void union (HashMap<Integer, HashSet<Integer>> forest,Node n1, Node n2) {
        HashSet<Integer> tree1 = findSet(forest,n1);
        HashSet<Integer> tree2 = findSet(forest,n2);
		
        tree1.addAll(tree2);

        forest.remove(n2.NodeIndex);

	}

    
}

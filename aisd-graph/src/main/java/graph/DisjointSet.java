package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by malbor806 on 23.06.2017.
 */
public class DisjointSet {

    private Map<Vertex, Node> map = new HashMap<Vertex, Node>();

    class Node {
        Node parent;
        Vertex data;
        int rank;
    }

    public void makeSets(Map<Integer, Vertex> vertexList) {
        for (Integer i : vertexList.keySet()) {
            makeSet(vertexList.get(i));
        }
    }

    private void makeSet(Vertex vertex) {
        Node node = new Node();
        node.data = vertex;
        node.parent = node;
        node.rank = 0;
        map.put(vertex, node);
    }

    public Vertex find(Vertex vertex) {
        return find(map.get(vertex)).data;
    }

    public Node find(Node node) {
        while (!node.equals(node.parent)) {
            node = node.parent;
        }
        return node;
    }

    public void union(Vertex first, Vertex second) {
        union(map.get(first), map.get(second));
    }

    private void union(Node first, Node second) {
        Node x = find(first);
        Node y = find(second);
        if (x.equals(y)) {
            return;
        }
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;
            if (x.rank == y.rank) {
                y.rank++;
            }
        }
    }
}

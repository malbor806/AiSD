package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Vertex {
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    private Map<Vertex, Edge> neighborhood;
    private Vertex previousVertex;
    private double distance;
    private int value;


    public Vertex(int value) {
        this.value = value;
        distance = INFINITY;
        previousVertex = null;
        neighborhood = new HashMap<Vertex, Edge>();
    }

    public Map<Vertex, Edge> getNeighborhood() {
        return neighborhood;
    }

    public int getValue() {
        return value;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}

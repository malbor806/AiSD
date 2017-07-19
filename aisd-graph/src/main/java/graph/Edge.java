package graph;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Edge {
    private Vertex firstVertex;
    private Vertex secondVertex;
    private double weight;

    public Edge(Vertex firstVertex, Vertex secondVertex, double weight) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }

    public Vertex getTo() {
        return secondVertex;
    }

    public Vertex getFrom() {
        return firstVertex;
    }


    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return firstVertex.getValue() + " " + secondVertex.getValue() + " " + weight;
    }
}

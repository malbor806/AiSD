package graph;

import java.util.*;

/**
 * Created by malbor806 on 31.05.2017.
 */
public class UndirectedGraph {
    private Map<Integer, Vertex> verticesList;
    private List<Edge> edges;
    private int numberOfVertex;
    private int numberOfEdges;
    private Map<Vertex, Edge> shortestPathResult;
    private List<Edge> mstResult;
    private Queue queue;

    public UndirectedGraph(List<String> data) {
        verticesList = new HashMap<Integer, Vertex>();
        edges = new ArrayList<Edge>();
        queue = new Queue();
        numberOfVertex = Integer.parseInt(data.get(0));
        numberOfEdges = Integer.parseInt(data.get(1));
        createGraph(data);
    }

    private void createGraph(List<String> data) {
        String[] line;
        for (int i = 2; i < data.size(); i++) {
            line = data.get(i).trim().split("\\s+");
            parseLine(line);
        }
    }

    private void parseLine(String[] line) {
        int newFirstVertexValue = Integer.parseInt(line[0]);
        int newSecondVertexValue = Integer.parseInt(line[1]);
        addVertexToVerticesList(newFirstVertexValue, newSecondVertexValue);
        double weight = Double.valueOf(line[2]);
        addVerticesToNeighborhoodList(newFirstVertexValue, newSecondVertexValue, weight);
    }

    private void addVertexToVerticesList(int newFirstVertexValue, int newSecondVertexValue) {
        if (!checkIfGraphContainsVertex(newFirstVertexValue)) {
            verticesList.put(newFirstVertexValue, new Vertex(newFirstVertexValue));
        }
        if (!checkIfGraphContainsVertex(newSecondVertexValue)) {
            verticesList.put(newSecondVertexValue, new Vertex(newSecondVertexValue));
        }
    }

    private void addVerticesToNeighborhoodList(int newFirstVertexValue, int newSecondVertexValue, double weight) {
        Vertex firstVertex = verticesList.get(newFirstVertexValue);
        Vertex secondVertex = verticesList.get(newSecondVertexValue);
        if (firstVertex != null && secondVertex != null) {
            Edge edge = new Edge(firstVertex, secondVertex, weight);
            edges.add(edge);
            firstVertex.getNeighborhood().put(secondVertex, edge);
            secondVertex.getNeighborhood().put(firstVertex, edge);
        }
    }


    private boolean checkIfGraphContainsVertex(int vertexValue) {
        if (vertexValue < 0) {
            try {
                throw new Exception("Wrong number of vertex");
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return verticesList.containsKey(vertexValue);
    }

    private void resetVertexDistanceAndPrevious() {
        for (int vertexIndex : verticesList.keySet()) {
            verticesList.get(vertexIndex).setDistance(Vertex.INFINITY);
            verticesList.get(vertexIndex).setPreviousVertex(null);
        }
    }

    private void insertVerticesToQueue(Queue queue) {
        for (int vertexIndex : verticesList.keySet()) {
            queue.insert(verticesList.get(vertexIndex));
        }
        queue.buildHeap();
    }

    public void findMSTusingPrime(int startingVertex) {
        mstResult = new ArrayList<Edge>();
        shortestPathResult = new HashMap<Vertex, Edge>();
        prime(startingVertex);
        printMstResult(startingVertex);
    }

    private void prime(int startingVertex) {
        resetVertexDistanceAndPrevious();
        verticesList.get(startingVertex).setDistance(0);
        insertVerticesToQueue(queue);
        Vertex currentVertex;
        while (!queue.isEmpty()) {
            currentVertex = verticesList.get(queue.extractMin().getValue());
            shortestPathResult.put(currentVertex, currentVertex.getNeighborhood().get(currentVertex.getPreviousVertex()));
            checkVertexNeighborhoodForMST(currentVertex, queue);
        }
        shortestPathResult.remove(verticesList.get(startingVertex));
    }

    private void checkVertexNeighborhoodForMST(Vertex currentVertex, Queue queue) {
        Map<Vertex, Edge> vertexNeighborhood = currentVertex.getNeighborhood();
        Edge edge;
        for (Vertex vertex : vertexNeighborhood.keySet()) {
            edge = vertexNeighborhood.get(vertex);
            if (vertex.getDistance() > edge.getWeight()) {
                vertex.setDistance(edge.getWeight());
                vertex.setPreviousVertex(currentVertex);
                queue.decreaseKey(vertex);
            }
        }
    }

    private void printMstResult(int startingVertex) {
        System.out.println("Starting vertex " + startingVertex);
        double counter = 0;
        for (Vertex vertex : shortestPathResult.keySet()) {
            if (vertex != null) {
                System.out.println(vertex.getValue() + " -> " + shortestPathResult.get(vertex).toString());
                counter += shortestPathResult.get(vertex).getWeight();
            }
        }
        System.out.println("distance: " + counter);
    }

    public void findMSTusingKruskal(int startingVertex) {
        mstResult = new ArrayList<Edge>();
        shortestPathResult = new HashMap<Vertex, Edge>();
        kruskal(startingVertex);
        printResult(startingVertex);
    }

    private void kruskal(int startingVertex) {
        List<Edge> kruskalEdge = edges;
        Collections.sort(kruskalEdge, new EdgeComparator());

        DisjointSet disjointSet = new DisjointSet();

        QueueEdge queue = new QueueEdge();
        for (Edge edge : kruskalEdge) {
            queue.insert(edge);
        }
        disjointSet.makeSets(verticesList);
        while (!queue.isEmpty()) {
            Edge e = queue.extractMin();
            if (!disjointSet.find(e.getFrom()).equals(disjointSet.find(e.getTo()))) {
                mstResult.add(e);
                disjointSet.union(e.getFrom(), e.getTo());
            }
        }
    }

    private void printResult(int startingVertex) {
        System.out.println("Starting vertex " + startingVertex);
        double result = 0;
        for (Edge e : mstResult) {
            System.out.println(e.toString());
            result += e.getWeight();
        }
        System.out.println("distance: " + result);
    }


    static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge o1, Edge o2) {
            return o1.getWeight() <= o2.getWeight() ? -1 : 1;
        }
    }
}

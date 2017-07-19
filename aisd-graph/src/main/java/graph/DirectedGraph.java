package graph;

import java.util.*;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class DirectedGraph {
    private Map<Integer, Vertex> verticesList;
    private List<Edge> edges;
    private int numberOfVertex;
    private int numberOfEdges;
    private List<Vertex> shortestPathResult;
    private Queue queue;

    public DirectedGraph(List<String> data) {
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
        //  String[] line = l[0].split(" ");
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
        Vertex vertex = verticesList.get(newFirstVertexValue);
        Vertex vertexToAdd = verticesList.get(newSecondVertexValue);
        if (vertex != null && vertexToAdd != null) {
            Edge edge = new Edge(vertex, vertexToAdd, weight);
            vertex.getNeighborhood().put(vertexToAdd, edge);
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

    public void findShortestPathsToVertices(int startingVertex) {
        shortestPathResult = new ArrayList<Vertex>();
        dijkstra(startingVertex);
        printResult(startingVertex);
    }

    private void dijkstra(int startingVertex) {
        resetVertexDistanceAndPrevious();
        verticesList.get(startingVertex).setDistance(0);
        insertVerticesToQueue(queue);
        Vertex currentVertex;
        while (!queue.isEmpty()) {
            currentVertex = queue.extractMin();
            shortestPathResult.add(currentVertex);
            checkVertexNeighborhood(currentVertex, queue);
        }
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

    private void checkVertexNeighborhood(Vertex currentVertex, Queue queue) {
        Map<Vertex, Edge> vertexNeighborhood = currentVertex.getNeighborhood();
        double weight;
        for (Vertex vertex : vertexNeighborhood.keySet()) {
            weight = findEdgeDistance(vertex, vertexNeighborhood);
            if (vertex.getDistance() > (currentVertex.getDistance() + weight)) {
                vertex.setDistance(currentVertex.getDistance() + weight);
                vertex.setPreviousVertex(currentVertex);
                queue.decreaseKey(vertex);
            }
        }
    }

    private double findEdgeDistance(Vertex vertex, Map<Vertex, Edge> vertexNeighborhood) {
        Edge edge = vertexNeighborhood.get(vertex);
        return edge.getWeight();
    }

    private void printResult(int startingVertex) {
        Collections.sort(shortestPathResult, new VertexComparator());
        System.out.println("Starting vertex: " + startingVertex + " last vertex: " + shortestPathResult.get(shortestPathResult.size() - 1).getValue());
        ArrayList<String> result;
        for (Vertex vertex : shortestPathResult) {
            result = new ArrayList<String>();
            System.out.println("    -> " + vertex.getValue() + " dist: " + vertex.getDistance());
            Vertex tmpVertex = vertex;
            while (tmpVertex.getValue() != startingVertex && tmpVertex.getPreviousVertex() != null) {
                result.add("(" + tmpVertex.getPreviousVertex().getValue() + "," + tmpVertex.getValue() + ") "
                        + tmpVertex.getPreviousVertex().getNeighborhood().get(tmpVertex).getWeight());
                tmpVertex = tmpVertex.getPreviousVertex();
            }
            for (int i = result.size() - 1; i >= 0; i--) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println();
        }
    }

    static class VertexComparator implements Comparator<Vertex> {
        public int compare(Vertex o1, Vertex o2) {
            return o1.getValue() < o2.getValue() ? -1 : 1;
        }
    }

}

package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malbor806 on 23.06.2017.
 */
public class QueueEdge {

    private List<Edge> edges;

    public QueueEdge() {
        edges = new ArrayList<Edge>();
    }

    public void insert(Edge edge) {
        int i = edges.size();
        edges.add(edge);
        while (i > 0 && edges.get(getParent(i)).getWeight() < edge.getWeight()) {
            swap(i, getParent(i));
            i = getParent(i);
        }
        edges.set(i, edge);
    }

    public void decreaseKey(Edge value) {
        int i = edges.indexOf(value);
        if (i == (-1) || edges.get(i).getWeight() < value.getWeight())
            return;
        bubbleUp(i);
    }

    private void bubbleUp(int i) {
        while (i >= 0 && edges.get(i).getWeight() < edges.get(getParent(i)).getWeight()) {
            swap(getParent(i), i);
        }
    }

    public Edge extractMin() {
        if (isEmpty()) {
            return null;
        }
        Edge min = edges.get(0);
        edges.set(0, edges.get(edges.size() - 1));
        heapify(0);
        edges.remove(edges.size() - 1);
        return min;
    }

    private int getParent(int index) {
        return (int) Math.floor((index - 1) / 2);
    }

    private int getLeft(int index) {
        return 2 * index + 1;
    }

    private int getRight(int index) {
        return 2 * (index + 1);
    }

    private void heapify(int index) {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest = index;
        if (left < edges.size() && edges.get(left).getWeight() < edges.get(smallest).getWeight()) {
            smallest = left;
        }
        if (right < edges.size() && edges.get(right).getWeight() < edges.get(smallest).getWeight()) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        Edge tmp = edges.get(i);
        edges.set(i, edges.get(j));
        edges.set(j, tmp);
    }

    public void buildHeap() {
        int threshold = (int) Math.floor((edges.size() - 1) / 2);
        for (int i = threshold; i >= 0; i--) {
            heapify(i);
        }
    }

    public void printQueue() {
        for (Edge edge : edges) {
            System.out.println(edge.toString());
        }
    }

    public boolean isEmpty() {
        return edges.size() == 0;
    }
}

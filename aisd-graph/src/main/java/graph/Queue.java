package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Queue {
    private List<Vertex> vertices;

    public Queue() {
        vertices = new ArrayList<Vertex>();
    }

    public void insert(Vertex vertex) {
        int i = vertices.size();
        vertices.add(vertex);
        while (i > 0 && vertices.get(getParent(i)).getDistance() < vertex.getDistance()) {
            swap(i, getParent(i));
            i = getParent(i);
        }
        vertices.set(i, vertex);
    }

    public void decreaseKey(Vertex value) {
        int i = vertices.indexOf(value);
        if (i == (-1) || vertices.get(i).getDistance() < value.getDistance())
            return;
        vertices.get(i).setDistance(value.getDistance());
        bubbleUp(i);
    }

    private void bubbleUp(int i) {
        while (i >= 0 && vertices.get(i).getDistance() < vertices.get(getParent(i)).getDistance()) {
            swap(getParent(i), i);
        }
    }

    public Vertex extractMin() {
        if (isEmpty()) {
            return null;
        }
        Vertex min = vertices.get(0);
        vertices.set(0, vertices.get(vertices.size() - 1));
        heapify(0);
        vertices.remove(vertices.size() - 1);
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
        if (left < vertices.size() && vertices.get(left).getDistance() < vertices.get(smallest).getDistance()) {
            smallest = left;
        }
        if (right < vertices.size() && vertices.get(right).getDistance() < vertices.get(smallest).getDistance()) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        Vertex tmp = vertices.get(i);
        vertices.set(i, vertices.get(j));
        vertices.set(j, tmp);
    }

    public void buildHeap() {
        int threshold = (int) Math.floor((vertices.size() - 1) / 2);
        for (int i = threshold; i >= 0; i--) {
            heapify(i);
        }
    }

    public void printQueue() {
        for (Vertex vertex : vertices) {
            System.out.println(vertex.getValue() + "  " + vertex.getDistance());
        }
    }

    public boolean isEmpty() {
        return vertices.size() == 0;
    }


}

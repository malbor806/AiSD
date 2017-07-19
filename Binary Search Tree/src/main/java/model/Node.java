package model;

/**
 * Created by malbor806 on 28.04.2017.
 */
public class Node<T extends Comparable<T>> {
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T value;
    private int size;

    public Node(Node<T> parent, T value) {
        this.parent = parent;
        left = null;
        right = null;
        this.value = value;
        size = 0;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }

}

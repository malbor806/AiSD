import model.Node;

import java.util.ArrayList;

/**
 * Created by malbor806 on 28.04.2017.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private long counter;

    public BinarySearchTree() {
        root = null;
        counter = 0;
    }

    public long getCounter() {
        return counter;
    }

    public void insert(T newValue) {
        if (root == null) {
            root = new Node<T>(null, newValue);
            root.setSize(1);
            return;
        }
        Node<T> tmp = findPlaceInTree(newValue);
        if (newValue.compareTo(tmp.getValue()) <= 0) {
            tmp.setLeft(new Node<T>(tmp, newValue));
            tmp.getLeft().setSize(1);
        } else {
            tmp.setRight(new Node<T>(tmp, newValue));
            tmp.getRight().setSize(1);
        }
    }

    private Node<T> findPlaceInTree(T value) {
        Node<T> tmp = root;
        while (tmp != null) {
            tmp.setSize(tmp.getSize() + 1);
            if (value.compareTo(tmp.getValue()) == 0) {
                return tmp;
            } else if (value.compareTo(tmp.getValue()) < 0) {
                if (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                } else {
                    return tmp;
                }
            } else {
                if (tmp.getRight() != null) {
                    tmp = tmp.getRight();
                } else {
                    return tmp;
                }
            }
        }
        return null;
    }

    public void delete(T deleteValue) {
        Node<T> nodeToDelete = findNodeInTree(deleteValue);
        if (nodeToDelete != null) {
            if (nodeToDelete.equals(root)) {
                nodeIsRoot(nodeToDelete);
            } else if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
                nodeHasNoChildren(nodeToDelete);
            } else if (hasOnlyOneSon(nodeToDelete)) {
                nodeHasOneSon(nodeToDelete);
            } else {
                removeNodeWithTwoSons(nodeToDelete);
            }
        }
    }

    private void nodeIsRoot(Node<T> nodeToDelete) {
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            root = null;
        } else if (root.getLeft() == null && root.getRight() != null) {
            root = root.getRight();
            root.setParent(null);
        } else if (root.getLeft() != null && root.getRight() == null) {
            root = root.getLeft();
            root.setParent(null);
        } else {
            removeNodeWithTwoSons(root);
        }
    }

    private void nodeHasNoChildren(Node<T> nodeToDelete) {
        if (nodeToDelete.equals(root)) {
            root = null;
            return;
        }
        if (nodeToDelete.getParent().getLeft() == nodeToDelete) {
            nodeToDelete.getParent().setLeft(null);
        } else {
            nodeToDelete.getParent().setRight(null);
        }
    }

    private boolean hasOnlyOneSon(Node<T> nodeToDelete) {
        return ((nodeToDelete.getLeft() != null && nodeToDelete.getRight() == null) ||
                (nodeToDelete.getLeft() == null && nodeToDelete.getRight() != null));
    }

    private void nodeHasOneSon(Node<T> nodeToDelete) {
        if (nodeToDelete.equals(nodeToDelete.getParent().getLeft())) {
            changeNodeLeftSonToNode(nodeToDelete);
        } else {
            changeNodeRightSonToNode(nodeToDelete);
        }
    }

    private void changeNodeLeftSonToNode(Node<T> nodeToDelete) {
        if (nodeToDelete.getLeft() != null) {
            nodeToDelete.getLeft().setParent(nodeToDelete.getParent());
            nodeToDelete.getParent().setLeft(nodeToDelete.getLeft());
        } else {
            nodeToDelete.getRight().setParent(nodeToDelete.getParent());
            nodeToDelete.getParent().setLeft(nodeToDelete.getRight());
        }
    }

    private void changeNodeRightSonToNode(Node<T> nodeToDelete) {
        if (nodeToDelete.getLeft() != null) {
            nodeToDelete.getLeft().setParent(nodeToDelete.getParent());
            nodeToDelete.getParent().setRight(nodeToDelete.getLeft());
        } else {
            nodeToDelete.getRight().setParent(nodeToDelete.getParent());
            nodeToDelete.getParent().setRight(nodeToDelete.getRight());
        }
    }

    private void removeNodeWithTwoSons(Node<T> nodeToDelete) {
        Node<T> successor = treeSuccessor(nodeToDelete);
        if (successor.equals(successor.getParent().getLeft())) {
            successor.getParent().setLeft(null);
        } else {
            successor.getParent().setRight(null);
        }
        nodeToDelete.getLeft().setParent(successor);
        nodeToDelete.getRight().setParent(successor);
        successor.setLeft(nodeToDelete.getLeft());
        successor.setRight(nodeToDelete.getRight());
        successor.setParent(nodeToDelete.getParent());
        if (nodeToDelete.equals(root)) {
            root = successor;
        } else {
            if (nodeToDelete.equals(nodeToDelete.getParent().getLeft())) {
                nodeToDelete.getParent().setLeft(successor);
            } else {
                nodeToDelete.getParent().setRight(successor);
            }
        }
    }

    private Node<T> findNodeInTree(T deleteValue) {
        Node<T> tmp = root;
        while (tmp != null) {
            if (deleteValue.compareTo(tmp.getValue()) == 0) {
                return tmp;
            } else if (deleteValue.compareTo(tmp.getValue()) < 0) {
                if (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                } else {
                    return null;
                }
            } else {
                if (tmp.getRight() != null) {
                    tmp = tmp.getRight();
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    private Node<T> treeSuccessor(Node<T> node) {
        Node<T> tmp;
        if (node.getRight() != null) {
            tmp = node.getRight();
            while (tmp.getLeft() != null) {
                tmp = tmp.getLeft();
            }
            return tmp;
        }
        tmp = node.getParent();
        while (tmp != null && node.equals(tmp.getRight())) {
            node = tmp;
            tmp = tmp.getParent();
        }
        return tmp;
    }

    public void min() {
        Node<T> tmp = root;
        if (root != null) {
            while (tmp.getLeft() != null) {
                tmp = tmp.getLeft();
            }
            System.out.println(tmp.getValue());
        } else {
            System.out.println();
        }
    }

    public void max() {
        Node<T> tmp = root;
        if (root != null) {
            while (tmp.getRight() != null) {
                tmp = tmp.getRight();
            }
            System.out.println(tmp.getValue());
        } else {
            System.out.println();
        }
    }

    public int maxTest() {
        Node<T> tmp = root;
        if (root != null) {
            int counter = 0;
            while (tmp.getRight() != null) {
                tmp = tmp.getRight();
                counter++;
            }
            return counter;
        } else {
            return 0;
        }
    }

    public void find(T value) {
        if (root != null) {
            Node<T> tmp = root;
            int compareResult = 0;
            while (tmp != null) {
                compareResult = value.compareTo(tmp.getValue());
                if (compareResult == 0) {
                    System.out.println(1);
                    return;
                } else if (compareResult < 0) {
                    tmp = tmp.getLeft();
                } else {
                    tmp = tmp.getRight();
                }
            }
        }
        System.out.println(0);
    }

    public void inOrder() {
        if (root != null) {
            inOrderWalk(root);
        }
        System.out.println();
    }

    private void inOrderWalk(Node<T> x) {
        if (x != null) {
            inOrderWalk(x.getLeft());
            System.out.print(x.getValue() + " ");
            inOrderWalk(x.getRight());
        }
    }

    public int getHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            int leftH = height(node.getLeft()), rightH = height(node.getRight());
            if (leftH >= rightH) {
                return leftH + 1;
            } else {
                return rightH + 1;
            }
        }
    }

    public void createBalancedTree(BinarySearchTree tree, ArrayList<Integer> arr, int start, int end) {
        if (end - start == 0) {
            tree.insert(arr.get(end));
        } else if (end - start == 1) {
            tree.insert(arr.get(start));
            tree.insert(arr.get(end));
        } else {
            int m = (start + end) / 2;
            tree.insert(arr.get(m));
            createBalancedTree(tree, arr, start, m - 1);
            createBalancedTree(tree, arr, m + 1, end);
        }
    }

    public void osRank(T value) {
        repairTreeSize(root);
        Node<T> node = findNodeInTree(value);
        if (node != null) {
            int r = getNodeSize(node.getLeft()) + 1;
            Node<T> tmp = node;
            while (!tmp.equals(root)) {
                if (tmp.equals(tmp.getParent().getRight())) {
                    r += getNodeSize(tmp.getParent().getLeft()) + 1;
                }
                tmp = tmp.getParent();
            }
            System.out.println(node.getValue() + "has {" + r + "} order statistic");
            return;
        }
        System.out.println("There is no node with this value");
    }

    private T findOrderStatistic(Node<T> node, int stat) {
        if (node == null) {
            return null;
        }
        int r = getNodeSize(node.getLeft()) + 1;
        if (stat == r) {
            counter++;
            return node.getValue();
        } else if (stat < r) {
            counter++;
            return findOrderStatistic(node.getLeft(), stat);
        } else {
            counter++;
            return findOrderStatistic(node.getRight(), stat - r);
        }
    }

    private void repairTreeSize(Node<T> node) {
        if (node != null) {
            repairTreeSize(node.getLeft());
            repairTreeSize(node.getRight());
            int size = getNodeSize(node.getLeft()) + getNodeSize(node.getRight()) + 1;
            node.setSize(size);
        }
    }

    private int getNodeSize(Node<T> node) {
        return (node != null) ? node.getSize() : 0;
    }

    public void osSelect(int stat) {
        repairTreeSize(root);
        counter = 0;
        if (root != null) {
            if (stat <= root.getSize()) {
                T value = findOrderStatistic(root, stat);
                //     System.out.println("On stat " + stat + " -> " + value);
            } else {
                System.out.println("Tree size is smaller than " + stat);
            }
        } else {
            System.out.println("Tree is empty!");
        }
    }
}

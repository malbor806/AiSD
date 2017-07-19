import java.util.ArrayList;
import java.util.List;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Heap {
    private List<Integer> data;
    private long comparisionInsert;
    private long comparisionDecrease;
    private long comparisionExtract;

    public Heap() {
        data = new ArrayList<Integer>();
        comparisionDecrease = 0;
        comparisionExtract = 0;
        comparisionInsert = 0;
    }

    public void insert(int value) {
        data.add(value);
        int i = data.size() - 1;
        while (i > 0 && data.get(getParent(i)) < value) {
            data.set(i, data.get(getParent(i)));
            i = getParent(i);
            comparisionInsert++;
        }
        data.set(i, value);
    }

    public void decreaseKey(int x, int value) {
        int i = data.indexOf(x);
        if (i == (-1) || data.get(i) < value)
            return;
        comparisionDecrease++;
        data.set(i, value);
        bubbleUp(i);

    }

    private void bubbleUp(int i) {
        while (i >= 0 && data.get(i) < data.get(getParent(i))) {
            swap(getParent(i),i);
            comparisionDecrease++;
        }
    }

    public int extractMin() {
        if (isEmpty()) {
            return -1;
        }
        int max = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        heapify(0);
        return max;
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
        int smallest;
        if (left < data.size() && data.get(left) < data.get(index)) {
            smallest = left;
        } else {
            smallest = index;
        }
        comparisionExtract++;
        if (right < data.size() && data.get(right) < data.get(smallest)) {
            smallest = right;
        }
        comparisionExtract++;
        if (smallest != index) {
            swap(index, smallest);
            comparisionExtract++;
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        int tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    public void buildHeap() {
        int threshold = (int) Math.floor((data.size() - 1) / 2);
        for (int i = threshold; i >= 0; i--) {
            heapify(i);
        }
    }

    private boolean isEmpty() {
        return data.size() == 0;
    }

    public void print() {
        int threshold, c = 0;
        for (int i = 0; Math.pow(2, i) < data.size(); i++) {
            threshold = Math.pow(2, i) + c > data.size() ? data.size() - c  : (int) Math.pow(2, i);
            for (int j = 0; j < threshold; j++) {
                System.out.print(data.get(c) + " ");
                c++;
            }
            System.out.println();
        }
    }

    public long getComparisionInsert() {
        return comparisionInsert;
    }

    public void setComparisionInsert(long comparisionInsert) {
        this.comparisionInsert = comparisionInsert;
    }

    public long getComparisionDecrease() {
        return comparisionDecrease;
    }

    public void setComparisionDecrease(long comparisionDecrease) {
        this.comparisionDecrease = comparisionDecrease;
    }

    public long getComparisionExtract() {
        return comparisionExtract;
    }

    public void setComparisionExtract(long comparisionExtract) {
        this.comparisionExtract = comparisionExtract;
    }
}

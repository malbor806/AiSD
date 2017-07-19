import java.util.Arrays;

/**
 * Created by malbor806 on 21.03.2017.
 */
public class InsertionMergeSort implements Sorting {
    private static final int LIMIT = 8;
    private long comparisionWithKey;
    private long keyShift;

    public InsertionMergeSort() {
        comparisionWithKey = 0;
        keyShift = 0;
    }

    @Override
    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        insertionMergeSort(tab);
    }

    private void insertionMergeSort(int[] tab) {
        int middle = tab.length / 2;
        if (tab.length < LIMIT) {
            insertionSort(tab);
        } else {
            int[] left = Arrays.copyOfRange(tab, 0, middle);
            int[] right = Arrays.copyOfRange(tab, middle, tab.length);
            insertionMergeSort(left);
            insertionMergeSort(right);
            merge(tab, left, right);
        }
    }

    private void insertionSort(int[] tab) {
        int j, key;
        for (int i = 1; i < tab.length; i++) {
            key = tab[i];
            j = i - 1;
            while (j >= 0 && tab[j] > key) {
                comparisionWithKey++;
                tab[j + 1] = tab[j];
                j = j - 1;
            }
            if (j > 0)
                comparisionWithKey++;

            tab[j + 1] = key;
            keyShift += 2;
        }
    }

    private void merge(int[] tab, int[] left, int[] right) {
        int length = left.length + right.length;
        int i, leftIndex, rightIndex;
        i = leftIndex = rightIndex = 0;
        while (i < length) {
            if ((leftIndex < left.length) && (rightIndex < right.length)) {
                if (left[leftIndex] < right[rightIndex]) {
                    tab[i] = left[leftIndex];
                    i++;
                    leftIndex++;
                    keyShift++;
                    comparisionWithKey++;
                } else {
                    tab[i] = right[rightIndex];
                    i++;
                    rightIndex++;
                    keyShift++;
                    comparisionWithKey++;
                }
            } else {
                if (leftIndex >= left.length) {
                    while (rightIndex < right.length) {
                        tab[i] = right[rightIndex];
                        i++;
                        rightIndex++;
                        keyShift++;
                        comparisionWithKey++;
                    }
                    comparisionWithKey++;
                }
                if (rightIndex >= right.length) {
                    while (leftIndex < left.length) {
                        tab[i] = left[leftIndex];
                        leftIndex++;
                        i++;
                        keyShift++;
                        comparisionWithKey++;
                    }
                    comparisionWithKey++;
                }
            }
        }
    }

    @Override
    public void sortWithLogs(int[] tab) {
        insertionMergeSortWithLogs(tab);
    }

    private void insertionMergeSortWithLogs(int[] tab) {
        int middle = tab.length / 2;
        if (tab.length - middle < 10) {
            insertionSortWithLogs(tab);
        } else {
            int[] left = Arrays.copyOfRange(tab, 0, middle);
            System.err.println(Arrays.toString(tab));
            int[] right = Arrays.copyOfRange(tab, middle, tab.length);
            System.err.println(Arrays.toString(tab));
            insertionMergeSortWithLogs(left);
            insertionMergeSortWithLogs(right);
            mergeWithLogs(tab, left, right);
        }
    }

    private void insertionSortWithLogs(int[] tab) {
        System.err.println("IS");
        comparisionWithKey = 0;
        keyShift = 0;
        int j, key;
        for (int i = 1; i < tab.length; i++) {
            key = tab[i];
            j = i - 1;
            System.err.println(Arrays.toString(tab));
            System.err.println("key = " + key);
            System.err.println("Indeksy startowe:  i = " + i + "  j = " + j);
            while (j >= 0 && tab[j] > key) {
                System.err.println("za tab[i+1]  = " + tab[j + 1] + "  <-- tab[i]= " + tab[j] + " tab=" + Arrays.toString(tab));
                comparisionWithKey++;
                tab[j + 1] = tab[j];
                j = j - 1;
            }
            if (j > 0)
                comparisionWithKey++;
            tab[j + 1] = key;
            System.err.println("za tab[j+1]  = " + tab[j + 1] + "  <-- key= " + key + " tab=" + Arrays.toString(tab));
            keyShift += 2;
        }
    }

    private void mergeWithLogs(int[] tab, int[] left, int[] right) {
        System.err.println("Merge: L=" + Arrays.toString(left) + " + R=" + Arrays.toString(right));
        int length = left.length + right.length;
        int i, leftIndex, rightIndex;
        i = leftIndex = rightIndex = 0;
        while (i < length) {
            if ((leftIndex < left.length) && (rightIndex < right.length)) {
                System.err.println("i = " + i + " leftIndex = " + leftIndex + "  rightIndex = " + rightIndex);
                if (left[leftIndex] < right[rightIndex]) {
                    System.err.println("left[leftIndex] = " + left[leftIndex] + " <  right[rightIndex] = " + right[rightIndex]);
                    System.err.println("tab[i] = " + tab[i] + " <- left[leftIndex] = " + left[leftIndex]);
                    tab[i] = left[leftIndex];
                    i++;
                    leftIndex++;
                    keyShift++;
                    comparisionWithKey++;
                } else {
                    System.err.println("left[leftIndex] = " + left[leftIndex] + " >  right[rightIndex] = " + right[rightIndex]);
                    System.err.println("tab[i] = " + tab[i] + " <- right[rightIndex] = " + right[rightIndex]);
                    tab[i] = right[rightIndex];
                    i++;
                    rightIndex++;
                    keyShift++;
                    comparisionWithKey++;
                }
            } else {
                if (leftIndex >= left.length) {
                    while (rightIndex < right.length) {
                        System.err.println("leftIndex > left.length");
                        System.err.println("tab[i] = " + tab[i] + " <- right[rightIndex] = " + right[rightIndex]);
                        tab[i] = right[rightIndex];
                        i++;
                        rightIndex++;
                        keyShift++;
                        comparisionWithKey++;
                    }
                    comparisionWithKey++;
                }
                if (rightIndex >= right.length) {
                    while (leftIndex < left.length) {
                        System.err.println("rightIndex > right.length");
                        System.err.println("tab[i] = " + tab[i] + " <- left[leftIndex] = " + left[leftIndex]);
                        tab[i] = left[leftIndex];
                        leftIndex++;
                        i++;
                        keyShift++;
                        comparisionWithKey++;
                    }
                    comparisionWithKey++;
                }
            }
            System.err.println("tab=" + Arrays.toString(tab));
        }
    }

    @Override
    public long getKeyShift() {
        return keyShift;
    }

    @Override
    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

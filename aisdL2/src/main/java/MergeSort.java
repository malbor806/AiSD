import java.util.Arrays;

/**
 * Created by malbor806 on 19.03.2017.
 */
public class MergeSort implements Sorting {
    private long comparisionWithKey;
    private long keyShift;

    public MergeSort() {
        comparisionWithKey = 0;
        keyShift = 0;
    }

    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        mergeSort(tab);
    }

    private void mergeSort(int[] tab) {
        if (tab.length > 1) {
            int middle = tab.length / 2;
            int[] left = Arrays.copyOfRange(tab, 0, middle);
            int[] right = Arrays.copyOfRange(tab, middle, tab.length);
            mergeSort(left);
            mergeSort(right);
            merge(tab, left, right);
        }
    }

    void merge(int[] tab, int[] left, int[] right) {
        int len = left.length + right.length;
        int i, leftIndex, rightIndex;
        i = leftIndex = rightIndex = 0;
        while (i < len) {
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

    public void sortWithLogs(int[] tab) {
        mergeSortWithLogs(tab);
    }

    private void mergeSortWithLogs(int[] tab) {
        if (tab.length > 1) {
            System.err.println("tab = " + Arrays.toString(tab));
            int middle = tab.length / 2;
            int[] left = Arrays.copyOfRange(tab, 0, middle);
            System.err.println("    Lewa czesc tablicy: " + Arrays.toString(left));
            int[] right = Arrays.copyOfRange(tab, middle, tab.length);
            System.err.println("    Prawa czesc tablicy: " + Arrays.toString(right));
            mergeSortWithLogs(left);
            mergeSortWithLogs(right);
            mergeWithLogs(tab, left, right);
        }
    }


    void mergeWithLogs(int[] tab, int[] left, int[] right) {
        System.err.println("Merge: L=" + Arrays.toString(left) + " + R=" + Arrays.toString(right));
        int len = left.length + right.length;
        int i, leftIndex, rightIndex;
        i = leftIndex = rightIndex = 0;
        while (i < len) {
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

    public long getKeyShift() {
        return keyShift;
    }

    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

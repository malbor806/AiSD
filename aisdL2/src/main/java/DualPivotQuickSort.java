import java.util.Arrays;
import java.util.Random;

/**
 * Created by malbor806 on 24.03.2017.
 */
public class DualPivotQuickSort implements Sorting {
    private static final int LIMIT = 27;
    private long comparisionWithKey;
    private long keyShift;
    private Random random;

    public DualPivotQuickSort() {
        random = new Random();
        comparisionWithKey = 0;
        keyShift = 0;
    }

    @Override
    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        dualPivotSort(tab, 0, tab.length - 1);
        // dualPivotQuicksort(tab,0, tab.length - 1,3);
    }


   /* private static void dualPivotQuicksort(int[] a, int left,int right, int div) {
        int len = right - left;
        if (len < 27) { // insertion sort for tiny array
            for (int i = left + 1; i <= right; i++) {for (int j = i; j > left && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
            }
            return;
        }
        int third = len / div;
        // "medians"
        int m1 = left + third;
        int m2 = right - third;
        if (m1 <= left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (a[m1] < a[m2]) {
            swap(a, m1, left);
            swap(a, m2, right);
        }
        else {
            swap(a, m1, right);
            swap(a, m2, left);
        }
        // pivots
        int pivot1 = a[left];
        int pivot2 = a[right];
        // pointers
        int less = left + 1;
        int great = right - 1;
        // sorting
        for (int k = less; k <= great; k++) {
            if (a[k] < pivot1) {
                swap(a, k, less++);
            }
            else if (a[k] > pivot2) {
                while (k < great && a[great] > pivot2) {
                    great--;
                }
                swap(a, k, great--);
                if (a[k] < pivot1) {
                    swap(a, k, less++);
                }
            }
        }
        // swaps
        int dist = great - less;
        if (dist < 13) {
            div++;
        }
        swap(a, less - 1, left);
        swap(a, great + 1, right);
        // subarrays
        dualPivotQuicksort(a, left, less - 2, div);
        dualPivotQuicksort(a, great + 2, right, div);
        // equal elements
        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (a[k] == pivot1) {
                    swap(a, k, less++);
                }
                else if (a[k] == pivot2) {
                    swap(a, k, great--);
                    if (a[k] == pivot1) {
                        swap(a, k, less++);
                    }
                }
            }
        }
        // subarray
        if (pivot1 < pivot2) {
            dualPivotQuicksort(a, less, great, div);
        }
    }*/


    private void dualPivotSort(int[] tab, int start, int end) {
        if (start < end) {
            if (end - start < LIMIT) {
                insertionSort(tab, start, end);
            } else {
                int p1, p2, leftIndex, rightIndex, swap;
                p1 = tab[start];
                p2 = tab[end];
                if (p1 > p2) {
                    swap(tab, start, end);
                    keyShift++;
                }
                comparisionWithKey++;
                leftIndex = start + 1;
                rightIndex = end - 1;
                int k = leftIndex;
                while (k <= rightIndex) {
                    comparisionWithKey++;
                    if (tab[k] < p1) {
                        keyShift++;
                        swap(tab, k, leftIndex);
                        leftIndex++;
                    } else if (tab[k] > p2) {
                        while (tab[rightIndex] > p2 && k < rightIndex) {
                            rightIndex--;
                            comparisionWithKey++;
                        }
                        comparisionWithKey++;
                        keyShift++;
                        swap(tab, k, rightIndex);
                        rightIndex--;
                        comparisionWithKey++;
                        if (tab[k] < p1) {
                            keyShift++;
                            swap(tab, k, leftIndex);
                            leftIndex++;
                        }
                    }
                    k++;
                }
                leftIndex--;
                rightIndex++;
                keyShift += 2;
                swap(tab, start, leftIndex);
                swap(tab, rightIndex, end);
                dualPivotSort(tab, start, leftIndex - 1);
                dualPivotSort(tab, leftIndex + 1, rightIndex - 1);
                dualPivotSort(tab, rightIndex + 1, end);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void insertionSort(int[] tab, int start, int end) {
        int j, key;
        for (int i = start + 1; i <= end; i++) {
            key = tab[i];
            j = i - 1;
            while (j >= start + 1 && tab[j] > key) {
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


    @Override
    public void sortWithLogs(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        dualPivotSortWithLogs(tab, 0, tab.length - 1);
        System.err.println(Arrays.toString(tab));
    }

    private void dualPivotSortWithLogs(int[] tab, int start, int end) {
        System.err.println("start " + start + " end " + end);
        System.err.println(Arrays.toString(tab));
        if (start < end) {
            if (end - start < LIMIT) {
                System.err.println("Insertion sort from " + start + " to " + end);
                insertionSortWithLogs(tab, start, end);
            } else {
                int p1, p2, leftIndex, rightIndex, swap;
                p1 = tab[start];
                p2 = tab[end];
                if (p1 > p2) {
                    swap(tab, start, end);
                }
                System.err.println("Pivots: p1 = " + p1 + " p2 = " + p2);
                leftIndex = start + 1;
                rightIndex = end - 1;
                int k = leftIndex;
                while (k <= rightIndex) {
                    System.err.println("-------------" + k + "--------------");
                    System.err.println("Indeksy leftIndex = " + leftIndex + "  rightIndex = " + rightIndex);
                    if (tab[k] < p1) {
                        System.err.println("tab[k] = " + tab[k] + " <->  tab[leftIndex] = " + tab[leftIndex]);
                        swap(tab, k, leftIndex);
                        leftIndex++;
                    } else if (tab[k] > p2) {
                        while (tab[rightIndex] > p2 && k < rightIndex) {
                            rightIndex--;
                        }
                        System.err.println("tab[k] = " + tab[k] + " <->  tab[rigthIndex] = " + tab[rightIndex]);
                        swap(tab, k, rightIndex);
                        rightIndex--;
                        if (tab[k] < p1) {
                            System.err.println("tab[k] = " + tab[k] + " <->  tab[leftIndex] = " + tab[leftIndex]);
                            swap(tab, k, leftIndex);
                            leftIndex++;
                        }
                    }
                    k++;
                }
                leftIndex--;
                rightIndex++;
                System.err.println("tab[start] = " + tab[start] + " <->  tab[leftIndex] = " + tab[leftIndex]);
                swap(tab, start, leftIndex);
                System.err.println("tab[end] = " + tab[end] + " <->  tab[rightIndex] = " + tab[rightIndex]);
                swap(tab, rightIndex, end);
                dualPivotSortWithLogs(tab, start, leftIndex);
                dualPivotSortWithLogs(tab, leftIndex + 1, rightIndex);
                dualPivotSortWithLogs(tab, rightIndex + 1, end);
            }
        }
    }


    private void insertionSortWithLogs(int[] tab, int start, int end) {
        System.err.println("IS start " + start + " end " + end);
        comparisionWithKey = 0;
        keyShift = 0;
        int j, key;
        for (int i = start + 1; i <= end; i++) {
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

    @Override
    public long getKeyShift() {
        return keyShift;
    }

    @Override
    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

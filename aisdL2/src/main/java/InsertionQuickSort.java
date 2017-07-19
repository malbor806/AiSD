import java.util.Arrays;
import java.util.Random;

/**
 * Created by malbor806 on 21.03.2017.
 */
public class InsertionQuickSort implements Sorting {
    private static final int LIMIT = 15;
    private long comparisionWithKey;
    private long keyShift;
    private Random random;

    public InsertionQuickSort() {
        random = new Random();
        comparisionWithKey = 0;
        keyShift = 0;
    }

    @Override
    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        insertionQuickSort(tab, 0, tab.length - 1);
    }


    private void insertionQuickSort(int[] tab, int start, int end) {
        if ((end - start) > LIMIT) {
            int i, j, pivot, pivotIndex, tmp;
            pivotIndex = start + random.nextInt((end - start + 1));
            pivot = tab[pivotIndex];
            i = start;
            j = end;
            do {
                while (tab[i] < pivot) {
                    i++;
                    comparisionWithKey++;
                }
                comparisionWithKey++;
                while (tab[j] > pivot) {
                    j--;
                    comparisionWithKey++;
                }
                comparisionWithKey++;
                if (i <= j) {
                    tmp = tab[i];
                    tab[i] = tab[j];
                    tab[j] = tmp;
                    i++;
                    j--;
                    keyShift++;
                }
            } while (i <= j);
            if (start < j) insertionQuickSort(tab, start, j);
            if (i < end) insertionQuickSort(tab, i, end);
        } else
            insertionSort(tab, start, end);
    }

    private void insertionSort(int[] tab, int start, int end) {
        int j, key;
        for (int i = start + 1; i <= end; i++) {
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

    @Override
    public void sortWithLogs(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        insertionQuickSortWithLogs(tab, 0, tab.length - 1);
    }

    private void insertionQuickSortWithLogs(int[] tab, int start, int end) {
        System.err.println("QS start " + start + " end " + end);
        if ((end - start) > LIMIT) {
            int i, j, pivot, pivotIndex, swap;
            pivotIndex = start + random.nextInt((end - start + 1));
            pivot = tab[pivotIndex];
            System.err.println("Pivot: " + pivot);
            i = start;
            j = end;
            do {
                System.err.println("tab=" + Arrays.toString(tab));
                System.err.println("Indeksy i = " + i + "  j = " + j);
                while (tab[i] < pivot) {
                    i++;
                    comparisionWithKey++;
                }
                comparisionWithKey++;
                while (tab[j] > pivot) {
                    j--;
                    comparisionWithKey++;
                }
                comparisionWithKey++;
                if (i <= j) {
                    System.err.println("zamiana na indeksach i = " + i + " z  j = " + j);
                    System.err.println("tab[i] = " + tab[i] + " <->  tab[j] = " + tab[j]);
                    swap = tab[i];
                    tab[i] = tab[j];
                    tab[j] = swap;
                    i++;
                    j--;
                    keyShift++;
                }
            } while (i <= j);
            if (start < j) insertionSortWithLogs(tab, start, j);
            if (i < end) insertionSortWithLogs(tab, i, end);
        } else
            insertionSortWithLogs(tab, start, end);
    }

    private void insertionSortWithLogs(int[] tab, int start, int end) {
        System.err.println("IS start " + start + " end " + end);
        comparisionWithKey = 0;
        keyShift = 0;
        int j, key;
        for (int i = start + 1; i < end; i++) {
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

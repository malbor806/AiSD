import java.util.Arrays;
import java.util.Random;

/**
 * Created by malbor806 on 19.03.2017.
 */
public class QuickSort implements Sorting {
    private long comparisionWithKey;
    private long keyShift;
    private Random random;

    public QuickSort() {
        random = new Random();
        comparisionWithKey = 0;
        keyShift = 0;
    }

    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        sort(tab, 0, tab.length - 1);
    }

    private void sort(int[] tab, int start, int end) {
        if ((end - start) > 0) {
            int i, j, pivot, pivotIndex, swap;
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
                    swap = tab[i];
                    tab[i] = tab[j];
                    tab[j] = swap;
                    i++;
                    j--;
                    keyShift++;
                }
            } while (i <= j);
            if (start < j) sort(tab, start, j);
            if (i < end) sort(tab, i, end);
        }
    }

    public void sortWithLogs(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        System.out.println(tab.length);
        sortWithLogs(tab, 0, tab.length - 1);
        System.err.println("koncowa tablica: " + Arrays.toString(tab));

    }

    private void sortWithLogs(int[] tab, int start, int end) {
        System.err.println("    QS: start = " + start + " end = " + end);
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
        if (start < j) sortWithLogs(tab, start, j);
        if (i < end) sortWithLogs(tab, i, end);
    }

    public long getKeyShift() {
        return keyShift;
    }

    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

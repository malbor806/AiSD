import sun.util.resources.cldr.so.CalendarData_so_ET;

import java.util.Arrays;

/**
 * Created by malbor806 on 19.03.2017.
 */
public class InsertionSort implements Sorting {
    private long comparisionWithKey;
    private long keyShift;


    public InsertionSort() {
        comparisionWithKey = 0;
        keyShift = 0;
    }

    public void sort(int[] tab) {
        int j, key;
        comparisionWithKey = 0;
        keyShift = 0;
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

    public void sortWithLogs(int[] tab) {
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


    public long getKeyShift() {
        return keyShift;
    }

    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

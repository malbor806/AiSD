import java.util.Arrays;

/**
 * Created by malbor806 on 31.03.2017.
 */
public class RadixSort implements Sorting {
    private long comparisionWithKey;
    private long keyShift;

    public RadixSort() {
        comparisionWithKey = 0;
        keyShift = 0;
    }


    public void sort(int[] tab) {
        comparisionWithKey = 0;
        keyShift = 0;
        radixSort(tab);
    }

    private void radixSort(int[] tab) {
        int maxElement = getMaxElement(tab);
        for (int i = 1; maxElement / i > 0; i *= 10)
            countingSort(tab, tab.length, i, 10);
    }

    private int getMaxElement(int[] tab) {
        int maxElement = tab[0];
        for (int aTab : tab) {
            comparisionWithKey++;
            if (aTab > maxElement)
                maxElement = aTab;
        }
        return maxElement;
    }

    private static int[] chooseBase(int[] tab, int b) {
        int[] result = new int[tab.length];
        char dig[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int tmp;
        for (int i = 0; i < tab.length; i++) {
            String s = "";
            int x = tab[i];
            while (x > 0) {
                tmp = x % b;
                s = dig[tmp] + s;
                x = x / b;
            }
            result[i] = Integer.parseInt(s);
        }
        return result;
    }

    private void countingSort(int[] tab, int n, int exp, int base) {
        int k = base;
        int[] tmp = new int[k];
        int[] result = new int[n];
        for (int i = 0; i < k; i++)
            tmp[i] = 0;
        for (int j = 0; j < n; j++)
            tmp[(tab[j] / exp) % 10]++;
        for (int j = 1; j < k; j++)
            tmp[j] = tmp[j] + tmp[j - 1];
        for (int j = n - 1; j >= 0; j--) {
            result[tmp[(tab[j] / exp) % 10] - 1] = tab[j];
            tmp[(tab[j] / exp) % 10]--;
        }
        for (int j = 0; j < n; j++) {
            tab[j] = result[j];
            keyShift++;
        }
    }

    public void sortWithLogs(int[] tab) {
        radixSortWithLogs(tab);
    }

    private void radixSortWithLogs(int[] tab) {
        int maxElement = getMaxElement(tab);
        System.err.println("max element tablicy " + maxElement);
        for (int i = 1; maxElement / i > 0; i *= 10)
            countingSortWithLogs(tab, tab.length, i, 10);
    }


    private void countingSortWithLogs(int[] tab, int n, int exp, int base) {
        int k = base;
        int[] tmp = new int[k];
        int[] result = new int[n];
        for (int i = 0; i < k; i++)
            tmp[i] = 0;
        System.err.println("uzupełnienie tablicy");
        for (int j = 0; j < n; j++) {
            tmp[(tab[j] / exp) % 10]++;
            System.err.println(Arrays.toString(tmp));
        }
        System.err.println("Sumy czesciowe");
        for (int j = 1; j < k; j++)
            tmp[j] = tmp[j] + tmp[j - 1];
        System.err.println(Arrays.toString(tmp));
        for (int j = n - 1; j >= 0; j--) {
            result[tmp[(tab[j] / exp) % 10] - 1] = tab[j];
            tmp[(tab[j] / exp) % 10]--;
            System.err.println("result " + Arrays.toString(result));
            System.err.println("tab pom " + Arrays.toString(tmp));
        }

        for (int j = 0; j < n; j++) {
            tab[j] = result[j];
            keyShift++;
        }
        System.err.println("Przeniesienie zmian do tab");
        System.err.println(Arrays.toString(tab));
    }

    public long getKeyShift() {
        return keyShift;
    }

    public long getComparisionWithKey() {
        return comparisionWithKey;
    }
}

import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by malbor806 on 19.03.2017.
 */
public class Main {
    private static Random random;
    private static Count count;
    private static Sorting sorting;

    public static void main(String[] args) throws FileNotFoundException {
        random = new Random();
        count = new Count();
        if (args.length > 0)
            userTests(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], args[3]);


        //   sorting = new InsertionQuickSort();
        // sorting.sortWithLogs(createSortedDescArray(15));
        //       sorting.sortWithLogs(createRandomArray(7, 15));
        //       sorting.sortWithLogs(new int[]{3, 3, 3});
        //startTests(100000, 100, "IS.txt", "IS2.txt", new InsertionSort(), "InsertionSort");
        // startTests(100000, 300, "MS.txt", "MS2.txt", new MergeSort(), "MergeSort");
        //    startTests(100000, 300, "IMS.txt", "IMS2.txt", new InsertionMergeSort(), "InsertionMergeSort");
        //startTests(100000, 300, "QS.txt", "QS2.txt", new QuickSort(), "QuickSort");
        //  startTests(100000, 300, "IQS.txt", "IQS2.txt", new InsertionQuickSort(), "InsertionQuickSort");
        //drawPlot("Insertion sort", "Ciąg odwrotnie posortowany", "IS2.txt");
        //drawPlot("Insertion sort", "sort", "QS211.txt");
        checkTimeSortedArray(new InsertionSort(), "IS", 10000);
        checkTimeSortedArray(new QuickSort(), "QS", 10000);
        checkTimeSortedArray(new InsertionQuickSort(), "IQS", 10000);
        checkTimeSortedArray(new MergeSort(), "MS", 10000);
        checkTimeSortedArray(new InsertionMergeSort(), "IMS", 10000);
        checkTimeRandomArray(new DualPivotQuickSort(), "DP", 100000);
        sorting = new RadixSort();
        // sorting.sortWithLogs(createRandomArray(12,10000));
        startTests(1100, 300, "RS.txt", "RS2.txt", new RadixSort(), "Radix Sort");
        //sorting.sortWithLogs(createRandomArray(30, 100));
        // startTests(10000, 200, "DP.txt", "DP2.txt", sorting, "DualPivot" );
    }

    private static void checkTimeSortedArray(Sorting sorting, String name, int arraySize) {
        long start, stop, avg = 0;
        int[] tab = createSortedDescArray(arraySize);
        for (int i = 0; i < 5; i++) {
            start = System.nanoTime();
            sorting.sort(tab);
            stop = System.nanoTime();
            avg = avg + (stop - start);
        }
        System.out.println((avg / 5) + " " + name);
    }

    private static void checkTimeRandomArray(Sorting sorting, String name, int arraySize) {
        long start, stop, avg = 0;
        int[] tab;
        for (int i = 0; i < 5; i++) {
            tab = createRandomArray(arraySize, 200);
            start = System.nanoTime();
            sorting.sort(tab);
            stop = System.nanoTime();
            avg = avg + (stop - start);
        }
        System.out.println((avg / 5) + " " + name);
    }

    public static void userTests(int n, int range, String arrayType, String sortingType) {
        if (n > 0 && range > 0) {
            int[] tab = new int[n];
            Sorting s;
            switch (sortingType) {
                case "IS":
                    s = new InsertionSort();
                    break;
                case "QS":
                    s = new QuickSort();
                    break;
                case "MS":
                    s = new MergeSort();
                    break;
                case "IMS":
                    s = new InsertionMergeSort();
                default:
                    return;
            }
            if (arrayType.equals("R") || arrayType.equals("r"))
                tab = createRandomArray(n, range);
            else if (arrayType.equals("S") || arrayType.equals("s"))
                tab = createSortedDescArray(n);
            s.sortWithLogs(tab);
        }
    }

    private static int[] createRandomArray(int arraySize, int range) {
        int[] t = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            t[i] = random.nextInt(range);
        return t;
    }

    private static int[] createSortedDescArray(int arraySize) {
        int[] t = new int[arraySize];
        int tmp = arraySize - 1;
        for (int i = 0; i < arraySize; i++) {
            t[i] = tmp;
            tmp--;
        }
        return t;
    }

    public static void startTests(int tests, int range, String fileName1, String fileName2, Sorting sorting, String sortingName) throws FileNotFoundException {
        doTestOnRandomArrayAndSaveItInFile(tests, range, fileName1, sorting);
        drawPlot(sortingName, "Ciąg losowy", fileName1);
        doTestOnDescSortArrayAndSaveItInFile(tests, range, fileName2, sorting);
        drawPlot(sortingName, "Ciąg odwrotnie posortowany", fileName2);
    }


    private static void doTestOnRandomArrayAndSaveItInFile(int tests, int range, String fileName, Sorting sorting) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        int m = 4;
        int[] tmp;
        for (int i = 100; i <= tests; i += 100) {
            count.setComparisionWithKey(0);
            count.setKeySwap(0);
            for (int j = 0; j < m; j++) {
                tmp = createRandomArray(i, range);
                sorting.sort(tmp);
                count.setComparisionWithKey(count.getComparisionWithKey() + sorting.getComparisionWithKey());
                count.setKeySwap(count.getKeySwap() + sorting.getKeyShift());
            }
            writer.println(i + " " + count.getComparisionWithKey() / m + " " + count.getKeySwap() / m);
            System.out.println(i);

        }
        writer.close();
    }

    private static void doTestOnDescSortArrayAndSaveItInFile(int tests, int range, String fileName, Sorting sorting) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        int m = 4;
        int[] tmp;
        for (int i = 100; i <= tests; i += 100) {
            count.setComparisionWithKey(0);
            count.setKeySwap(0);
            for (int j = 0; j < m; j++) {
                tmp = createSortedDescArray(i);
                sorting.sort(tmp);
                count.setComparisionWithKey(count.getComparisionWithKey() + sorting.getComparisionWithKey());
                count.setKeySwap(count.getKeySwap() + sorting.getKeyShift());
                // System.out.println(i + " " + sorting.getComparisionWithKey()+ " " + sorting.getKeyShift());
            }
            writer.println(i + " " + count.getComparisionWithKey() / m + " " + count.getKeySwap() / m);
            System.out.println(i);

        }
        writer.close();
    }

    private static void drawPlot(String sortingType, String plotTitle, String fileName) throws FileNotFoundException {
        XYLineChart xyLineChart = new XYLineChart(sortingType,
                plotTitle, "n", "", fileName);

        xyLineChart.pack();
        RefineryUtilities.centerFrameOnScreen(xyLineChart);
        xyLineChart.setVisible(true);
    }
}

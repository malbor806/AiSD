import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import static java.lang.System.nanoTime;

/**
 * Created by malbor806 on 15.05.2017.
 */
public class Main {
    private static RandomString randomString;
    private static Random random;
    private static LCS lcs;
    private static EditDistance editDistance;

    public static void main(String[] args) throws FileNotFoundException {
        lcs = new LCS();
        random = new Random();
        editDistance = new EditDistance();
        randomString = new RandomString();
        String X = "ABCBDAB";
        String Y = "BDCABA";
        lcs.getLCS(X, Y, true);
        testTheSameLength(100);
        //testTheSameLength(1000, 10);
        //  startTest("lcs.txt", 5000, 10);
        // testDiffrentLength("lcs2.txt", 10000, 40);
        testDiffrentLengthEditDistance("ed.txt", 5000, 40);
    }

    private static void testDiffrentLength(String fileName, int tries, int middleTest) throws FileNotFoundException {
        PrintWriter writer;
        long avg, max;
        try {
            writer = new PrintWriter(fileName);
            for (int i = 100; i <= tries; i += 100) {
                avg = 0;
                max = 0;
                for (int j = 0; j < middleTest; j++) {
                    avg += countComparisionFromRandomWord(i); //countTimeFormRandomLength(i);
                    max += countComparisionFromTheSameLength(i);
                }
                writer.println(i + " " + (avg / middleTest) + " " + (max / middleTest));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        drawPlot("LCS", "LCS", fileName);

    }


    private static void testDiffrentLengthEditDistance(String fileName, int tries, int middleTest) throws FileNotFoundException {
        PrintWriter writer;
        long avg, max;
        try {
            writer = new PrintWriter(fileName);
            for (int i = 100; i <= tries; i += 100) {
                avg = 0;
                max = 0;
                for (int j = 0; j < middleTest; j++) {
                    countComparisionFromEditDistance(i);
                    avg += editDistance.getCounter(); //countTimeFormRandomLength(i);
                    countComparisionFromTheSameLengthEditDistance(i);
                    max += editDistance.getCounter();
                }
                writer.println(i + " " + (avg / middleTest) + " " + (max/middleTest));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        drawPlot("LCS", "LCS", fileName);

    }

    private static void countComparisionFromTheSameLengthEditDistance(int wordLength) {
        String X, Y;
        X = randomString.createRandomWord(wordLength);
        Y = randomString.createRandomWord(wordLength);
        editDistance.getEditDistance(X,Y);
    }

    private static long countComparisionFromTheSameLength(int i) {
        String X, Y;
        X = randomString.createRandomWord(i);
        Y = randomString.createRandomWord(i);
        lcs.getLCS(X, Y, false);
        return lcs.getCounter();
    }

    private static long countComparisionFromRandomWord(int i) {
        String X, Y;
        X = randomString.createRandomWord(random.nextInt(i));
        Y = randomString.createRandomWord(random.nextInt(i));
        lcs.getLCS(X, Y, false);
        return lcs.getCounter();
    }

    private static long countComparisionFromEditDistance(int i) {
        String X, Y;
        X = randomString.createRandomWord(random.nextInt(i));
        Y = randomString.createRandomWord(random.nextInt(i));
        editDistance.getEditDistance(X, Y);
        return lcs.getCounter();
    }


    private static long countTimeFormRandomLength(int i) {
        String X, Y;
        long start, end;
        X = randomString.createRandomWord(random.nextInt(i));
        Y = randomString.createRandomWord(random.nextInt(i));
        start = nanoTime();
        lcs.getLCS(X, Y, false);
        end = nanoTime();
        return (end - start);
    }


    private static void testTheSameLength(int wordLength) {
        String X, Y;
        X = randomString.createRandomWord(wordLength);
        Y = randomString.createRandomWord(wordLength);
        lcs.getLCS(X, Y, true);
    }

    private static long countTime(int wordLength) {
        String X, Y;
        long start, end;
        X = randomString.createRandomWord(wordLength);
        Y = randomString.createRandomWord(wordLength);
        start = nanoTime();
        lcs.getLCS(X, Y, false);
        end = nanoTime();
        return (end - start);
    }


    private static void startTest(String fileName, int tries, int middleTest) throws FileNotFoundException {
        saveValuesToFile(fileName, tries, middleTest);
        drawPlot("LCS", "LCS", fileName);
    }

    private static void saveValuesToFile(String fileName, int tries, int middleTest) {
        PrintWriter writer;
        long avgTime;
        try {
            writer = new PrintWriter(fileName);
            for (int i = 100; i <= tries; i += 100) {
                avgTime = 0;
                for (int j = 0; j < middleTest; j++) {
                    avgTime += countTime(i);
                }
                writer.println(i + " " + (avgTime / middleTest));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void drawPlot(String testType, String plotTitle, String fileName) throws FileNotFoundException {
        XYLineChart xyLineChart = new XYLineChart(testType,
                plotTitle, "n", "", fileName);

        xyLineChart.pack();
        RefineryUtilities.centerFrameOnScreen(xyLineChart);
        xyLineChart.setVisible(true);
    }

}

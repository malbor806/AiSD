import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Main {
    private static Random random;

    public static void main(String[] args) {
        random = new Random();
        startTests();
    }


    private  static void startTests(){
        ArrayList<Long> statsInsert = new ArrayList<Long>();
        ArrayList<Long> statsExtract = new ArrayList<Long>();
        ArrayList<Long> statsDecrease = new ArrayList<Long>();
        for (int i = 1000; i <= 100000; i+=1000){
            Heap heap = new Heap();
            createRandomList(heap, i);
            heap.buildHeap();
            heap.setComparisionExtract(0);
            statsInsert.add(heap.getComparisionInsert());
            for (int j = 0; j < 1000; j++){
                heap.decreaseKey(j+1, j-1);
                heap.extractMin();
            }
            statsDecrease.add(heap.getComparisionDecrease());
            statsExtract.add(heap.getComparisionExtract());
        }
        try {
            drawPlot("Heap -insert", "insert", "insert");
            drawPlot("Heap -extract", "extract", "extract");
            drawPlot("Heap -decrease", "decrease", "decrease");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createRandomList(Heap heap,int size) {
        List<Integer> list = createSortedList(size);
        int i = 0, index;
        while (i < size) {
            index = random.nextInt(list.size());
            heap.insert(index);
            list.remove(index);
            i++;
        }
    }

    private static List<Integer> createSortedList(int size) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = size; i >= 0; i--) {
            list.add(i);
        }
        return list;
    }

    private static void saveData(ArrayList<Long> stats, String fileName) {
        PrintWriter writer;
        long stat;
        try {
            writer = new PrintWriter(fileName);
            for (int i = 1; i <= stats.size(); i++) {
                writer.println(i*1000 + " " + stats.get(i-1));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void drawPlot(String sortingType, String plotTitle, String fileName) throws FileNotFoundException {
        XYLineChart xyLineChart = new XYLineChart(sortingType,
                plotTitle, "n", "", fileName);

        xyLineChart.pack();
        RefineryUtilities.centerFrameOnScreen(xyLineChart);
        xyLineChart.setVisible(true);
    }
}

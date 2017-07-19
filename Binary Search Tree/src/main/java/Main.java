import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by malbor806 on 28.04.2017.
 */
public class Main {
    private static Random random;
    private static long time;
    private static int level;
    private static int height;
    private static ArrayList<Long> counters;

    public static void main(String[] args) {
        random = new Random();
        //     readDataFromFile(args[0]);
        System.out.println("-------------------------");
        //startTest();
        counters = new ArrayList<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.osSelect(1);
        bst.osRank(2);
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(7);
        bst.insert(6);
        bst.delete(8);
        bst.insert(1);
        bst.inOrder();
        bst.osRank(3);
        bst.osSelect(5);
        test();
    }


    private static void test() {
        for (int i = 2000; i < 100000; i = i + 1000) {
            testOsSelect(i, 100);
        }
        saveData(counters, "osSelect.txt");
        System.out.println(counters);
    }

    private static void saveData(ArrayList<Long> stats, String fileName) {
        PrintWriter writer;
        long stat;
        try {
            writer = new PrintWriter(fileName);
            for (int i = 0; i < stats.size(); i++) {
                writer.println(i + " " + stats.get(i));
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testOsSelect(int tries, int middle) {
        long counter = 0;
        int k;
        BinarySearchTree<Integer> bst;
        bst = new BinarySearchTree<>();
        createRandomTree(bst, tries);
        for (int i = 0; i < middle; i++) {

            k = random.nextInt(tries);
            bst.osSelect(k);
            counter += bst.getCounter();
        }
        counters.add(counter / middle);
    }

    private static void startTest() {
        for (int i = 5000; i < 15000; i = i + 1000) {
            getStats(i, 30);
            System.out.println("-----------------------------");
        }
    }

    private static void getStats(int size, int tries) {
        getStatsFromBalancedTree(size, tries);
        getStatsForRandomTree(size, tries);
        getStatsForSortedTree(size, tries);
    }

    private static void getStatsFromBalancedTree(int size, int tries) {
        BinarySearchTree<Integer> bst;
        long avgTime = 0, avgLvl = 0, avgHeight = 0;
        for (int i = 0; i < tries; i++) {
            bst = new BinarySearchTree<>();
            createBalancedTree(bst, size);
            checkTime(bst);
            avgTime += time;
            avgLvl += level;
            avgHeight += height;
        }
        System.out.println("Time: " + avgTime / tries + "  avg lvl of min elem " + avgLvl / tries + "  avgHeight " + avgHeight / tries);
    }

    private static void getStatsForRandomTree(int size, int tries) {
        long avgTime = 0, avgLvl = 0, avgHeight = 0;
        BinarySearchTree<Integer> bst;
        for (int i = 0; i < tries; i++) {
            bst = new BinarySearchTree<>();
            createRandomTree(bst, size);
            checkTime(bst);
            avgTime += time;
            avgLvl += level;
            avgHeight += height;
        }
        System.out.println("Time: " + avgTime / tries + "  avg lvl of min elem " + avgLvl / tries + "  avgHeight " + avgHeight / tries);
    }

    private static void getStatsForSortedTree(int size, int tries) {
        long avgTime = 0, avgLvl = 0, avgHeight = 0;
        BinarySearchTree<Integer> bst;
        for (int i = 0; i < tries; i++) {
            bst = new BinarySearchTree<>();
            createSortedTree(bst, size);
            checkTime(bst);
            avgTime += time;
            avgLvl += level;
            avgHeight += height;
        }
        System.out.println("Time: " + avgTime / tries + "  avg lvl of min elem " + avgLvl / tries + "  avgHeight " + avgHeight / tries);
    }

    private static void createBalancedTree(BinarySearchTree bst, int size) {
        ArrayList<Integer> list = (ArrayList<Integer>) createSortedList(size);
        bst.createBalancedTree(bst, list, 0, size - 1);
    }

    private static void createSortedTree(BinarySearchTree tree, int size) {
        for (int i = 1; i <= size; i++) {
            tree.insert(i);
        }
    }

    private static void createRandomTree(BinarySearchTree tree, int size) {
        List<Integer> list = createSortedList(size);
        int i = 0, index;
        while (i < size) {
            index = random.nextInt(list.size());
            tree.insert(list.get(index));
            list.remove(index);
            i++;
        }
    }

    private static List<Integer> createSortedList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    private static void checkTime(BinarySearchTree bst) {
        long start, stop;
        start = System.nanoTime();
        level = bst.maxTest();
        stop = System.nanoTime();
        time = (stop - start);
        height = bst.getHeight();
    }

    private static void readDataFromFile(String fileName) {
        String line;
        ArrayList<String> data = new ArrayList<>();
        File input = new File(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int num = Integer.parseInt(data.get(0));
        for (int j = 1; j <= num; j++) {
            String[] d = data.get(j).split(" ");
            switch (d[0]) {
                case "insert":
                    tree.insert(Integer.parseInt(d[1]));
                    break;
                case "delete":
                    tree.delete(Integer.parseInt(d[1]));
                    break;
                case "find":
                    tree.find(Integer.parseInt(d[1]));
                    break;
                case "min":
                    tree.min();
                    break;
                case "max":
                    tree.max();
                    break;
                case "inorder":
                    tree.inOrder();
                    break;
            }

        }

    }
}

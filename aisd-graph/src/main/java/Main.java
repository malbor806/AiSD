import graph.DirectedGraph;
import graph.UndirectedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malbor806 on 30.05.2017.
 */
public class Main {
    private static List<String> data;

    public static void main(String[] args) {
        if (args.length > 0) {
            data = new ArrayList<String>();
            readDataFromFile(args[0]);
            DirectedGraph graph = new DirectedGraph(data);
            graph.findShortestPathsToVertices(0);
            UndirectedGraph undirectedGraph = new UndirectedGraph(data);
            undirectedGraph.findMSTusingPrime(0);
            undirectedGraph.findMSTusingKruskal(0);
        }
    }

    private static void readDataFromFile(String fileName) {
        File file = new File(fileName);
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.io.*;

/**
 * Created by malbor806 on 23.06.2017.
 */
public class XYLineChart extends ApplicationFrame {

    public XYLineChart(String applicationTitle, String chartTitle, String x, String y, String fileName) throws FileNotFoundException {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                chartTitle,
                x, y,
                createDataset(fileName),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset(String fileName) throws FileNotFoundException {
        XYSeries comp = new XYSeries("porownanie");
        XYSeriesCollection dataset = new XYSeriesCollection();
        String[] line;
        String lineFromFile;
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        long comparision, swaps;
        int index;
        try {
            while ((lineFromFile = br.readLine()) != null) {
                line = lineFromFile.split(" ");
                index = Integer.parseInt(line[0]);
                comparision = Long.parseLong(line[1]);
                comp.add(index, comparision);
            }

            br.close();
            dataset.addSeries(comp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}

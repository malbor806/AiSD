import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.io.*;

/**
 * Created by malbor806 on 17.05.2017.
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
        XYSeries times = new XYSeries("avg");
        XYSeries maxs = new XYSeries("max");
        XYSeriesCollection dataset = new XYSeriesCollection();
        String[] line;
        String lineFromFile;
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        long avg, max;
        int index;
        try {
            while ((lineFromFile = br.readLine()) != null) {
                line = lineFromFile.split(" ");
                index = Integer.parseInt(line[0]);
                avg = Long.parseLong(line[1]);
                max = Long.parseLong(line[2]);
                times.add(index, avg);
                maxs.add(index, max);

            }

            br.close();
            dataset.addSeries(times);
            dataset.addSeries(maxs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}

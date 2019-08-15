package UI;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class NewClass extends ApplicationFrame {

    public NewClass(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Particles",
                "gbest",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        HashMap<Double, Integer> map = new HashMap<>();
        map.put(2.3, 290);
        map.put(1.0, 820);
        map.put(1.1, 2);
        map.put(10.0, 90);
        map.put(34.6, 20);
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            dataset.addValue((Number) pair.getKey(), (Comparable) pair.getValue(), (Comparable)2);
        }

        return dataset;
    }

    public static void main(String[] args) {
        NewClass chart = new NewClass("Chart",
                "Particle's Gbest through iterations?");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}

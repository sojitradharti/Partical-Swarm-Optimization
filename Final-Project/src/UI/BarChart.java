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

public class BarChart extends ApplicationFrame {

    public BarChart(String applicationTitle, String chartTitle) {
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
        Map<Double, Map<Double, Double>> particles = new HashMap<>();
        particles.put(1.0, new HashMap() {
            {
                put(5.2, 7.7);
            }
        });
        particles.put(2.0, new HashMap() {
            {
                put(0.2, 8.7);
            }
        });
        particles.put(3.0, new HashMap() {
            {
                put(9.2, 3.7);
            }
        });
        particles.put(4.0, new HashMap() {
            {
                put(3.2, 5.7);
            }
        });
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        for (Map.Entry<Double, Map<Double, Double>> entry : particles.entrySet()) {
            for (Map.Entry<Double, Double> e : entry.getValue().entrySet()) {
                dataset.addValue(e.getKey(), (Comparable) entry.getKey(), (Comparable) e.getValue());
            }
        }

        return dataset;
    }

    public static void main(String[] args) {
        BarChart chart = new BarChart("Chart",
                "Particle's Gbest through iterations?");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}

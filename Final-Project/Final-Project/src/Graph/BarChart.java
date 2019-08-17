package Graph;

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

    Map<Double, Map<Double, Double>> particles;

    public BarChart(String applicationTitle, String chartTitle, Map<Double, Map<Double, Double>> particles) {
        super(applicationTitle);
        this.particles = particles;
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

        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        for (Map.Entry<Double, Map<Double, Double>> entry : particles.entrySet()) {
            for (Map.Entry<Double, Double> e : entry.getValue().entrySet()) {
                dataset.addValue(e.getValue(),entry.getKey(),  e.getKey());
            }
        }

        return dataset;
    }
}

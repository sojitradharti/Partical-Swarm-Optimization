/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.awt.Color;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author premprakash.k
 */
public class LineChart extends ApplicationFrame {

    public LineChart(final String title, Map<Double, Map<Double, Double>> particles) {

        super(title);

        final XYDataset dataset = createDataset(particles);
        final JFreeChart chart = createChart(title, dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 1000));
        setContentPane(chartPanel);

    }

    private XYDataset createDataset(Map<Double, Map<Double, Double>> particles) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        particles.entrySet().stream().map((entry) -> {
            XYSeries series = new XYSeries(entry.getKey());
            entry.getValue().entrySet().stream().forEach((e) -> {
                series.add(e.getKey(), e.getValue());
            });
            return series;
        }).forEach((series) -> {
            dataset.addSeries(series);
        });

        return dataset;

    }

    private JFreeChart createChart(String title, XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Iterations ",
                "pBest",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.GRAY);
        final XYPlot plot = chart.getXYPlot();
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;

    }

}

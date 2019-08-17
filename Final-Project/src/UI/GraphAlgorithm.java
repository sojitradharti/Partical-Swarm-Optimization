package UI;


import java.awt.BorderLayout;import java.awt.Insets;

import javax.swing.JFrame;

public class GraphAlgorithm extends java.applet.Applet {

    int maxnodes = 10;
    int[] links = {4, 2, 3, 5, 1};
    int[] route = {0, 4, 0, 2, 4, 3};
    GraphCanvas graphcanvas;

    public void init() {
        setLayout(new BorderLayout(10, 10));
        graphcanvas = new GraphCanvas(this, maxnodes, links, route);
        add("Center", graphcanvas);

    }

    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }

    public static void main(String arg[]) {
        GraphAlgorithm g = new GraphAlgorithm();
        JFrame frame = new JFrame("Dijkstra");
        g.init();

        frame.add("Center", g);

        frame.resize(800, 600);
        frame.show();
        frame.setSize(800, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

package  Animation;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.table.TableColumn;


public class GraphAlgorithm extends java.applet.Applet {
//	int maxnodes = 21;
//	int[] links = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//	int[] route = {0,1,0,2,3,4,0,5,6,7,8,9,10,11,12,13,0,14,15,16,17,0,19,20};
    GraphCanvas graphcanvas ;


    public void setparameters(int maxnodes,int[] links,int[] route) {
	setLayout(new BorderLayout(10, 10));
	graphcanvas = new GraphCanvas(this,maxnodes,links, route);
	add( "Center",graphcanvas);

    }

    public Insets insets() {
	return new Insets(10, 10, 10, 10);
    }
    

}


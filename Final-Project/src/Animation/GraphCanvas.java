package Animation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

public class GraphCanvas extends Canvas implements Runnable {

	int MAXNODES;
	int MAX = MAXNODES +1;
	final int NODESIZE = 30;
	final int NODERADIX = 20;
	final int DIJKSTRA = 1;
	boolean perfalgo;

	Point node[] = new Point[MAX]; // node
	int weight[][] ; // weight of arrow
	Point arrow[][] = new Point[MAX][MAX]; // current position of arrowhead
	Point startp[][] = new Point[MAX][MAX]; // start and
	Point endp[][] = new Point[MAX][MAX]; // endpoint of arrow
	float dir_x[][] = new float[MAX][MAX]; // direction of arrow
	float dir_y[][] = new float[MAX][MAX];
	boolean algedge[][] = new boolean[MAX][MAX];
	int dist[] = new int[MAX];
	int finaldist[] = new int[MAX];
	Color colornode[] = new Color[99999];
	boolean changed[] = new boolean[MAX];
	int link[];
	int route[];
	 // indicates distance change during
											// algorithm
	int numchanged = 0;
	int neighbours = 0;

	int mindist, minnode, minstart, minend;

	int numnodes = 0; // number of nodes
	int emptyspots = 0; // empty spots in array node[] (due to node deletion)
	int startgraph = 0; // start of graph
	int hitnode; // mouse clicked on or close to this node
	int node1, node2;

	Thread algrthm;

	Font roman = new Font("TimesRoman", Font.BOLD, 12);
	Font helvetica = new Font("Helvetica", Font.BOLD, 15);
	FontMetrics fmetrics = getFontMetrics(roman);
	int h = (int) fmetrics.getHeight() / 3;
	GraphAlgorithm parent;
	
	public GraphCanvas(GraphAlgorithm myparent,int maxnodes,  int links[],int route[] ) {
		MAXNODES = maxnodes;
		MAX = MAXNODES+1;
		parent = myparent;
		init();
		
		setBackground(Color.lightGray);
		link = links;
		this.route = route;

	}
	
	
	
	public void calculateWeight(){
		
		for(int i=1;i<MAXNODES;i++){
			weight[0][i] = 5;
			weight[i][0] = 5;
		}
		for(int i=0; i<link.length-1; i++){
			weight[link[i]][link[i+1]]=5;
		}
		
	}

	public void init() {
		node = new Point[MAX];
		weight = new int[MAX][MAX];
		arrow = new Point[MAX][MAX];
		startp = new Point[MAX][MAX];
		endp = new Point[MAX][MAX];
		dir_x = new float[MAX][MAX];
		dir_y = new float[MAX][MAX];
		algedge = new boolean[MAX][MAX];
		dist = new int[MAX];
		finaldist = new int[MAX];
		colornode = new Color[MAX];
		changed = new boolean[MAX];
		for (int i = 0; i < MAXNODES; i++) {
			colornode[i] = Color.black; // oval color
			for (int j = 0; j < MAXNODES; j++)
				algedge[i][j] = false;
		
		}
		
		colornode[startgraph] = Color.white; // 0 node color
                // performalg = false;
	}

	public void showexample() {
		// draws a graph on the screen
		
		int w, h;
		// clear();
		// init();
		numnodes = MAXNODES;
		emptyspots = 0;
		for (int i = 0; i < MAXNODES; i++) {
			node[i] = new Point(0, 0);
			for (int j = 0; j < MAXNODES; j++)
					weight[i][j] = 0;
		}
		w = this.size().width / 4;
		h = this.size().height / 4;
		node[0] = new Point(w, h);

		Random rand = new Random();

		for (int i = 1; i < MAXNODES; i++) {
			boolean sidelocation = rand.nextBoolean();
			Point point = new Point();
			int mod = i % 2;
			if (mod == 0) {

				// point.x=(int) (node[0].getX()+(rand.nextInt(700-450))+250);
				// point.y = (int) (node[0].getY()+(rand.nextInt(700-450))+250);
				// node[i] = point;
				if(sidelocation)
					node[i] = new Point( (int) ((int) (i * w) / 6)+rand.nextInt(20 ), h + rand.nextInt(150- 100 )+300);// dharti
				else
					node[i] = new Point( (int) ((int) (i * w) / 6)-rand.nextInt(20), h - rand.nextInt(150 - 100 )+300);
			} else {
				// point.x=(int) (node[0].getX()-(rand.nextInt(700-450))+250);
				// point.y = (int) (node[0].getY()-(rand.nextInt(700-450))+250);
				// node[i] = point;
				if(sidelocation)
					node[i] = new Point(w + rand.nextInt(150 - 100 )+300, (int) ((h * i) / 6)+rand.nextInt(20 ));
				else
					node[i] = new Point(w - rand.nextInt(150 - 100 )+300, (int) ((h * i) / 6)-rand.nextInt(20 ));
			}
		}
		calculateWeight();


		for (int i = 0; i < numnodes; i++)
			for (int j = 0; j < numnodes; j++)
				if (weight[i][j] > 0)
					arrowupdate(i, j, weight[i][j]);
		//repaint();

	}

	public void arrowupdate(int p1, int p2, int w) {
		// make a new arrow from node p1 to p2 with weight w, or change
		// the weight of the existing arrow to w, calculate the resulting
		// position of the arrowhead
		int dx, dy;
		float l;
		weight[p1][p2] = w;

		// direction line between p1 and p2
		dx = node[p2].x - node[p1].x;
		dy = node[p2].y - node[p1].y;

		// distance between p1 and p2
		l = (float) (Math.sqrt((float) (dx * dx + dy * dy)));
		dir_x[p1][p2] = dx / l;
		dir_y[p1][p2] = dy / l;

		// calculate the start and endpoints of the arrow,
		// adjust startpoints if there also is an arrow from p2 to p1
		if (weight[p2][p1] > 0) {
			startp[p1][p2] = new Point((int) (node[p1].x - 5 * dir_y[p1][p2]),
					(int) (node[p1].y + 5 * dir_x[p1][p2]));
			endp[p1][p2] = new Point((int) (node[p2].x - 5 * dir_y[p1][p2]),
					(int) (node[p2].y + 5 * dir_x[p1][p2]));
		} else {
			startp[p1][p2] = new Point(node[p1].x, node[p1].y);
			endp[p1][p2] = new Point(node[p2].x, node[p2].y);
		}

		// range for arrowhead is not all the way to the start/endpoints
		int diff_x = (int) (Math.abs(20 * dir_x[p1][p2]));
		int diff_y = (int) (Math.abs(20 * dir_y[p1][p2]));

		// calculate new x-position arrowhead
		if (startp[p1][p2].x > endp[p1][p2].x) {
			arrow[p1][p2] = new Point(
					endp[p1][p2].x
							+ diff_x
							+ (Math.abs(endp[p1][p2].x - startp[p1][p2].x) - 2 * diff_x)
							* (100 - w) / 100, 0);
		} else {
			arrow[p1][p2] = new Point(
					startp[p1][p2].x
							+ diff_x
							+ (Math.abs(endp[p1][p2].x - startp[p1][p2].x) - 2 * diff_x)
							* w / 100, 0);
		}

		// calculate new y-position arrowhead
		if (startp[p1][p2].y > endp[p1][p2].y) {
			arrow[p1][p2].y = endp[p1][p2].y
					+ diff_y
					+ (Math.abs(endp[p1][p2].y - startp[p1][p2].y) - 2 * diff_y)
					* (100 - w) / 100;
		} else {
			arrow[p1][p2].y = startp[p1][p2].y
					+ diff_y
					+ (Math.abs(endp[p1][p2].y - startp[p1][p2].y) - 2 * diff_y)
					* w / 100;
		}
		
	}

	private Image offScreenImage;
	private Graphics offScreenGraphics;
	private Dimension offScreenSize;

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
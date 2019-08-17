package UI;


import UI.GraphAlgorithm;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

class GraphCanvas extends Canvas implements Runnable {

    int MAXNODES;
    int MAX = MAXNODES + 1;
    final int NODESIZE = 30;
    final int NODERADIX = 20;
    final int DIJKSTRA = 1;
    boolean perfalgo;

    Point node[] = new Point[MAX]; // node
    int weight[][]; // weight of arrow
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

    GraphCanvas(GraphAlgorithm myparent, int maxnodes, int links[], int route[]) {
        MAXNODES = maxnodes;
        MAX = MAXNODES + 1;
        parent = myparent;
        init();
        // algorithm=DIJKSTRA;
        setBackground(Color.gray);
        link = links;
        this.route = route;

    }

    public void calculateWeight() {
        System.out.println("calculating weight");
        for (int i = 1; i < MAXNODES; i++) {
            weight[0][i] = 5;
            weight[i][0] = 5;
        }
        for (int i = 0; i < link.length - 1; i++) {
            weight[link[i]][link[i + 1]] = 5;
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
            colornode[i] = Color.gray;
            for (int j = 0; j < MAXNODES; j++) {
                algedge[i][j] = false;
            }

        }

        colornode[startgraph] = Color.blue;
        // performalg = false;
    }

    public void start() {

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
            for (int j = 0; j < MAXNODES; j++) {
                weight[i][j] = 0;
            }
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
                if (sidelocation) {
                    node[i] = new Point((int) ((int) (i * w) / 6) + rand.nextInt(20), h + rand.nextInt(150 - 100) + 100);
                } else {
                    node[i] = new Point((int) ((int) (i * w) / 6) - rand.nextInt(20), h - rand.nextInt(150 - 100) + 100);
                }
            } else {
				// point.x=(int) (node[0].getX()-(rand.nextInt(700-450))+250);
                // point.y = (int) (node[0].getY()-(rand.nextInt(700-450))+250);
                // node[i] = point;
                if (sidelocation) {
                    node[i] = new Point(w + rand.nextInt(150 - 100) + 100, (int) ((h * i) / 6) + rand.nextInt(20));
                } else {
                    node[i] = new Point(w - rand.nextInt(150 - 100) + 100, (int) ((h * i) / 6) - rand.nextInt(20));
                }
            }
        }
        calculateWeight();

        for (int i = 0; i < numnodes; i++) {
            for (int j = 0; j < numnodes; j++) {
                if (weight[i][j] > 0) {
                    arrowupdate(i, j, weight[i][j]);
                }
            }
        }
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

    public final synchronized void update(Graphics g) {
        // prepare new image offscreen
        Dimension d = size();
        if ((offScreenImage == null) || (d.width != offScreenSize.width)
                || (d.height != offScreenSize.height)) {
            offScreenImage = createImage(d.width, d.height);
            offScreenSize = d;
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.setColor(Color.white);
        offScreenGraphics.fillRect(0, 0, d.width, d.height);
        paint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void drawarrow(Graphics g, int i, int j) {
        // draw arrow between node i and node j
        int x1, x2, x3, y1, y2, y3;

        // calculate arrowhead
        x1 = (int) (arrow[i][j].x - 3 * dir_x[i][j] + 6 * dir_y[i][j]);
        x2 = (int) (arrow[i][j].x - 3 * dir_x[i][j] - 6 * dir_y[i][j]);
        x3 = (int) (arrow[i][j].x + 6 * dir_x[i][j]);

        y1 = (int) (arrow[i][j].y - 3 * dir_y[i][j] - 6 * dir_x[i][j]);
        y2 = (int) (arrow[i][j].y - 3 * dir_y[i][j] + 6 * dir_x[i][j]);
        y3 = (int) (arrow[i][j].y + 6 * dir_y[i][j]);

        int arrowhead_x[] = {x1, x2, x3, x1};
        int arrowhead_y[] = {y1, y2, y3, y1};

		// if edge already chosen by algorithm change color
        // draw arrow
        g.drawLine(startp[i][j].x, startp[i][j].y, endp[i][j].x, endp[i][j].y);
        g.fillPolygon(arrowhead_x, arrowhead_y, 4);

		// write weight of arrow at an appropriate position
        //
    }

    public void start_animation() {

        perfalgo = true;

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public void paint(Graphics g) {
        showexample();
        mindist = 0;
        minnode = MAXNODES;
        minstart = MAXNODES;
        minend = MAXNODES;
        for (int i = 0; i < MAXNODES; i++) {
            changed[i] = false;
        }
        numchanged = 0;
        neighbours = 0;
        g.setFont(roman);
        g.setColor(Color.black);
		// if (step==1)
        // showstring="Algorithm running: red arrows point to nodes reachable from "
        // +
        // " the startnode.\nThe distance to: ";
        // else
        // showstring="Step " + step +
        // ": Red arrows point to nodes reachable from " +
        // "nodes that already have a final distance." +
        // "\nThe distance to: ";

		// draw a new arrow upto current mouse position
        // if (newarrow)
        // g.drawLine(node[node1].x, node[node1].y, thispoint.x, thispoint.y);
        // draw all arrows
        for (int i = 0; i < numnodes; i++) {
            for (int j = 0; j < numnodes; j++) {
                if (weight[i][j] > 0) {
					// if algorithm is running then perform next step for this
                    // arrow
                    // if (performalg)
                    // detailsalg(g, i, j);
                    drawarrow(g, i, j);
                }
            }
        }

		// if arrowhead has been dragged to 0, draw it anyway, so the user
        // will have the option to make it positive again
        // if (movearrow && weight[node1][node2]==0) {
        // drawarrow(g, node1, node2);
        // g.drawLine(startp[node1][node2].x, startp[node1][node2].y,
        // endp[node1][node2].x, endp[node1][node2].y);
        // }
        // draw the nodes
        for (int i = 0; i < numnodes; i++) {
            if (node[i].x > 0) {
                g.setColor(colornode[i]);
                g.fillOval(node[i].x - NODERADIX, node[i].y - NODERADIX,
                        NODESIZE, NODESIZE);
            }
        }

        // reflect the startnode being moved
        g.setColor(Color.blue);
		// if (movestart)
        // NODESIZE, NODESIZE);

        g.setColor(Color.black);
		// finish this step of the algorithm
        // if (performalg) endstepalg(g);

        // draw black circles around nodes, write their names to the screen
        g.setFont(helvetica);
        for (int i = 0; i < numnodes; i++) {
            if (node[i].x > 0) {
                g.setColor(Color.black);
                g.drawOval(node[i].x - NODERADIX, node[i].y - NODERADIX,
                        NODESIZE, NODESIZE);
                g.setColor(Color.RED);
                g.drawString("" + i, node[i].x, node[i].y);
            }
        }
        start_animation();
        if (perfalgo) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i < route.length - 1; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                g.setColor(Color.red);
                drawarrow(g, route[i], route[i + 1]);
            }
        }
        //showexample();
    }
}

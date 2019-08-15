


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BallAnimation {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> arr = new ArrayList();
   
    
    
   
    public static void main(String[] args) {
         for(int i=0; i<= 20; i++)
    {
        arr.add(i);
    }
        new BallAnimation();
    }

    public BallAnimation() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private int x = 0;
        private int y = 20;

        public TestPane() {
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveBall();
                    repaint();
                }
            });
            timer.start();
        }

        protected void moveBall() {
            x++;
            //y++;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
public  int[] RandomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
        @Override
        
        protected void paintComponent(Graphics g) {
             Graphics2D g2d;
            super.paintComponent(g);
            for( int i= 0; i< arr.size()-2;i++)
            {
            g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLUE);
            g2d.drawString(i+" ", arr.get(i)+(arr.get(i)*30)+10, y);
            g2d.fillOval(arr.get(i)+(arr.get(i)*30)+10, y, 30, 30);
           
            Collections.shuffle(arr);
             
            }
                    
           
            
            
        }
        

    }

}
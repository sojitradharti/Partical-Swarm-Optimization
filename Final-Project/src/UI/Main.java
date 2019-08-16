/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Graph.BarChart;
import Animation.AnimationUI;
import Animation.GraphUI;
import Business.Graph;
import Business.Location;
import Business.LocationModel;
import Business.ParticleModel;
import Graph.LineChart;
import PSO.Particle;
import PSO.Swarm;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author premprakash.k
 */
public class Main extends javax.swing.JFrame {

    public static int ParticleCount;
    public static int noOfLocations;
    public static int maxLocationOrders;
    public static int maxSalesmanCapacity;

    private static int getRandom() {
        Random rand = new Random();
        int val = rand.nextInt(80);
        if (val == 0) {
            val = val + 1;
        }
        return val;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int target;
    static Graph graph;
    static ParticleModel pm;
    static Location loc;
    static int iterations;
    static Swarm swarm;
    static LocationModel locmodel;

    /**
     * Creates new form NewJFrame
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        particleCount = new javax.swing.JLabel();
        locationCount = new javax.swing.JLabel();
        particlesInput = new javax.swing.JTextField();
        locationinput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        IterationInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        targetInput = new javax.swing.JTextField();
        btnRun = new javax.swing.JButton();
        maxPacket = new javax.swing.JLabel();
        maxPacketInput = new javax.swing.JTextField();
        locDemand = new javax.swing.JLabel();
        maxLocDemand = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Particle Swarm Optimization");

        particleCount.setText("Number of Salesperson :");

        locationCount.setText("Number of locations : ");

        particlesInput.setText("5");
        particlesInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                particlesInputActionPerformed(evt);
            }
        });

        locationinput.setText("10");

        jLabel2.setText("Max Iterations :");

        IterationInput.setText("5");
        IterationInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IterationInputActionPerformed(evt);
            }
        });

        jLabel3.setText("Target Value : ");

        targetInput.setText("14");
        targetInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetInputActionPerformed(evt);
            }
        });

        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        maxPacket.setText("Max Packet carried by Salesperson :");

        maxPacketInput.setText("2");
        maxPacketInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxPacketInputActionPerformed(evt);
            }
        });

        locDemand.setText("Max location's demand :");

        maxLocDemand.setText("3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(particleCount)
                            .addComponent(locationCount)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(maxPacket)
                            .addComponent(locDemand))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxPacketInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(particlesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationinput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxLocDemand, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IterationInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(targetInput, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(btnRun)))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(particleCount)
                    .addComponent(particlesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxPacket)
                    .addComponent(maxPacketInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationCount)
                    .addComponent(locationinput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locDemand)
                    .addComponent(maxLocDemand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(IterationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(targetInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnRun)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void particlesInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_particlesInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_particlesInputActionPerformed

    private void IterationInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IterationInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IterationInputActionPerformed

    private void targetInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetInputActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        // TODO add your handling code here:
        ParticleCount = Integer.parseInt(particlesInput.getText());
        noOfLocations = Integer.parseInt(locationinput.getText());
        iterations = Integer.parseInt(IterationInput.getText());
        target = Integer.parseInt(targetInput.getText());
        maxSalesmanCapacity = Integer.parseInt(maxPacketInput.getText());
        maxLocationOrders = Integer.parseInt(maxLocDemand.getText());
        Pattern regEx = Pattern.compile("\\d*");
//        if (!regEx.matcher(particlesInput.getText()).matches() &&!regEx.matcher(maxPacketInput.getText()).matches() && !regEx.matcher(maxLocDemand.getText()).matches() && !regEx.matcher(locationinput.getText()).matches() &&!regEx.matcher(IterationInput.getText()).matches() && ! regEx.matcher(targetInput.getText()).matches()) {
//            JOptionPane.showMessageDialog(null, "Please enter digits only");
//        }

        graph = new Graph(noOfLocations);
        CreateGraph();
        pm = new ParticleModel(ParticleCount, graph, maxSalesmanCapacity, noOfLocations);
        locmodel = new LocationModel(noOfLocations, maxLocationOrders);
        //loc = new Location(noOfLocations, maxLocationDemand);

        //  print(pm.arrParticle);
        // print graph adjacency matrix
        System.out.println("The adjacency matrix for the given graph is: ");
        for (int i = 0; i <= noOfLocations; i++) {

            for (int j = 0; j <= noOfLocations; j++) {
                System.out.print(graph.getEdge(i, j) + "\t");
            }
            System.out.println();
        }

        CreateSwarm();

        HashMap<Double, Map<Double, Double>> particles = new HashMap<Double, Map<Double, Double>>();
        for (int num = 1; num <= iterations; num++) {
            System.out.println("-----------------------------Iteration :" + num + "------------------------------\n");
            if (swarm.calculatebestSolution(target)) {
                swarm.trackResultOfParticle(num, particles);
                System.out.println("Target reached at iteration : " + num);
                break;
            } else {
                swarm.trackResultOfParticle(num, particles);
            }

        }
        int[] BestRoute = swarm.getBestRoute();
        System.out.println("The most efficient route is : " + Arrays.toString(BestRoute));

        //Bar chart display code
        BarChart barChart = new BarChart("Chart",
                "Particle's Gbest through iterations", particles);
        barChart.pack();
        RefineryUtilities.centerFrameOnScreen(barChart);
        barChart.setVisible(true);

        //Line chart display code
        LineChart chart = new LineChart("Particles", particles);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
        Map<String, List<Integer>> ResultModel = swarm.CountBestRouteRounds(BestRoute);
        for (Map.Entry<String, List<Integer>> entry : ResultModel.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            // TODO: animation
        }
        //  Map<String, List<Integer>> ResultModel =  swarm.CountBestRouteRounds(BestRoute);

        //System.out.print("");

    }//GEN-LAST:event_btnRunActionPerformed


    private void maxPacketInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxPacketInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxPacketInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private static void print(Particle[] par) {
        for (Particle ob : par) {
            System.out.println(ob.name + "\t");
        }

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateGraph() {
        Random rand = new Random();
        for (int i = 0; i <= noOfLocations; i++) {
            for (int j = i; j <= noOfLocations; j++) {
                if (i == j) {
                    graph.addEdge(i, j, 0);
                } else {
                    graph.addEdge(i, j, getRandom());

                }
            }
        }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateSwarm() {
        swarm = new Swarm(pm, locmodel, noOfLocations, graph);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IterationInput;
    private javax.swing.JButton btnRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel locDemand;
    private javax.swing.JLabel locationCount;
    private javax.swing.JTextField locationinput;
    private javax.swing.JTextField maxLocDemand;
    private javax.swing.JLabel maxPacket;
    private javax.swing.JTextField maxPacketInput;
    private javax.swing.JLabel particleCount;
    private javax.swing.JTextField particlesInput;
    private javax.swing.JTextField targetInput;
    // End of variables declaration//GEN-END:variables
}

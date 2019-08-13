/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Business.Graph;
import Business.Location;
import Business.ParticleModel;
import PSO.Particle;
import PSO.Swarm;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author premprakash.k
 */
public class Main extends javax.swing.JFrame {

    public static int ParticleCount;
    public static int noOfLocations;
    public static int maxLocationDemand;
    public static int maxSalesmanCapacity;
    int target;
    static Graph graph;
    static ParticleModel pm;
    static Location loc;
    static int iterations;
    static Swarm swarm;

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
        jButton1 = new javax.swing.JButton();
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

        particlesInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                particlesInputActionPerformed(evt);
            }
        });

        jLabel2.setText("Max Iterations :");

        IterationInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IterationInputActionPerformed(evt);
            }
        });

        jLabel3.setText("Target Value : ");

        targetInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetInputActionPerformed(evt);
            }
        });

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        maxPacket.setText("Max Packet carried by Salesperson :");

        locDemand.setText("Max location's demand :");

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
                        .addComponent(jButton1)))
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
                .addComponent(jButton1)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ParticleCount = Integer.parseInt(particlesInput.getText());
        noOfLocations = Integer.parseInt(locationinput.getText());
        iterations = Integer.parseInt(IterationInput.getText());
        target = Integer.parseInt(targetInput.getText());
        maxSalesmanCapacity = Integer.parseInt(maxPacketInput.getText());
        maxLocationDemand = Integer.parseInt(maxLocDemand.getText());

        if (particlesInput.getText().isEmpty() && locationinput.getText().isEmpty() && IterationInput.getText().isEmpty() && targetInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please enter all the fields");
        }
        graph = new Graph(noOfLocations);
        CreateGraph();
        pm = new ParticleModel(ParticleCount, graph, maxSalesmanCapacity, noOfLocations);
        loc = new Location(noOfLocations, maxLocationDemand);

        print(pm.arrParticle);
        // print graph adjacency matrix
        System.out.println("The adjacency matrix for the given graph is: ");
        for (int i = 0; i < noOfLocations; i++) {

            for (int j = 0; j < noOfLocations; j++) {
                System.out.print(graph.getEdge(i, j) + "\t");
            }
            System.out.println();
        }

        CreateSwarm();
        //  Map<String, Map<Double, Double>> particleProgress = new HashMap<String, Map<Double,Double>>();
        //iterations to get best solution.
        for (int t = 1; t <= iterations; t++) {
            System.out.println("-------------------------------------Iteration :" + t + "-------------------------------------\n");
            swarm.calculatebestSolution();
            //swarm.printIterationResults(t, particleProgress);			
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private static void print(Particle[] guys) {
        for (Particle ob : guys) {
            System.out.println(ob.name + "\t");
        }

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateGraph() {
        Random rand = new Random();
        for (int i = 0; i < noOfLocations; i++) {
            for (int j = i; j < noOfLocations; j++) {
                if (i == j) {
                    graph.addEdge(i, j, 0);
                } else {
                    graph.addEdge(i, j, rand.nextInt(noOfLocations));

                }
            }
        }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateSwarm() {
        swarm = new Swarm(pm, loc, noOfLocations, graph);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IterationInput;
    private javax.swing.JButton jButton1;
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

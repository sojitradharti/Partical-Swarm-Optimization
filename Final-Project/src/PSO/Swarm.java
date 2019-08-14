/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.Executor;
import Business.Graph;
import Business.Location;

import Business.ParticleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//http://gardeux-vincent.eu/These/Papiers/Bibli2/Schutte03.pdf
/**
 *
 * @author sojit
 */
public class Swarm {

    public ArrayList<Double> gBestRoute; // pbest
    double gFitnessValue;
    double[] gBestVelocity;
    Thread process;
    Executor exe;

    private final ParticleModel parModel;
    public Graph graph;

    public Swarm(ParticleModel db, Location loc, int locationCount, Graph graph) {
        //find global best	
        this.parModel = db;
        this.graph = graph;
        gBestRoute = new ArrayList();
        gBestVelocity = new double[locationCount];
        gFitnessValue = Double.MAX_VALUE;
        findGlobalBest(parModel.getArraySalesperson());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void findGlobalBest(Particle[] arrPar) {
        for (Particle par : arrPar) {
            if (par.pFitnessValue < gFitnessValue) {
                gFitnessValue = par.pFitnessValue;
                gBestRoute = par.particleRoute;
                gBestVelocity = par.pBestVelocity;
            }
        }
        System.out.println("---------------Global Solution---------------");
        System.out.println("global FitnessValue  " + gFitnessValue);
        System.out.println("global BestRoute " + gBestRoute);
        System.out.println("global BestVelocity" + Arrays.toString(gBestVelocity));
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("\n");
    }

    public void calculatebestSolution() {

        for (Particle p : parModel.getArraySalesperson()) {
            exe = new Executor(p, graph, gBestRoute);
            process = new Thread(exe);
            process.start();

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Swarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //update all global variables after each paricle updated
        findGlobalBest(parModel.getArraySalesperson());
        try {
            Thread.sleep(2000);

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (InterruptedException ex) {
            Logger.getLogger(Swarm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getParticleProgress(int num, Map<String, Map<Double, Double>> particles) {

        //  System.out.print(t + " \t\t");
        int count = 1;
        for (Particle p : parModel.getArraySalesperson()) {
            if (particles.get("p" + count) == null) {
                particles.put("p" + count, new HashMap<Double, Double>());
            }

            particles.get("p" + count).put((double) num, p.pBestValue);
            System.out.print(p.pFitnessValue + "\t" + p.pBestValue + "\t\t");
            //  System.out.println(p.xSolution.toString());
            //  System.out.println(p.pVelocity.toString());
            count++;
        }
        System.out.println(gFitnessValue);
    }
}
        // return particles;


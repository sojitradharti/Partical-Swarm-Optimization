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
import java.util.Random;

//http://gardeux-vincent.eu/These/Papiers/Bibli2/Schutte03.pdf
/**
 *
 * @author sojit
 */
public class Swarm {

    public ArrayList<Double> gBestRoute; // pbest
    int gFitnessValue;
    double[] gBestVelocity;
     Thread process;
    Executor exe;

    private final ParticleModel parModel;
    public Graph graph = new Graph();

    public Swarm(ParticleModel db, Location loc, int locationCount, Graph graph) {
        //find global best	
        this.parModel = db;
        this.graph = graph;
        gBestRoute = new ArrayList();
        gBestVelocity = new double[locationCount];
        gFitnessValue = Integer.MAX_VALUE;
        findGlobalBest(parModel.getArrParticle());
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
        System.out.println("global FitnessValue  " + gFitnessValue);
        System.out.println("global BestRoute " + gBestRoute);
        System.out.println("global BestVelocity" + Arrays.toString(gBestVelocity));
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("\n");
    }

    public void calculatebestSolution() {
      
      for (Particle p : parModel.getArrParticle()) {
            exe = new Executor(p, graph, gBestRoute);
            process = new Thread(exe);
            process.start();

        }
        try {
            // System.out.println("Waiting for threads to finish.");
            process.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        //update all global variables after each paricle updated
        findGlobalBest(parModel.getArrParticle());

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

   

}

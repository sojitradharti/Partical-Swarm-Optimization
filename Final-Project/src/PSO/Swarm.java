/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.Graph;
import Business.Location;

import Business.ParticleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sojit
 */
public class Swarm {

    public ArrayList<Double> gBestRoute; // pbest
    int gFitnessValue;
    double[] gBestVelocity;

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
            System.out.println("For particle :" + p.name);;
            //  velocity
            findNewVelocity(p);

            // find new Route
            updatePosition(p);

            // update the fitness value of the particles
            p.pFitnessValue = generateFitnessValue(p.particleRoute);

            // update pBest of the particle
            if (p.pFitnessValue < p.pBestValue) {
                p.PersonalBestRoute = p.particleRoute;
                p.pBestValue = p.pFitnessValue;
                p.pBestVelocity = p.pVelocity;
            }
            System.out.println("PersonalBestRoute " + p.PersonalBestRoute);
            System.out.println( "p Fitness " + p.pFitnessValue + "  PBestValue " + p.pBestValue);
            System.out.println("P Best Velocity " + Arrays.toString(p.pBestVelocity));
            System.out.println("\n");
        }

        //update all global variables after each paricle updated
        findGlobalBest(parModel.getArrParticle());

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // to update velocity in each iteration
    private void findNewVelocity(Particle p) {
//    { 
//        int rangeMax = 1;
//        int rangeMin = 0;
//        Random r = new Random();
        double w = 0.6;

        double o1 = 0.2;
        double b1 = 0.3;

        double o2 = 0.2;
        double b2 = 0.5;
//        double r1 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        double r2 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        double r3 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        double[] newVelocity = new double[p.pVelocity.length];
        for (int i = 0; i < newVelocity.length; i++) {
            double inertia = w * p.pVelocity[i];
            double cognitiveComp = o1 * b1 * (p.PersonalBestRoute.get(i) - p.particleRoute.get(i));
            double socialComp = o2 * b2 * (gBestRoute.get(i) - p.particleRoute.get(i));
            newVelocity[i] = Math.round((inertia + cognitiveComp + socialComp) * 100.0) / 100.0;
            // newVelocity[i] = inertia + cognitiveComp + socialComp;
            // Math.round(a * 100.0) / 100.0;

        }
        p.pVelocity = newVelocity;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // learning function to update route of particle in next iteration
    private void updatePosition(Particle p) {
        int value = 0;
        for (int i = 0; i < p.particleRoute.size(); i++) {
            //value = p.particleRoute.get(i) + p.pVelocity[i] > p.particleRoute.size() ? p.particleRoute.size() :p.particleRoute.get(i) + p.pVelocity[i];
            // p.particleRoute.set(i, value);
            if (p.particleRoute.get(i) + p.pVelocity[i] > p.particleRoute.size()) {
                p.particleRoute.set(i, (double) p.particleRoute.size());
            } else {

                p.particleRoute.set(i, Double.sum(p.particleRoute.get(i), p.pVelocity[i]));
            }
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int generateFitnessValue(ArrayList<Double> particleRoute) {
        int initial = 0;
        int sum = 0;

        //return the value of objective function
        for (int i = 0; i < particleRoute.size() - 1; i++) {

            int v = (int) Math.round(particleRoute.get(i));
            // System.out.println("initial :" + initial + " v :" +v );
            sum += graph.getAdjacency_matrix()[initial][v];
            initial = v;
        }

        sum += graph.getAdjacency_matrix()[initial][0]; // add distance back to the depot

        return sum;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

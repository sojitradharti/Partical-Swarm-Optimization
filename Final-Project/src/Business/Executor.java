/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Particle;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sojit
 */
public class Executor extends Thread {

    public Particle p;
    public Graph graph;
    public ArrayList<Double> gBestRoute;

    public Executor(Particle p, Graph graph, ArrayList<Double> gBestRoute) {
        this.p = p;
        this.graph = graph;
        this.gBestRoute = gBestRoute;
        // TimerTask tt = new TimerTask();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        // System.err.println("For Particle :" + p.name);
        System.err.println("For particle :" + p.name);;
        //   System.err.println(Thread.currentThread().getName());
        findNewVelocity(p);
        findNewPosition(p);
        // update the fitness value of the particles
        p.pFitnessValue = generateFitnessValue(p.particleRoute);

        // update pBest of the particle
        if (p.pFitnessValue < p.pBestValue) {
            p.pBestRoute = p.particleRoute;
            p.pBestValue = p.pFitnessValue;
            p.pBestVelocity = p.pVelocity;
        }
        System.out.println("PersonalBestRoute " + p.pBestRoute);
        System.out.println("particle " + p.name + " p Fitness " + p.pFitnessValue + "  p BestValue " + p.pBestValue);
        System.out.println("P Best Velocity " + Arrays.toString(p.pBestVelocity));
            // Thread.currentThread().interrupt();

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // to update velocity in each iteration
    private void findNewVelocity(Particle p) {
        double w = 0.6; //inertia weight
        double c1 = 0.6;//local best
        double c2 = 0.1;//global best
        double[] newVelocity = new double[p.pVelocity.length];
        for (int i = 0; i < newVelocity.length; i++) {
            double inertia = w * p.pVelocity[i];
            double cognitiveComp = c1 * (p.pBestRoute.get(i) - p.particleRoute.get(i));
            double socialComp = c2 * (gBestRoute.get(i) - p.particleRoute.get(i));
            newVelocity[i] = inertia + cognitiveComp + socialComp;
        }
        p.pVelocity = newVelocity;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double generateFitnessValue(ArrayList<Double> particleRoute) {

        double initial = 0;
        double sum = 0;

        //return the value of objective function
        for (int i = 0; i < particleRoute.size() - 1; i++) {
            double v = particleRoute.get(i);
            // System.out.println("initial :" + initial + " v :" +v );
            try {
                sum += graph.getAdjacency_matrix()[(int) initial][(int) v];
                initial = v;
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.err.println("initial " + initial + "v :" + v);
            }

        }
        sum += graph.getAdjacency_matrix()[(int) initial][0];
        return sum;

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // learning function to update route of particle in next iteration
    private void findNewPosition(Particle p) {
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
}

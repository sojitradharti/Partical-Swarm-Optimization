/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author sojit
 */
public final class Particle {

    public String name;
    public int maxCapacity;
    public ArrayList<Double> particleRoute;
    public ArrayList<Double> pBestRoute;
    public double pFitnessValue;
    public double pBestValue;

    public double getpBestValue() {
        return pBestValue;
    }

    public void setpBestValue(double pBestValue) {
        this.pBestValue = pBestValue;
    }
    public double[] pBestVelocity;
    public double[] pVelocity;
    int noOfLoc;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    Graph graph;

    public Particle(int name, Graph graph, int maxCapacity, int noOfLoc) {
        this.noOfLoc = noOfLoc;
        this.maxCapacity = maxCapacity;
        setMaxCapacity(this.maxCapacity);
        System.out.println("Salesperson : " + name + ", Capacity : " + maxCapacity);
        setName(name);
        this.graph = graph;
        particleRoute = new ArrayList();
        ArrayList<Double> route = new ArrayList();
        for (int i = 0; i <= noOfLoc - 1; i++) {
            route.add((double) i + 1);
        }
        Collections.shuffle(route);
        System.out.println("Route for " + name + " :" + route.toString());
        particleRoute = route;
        pBestRoute = particleRoute;
        pFitnessValue = GenerateFitness();
        pBestValue = pFitnessValue;
        setpBestValue(pBestValue);
        pVelocity = setVelocity(particleRoute.size());
        pBestVelocity = pVelocity;
//       updateVelocity();

    }

    public ArrayList<Double> getParticleRoute() {
        return particleRoute;
    }

    public void setParticleRoute(ArrayList<Double> particleRoute) {
        this.particleRoute = particleRoute;
    }

    public ArrayList<Double> getPersonalBestRoute() {
        return pBestRoute;
    }

    public void setPersonalBestRoute(ArrayList<Double> PersonalBestRoute) {
        this.pBestRoute = PersonalBestRoute;
    }

    public String getName() {
        return name;
    }

    public void setName(int name) {
        this.name = " " + (name + 1);
    }

    private int GenerateFitness() {
        int initial = 0; // since we will be starting from the depot which has node 0
        int sum = 0;

        //return the value of objective function
        for (int i = 0; i < pBestRoute.size() - 1; i++) {
            int v = (int) Math.round(pBestRoute.get(i));
            sum += graph.getAdjacency_matrix()[initial][v];
            initial = v;
        }

        sum += graph.getAdjacency_matrix()[initial][0]; // add distance back to the depot

        return sum;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double[] setVelocity(int par) {
        this.pVelocity = new double[par];
        // randomly generate the velocity
        for (int i = 0; i < par; i++) {
            this.pVelocity[i] = getRandomVelocity(par);
        }
        return this.pVelocity;
        //  throw new UnsupportedOpSSerationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double getRandomVelocity(int upper) {
        int lower = 0;
        return (new Random().nextDouble() * (upper - lower)) + lower;
    }

}

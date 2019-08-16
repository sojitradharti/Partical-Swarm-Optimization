/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.Executor;
import Business.Graph;
import Business.LocationModel;

import Business.ParticleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public LocationModel loc;

    public Swarm(ParticleModel db, LocationModel loc, int locationCount, Graph graph) {
        //find global best	
        this.parModel = db;
        this.graph = graph;
        this.loc = loc;
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

    public boolean calculatebestSolution(int target) {

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
        if (gFitnessValue <= target) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getBestRoute() {
        int bestRoute[] = new int[gBestVelocity.length];
        Map<Double, List<Integer>> frequency = new HashMap<>();

        for (int i = 0; i < gBestRoute.size(); i++) {
            if (frequency.get(gBestRoute.get(i)) == null) {
                frequency.put(gBestRoute.get(i), new ArrayList<Integer>());
            }
            frequency.get(gBestRoute.get(i)).add(i);
        }

        Collections.sort(gBestRoute);

        for (int i = 0; i < bestRoute.length; i++) {
            if (frequency.get(gBestRoute.get(i)).size() > 1) {			
                int num = i;
                for (int k = 0; k < frequency.get(gBestRoute.get(num)).size(); k++) {
                    bestRoute[i] = frequency.get(gBestRoute.get(num)).get(k) + 1;
                    i++;
                }

            } else {
                bestRoute[i] = frequency.get(gBestRoute.get(i)).get(0) + 1;
            }
        }
        return bestRoute;
    }

    public void trackResultOfParticle(int num, Map<Double, Map<Double, Double>> particles) {
        double count = 1;
        for (Particle p : parModel.getArraySalesperson()) {
            if (particles.get(count) == null) {
                particles.put(count, new HashMap<>());
            }
            particles.get(count).put((double) num, p.pBestValue);
            count++;
        }
    }

    public Map<String, List<Integer>> CountBestRouteRounds(int[] bestRoute) {
        Map<String, List<Integer>> model = new HashMap<>();
        List<Integer> route;
        int roundsCount = 0;
        int totalDistance = 0;

        for (int v = 0; v < parModel.getArraySalesperson().length; v++) {
            route = new ArrayList<>();
            roundsCount = 0;
            int availableCapacity = parModel.getArraySalesperson()[v].maxCapacity;
            for (int i = 0; i < bestRoute.length; i++) {
                int locDemand = loc.Locations[bestRoute[i] - 1].maxDemand;
                while (locDemand > 0) {
                    roundsCount++;
                    route.add(0);
                    locDemand = locDemand - availableCapacity;
                    route.add(bestRoute[i]);
                }
            }
            model.put("Salesperson " + parModel.getArraySalesperson()[v].name + " Capacity : " + parModel.getArraySalesperson()[v].maxCapacity + ", Total Rounds : " + roundsCount + ", TotalDistance:" + totalDistance + "  ,Route followed" , route);
            route.add(0);
        }
        return model;
    }

}

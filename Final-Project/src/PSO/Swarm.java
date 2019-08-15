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
                // find the lowest velocity and add that first				
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

    public void TrackResultOfParticle(int num, Map<Double, Map<Double, Double>> particles) {

        double count = 1;
        for (Particle p : parModel.getArraySalesperson()) {
            if (particles.get(count) == null) {
                particles.put(count, new HashMap<Double, Double>());
            }

            particles.get(count).put((double) num, p.pBestValue);
            System.out.print(p.pFitnessValue + "\t" + p.pBestValue + "\t\t");
            count++;
        }
        System.out.println(gFitnessValue);
        System.out.println(particles);
    }

    public Map<String, List<Integer>> CountBestRouteTrips(int[] bestRoute) {
        Map<String, List<Integer>> model = new HashMap<String, List<Integer>>();
                System.out.println(Arrays.toString(bestRoute));
		List<Integer> route;
		int tripsCount =0;
		int totalDistance = 0;
               
		for(int v=0; v< parModel.getArraySalesperson().length; v++){
                    
			route = new ArrayList<Integer>();
			
			tripsCount=0;
			//totalDistance=0;
			int availableCapacity = parModel.getArraySalesperson()[v].maxCapacity; 
                        
			for(int i=0; i<bestRoute.length; i++){
                           //  System.out.println("i :" + i);
                          int custDemand = loc.Locations[bestRoute[i]-1].maxDemand;
                             System.out.println("Demand :"+ loc.Locations[bestRoute[i]-1].name+ " : " +custDemand);
                             
                               while(custDemand > 0)
                                  {
                                      tripsCount++;
                                      route.add(0);
                                      //totalDistance+= graph.getAdjacency_matrix()[route.get(route.indexOf(bestRoute[i])-1)][bestRoute[i]];
                                      custDemand = custDemand - availableCapacity ;
                                      route.add(bestRoute[i]);
                                     
                                  }                              
			}
                          model.put("vehicleName : " + parModel.getArraySalesperson()[v].name + "VehicleCap:"+ parModel.getArraySalesperson()[v].maxCapacity +",Trips:"+tripsCount+",TotalDistance:"+totalDistance, route);
			route.add(0);			
			
		}
		return model;
    }


}


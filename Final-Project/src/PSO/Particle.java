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
    public ArrayList<Integer> particleRoute; // xsolution
    public ArrayList<Integer> PersonalBestRoute; // pbest
    int pFitnessValue;	
    int pBestValue;
    int[] pBestVelocity;	
    int[] pVelocity;
    
    
   
    Graph graph = new Graph();

    public Particle(int name,Graph graph) {
        setName(name);
        this.graph = graph;
        particleRoute = new ArrayList();
        ArrayList<Integer> route = new ArrayList();
        for (int i = 0; i <= 9; i++) {
            route.add(i + 1);
        }
        Collections.shuffle(route);
        System.out.println("initial random particle route for particle " +name +" :" + route.toString() );
        particleRoute = route;
        PersonalBestRoute = particleRoute;
        pFitnessValue = GenerateFitness();
        pBestValue = pFitnessValue;
         pVelocity = setVelocity(10);// need to change to dynamic variable
         pBestVelocity = pVelocity;
//       updateVelocity();

    }

    public ArrayList<Integer> getParticleRoute() {
        return particleRoute;
    }

    public void setParticleRoute(ArrayList<Integer> particleRoute) {
        this.particleRoute = particleRoute;
    }

    public ArrayList<Integer> getPersonalBestRoute() {
        return PersonalBestRoute;
    }

    public void setPersonalBestRoute(ArrayList<Integer> PersonalBestRoute) {
        this.PersonalBestRoute = PersonalBestRoute;
    }
 

    public String getName() {
        return name;
    }

    public void setName(int name) {
        this.name = "person " + name;
    }

    private int GenerateFitness() {
        int initial = 0; // since we will be starting from the depot which has node 0
        int sum = 0;

        //return the value of objective function
        for (int i = 0; i < PersonalBestRoute.size() - 1; i++) {
            int v = (int) Math.round(PersonalBestRoute.get(i));
            sum += graph.getAdjacency_matrix()[initial][v];
            initial = v;
        }

        sum += graph.getAdjacency_matrix()[initial][0]; // add distance back to the depot
      
        return sum;
        //set fitnessSum;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    private int[] setVelocity(int par) {
        this.pVelocity = new int[par];
		// randomly generate the velocity
		for (int i = 0; i < par; i++) {
			this.pVelocity[i] = getRandomVelocity(par);
		}
                return this.pVelocity;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int getRandomVelocity(int upper) {
		int lower = 0;
		return (int)(new Random().nextDouble()* (upper - lower)) + lower;
	}


}

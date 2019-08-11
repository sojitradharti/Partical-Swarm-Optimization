/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.Location;

import Business.ParticleModel;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sojit
 */
public class Swarm {
   
    public ArrayList<Integer> gBestRoute; // pbest
    int gFitnessValue;   
    int[] gBestVelocity;	
    
    private final ParticleModel deliveryGuys;
  

    public Swarm(ParticleModel db, Location loc, int locationCount) {
        //find global best	
                 this.deliveryGuys = db;
		gBestRoute = new ArrayList();	
		gBestVelocity = new int[locationCount];
		gFitnessValue = Integer.MAX_VALUE;
		findGlobalBest(deliveryGuys.getArrParticle());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private void findGlobalBest(Particle[] arrGuy) {
         for(Particle guy : arrGuy)         {
             if(guy.pFitnessValue < gFitnessValue){
				gFitnessValue = guy.pFitnessValue;
				gBestRoute = guy.particleRoute;
				gBestVelocity = guy.pBestVelocity;
			}
         }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//      private void updateVelocity() {
//        int rangeMax = 1;
//        int rangeMin = 0;
//        Random r = new Random();
//        double r1 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        double r2 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        double r3 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
//        int[] newVelocity = new int[pVelocity.length];
//
//        for (int i = 0; i < newVelocity.length; i++) {
//            newVelocity[i] = (int) (2 * r1 * pVelocity[i] + (2 * r2 * (PersonalBestRoute.get(i) - particleRoute.get(i))) + (2 * r3 * (globalBest[i] - particleRoute.get(i))));
//        }
//
//        pVelocity = newVelocity;
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void calculatebestSolution() {
        for(Particle p: deliveryGuys.getArrParticle()){
			
			// find the new velocity
			updateVelocity(p);			
			
			// find new solution
			updateSolution(p);
			
			// update the fitness value of the particles
			p.pFitnessValue = generateFitnessValue(p.particleRoute);
			
			// update pBest of the particle
			if(p.pFitnessValue < p.pBestValue){
				p.PersonalBestRoute = p.particleRoute;
				p.pBestValue = p.pFitnessValue;
				p.pBestVelocity = p.pVelocity;
			}
		
		}
		
		//update the gBest after this one iteration
		findGlobalBest(deliveryGuys.getArrParticle());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateVelocity(Particle p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateSolution(Particle p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int generateFitnessValue(ArrayList<Integer> particleRoute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
      
}

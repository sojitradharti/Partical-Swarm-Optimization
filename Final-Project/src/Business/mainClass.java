/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Particle;

import PSO.Swarm;
import java.util.Random;

/**
 *
 * @author sojit
 */
public class mainClass {

    public final static int ParticleCount = 8;
    public final static int locationCount = 11;
    static  Graph graph = new Graph();
    static  ParticleModel pm ;
    static Location loc;
    static int Iterations = 100;
    static Swarm swarm;
    public static void main(String[] args)
    {
      
     
       
       // creating random cost of edges
       CreateGraph();
       pm = new ParticleModel(ParticleCount,graph);
       loc = new Location(locationCount);
         print(pm.arrParticle);
       // print graph adjacency matrix
       System.out.println("The adjacency matrix for the given graph is: ");
           for(int i=0; i<locationCount; i++){
			
			for(int j=0; j<locationCount; j++){
				System.out.print(graph.getEdge(i, j) + "\t");  	
			}
			System.out.println();
		}
         
        CreateSwarm();
        
        //iterations to get best solution.
         for(int t=1; t<= Iterations;t++){
			swarm.calculatebestSolution();	
			//swarm.printIterationResults(t, particleProgress);			
		}
          
           
       // print(Locations);
    }

    private static void print(Particle[] guys) {
        for(Particle ob : guys)
        {
            System.out.println(ob.name + "\t");
        }
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateGraph() {
        int count = 1;
        
         Random rand = new Random();
               for(int i=0; i< locationCount; i++){
			for(int j=i; j<locationCount; j++){
				if(i==j){
					 graph.addEdge(i,j,0);
                                }
				else
                                {
					graph.addEdge(i,j,rand.nextInt(locationCount));
                                        
                                }
			}			
		}
    
               
                count++;
           
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateSwarm() {
        swarm =  new Swarm(pm, loc,locationCount,graph);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

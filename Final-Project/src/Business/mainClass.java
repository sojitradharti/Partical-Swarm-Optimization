/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Person;
import PSO.Swarm;
import java.util.Random;

/**
 *
 * @author sojit
 */
public class mainClass {

    public final static int distributorSize = 8;
    public final static int destinationCount = 10;
    static  Graph graph = new Graph();
    static  distributor ds = new distributor(distributorSize);
      static destination dest = new destination(destinationCount);
    public static void main(String[] args)
    {
      
       print(ds.persn);
       
       // creating random cost of edges
       CreateRandomDistance();
       
       // print graph adjacency matrix
       System.out.println("The adjacency matrix for the given graph is: ");
           for(int i=0; i<destinationCount; i++){
			
			for(int j=0; j<destinationCount; j++){
				System.out.print(graph.getEdge(i, j) + "\t");  	
			}
			System.out.println();
		}
         
        CreateSwarm();
        
         
          
           
       // print(Locations);
    }

    private static void print(Person[] Persons) {
        for(Person ob : Persons)
        {
            System.out.println(ob.name + "\t");
        }
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateRandomDistance() {
        int count = 1;
        
         Random rand = new Random();
               for(int i=0; i<destinationCount; i++){
			for(int j=i; j<destinationCount; j++){
				if(i==j){
					 graph.addEdge(i,j,0);
                                }
				else
                                {
					graph.addEdge(i,j,rand.nextInt(destinationCount));
                                        
                                }
			}			
		}
    
               
                count++;
           
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void CreateSwarm() {
        Swarm swarm =new Swarm(ds, dest);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import Business.destination;
import Business.distributor;

/**
 *
 * @author sojit
 */
public class Swarm {
    int gBest;
    int[] globalBestRoute;
    int[] globalvelocity;
    Person[] particles;

    public Swarm(distributor ds, destination dest) {
        this.particles = ds.persn;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Particle;


/**
 *
 * @author Kamini
 */
public class ParticleModel {
 public Particle[] arrParticle ; 
 
 public  ParticleModel(int size,Graph graph)
    {
       arrParticle = new Particle[size];        
        for(int i = 0 ;i< size;i++)
        {
            Particle particle = new Particle(i,graph);
            
            arrParticle[i] = particle;
        }
        setArrGuy(arrParticle);
    }
    public Particle[] getArrParticle() {
        return arrParticle;
    }

    public void setArrGuy(Particle[] arrParticle) {
        this.arrParticle = arrParticle;
    }
  
   
}

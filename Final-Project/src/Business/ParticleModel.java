/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Particle;
import java.util.Random;

/**
 *
 * @author Kamini
 */
public class ParticleModel {

    public Particle[] arrParticle;
    public int cap;
    int locations;

    public ParticleModel(int size, Graph graph, int cap, int locations) {
        this.cap = cap;
        this.locations =locations;
        arrParticle = new Particle[size];
        for (int i = 0; i < size; i++) {
            Particle particle = new Particle(i, graph, generateRandomCapacity(cap), locations);

            arrParticle[i] = particle;
        }
        setArraySalesperson(arrParticle);
    }

    public int generateRandomCapacity(int cap) {
        Random ran = new Random();
        int k = ran.nextInt(cap)+1;
        return k;
    }

    public Particle[] getArraySalesperson() {
        return arrParticle;
    }

    public void setArraySalesperson(Particle[] arrParticle) {
        this.arrParticle = arrParticle;
    }

}

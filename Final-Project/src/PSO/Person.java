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
public final class Person {

    public String name;
    ArrayList<Integer> PersonalBestRoute;
    // total distance of person's route
    int[] globalBest;
    public int pBestfitnessValue;
    int[] velocity;
    public ArrayList<Integer> particleRoute;
    Graph graph = new Graph();

    public Person(int name) {
        setName(name);
        particleRoute = new ArrayList();
        ArrayList<Integer> route = new ArrayList();
        for (int i = 0; i < 10; i++) {
            route.add(i + 1);
        }
        Collections.shuffle(route);
        particleRoute = route;
        PersonalBestRoute = particleRoute;
      //  GenerateFitness();
       // updateVelocity();

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

    public int getpBestfitnessValue() {
        return pBestfitnessValue;
    }

    public void setpBestfitnessValue(int pBestfitnessValue) {
        this.pBestfitnessValue = pBestfitnessValue;
    }

    public int[] getVelocity() {
        return velocity;
    }

    public void setVelocity(int[] velocity) {
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public void setName(int name) {
        this.name = "person " + name;
    }

    private void GenerateFitness() {
        int initial = 0; // since we will be starting from the depot which has node 0
        int sum = 0;

        //return the value of objective function
        for (int i = 0; i < PersonalBestRoute.size() - 1; i++) {
            int v = (int) Math.round(PersonalBestRoute.get(i));
            sum += graph.getAdjacency_matrix()[initial][v];
            initial = v;
        }

        sum += graph.getAdjacency_matrix()[initial][0]; // add distance back to the depot
        this.setpBestfitnessValue(sum);
        //set fitnessSum;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateVelocity() {
        int rangeMax = 1;
        int rangeMin = 0;
        Random r = new Random();
        double r1 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        double r2 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        double r3 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        int[] newVelocity = new int[velocity.length];

        for (int i = 0; i < newVelocity.length; i++) {
            newVelocity[i] = (int) (2 * r1 * velocity[i] + (2 * r2 * (PersonalBestRoute.get(i) - particleRoute.get(i))) + (2 * r3 * (globalBest[i] - particleRoute.get(i))));
        }

        velocity = newVelocity;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

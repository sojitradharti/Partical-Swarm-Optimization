/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Random;

/**
 *
 * @author Kamini
 */
public class Location {

    public String name;
    int maxDemand;

    public Location(int name, int maxDemand) {
        this.maxDemand = getRandomMaxDemand(maxDemand);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(int name) {
        this.name = "Location " + name;
    }

    private int getRandomMaxDemand(int maxDemand) {
        Random ran = new Random();
        return ran.nextInt(maxDemand);
    }

}

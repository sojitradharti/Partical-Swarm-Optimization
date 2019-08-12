/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author sojit
 */
public class destination {

    Location[] Locations;

    destination(int destinationSize) {
        Locations = new Location[destinationSize];
        for (int i = 0; i < destinationSize; i++) {
            Location l = new Location(i);
            Locations[i] = l;
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

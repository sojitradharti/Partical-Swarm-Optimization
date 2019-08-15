/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import static UI.Main.maxLocationOrders;

/**
 *
 * @author Kamini
 */
public class LocationModel {

    public Location[] Locations;

    public LocationModel(int locsize,int maxLocationDemand) {
        Locations = new Location[locsize];
        for (int i = 0; i < locsize; i++) {
            Location l = new Location(i,maxLocationDemand);
           Locations[i] = l;
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

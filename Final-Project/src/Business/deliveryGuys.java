/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import PSO.Guy;


/**
 *
 * @author sojit
 */
public class deliveryGuys {
 public Guy[] arrGuy ; 
 
 public  deliveryGuys(int size,Graph graph)
    {
       arrGuy = new Guy[size];        
        for(int i = 0 ;i< size;i++)
        {
            Guy guy = new Guy(i,graph);
            
            arrGuy[i] = guy;
        }
        setArrGuy(arrGuy);
    }
    public Guy[] getArrGuy() {
        return arrGuy;
    }

    public void setArrGuy(Guy[] arrGuy) {
        this.arrGuy = arrGuy;
    }
  
   
}

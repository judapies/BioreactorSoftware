/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graphs;

import com.control.Variables;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanDavid
 */
public class HiloGrafica implements Runnable{
    
    public static double Tiempo=0;
    @Override
    public void run() {
        
        while(Variables.estadoAdquisicion)
        {
            com.graphs.Graficar.graficar();
            
            try {
                Thread.sleep(Variables.TMuestreo);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

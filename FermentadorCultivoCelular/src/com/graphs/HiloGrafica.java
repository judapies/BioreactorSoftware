/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graphs;

import com.control.Variables;
import com.model.Bioreactor;
import com.views.InterfazFermentador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

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
            for(final Bioreactor bio:Variables.bioreactores){
                if(!bio.isEstadoAdquisicion())
                    continue;
                
                final BioreactorChart chart=InterfazFermentador.chartsPorBioreactor.get(bio.getId());
                if(chart==null){
                    continue;
                }
                
                final double temp;
                final double rpm;
                final double ph;
                final double od;
                final double co2;
                
                try {
                    temp=bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
                    rpm=bio.leerEntrada(Bioreactor.Entrada.RPM_CH1);
                    ph=bio.leerEntrada(Bioreactor.Entrada.PH);
                    od=bio.leerEntrada(Bioreactor.Entrada.OD);
                    co2=bio.leerEntrada(Bioreactor.Entrada.CO2);
                } catch (Exception e) {
                    continue;
                }
                
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        chart.addSample(temp, rpm, ph, od, co2);
                    }
                });
            }
            
            try {
                Thread.sleep(Variables.TMuestreo);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloGrafica.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }
    
}

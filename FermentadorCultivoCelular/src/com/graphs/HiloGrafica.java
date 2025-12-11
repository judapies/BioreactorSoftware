package com.graphs;

import com.control.Variables;
import com.model.Bioreactor;
import com.views.InterfazFermentador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class HiloGrafica implements Runnable {

    // base de chequeo (no de muestreo)
    private static final int BASE_SLEEP_MS = 200;

    @Override
    public void run() {

        while (Variables.estadoAdquisicion) {   
            long ahora = System.currentTimeMillis();
            for (final Bioreactor bio : Variables.bioreactores) {
                if (!bio.isEstadoAdquisicion()) {
                    continue;
                }

                int tmBio = bio.getTiempoMuestreoMs();
                if (tmBio <= 0) {
                    tmBio = 1000;
                }

                long proximo = bio.getProximoMuestreoMs();
                
                if (proximo == 0) {
                    bio.setProximoMuestreoMs(ahora);
                    proximo = ahora;
                }
                
                if (ahora < proximo) {
                    continue;
                }

                final BioreactorChart chart = InterfazFermentador.chartsPorBioreactor.get(bio.getId());
                if (chart == null) {
                    bio.setProximoMuestreoMs(ahora + tmBio);
                    continue;
                }

                final double temp;
                final double rpm;
                final double ph;
                final double od;
                final double co2;

                try {
                    temp = bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
                    rpm  = bio.leerEntrada(Bioreactor.Entrada.RPM_CH1);
                    ph   = bio.leerEntrada(Bioreactor.Entrada.PH);
                    od   = bio.leerEntrada(Bioreactor.Entrada.OD);
                    co2  = bio.leerEntrada(Bioreactor.Entrada.CO2);
                } catch (Exception e) {
                    bio.setProximoMuestreoMs(ahora + tmBio);
                    continue;
                }                
                bio.setProximoMuestreoMs(ahora + tmBio);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        chart.addSample(temp, rpm, ph, od, co2);
                    }
                });
            }

            try {
                Thread.sleep(BASE_SLEEP_MS);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloGrafica.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}

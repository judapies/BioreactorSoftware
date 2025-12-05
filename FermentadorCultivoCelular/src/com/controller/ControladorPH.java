/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Bioreactor;
import com.model.BombaPeristaltica;

/**
 *
 * @author PC
 */
public class ControladorPH {

    private final Bioreactor bioreactor;

    public ControladorPH(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Controla el pH activando la bomba de ácido o base según el error con
     * respecto al setpoint.
     *
     */
    public void controlar() {
        double valorpH = bioreactor.leerEntrada(Bioreactor.Entrada.PH);
        double error = bioreactor.getParametros().getPh().getSetpoint() - valorpH;
        double banda = bioreactor.getParametros().getPh().getBanda();

        for (int i = 0; i < bioreactor.getBombasSize(); i++) {
            BombaPeristaltica bomba = bioreactor.getBomba(i);
            String tipo = bomba.getParametros().getAsignacionBomba(); // "Ácido" o "Base"
            boolean encendida = bomba.isEstadoOn();

            if (error > 0) {
                if ("Ácido".equalsIgnoreCase(tipo) && encendida) {
                    bomba.setEstadoControl(false);                    
                    bioreactor.activarSalida(Bioreactor.Salida.valueOf("BOMBA_PERISTALTICA_" + (i + 1)), 10); // OFF
                }

                if ("Base".equalsIgnoreCase(tipo) && encendida) {
                    bomba.setEstadoControl(Math.abs(error) > banda);
                }

            } else {
                if ("Base".equalsIgnoreCase(tipo) && encendida) {
                    bomba.setEstadoControl(false);
                    bioreactor.activarSalida(Bioreactor.Salida.valueOf("BOMBA_PERISTALTICA_" + (i + 1)), 10); // OFF
                }

                if ("Ácido".equalsIgnoreCase(tipo) && encendida) {
                    bomba.setEstadoControl(Math.abs(error) > banda);
                }
            }
        }
    }

    /**
     * Detiene las bombas de ácido y base si están en modo manual (activadas por
     * el usuario).
     */
    public void detenerBombasPH() {
        for (int i = 0; i < bioreactor.getBombasSize(); i++) {
            BombaPeristaltica bomba = bioreactor.getBomba(i);
            String tipo = bomba.getParametros().getAsignacionBomba(); // "Ácido" o "Base"

            if (bomba.isEstadoOn() && (tipo.equalsIgnoreCase("Ácido") || tipo.equalsIgnoreCase("Base"))) {
                //bomba.setEstadoOn(false); // Desactiva modo automático
                bioreactor.activarSalida(Bioreactor.Salida.valueOf("BOMBA_PERISTALTICA_" + (i + 1)), 10); // OFF
                System.out.println("Apaga Bombas");
            }
        }
    }

}

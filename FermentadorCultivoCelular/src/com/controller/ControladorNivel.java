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
public class ControladorNivel {

    private final Bioreactor bioreactor;

    public ControladorNivel(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Ejecuta la lógica de control de bombas según su asignación a nivel.
     */
    public void controlar() {
        for (int i = 0; i < bioreactor.getBombasSize(); i++) {
            BombaPeristaltica bomba = bioreactor.getBomba(i);
            String asignacion = bomba.getParametros().getAsignacionBomba(); // "Nivel Alto", "Nivel Medio", "Nivel Bajo"
            boolean encendida = bomba.isEstadoOn();

            if (!encendida) {
                bomba.setEstadoControl(false);
                continue;
            }

            boolean activar = false;

            if ("Nivel Alto".equalsIgnoreCase(asignacion)) {
                activar = bioreactor.leerEntrada(Bioreactor.Entrada.NIVEL_ALTO) < 128;
            } else if ("Nivel Medio".equalsIgnoreCase(asignacion)) {
                activar = bioreactor.leerEntrada(Bioreactor.Entrada.NIVEL_MEDIO) < 128;
            } else if ("Nivel Bajo".equalsIgnoreCase(asignacion)) {
                activar = bioreactor.leerEntrada(Bioreactor.Entrada.NIVEL_BAJO) < 128;
            }

            bomba.setEstadoControl(activar);

            // Si el sensor no está activo, apagar salida
            if (!activar) {
                bioreactor.activarSalida(Bioreactor.Salida.valueOf("BOMBA" + (i + 1)), 10); // OFF
            }
        }
    }
}


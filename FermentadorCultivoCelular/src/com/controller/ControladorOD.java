/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Bioreactor;

/**
 *
 * @author PC
 */
public class ControladorOD {

    private final Bioreactor bioreactor;
    private int flancoOD = 0;

    public ControladorOD(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Controla el OD activando el tipo de gas según el error y flanco.
     *
     */
    public void controlar() {
        double spOD=bioreactor.getParametros().getOd().getSetpoint();
        double pvOD=bioreactor.leerEntrada(Bioreactor.Entrada.OD);
        double histeresis=bioreactor.getParametros().getOd().getHisteresis();
        int tipoGas=bioreactor.getParametros().getOd().getTipoDeGas();
        
        if (pvOD < spOD && flancoOD == 0) {
            activarGas(tipoGas);
            flancoOD = 0;
        } else {
            apagarTodosLosGases();
            flancoOD = 1;
        }

        if (flancoOD == 1 && pvOD < (spOD - histeresis)) {
            flancoOD = 0;
        }
    }

    /**
     * Apaga todos los gases.
     */
    public void detener() {
        apagarTodosLosGases();
    }

    private void activarGas(int tipo) {
        /*bioreactor.activarSalida(Bioreactor.Salida.AIRE, tipo == 0 ? 5 : 10);
        bioreactor.activarSalida(Bioreactor.Salida.OXIGENO, tipo == 1 ? 5 : 10);
        bioreactor.activarSalida(Bioreactor.Salida.CO2, tipo == 2 ? 5 : 10);
        bioreactor.activarSalida(Bioreactor.Salida.NITROGENO, tipo == 3 ? 5 : 10);*/
    }

    private void apagarTodosLosGases() {
        /*bioreactor.activarSalida(Bioreactor.Salida.AIRE, 10);
        bioreactor.activarSalida(Bioreactor.Salida.OXIGENO, 10);
        bioreactor.activarSalida(Bioreactor.Salida.CO2, 10);
        bioreactor.activarSalida(Bioreactor.Salida.NITROGENO, 10);*/
    }
}

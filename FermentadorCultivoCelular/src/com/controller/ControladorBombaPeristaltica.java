/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Activador;
import com.model.DutySupplier;
import com.model.EstadoGetter;
import com.model.TiempoSupplier;

/**
 *
 * @author PC
 */
public class ControladorBombaPeristaltica {

    private final String nombre;
    private final EstadoGetter estadoControl;
    private final TiempoSupplier tiempo;
    private final DutySupplier duty;
    private final Activador activador;
    private final Runnable callbackFinDeCiclo;
    private final Runnable callbackInicioDeCiclo;

    private long tInicio = 0;
    private boolean cicloIniciado = false;
    private boolean yaGuardado = false;

    public ControladorBombaPeristaltica(String nombre,
                                        EstadoGetter estadoControl,
                                        TiempoSupplier tiempo,
                                        DutySupplier duty,
                                        Activador activador,
                                        Runnable callbackFinDeCiclo,
                                        Runnable callbackInicioDeCiclo) {
        this.nombre = nombre;
        this.estadoControl = estadoControl;
        this.tiempo = tiempo;
        this.duty = duty;
        this.activador = activador;
        this.callbackFinDeCiclo = callbackFinDeCiclo;
        this.callbackInicioDeCiclo = callbackInicioDeCiclo;
    }

    public void actualizar() {
        long tActual = System.currentTimeMillis();
        
        if (estadoControl.get()) {
            if (!cicloIniciado) {
                tInicio = tActual;
                cicloIniciado = true;
                yaGuardado = false;
                
            }

            long duracionCiclo = (long) (tiempo.get() * 1000);
            long tiempoActivo = (long) (duracionCiclo * duty.get() / 100);
            long tDelta = tActual - tInicio;
            
            if (tDelta <= tiempoActivo) {
                activador.accept(5);
                yaGuardado = false;
                if (callbackInicioDeCiclo != null) {
                    callbackInicioDeCiclo.run();
                }
            } else {
                activador.accept(10);                
                if (!yaGuardado) {
                    callbackFinDeCiclo.run();
                    yaGuardado = true;
                }
            }

            if (tDelta >= duracionCiclo) {
                tInicio = System.currentTimeMillis();
                callbackFinDeCiclo.run();
                yaGuardado = false;
            }

        } else {
            activador.accept(10);            
            if (!yaGuardado) {
                callbackFinDeCiclo.run();
                yaGuardado = true;
            }
            cicloIniciado = false;
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.Bioreactor.Salida;
import java.io.Serializable;

/**
 *
 * @author PC
 */
public class BombaPeristaltica implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private final String nombreSalida; // Ej. "BOMBA1"
    private final Salida salida;       // Enum correspondiente
    private final Bioreactor bioreactor;

    private double tiempoEncendidoSegundos;
    private double porcentajeDuty;
    private boolean estadoControl;
    private boolean runBomba;
    private boolean estadoOn;

    private long tiempoAcumuladoMs = 0;
    private double calibracion = 1.0;
    private long tInicio = 0;
    private final ParametrosBombaPeristaltica parametros;

    public BombaPeristaltica(String nombreSalida, Salida salida, Bioreactor bioreactor) {
        this.nombreSalida = nombreSalida;
        this.salida = salida;
        this.bioreactor = bioreactor;
        this.parametros = new ParametrosBombaPeristaltica(); 
    }

    public void iniciarCiclo() {
        tInicio = System.currentTimeMillis();
    }

    public void cerrarCiclo() {
        long tFin = System.currentTimeMillis();
        long duracion = tFin - tInicio;
        tiempoAcumuladoMs += duracion;
        tInicio = tFin;
        bioreactor.activarSalida(salida, 10); // OFF
        //System.out.println("Guardado " + nombreSalida+tiempoAcumuladoMs);
    }

    public double getTotalLitros() {
        return (tiempoAcumuladoMs / 1000.0) * calibracion;
    }

    public double getTiempoEncendidoSegundos() {
        return tiempoEncendidoSegundos;
    }

    public void setTiempoEncendidoSegundos(double t) {
        this.tiempoEncendidoSegundos = t;
    }

    public double getPorcentajeDuty() {
        return porcentajeDuty;
    }

    public void setPorcentajeDuty(double p) {
        this.porcentajeDuty = p;
    }

    public boolean isEstadoControl() {
        return estadoControl;
    }

    public void setEstadoControl(boolean e) {
        this.estadoControl = e;
    }

    public void setCalibracion(double c) {
        this.calibracion = c;
    }

    public double getCalibracion() {
        return calibracion;
    }

    public Salida getSalida() {
        return salida;
    }
    
    public ParametrosBombaPeristaltica getParametros() {
        return parametros;
    }

    /**
     * @return the runBomba
     */
    public boolean isRunBomba() {
        return runBomba;
    }

    /**
     * @param runBomba the runBomba to set
     */
    public void setRunBomba(boolean runBomba) {
        this.runBomba = runBomba;
    }

    /**
     * @return the estadoOn
     */
    public boolean isEstadoOn() {
        return estadoOn;
    }

    /**
     * @param estadoOn the estadoOn to set
     */
    public void setEstadoOn(boolean estadoOn) {
        this.estadoOn = estadoOn;
    }
}

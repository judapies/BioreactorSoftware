/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class ParametrosEsterilizacion extends ParametrosControl implements Serializable{
    private static final long serialVersionUID = 1L;

    private double desvio;
    private double histeresis;
    private double TPurga;
    private int pulsosPurga;
    private int tiempoMinutos;

    public double getDesvio() {
        return desvio;
    }

    public void setDesvio(double desvio) {
        this.desvio = desvio;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(int tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    /**
     * Devuelve el tiempo de esterilización en segundos.
     */
    public double getTiempoSegundos() {
        return tiempoMinutos * 60.0;
    }

    /**
     * @return the histeresis
     */
    public double getHisteresis() {
        return histeresis;
    }

    /**
     * @param histeresis the histeresis to set
     */
    public void setHisteresis(double histeresis) {
        this.histeresis = histeresis;
    }

    /**
     * @return the TPurga
     */
    public double getTPurga() {
        return TPurga;
    }

    /**
     * @param TPurga the TPurga to set
     */
    public void setTPurga(double TPurga) {
        this.TPurga = TPurga;
    }

    /**
     * @return the pulsosPurga
     */
    public int getPulsosPurga() {
        return pulsosPurga;
    }

    /**
     * @param pulsosPurga the pulsosPurga to set
     */
    public void setPulsosPurga(int pulsosPurga) {
        this.pulsosPurga = pulsosPurga;
    }
}


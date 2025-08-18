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
public class ParametrosOD extends ParametrosControl implements Serializable{

    private static final long serialVersionUID = 1L;
    private double histeresis;
    private int tipoDeGas;
    private double mOD;
    private double bOD;

    public double getHisteresis() {
        return histeresis;
    }

    public void setHisteresis(double histeresis) {
        this.histeresis = histeresis;
    }

    public int getTipoDeGas() {
        return tipoDeGas;
    }

    public void setTipoDeGas(int tipoDeGas) {
        this.tipoDeGas = tipoDeGas;
    }

    public double getMOD() {
        return mOD;
    }

    public void setMOD(double mOD) {
        this.mOD = mOD;
    }

    public double getBOD() {
        return bOD;
    }

    public void setBOD(double bOD) {
        this.bOD = bOD;
    }
}

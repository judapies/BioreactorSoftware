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
public class ParametrosTemperatura extends ParametrosControl implements Serializable{

    private static final long serialVersionUID = 1L;
    private double desvio;
    private double ganancia2;
    private double tCiclo;
    private double offsetTemperatura;
    private double coeficiente;
    private double derivativo;
    private double integral;
    private double mTem;
    private double bTem;
    private double histeresis;
    private double bandaInferior;
    private double bandaSuperior;
    private boolean controlIntercambiador;

    public double getDesvio() {
        return desvio;
    }

    public void setDesvio(double desvio) {
        this.desvio = desvio;
    }

    public double getGanancia2() {
        return ganancia2;
    }

    public void setGanancia2(double ganancia2) {
        this.ganancia2 = ganancia2;
    }

    public double getTCiclo() {
        return tCiclo;
    }

    public void setTCiclo(double tCiclo) {
        this.tCiclo = tCiclo;
    }

    public double getOffsetTemperatura() {
        return offsetTemperatura;
    }

    public void setOffsetTemperatura(double offsetTemperatura) {
        this.offsetTemperatura = offsetTemperatura;
    }

    public double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public double getDerivativo() {
        return derivativo;
    }

    public void setDerivativo(double derivativo) {
        this.derivativo = derivativo;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public double getMTem() {
        return mTem;
    }

    public void setMTem(double mTem) {
        this.mTem = mTem;
    }

    public double getBTem() {
        return bTem;
    }

    public void setBTem(double bTem) {
        this.bTem = bTem;
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
     * @return the bandaInferior
     */
    public double getBandaInferior() {
        return bandaInferior;
    }

    /**
     * @param bandaInferior the bandaInferior to set
     */
    public void setBandaInferior(double bandaInferior) {
        this.bandaInferior = bandaInferior;
    }

    /**
     * @return the bandaSuperior
     */
    public double getBandaSuperior() {
        return bandaSuperior;
    }

    /**
     * @param bandaSuperior the bandaSuperior to set
     */
    public void setBandaSuperior(double bandaSuperior) {
        this.bandaSuperior = bandaSuperior;
    }

    /**
     * @return the controlIntercambiador
     */
    public boolean isControlIntercambiador() {
        return controlIntercambiador;
    }

    /**
     * @param controlIntercambiador the controlIntercambiador to set
     */
    public void setControlIntercambiador(boolean controlIntercambiador) {
        this.controlIntercambiador = controlIntercambiador;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author PC
 */
import java.io.Serializable;

public class ParametrosBombaPeristaltica implements Serializable {

    private static final long serialVersionUID = 1L;

    private double calibracionBomba;
    private int tBomba;
    private int spBomba;
    private String asignacionBomba;

    public double getCalibracionBomba() {
        return calibracionBomba;
    }

    public void setCalibracionBomba(double calibracionBomba) {
        this.calibracionBomba = calibracionBomba;
    }

    public int getTBomba() {
        return tBomba;
    }

    public void setTBomba(int tBomba) {
        this.tBomba = tBomba;
    }

    public int getSPBomba() {
        return spBomba;
    }

    public void setSPBomba(int spBomba) {
        this.spBomba = spBomba;
    }

    public String getAsignacionBomba() {
        return asignacionBomba;
    }

    public void setAsignacionBomba(String asignacionBomba) {
        this.asignacionBomba = asignacionBomba;
    }
}

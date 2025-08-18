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
public class ParametrosPH extends ParametrosControl implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private double bPH;
    private double mPH;
    private double banda;

    public double getBPH() {
        return bPH;
    }

    public void setBPH(double bPH) {
        this.bPH = bPH;
    }

    public double getMPH() {
        return mPH;
    }

    public void setMPH(double mPH) {
        this.mPH = mPH;
    }

    public double getBanda() {
        return banda;
    }

    public void setBanda(double banda) {
        this.banda = banda;
    }
}

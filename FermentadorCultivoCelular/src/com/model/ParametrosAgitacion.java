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
public class ParametrosAgitacion extends ParametrosControl  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private double banda;
    private double velCambio;

    public double getBanda() {
        return banda;
    }

    public void setBanda(double banda) {
        this.banda = banda;
    }

    public double getVelCambio() {
        return velCambio;
    }

    public void setVelCambio(double velCambio) {
        this.velCambio = velCambio;
    }
}

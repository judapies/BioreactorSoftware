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
public class ParametrosVenteoCO2 extends ParametrosControl implements Serializable{

    private static final long serialVersionUID = 1L;
    private double histeresis;

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

    
}

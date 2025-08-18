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
public class ParametrosAjustesPresion extends ParametrosControl implements Serializable{

    private static final long serialVersionUID = 1L;
    private double ajustePresionCamara;
    private double presionAtmosferica;
    private double ajustePresionPreCamara;

    public double getAjustePresionCamara() {
        return ajustePresionCamara;
    }

    public void setAjustePresionCamara(double valor) {
        this.ajustePresionCamara = valor;
    }

    public double getPresionAtmosferica() {
        return presionAtmosferica;
    }

    public void setPresionAtmosferica(double valor) {
        this.presionAtmosferica = valor;
    }

    public double getAjustePresionPreCamara() {
        return ajustePresionPreCamara;
    }

    public void setAjustePresionPreCamara(double valor) {
        this.ajustePresionPreCamara = valor;
    }
}

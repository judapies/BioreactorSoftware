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

public class ParametrosConfiguracionBioreactor implements Serializable {

    private static final long serialVersionUID = 1L;

    private ParametrosTemperatura temperatura;
    private ParametrosPH ph;
    private ParametrosOD od;
    private ParametrosAjustesPresion presion;
    private ParametrosAgitacion agitacion;
    private ParametrosEsterilizacion esterlizacion;
    private ParametrosVenteoCO2 co2;

    public ParametrosConfiguracionBioreactor() {
        this.temperatura = new ParametrosTemperatura();
        this.ph = new ParametrosPH();
        this.od = new ParametrosOD();
        this.presion = new ParametrosAjustesPresion();
        this.agitacion = new ParametrosAgitacion();
        this.esterlizacion = new ParametrosEsterilizacion();
        this.co2 = new ParametrosVenteoCO2();
    }

    public ParametrosTemperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(ParametrosTemperatura temperatura) {
        this.temperatura = temperatura;
    }

    public ParametrosPH getPh() {
        return ph;
    }

    public void setPh(ParametrosPH ph) {
        this.ph = ph;
    }

    public ParametrosOD getOd() {
        return od;
    }

    public void setOd(ParametrosOD od) {
        this.od = od;
    }

    public ParametrosAjustesPresion getPresion() {
        return presion;
    }

    public void setPresion(ParametrosAjustesPresion presion) {
        this.presion = presion;
    }

    public ParametrosAgitacion getAgitacion() {
        return agitacion;
    }

    public void setAgitacion(ParametrosAgitacion agitacion) {
        this.agitacion = agitacion;
    }

    /**
     * @return the esterlizacion
     */
    public ParametrosEsterilizacion getEsterlizacion() {
        return esterlizacion;
    }

    /**
     * @param esterlizacion the esterlizacion to set
     */
    public void setEsterlizacion(ParametrosEsterilizacion esterlizacion) {
        this.esterlizacion = esterlizacion;
    }

    /**
     * @return the co2
     */
    public ParametrosVenteoCO2 getCO2() {
        return co2;
    }

    /**
     * @param co2 the co2 to set
     */
    public void setCO2(ParametrosVenteoCO2 co2) {
        this.co2 = co2;
    }
}

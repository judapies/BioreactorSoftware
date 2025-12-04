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
public interface IBioreactor {
    int getId();
    int getCapacidadLitros();

    void actualizarPresion(double presion);
    void actualizarEntradaDigital(int indice, boolean estado);

    //public boolean esterilizar(double setpointEsterilizacion, double histeresis, double desvio);
    void desfogar();
    void enfriar();
    void detieneEnfriar();
    void recircularAgua();
    void detieneRecircularAgua();
    void controlarVenteoCO2(double setpoint, double histeresis);
    void activarCO2(Integer activar);

    byte[] getTramaDeSalida();
}

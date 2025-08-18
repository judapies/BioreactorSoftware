/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Bioreactor;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ControladorAgitacion {

    private final Bioreactor bioreactor;
    private int conteoControl = 0;
    private int pwm = 0;

    public ControladorAgitacion(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Ejecuta el control de agitación.
     *
     * @param sprpm Setpoint de agitación (RPM)
     * @param pvrpm Valor actual medido de RPM
     * @param banda Banda de tolerancia
     * @param velocidadRespuesta Escalado para el ajuste del PWM
     * @param buffer Buffer de salida (por ejemplo: PC_Tx)
     * @param lsbIndex Índice LSB donde se escribe el PWM
     * @param msbIndex Índice MSB donde se escribe el PWM
     */
    //public void controlar(int sprpm, int pvrpm, int banda, int velocidadRespuesta, byte[] buffer, int msbIndex, int lsbIndex) {
    public void controlar() {
        double sprpm=bioreactor.getParametros().getAgitacion().getSetpoint();
        double pvrpm=bioreactor.leerEntrada(Bioreactor.Entrada.RPM_CH1);
        double banda=bioreactor.getParametros().getAgitacion().getBanda();
        double velocidadRespuesta=bioreactor.getParametros().getAgitacion().getVelCambio();
        
        if (conteoControl < 2) {
            conteoControl++;
            return;
        }

        conteoControl = 0;
        double error = sprpm - pvrpm;

        if (Math.abs(error) > banda) {
            if (error > 100) pwm += velocidadRespuesta * 4;
            else if (error > 50) pwm += velocidadRespuesta * 2;
            else if (error > banda) pwm += velocidadRespuesta;
            
            if (error < -100) pwm -= velocidadRespuesta * 4;
            else if (error < -50) pwm -= velocidadRespuesta * 2;
            else if (error < -banda) pwm -= velocidadRespuesta;
        }

        if (pwm > 1023) pwm = 1023;
        if (pwm < 0) pwm = 0;

        // Verificación de fallo del encoder
        if (pvrpm < 10 && pwm >= 700) {
            JOptionPane.showMessageDialog(null,
                    "Error Agitador\nVerifique conexión del encoder",
                    "Error", JOptionPane.ERROR_MESSAGE);
            pwm = 0;
        }

        // Actualizar el buffer de salida
        bioreactor.activarSalida(Bioreactor.Salida.AGITADOR_MSB, pwm / 256);
        bioreactor.activarSalida(Bioreactor.Salida.AGITADOR_LSB, pwm);
    }

    /**
     * Detiene la agitación (PWM = 0) y limpia el buffer.
     */
    public void detener() {
        pwm = 0;
        bioreactor.activarSalida(Bioreactor.Salida.AGITADOR_MSB, pwm);
        bioreactor.activarSalida(Bioreactor.Salida.AGITADOR_LSB, pwm);
    }
}

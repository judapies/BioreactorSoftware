/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.control.Variables;
import com.model.Bioreactor;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class ControladorAgitacion {

    private final Bioreactor bioreactor;
    private int conteoControl = 0;
    private int pwm = 0;
    private boolean errorEncoderMostrado = false;
    private JDialog dialogError;

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
        double sprpm = bioreactor.getParametros().getAgitacion().getSetpoint();
        double pvrpm = bioreactor.leerEntrada(Bioreactor.Entrada.RPM_CH1);
        double banda = bioreactor.getParametros().getAgitacion().getBanda();
        double velocidadRespuesta = bioreactor.getParametros().getAgitacion().getVelCambio();

        if (conteoControl < 2) {
            conteoControl++;
            return;
        }

        conteoControl = 0;
        double error = sprpm - pvrpm;

        if (Math.abs(error) > banda) {
            if (error > 100) {
                pwm += velocidadRespuesta * 4;
            } else if (error > 50) {
                pwm += velocidadRespuesta * 2;
            } else if (error > banda) {
                pwm += velocidadRespuesta;
            }

            if (error < -100) {
                pwm -= velocidadRespuesta * 4;
            } else if (error < -50) {
                pwm -= velocidadRespuesta * 2;
            } else if (error < -banda) {
                pwm -= velocidadRespuesta;
            }
        }

        if (pwm > 1023) {
            pwm = 1023;
        }
        if (pwm < 0) {
            pwm = 0;
        }

        // Verificación de fallo del encoder
        if (pvrpm < 10 && pwm >= 700) {
            if (!errorEncoderMostrado) {
                errorEncoderMostrado = true;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mostrarErrorNoModal("Error Agitador\nVerifique conexión del encoder");
                    }
                });
                Variables.añadirEvento("Error de agitación en Bioreactor " + bioreactor.getId());
                bioreactor.setEstadoControlAgitacion(false);
                com.views.Control.InicioControlAgitacion.setText("Iniciar");
                com.views.Control.InicioControlAgitacion.setBackground(Color.GREEN);
            }
            pwm = 0;
        } else {
            errorEncoderMostrado = false;
            if (dialogError != null) {
                dialogError.dispose();
                dialogError = null;
            }
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

    private void mostrarErrorNoModal(String msg) {

        if (dialogError != null && dialogError.isVisible()) {
            return;
        }

        dialogError = new JDialog();
        dialogError.setTitle("Error");
        dialogError.setModal(false); // CLAVE
        dialogError.setSize(300, 150);
        dialogError.setLocationRelativeTo(null);

        JOptionPane optionPane = new JOptionPane(msg, JOptionPane.ERROR_MESSAGE);

        dialogError.setContentPane(optionPane);
        dialogError.setVisible(true);
    }

}

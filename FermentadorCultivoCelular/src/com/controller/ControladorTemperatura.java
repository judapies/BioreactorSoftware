/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author PC
 */
import com.model.Bioreactor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ControladorTemperatura {

    private final Bioreactor bioreactor;

    private int tiempoRecta;
    private int tiempoControl;
    
    private double setpoint;
    private double histeresis;
    private double desvio;
    private double bandaSuperior;
    private double bandaInferior;
    private double error;
    private double derivativo;
    private double integral;
    private double ganancia;
    private double Tciclo;
    private double Tpulso;
    private double aumento;
    private double potencia;
    private boolean calentar = true;
    private int y=0;
    private double[]x=new double[2]; 
    
    long t1=0;
    long t5=0;

    /**
     * Constructor del controlador de temperatura.
     * 
     * @param bioreactor instancia del biorreactor sobre el cual se aplicará el control
     */
    public ControladorTemperatura(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Controla el intercambiador de calor (agua), regulando la temperatura 
     * mediante calentamiento o enfriamiento según la lógica ON/OFF y la 
     * disponibilidad de agua en el reservorio.
     */
    public void controlarIntercambiador() {
        double temperatura = bioreactor.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
        setpoint = bioreactor.getParametros().getTemperatura().getSetpoint();
        desvio = bioreactor.getParametros().getTemperatura().getDesvio();
        histeresis = bioreactor.getParametros().getTemperatura().getHisteresis();
        bandaInferior = bioreactor.getParametros().getTemperatura().getBandaInferior();
        bandaSuperior = bioreactor.getParametros().getTemperatura().getBandaSuperior();

        if (bioreactor.leerEntrada(Bioreactor.Entrada.ENTRADA_DIGITAL_1) == 5) {//Reservorio  Lleno de agua
            if (calentar) {
                bioreactor.recircularAgua();//Siempre debe estar recirculando agua
                if (temperatura > setpoint + bandaSuperior) {
                    calentar = false;
                } else if (temperatura > setpoint + desvio) {
                    bioreactor.detieneCalentamientoIntercambiador();
                } else if (temperatura < setpoint + desvio - histeresis) {
                    bioreactor.calentamientoAguaIntercambiador();
                }
            } else {
                bioreactor.enfriar();
                if (temperatura < setpoint + bandaInferior) {
                    calentar = true;
                }
            }
        } else {
            bioreactor.llenadoReservorio();
        }

    }

    /**
     * Detiene el calentamiento del intercambiador de agua inmediatamente.
     */
    public void detenerIntercambiador() {
        bioreactor.detieneCalentamientoIntercambiador();
    }

    /**
     * Controla la resistencia eléctrica del biorreactor usando un control 
     * proporcional con parámetros configurables:
     * 
     * - Error = Setpoint + Desvío - Temperatura.
     * - Corrección de la ganancia en función de la pendiente de la temperatura.
     * - Anti Wind-UP/DOWN para evitar saturación.
     * 
     * Activa o desactiva la salida SSR en ciclos de tiempo definidos (Tciclo).
     */
    public void controlarResistencia() {
        double temperatura = bioreactor.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
        double Temporal=0.0;
        setpoint = bioreactor.getParametros().getTemperatura().getSetpoint();
        desvio = bioreactor.getParametros().getTemperatura().getDesvio();
        histeresis = bioreactor.getParametros().getTemperatura().getHisteresis();
        derivativo = bioreactor.getParametros().getTemperatura().getDerivativo();
        integral = bioreactor.getParametros().getTemperatura().getIntegral();
        ganancia = bioreactor.getParametros().getTemperatura().getGanancia2();
        Tciclo = bioreactor.getParametros().getTemperatura().getTCiclo();
        
        t1 = System.currentTimeMillis();
        
        if (!timer.isRunning()) {
            timer.setDelay(1000);
            timer.start();
        }
        if (tiempoRecta >= derivativo / 2.0) {
            actualizaRecta(temperatura);
            tiempoRecta = 0;
            System.out.println("Actualiza Recta");
        }

        error = desvio + setpoint - temperatura;// Cálculo del error ******

        if (error > (desvio + 0.1) && aumento < 0.3 && potencia < 90) {
            if (tiempoControl >= derivativo) {
                tiempoControl = 0;
                if (aumento < -0.5) {
                    ganancia += integral * 1.0;
                } else if (aumento < -0.1) {
                    ganancia += integral * 0.6;
                } else {
                    ganancia += integral * 0.3;
                }
            }
        }

        Temporal = error * ganancia;  // Control Proporcional.
        if (Temporal > setpoint){ // Anti Wind-UP      
            Temporal = setpoint;
        }

        if (Temporal < 0.0){ // Anti Wind_DOWN                    
            Temporal = 0.0;
        }

        Tpulso = (Temporal / setpoint) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

        if ((t1 - t5) < Tpulso * 1000) {
            //Hardware.Activar("Peltier-", true);
            bioreactor.activarSalida(Bioreactor.Salida.SSR, 5);
        } else {
            //Hardware.Activar("Peltier-", false);
            bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
        }
        
        if ((t1 - t5) >= Tciclo * 1000) {
            t5 = System.currentTimeMillis();
        }
        potencia = (Tpulso * 100) / Tciclo;
        System.out.println("Potencia: " + potencia + " Ganancia: " + ganancia);
    }

    /**
     * Detiene inmediatamente la resistencia eléctrica (SSR en OFF).
     */
    public void detenerResistencia() {
        bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
    }
    
    /**
     * Actualiza la pendiente (aumento) de la temperatura en función de 
     * las últimas dos lecturas. Esto se usa para ajustar dinámicamente la ganancia.
     * 
     * @param temperatura temperatura actual
     */
    public void actualizaRecta(double temperatura){
        x[y]=temperatura;
        y++;
        if(y>=2){
            y=0;
            aumento=x[1]-x[0];
        }
    }
    
    /**
     * Timer encargado de actualizar contadores internos para el cálculo
     * de rectas (pendiente) y tiempos de control.
     */
    @SuppressWarnings("StaticNonFinalUsedInInitialization")
        public Timer timer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            tiempoRecta++;
            tiempoControl++;
        }
    }); 

}

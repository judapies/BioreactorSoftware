/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 * Clase que implementa el control de esterilización para un biorreactor.
 * Administra el control de temperatura, presión y tiempos necesarios para
 * ejecutar el ciclo de esterilización, así como la purga de aire.
 * 
 * Utiliza un modelo de control por histeresis y control proporcional de
 * la resistencia con parámetros configurables.
 * 
 * @author judapies
 */
import com.control.Variables;
import com.model.Bioreactor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class ControladorEsterilizacion {

    private final Bioreactor bioreactor;

    private double setpoint;
    private double histeresis;
    private double desvio;
    private double tiempoEsterilizacion;

    private boolean esterilizando = false;
    private boolean desfogando = false;
    private boolean finalizo = false;
    private boolean purgaAire2 = false;
    private boolean purgo = false;
    private long tiempoInicio = -1;
    long t1 = 0;
    long t5 = 0;

    private int tiempoRecta;
    private int tiempoControl;
    private int tPurgaAire;

    private double error;
    private double derivativo;
    private double integral;
    private double ganancia;
    private double Tciclo;
    private double Tpulso;
    private double aumento;
    private double potencia;
    private boolean calentar = true;
    private int y = 0;
    private double[] x = new double[2];

     /**
     * Constructor del controlador de esterilización.
     * 
     * @param bioreactor instancia del bioreactor sobre el cual se actúa
     */
    public ControladorEsterilizacion(Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
    }

    /**
     * Configura los parámetros básicos del control de esterilización.
     *
     * @param setpoint           temperatura objetivo
     * @param histeresis         margen de control ON/OFF
     * @param desvio             desviación permitida
     * @param tiempoEsteriliza   duración total de la esterilización (segundos)
     */
    public void configurar(double setpoint, double histeresis, double desvio, double tiempoEsteriliza) {
        this.setpoint = setpoint;
        this.histeresis = histeresis;
        this.desvio = desvio;
        this.tiempoEsterilizacion = tiempoEsteriliza;
    }

    /**
     * Controla el proceso de esterilización basado en temperatura, presión y tiempo.
     * 
     * @return true si el ciclo de esterilización ha finalizado, false en otro caso
     */
    public boolean controlar() {
        double presion = bioreactor.leerEntrada(Bioreactor.Entrada.PRESION_PRE_CAMARA);
        double presionC = bioreactor.leerEntrada(Bioreactor.Entrada.PRESION_CAMARA);
        double temperatura = bioreactor.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);

        // Ecuación polinómica para estimar temperatura por presión
        double p1 = 0.0000011617, p2 = -0.0010575, p3 = 0.44114, p4 = 65.043;
        double Tpre = (Math.pow(presion, 3) * p1) + (Math.pow(presion, 2) * p2) + (presion * p3) + p4;

        bioreactor.activarSalida(Bioreactor.Salida.DRENAJE, 5);

        if (esterilizando) {
            long transcurrido = getTiempoTranscurrido();
            if (transcurrido < tiempoEsterilizacion) {
                if (Tpre > setpoint + desvio) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10); // OFF
                } else if (Tpre <= setpoint + desvio - histeresis) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 5); // ON
                }
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 10); // OFF
                return false;
            } else {
                esterilizando = false;
                desfogando = true;
                //tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 10); // OFF
                bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10); // OFF
                return false;
            }
        } else if (desfogando) {
            if (presionC > bioreactor.getParametros().getPresion().getPresionAtmosferica()) {
                finalizo = false;
                //tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10); // OFF
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 5); // ON
                return false;
            } else {
                finalizo = true;
                //tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10); // OFF
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 5); // ON
                return true;
            }
        } else {
            if (temperatura >= setpoint) {
                if (!esterilizando) {
                    esterilizando = true;
                    tiempoInicio = System.currentTimeMillis();
                }

                if (Tpre > setpoint + desvio) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10);
                } else if (Tpre <= setpoint + desvio - histeresis) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 5);
                }
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 10); // OFF
                return false;
            } else {
                purgaAire(temperatura);
                if (Tpre > setpoint + desvio + 2.0) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10);
                } else if (Tpre <= setpoint + desvio + 2.0 - histeresis) {
                    bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 5);
                }
                bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 10); // OFF
                return false;
            }
        }
    }

    /**
     * Controla la resistencia eléctrica del biorreactor mediante un control proporcional,
     * aplicando correcciones en base al error, ganancia y tiempo de ciclo.
     */
    public void controlarResistencia() {
        double temperatura = bioreactor.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
        double presionC = bioreactor.leerEntrada(Bioreactor.Entrada.PRESION_CAMARA);
        double Temporal = 0.0;
        setpoint = bioreactor.getParametros().getEsterlizacion().getSetpoint();
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
        if (Temporal > setpoint) { // Anti Wind-UP      
            Temporal = setpoint;
        }

        if (Temporal < 0.0) { // Anti Wind_DOWN                    
            Temporal = 0.0;
        }

        Tpulso = (Temporal / setpoint) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

        if (esterilizando) {
            long transcurrido = getTiempoTranscurrido();
            if (transcurrido < tiempoEsterilizacion) {
                if ((t1 - t5) < Tpulso * 1000) {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 5);
                } else {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
                }
            } else {
                esterilizando = false;
                desfogando = true;
                tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.SSR, 10); // OFF
            }
        } else if (desfogando) {
            if (presionC > bioreactor.getParametros().getPresion().getPresionAtmosferica()) {
                finalizo = false;
                tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.SSR, 10); // OFF
            } else {
                finalizo = true;
                tiempoInicio = -1;
                bioreactor.activarSalida(Bioreactor.Salida.SSR, 10); // OFF                
            }
        } else {
            if (temperatura >= setpoint) {
                if (!esterilizando) {
                    esterilizando = true;
                    tiempoInicio = System.currentTimeMillis();
                }

                if ((t1 - t5) < Tpulso * 1000) {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 5);
                } else {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
                }
            } else {
                purgaAire(temperatura);
                if ((t1 - t5) < Tpulso * 1000) {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 5);
                } else {
                    bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
                }
            }
        }
        if ((t1 - t5) >= Tciclo * 1000) {
            t5 = System.currentTimeMillis();
        }
        potencia = (Tpulso * 100) / Tciclo;
        System.out.println("Potencia: " + potencia + " Ganancia: " + ganancia);
    }

    /**
     * Detiene inmediatamente la resistencia eléctrica.
     */
    public void detenerResistencia() {
        bioreactor.activarSalida(Bioreactor.Salida.SSR, 10);
    }

    /**
     * Actualiza el cálculo de la pendiente de la recta de temperatura,
     * usado para ajustar dinámicamente la ganancia.
     * 
     * @param temperatura valor actual de la temperatura
     */
    public void actualizaRecta(double temperatura) {
        x[y] = temperatura;
        y++;
        if (y >= 2) {
            y = 0;
            aumento = x[1] - x[0];
        }
    }

    /**
     * Timer encargado de actualizar contadores internos (tiempo de recta,
     * control y pulsos de purga).
     */
    @SuppressWarnings("StaticNonFinalUsedInInitialization")
    public Timer timer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            tiempoRecta++;
            tiempoControl++;
            if(purgaAire2)
                tPurgaAire++;
        }
    });

    /**
     * Devuelve el tiempo transcurrido desde el inicio de la esterilización.
     * 
     * @return segundos transcurridos desde el inicio, 0 si no ha comenzado
     */
    public long getTiempoTranscurrido() {
        if (!esterilizando || tiempoInicio <= 0) {
            return 0;
        }
        return (System.currentTimeMillis() - tiempoInicio) / 1000;
    }

    /**
     * Devuelve el tiempo restante para completar el ciclo de esterilización.
     * 
     * @return segundos restantes, nunca menor que 0
     */
    public long getTiempoRestante() {
        long restante = (long) tiempoEsterilizacion - getTiempoTranscurrido();
        return Math.max(restante, 0);
    }

    /**
     * Retorna el tiempo transcurrido y el total formateados como MM:SS / MM:SS.
     * 
     * @return cadena con el tiempo formateado
     */
    public String getTiempoFormateado() {
        long trans = getTiempoTranscurrido();
        long total = (long) tiempoEsterilizacion;
        
        /*return String.format("%02d:%02d / %02d:%02d",
                trans / 60, trans % 60, total / 60, total % 60);*/
        if(desfogando || finalizo){
            return String.format("!Finalizado!");
        }else{
            return String.format("%02d:%02d",trans / 60, trans % 60);
        }
    }

    /**
     * Indica si el ciclo de esterilización está en progreso.
     * 
     * @return true si se está esterilizando, false en otro caso
     */
    public boolean isEsterilizando() {
        return esterilizando;
    }

    /**
     * Cancela el proceso actual de esterilización,
     * apagando todas las salidas relacionadas.
     */
    public void cancelar() {
        esterilizando = false;
        desfogando = false;
        finalizo = false;
        purgo=false;
        purgaAire2=false;
        Variables.pulsosPurga=0;
        tiempoInicio = -1;
        bioreactor.activarSalida(Bioreactor.Salida.SUMINISTRO_VAPOR, 10);
        bioreactor.activarSalida(Bioreactor.Salida.DESFOGUE_VAPOR, 10);
        bioreactor.activarSalida(Bioreactor.Salida.DRENAJE, 10);
    }
    
    /**
     * Realiza la lógica de purga de aire antes del inicio de la esterilización,
     * utilizando el venteo de CO2 en pulsos configurables.
     *
     * @param temperatura temperatura actual de la cámara
     */
    void purgaAire(double temperatura){
        if(temperatura>bioreactor.getParametros().getEsterlizacion().getTPurga() && !purgo){
            purgaAire2=true;
        }else{
            purgaAire2=false;
            if(!purgo){
                bioreactor.activarSalida(Bioreactor.Salida.VENTEO_CO2, 5);
            }else{
                bioreactor.activarSalida(Bioreactor.Salida.VENTEO_CO2, 10);
            }
        }
    
        if(purgaAire2){
            if(tPurgaAire>0 && tPurgaAire<12){
                bioreactor.activarSalida(Bioreactor.Salida.VENTEO_CO2, 5);
            }else if(tPurgaAire>=12 && tPurgaAire<66){
                bioreactor.activarSalida(Bioreactor.Salida.VENTEO_CO2, 10);
            }else if(tPurgaAire>0){
                Variables.pulsosPurga++;
                bioreactor.activarSalida(Bioreactor.Salida.VENTEO_CO2, 10);
                tPurgaAire=0;
            }
        
            if(Variables.pulsosPurga>=bioreactor.getParametros().getEsterlizacion().getPulsosPurga()){
                purgaAire2=false;
                purgo=true;
            }
        }
    }
}

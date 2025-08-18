/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import static com.control.Variables.ErrorTemperatura;
import static com.control.Variables.Ganancia;
import static com.control.Variables.Tciclo;
import static com.control.Variables.Temporal;
import static com.control.Variables.Tpulso;
import static com.control.Variables.desvio;
import com.views.BombasPeristalticas;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author JuanDavid
 */
public class HiloPWM implements Runnable{
    private  DecimalFormat decimales2 = new DecimalFormat("0.0");
    private boolean guardo=true;
    private boolean guardo2=true;
    private boolean guardo3=true;
    private boolean guardo4=true;
    private boolean creo1=false;
    private boolean creo2=false;
    private boolean creo3=false;
    private boolean creo4=false;
    long t0 = System.currentTimeMillis();
    long t1=0;
    double cicloUtilBomba1=0,cicloUtilBomba2=0,cicloUtilBomba3=0,
                cicloUtilBomba4=0,cicloUtilAcido2,cicloUtilFoam2; // Ciclo util en segundos
    long t2 = System.currentTimeMillis();
    long t3 = System.currentTimeMillis();
    long t4 = System.currentTimeMillis();
    long t5 = System.currentTimeMillis();
    long t6 = System.currentTimeMillis();
    long t7 = System.currentTimeMillis();
    long t8 = System.currentTimeMillis();
    long tant = 0;
    long tant2 = 0;
    long tant3 = 0;
    long tant4 = 0;
    private int y=0;
    private double[]x=new double[2]; 
    private double aumento=0;
    private int tiempoRecta=0;
    private double potencia=0;
    private int tiempoControl=0;
    private int tiempoLlenado=0;
    private int tiempoDesfogue=0;
    private boolean cuentaLlenado=false,crea=false,cuentaDesfogue=false,sonido=false;
    private JFrame alerta=new JFrame("Alerta");
    private JFrame alertaTiempo=new JFrame("Tiempo Excesivo de Calentamiento");
    private JFrame alertaTemperatura=new JFrame("Temperatura Excesiva");
    private JFrame alertaPresion=new JFrame("Presion Excesiva");
    private boolean cuentaCalentamiento=false,calentando=false,esterilizando=false,desfogando=false,purgaAire2=false,purgo=false,desfogo=false;
    private boolean purgaAire=false,purgoChaqueta=false,enfrio=false,manteniendo=false,controlando=false;
    private int tiempoCalentamiento=0,tPurgaAire=0,tPurgaAireC=0,mEst=0,sEst=0,mMant=0,sMant=0,mCtrl=0,sCtrl=0;
    private double p1=0.0000011617,p2=-0.0010575,p3=0.44114,p4=65.043;
    
    private int tiempoCondensado=0;
    private int t_timer2=0;
    private String estado="";
    
    private int tpurga=0;
    private boolean abrePurga=false;
    
    
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        mEst=Variables.SPTiempo;
        mMant=Variables.retardo;
        mCtrl=Variables.duracionControl;
        if(!crea){
            alerta.setPreferredSize(new Dimension(500, 100));
            alerta.setMinimumSize(new Dimension(500, 100));
            alerta.setMaximumSize(new Dimension(500, 100));
            alerta.setAlwaysOnTop(true);
            JButton acepta=new JButton("Aceptar");
            acepta.setPreferredSize(new Dimension(100, 40));
            alerta.add(new JLabel("Verifique el suministro de agua\n Sistema de recirculación sin el nivel suficiente"));
            alerta.add(acepta);
            alerta.setLayout(new GridLayout(2, 1));
            acepta.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    alerta.dispose();
                    tiempoLlenado=0;
                    cuentaLlenado=false;
                }
            }); 
            alertaTiempo.setPreferredSize(new Dimension(400, 100));
            alertaTiempo.setMinimumSize(new Dimension(400, 100));
            alertaTiempo.setMaximumSize(new Dimension(400, 100));
            alertaTiempo.setAlwaysOnTop(true);
            JButton aceptar=new JButton("Aceptar");
            aceptar.setPreferredSize(new Dimension(100, 40));
            alertaTiempo.add(new JLabel("Verifique el suministro de vapor"));
            alertaTiempo.add(aceptar);
            alertaTiempo.setLayout(new GridLayout(2, 1));
            aceptar.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    alertaTiempo.dispose();
                    tiempoCalentamiento=0;
                    cuentaCalentamiento=false;
                    Variables.estadoControlEsterilizacion=false;
                    //com.views.Control.InicioControlEsterilizacion.setText("Iniciar");
                    //com.views.Control.InicioControlEsterilizacion.setBackground(Color.GREEN);
                    Variables.errorEsterilizacion=false;
                    //com.views.Control.LabelEstado.setText("CONTROL DETENIDO");
                }
            });
            
            alertaTemperatura.setPreferredSize(new Dimension(400, 100));
            alertaTemperatura.setMinimumSize(new Dimension(400, 100));
            alertaTemperatura.setMaximumSize(new Dimension(400, 100));
            alertaTemperatura.setAlwaysOnTop(true);
            JButton aceptan=new JButton("Aceptar");
            aceptan.setPreferredSize(new Dimension(100, 40));
            alertaTemperatura.add(new JLabel("Verifique la presion del suministro de vapor"));
            alertaTemperatura.add(aceptan);
            alertaTemperatura.setLayout(new GridLayout(2, 1));
            aceptan.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    alertaTemperatura.dispose();
                    tiempoCalentamiento=0;
                    cuentaCalentamiento=false;
                    Variables.estadoControlEsterilizacion=false;
                    //com.views.Control.InicioControlEsterilizacion.setText("Iniciar");
                    //com.views.Control.InicioControlEsterilizacion.setBackground(Color.GREEN);
                    Variables.errorEsterilizacion=false;
                    //com.views.Control.LabelEstado.setText("CONTROL DETENIDO");
                }
            });
            crea=true;
        }
        while(Variables.estadoControlTemperatura || Variables.estadoControlEsterilizacion|| Variables.estadoControlBomba1 || Variables.estadoControlBomba2 || Variables.estadoControlBomba3 || Variables.estadoControlBomba4){
                  
//----------------------------Control de temperatura NO esterilizando-----------------------------//
            
            if(Variables.estadoControlTemperatura){
                
                Variables.estadoPurga=true;
                
                if((Variables.SPTemperatura-1)<Variables.valorTemperatura && !Variables.estadoEnfriaChaqueta){
                    Variables.estadoEnfriaChaqueta=true;
                }else if(!Variables.estadoEnfriaChaqueta && !Variables.estadoSacaCondensado && Variables.nivelReservorio){
                    cuentaLlenado = false;
                    tiempoLlenado = 0;
                    if (tiempoRecta >= Variables.derivativo/2.0) {
                        actualizaRecta();
                        tiempoRecta = 0;
                        System.out.println("Actualiza Recta");
                    }
                    if (Variables.enfria) {//Si tiene que enfriar
                        ErrorTemperatura = Variables.SPTemperatura - desvio -Variables.valorTemperatura;// Cálculo del error ******

                        if (ErrorTemperatura > (-desvio - 0.1) && aumento > -0.3 && potencia < 90) {
                            if (tiempoControl >= Variables.derivativo) {
                                tiempoControl = 0;
                                if (aumento > 0.5) {
                                    Ganancia += Variables.integral * 1.0;
                                } else if (aumento > 0.1) {
                                    Ganancia += Variables.integral * 0.6;
                                } else {
                                    Ganancia += Variables.integral * 0.3;
                                }
                            }
                        }
                        Temporal = Math.abs(ErrorTemperatura) * Ganancia;  // Control Proporcional.
                        if (Temporal > Variables.SPTemperatura) // Anti Wind-UP      
                            Temporal = Variables.SPTemperatura;

                        if (Temporal < 0.0) // Anti Wind_DOWN                    
                            Temporal = 0.0;

                        Tpulso = (Temporal / Variables.SPTemperatura) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

                        if (Variables.valorTemperatura <= Variables.SPTemperatura - 0.2) {
                            //Hardware.Activar("BombaAgua", false);
                            if (Variables.valorTemperatura <= Variables.SPTemperatura - 1.0){
                                Variables.enfria=false;
                            }
                        }else{
                            //Hardware.Activar("BombaAgua", true);
                            //RecirculaAgua(true);
                        }

                        if ((t1 - t5) < Tpulso * 1000) {
                            //Hardware.Activar("Peltier+", true);
                            //Calienta(true);
                        }else {
                            //Hardware.Activar("Peltier+", false);
                        }
                        //Hardware.Activar("Peltier-", false);
                        if ((t1 - t5) >= Tciclo * 1000) {
                            t5 = System.currentTimeMillis();
                        }
                        potencia = (Tpulso * 100) / Tciclo;
                        System.out.println("Potencia: "+potencia+" Ganancia: "+Ganancia);
                    }else {//Si tiene que calentar
                        ErrorTemperatura = desvio + Variables.SPTemperatura - Variables.valorTemperatura;// Cálculo del error ******

                        if (ErrorTemperatura > (desvio + 0.1) && aumento < 0.3 && potencia < 90) {
                            if (tiempoControl >= Variables.derivativo) {
                                tiempoControl = 0;
                                if (aumento < -0.5) {
                                    Ganancia += Variables.integral * 1.0;
                                } else if (aumento < -0.1) {
                                    Ganancia += Variables.integral * 0.6;
                                } else {
                                    Ganancia += Variables.integral * 0.3;
                                }
                            }
                        }

                        Temporal = ErrorTemperatura * Ganancia;  // Control Proporcional.
                        if (Temporal > Variables.SPTemperatura) // Anti Wind-UP      
                            Temporal = Variables.SPTemperatura;

                        if (Temporal < 0.0) // Anti Wind_DOWN                    
                            Temporal = 0.0;

                        Tpulso = (Temporal / Variables.SPTemperatura) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

                        if (Variables.valorTemperatura >= Variables.SPTemperatura + 0.2) {
                            //RecirculaAgua(false);
                            if (Variables.valorTemperatura >= Variables.SPTemperatura + 1.0){
                                Variables.enfria=true;
                            }
                        }else{
                            //RecirculaAgua(true);
                        }

                        if ((t1 - t5) < Tpulso * 1000) {
                            //Calienta(true);
                        }else {
                            //Calienta(false);
                        }
                        if ((t1 - t5) >= Tciclo * 1000) {
                            t5 = System.currentTimeMillis();
                        }
                        potencia = (Tpulso * 100) / Tciclo;
                        System.out.println("Potencia: "+potencia+" Ganancia: "+Ganancia);
                    }
                    
                }
                
                if(Variables.estadoEnfriaChaqueta && !Variables.estadoSacaCondensado){
                    //EnfriaChaqueta();
                }
                if(!Variables.estadoEnfriaChaqueta && Variables.estadoSacaCondensado){
                    //SacaCondensado();
                }
                
                if(Variables.PresionCamara>Variables.presionPurgaCO2){
                    //Hardware.Activar("AC11", true);
                }
                if(Variables.PresionCamara<Variables.presionPurgaCO2-Variables.histeresisPurgaCO2){
                    //Hardware.Activar("AC11", false);
                }
                
            }
 //----------------------------Fin Control de temperatura-----------------------------//
   
       
//----------------------------Control de esterilizacion-----------------------------//
            if( Variables.estadoControlEsterilizacion){
                
                if(Variables.estadoDesfogue){
                    Variables.estadoEnfriaChaqueta=false;
                    Variables.estadoDesfogue=true;
                    Variables.estadoEsterilizando=false;
                    if(Variables.PresionPreCamara>Variables.PresionAtmosferica+5){
                        Variables.estadoDesfogue=true;
                        Variables.estadoEsterilizando=false;
                        //DesfogaVapor();
                    }else{
                        if(Variables.valorTemperatura < 95.0){
                            Variables.estadoDesfogue=false;
                            Variables.estadoEnfriaChaqueta=true;
                        }else{
                            Variables.estadoDesfogue=true;
                        }
                    }
                }else if(Variables.estadoEnfriaChaqueta){
                    Variables.estadoEnfriaChaqueta=true;
                    Variables.estadoDesfogue=false;
                    Variables.estadoEsterilizando=false;
                }else{
                    Variables.estadoEsterilizando=true;
                    cuentaLlenado = false;
                    tiempoLlenado = 0;
                    if (tiempoRecta >= Variables.derivativo/2.0) {
                        actualizaRecta();
                        tiempoRecta = 0;
                        System.out.println("Actualiza Recta");
                    }
                    if (Variables.enfria) {//Si tiene que enfriar
                        ErrorTemperatura = Variables.SPEsterilizacion - desvio -Variables.valorTemperatura;// Cálculo del error ******

                        if (ErrorTemperatura > (-desvio - 0.1) && aumento > -0.3 && potencia < 90) {
                            if (tiempoControl >= Variables.derivativo) {
                                tiempoControl = 0;
                                if (aumento > 0.5) {
                                    Ganancia += Variables.integral * 1.0;
                                } else if (aumento > 0.1) {
                                    Ganancia += Variables.integral * 0.6;
                                } else {
                                    Ganancia += Variables.integral * 0.3;
                                }
                            }
                        }
                        Temporal = Math.abs(ErrorTemperatura) * Ganancia;  // Control Proporcional.
                        if (Temporal > Variables.SPEsterilizacion) // Anti Wind-UP      
                            Temporal = Variables.SPEsterilizacion;

                        if (Temporal < 0.0) // Anti Wind_DOWN                    
                            Temporal = 0.0;

                        Tpulso = (Temporal / Variables.SPEsterilizacion) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

                        if (Variables.valorTemperatura <= Variables.SPEsterilizacion - 0.2) {
                            //Hardware.Activar("BombaAgua", false);
                            //Calienta(false);
                            if (Variables.valorTemperatura <= Variables.SPEsterilizacion - 1.0){
                                Variables.enfria=false;
                            }
                        }else{
                            //Hardware.Activar("BombaAgua", true);
                            //RecirculaAgua(true);
                        }

                        if ((t1 - t5) < Tpulso * 1000) {
                            //Hardware.Activar("Peltier+", true);
                            //Calienta(false);
                        }else {
                            //Hardware.Activar("Peltier+", false);
                        }
                        //Hardware.Activar("Peltier-", false);
                        if ((t1 - t5) >= Tciclo * 1000) {
                            t5 = System.currentTimeMillis();
                        }
                        potencia = (Tpulso * 100) / Tciclo;
                        System.out.println("Potencia: "+potencia+" Ganancia: "+Ganancia);
                    }else {//Si tiene que calentar
                        ErrorTemperatura = desvio + Variables.SPEsterilizacion - Variables.valorTemperatura;// Cálculo del error ******

                        if (ErrorTemperatura > (desvio + 0.1) && aumento < 0.3 && potencia < 90) {
                            if (tiempoControl >= Variables.derivativo) {
                                tiempoControl = 0;
                                if (aumento < -0.5) {
                                    Ganancia += Variables.integral * 1.0;
                                } else if (aumento < -0.1) {
                                    Ganancia += Variables.integral * 0.6;
                                } else {
                                    Ganancia += Variables.integral * 0.3;
                                }
                            }
                        }

                        Temporal = ErrorTemperatura * Ganancia;  // Control Proporcional.
                        if (Temporal > Variables.SPEsterilizacion) // Anti Wind-UP      
                            Temporal = Variables.SPEsterilizacion;

                        if (Temporal < 0.0) // Anti Wind_DOWN                    
                            Temporal = 0.0;

                        Tpulso = (Temporal / Variables.SPEsterilizacion) * Tciclo;  // Tpulso(t)= Tciclo*(y(t)-ymin)/(ymax - ymin); calculo de ciclo util para control de resistencia.ymax=140.ymin=0;

                        if (Variables.valorTemperatura >= Variables.SPEsterilizacion + 0.2) {
                            //RecirculaAgua(false);
                            if (Variables.valorTemperatura >= Variables.SPEsterilizacion + 1.0){
                                Variables.enfria=true;
                            }
                        }else{
                            //RecirculaAgua(true);
                        }

                        if ((t1 - t5) < Tpulso * 1000) {
                            //Calienta(true);
                        }else {
                            //Calienta(false);
                        }
                        if ((t1 - t5) >= Tciclo * 1000) {
                            t5 = System.currentTimeMillis();
                        }
                        potencia = (Tpulso * 100) / Tciclo;
                        System.out.println("Potencia: "+potencia+" Ganancia: "+Ganancia);
                    }
                
                    if(Variables.PresionCamara<Variables.PresionAtmosferica+5){
                        //Hardware.Activar("AC11", true); //abre venteo para purga
                        Variables.estadoPurga=false;
                    }else{
                        if(Variables.valorTemperatura<100.0){
                            Variables.estadoPurga=true;
                        }else{
                            Variables.estadoPurga=false;
                        }
                        
                    }
                    
                }
                //--------------
                if(Variables.estadoEnfriaChaqueta){
                    //EnfriaChaqueta();
                    if(Variables.valorTemperatura<Variables.SPTemperatura-1){
                        Variables.estadoSacaCondensado=false;
                        Variables.estadoSacaCondensado=true;
                    }
                }
                if(Variables.estadoSacaCondensado){
                    Variables.estadoSacaCondensado=true;
                    //SacaCondensado();
                }
            }
            if(!Variables.estadoControlEsterilizacion && !Variables.estadoControlTemperatura){
                //ApagaSalidas();
            }
 //----------------------------Fin Control de esterilizacion-----------------------------//          
            
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPWM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        creo1=false;
        creo2=false;
        creo3=false;
        creo4=false;
        cuentaDesfogue=false;
        tiempoDesfogue=0;
    }
    
    public void actualizaRecta(){
        x[y]=Variables.valorTemperatura;
        y++;
        if(y>=2){
            y=0;
            aumento=x[1]-x[0];
        }
    }
    
    public void actualizaRecta(double temperatura){
        x[y]=temperatura;
        y++;
        if(y>=2){
            y=0;
            aumento=x[1]-x[0];
        }
    }
       
    public void ReproducirSonido(String nombreSonido) throws IOException{
       try {
        AudioInputStream audioInputStream;
        audioInputStream=AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       } catch(UnsupportedAudioFileException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
     }
}

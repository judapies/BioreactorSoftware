/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunicacion;

import com.control.Variables;
import com.controller.ControladorAgitacion;
import com.controller.ControladorBombasPorReactor;
import com.controller.ControladorEsterilizacion;
import com.controller.ControladorNivel;
import com.controller.ControladorOD;
import com.controller.ControladorPH;
import com.controller.ControladorTemperatura;
import jPicUsb.iface;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.views.InterfazPrincipal;
import com.model.Bioreactor;
import com.util.LabelUpdater;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanDavid
 */
public class Hilo implements Runnable {

    int tiempoespera = 0, IndiceRPM = 0;
    DecimalFormat decimales = new DecimalFormat("0.00");
    List<ControladorBombasPorReactor> controladoresBombas = new ArrayList<>();
    List<ControladorEsterilizacion> controladorEsterilizacion = new ArrayList<>();
    List<ControladorPH> controladorPH = new ArrayList<>();
    List<ControladorAgitacion> controladorAgitacion = new ArrayList<>();
    List<ControladorOD> controladorOD = new ArrayList<>();
    List<ControladorNivel> controladorNivel = new ArrayList<>();
    List<ControladorTemperatura> controladorTemperatura = new ArrayList<>();

    int presionPreCamara = 0;
    double temperatura = 10;
    boolean inicio = false;

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        cargarIface();

        for (Bioreactor bioreactor : Variables.bioreactores) {
            controladoresBombas.add(new ControladorBombasPorReactor(bioreactor));
            controladorEsterilizacion.add(new ControladorEsterilizacion(bioreactor));
            controladorPH.add(new ControladorPH(bioreactor));
            controladorAgitacion.add(new ControladorAgitacion(bioreactor));
            controladorOD.add(new ControladorOD(bioreactor));
            controladorNivel.add(new ControladorNivel(bioreactor));
            controladorTemperatura.add(new ControladorTemperatura(bioreactor));
        }

        while (true) {
            for (int i = 0; i < Variables.bioreactores.size(); i++) {
                Bioreactor b = Variables.bioreactores.get(i);
                try {
                    //simulaLecturas();
                    b.actualizarEntradasDesdeTrama(establecerComunicacion(b, false));
                } catch (Exception e) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, "Error en establecer Comunicacion", e);
                    JOptionPane.showMessageDialog(null,
                            "Error durante la comunicación: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (!Variables.testComponentes) {
                    controladorNivel.get(i).controlar();

                    if (b.isEstadoControlCO2()) {
                        b.controlarVenteoCO2(b.getParametros().getCO2().getSetpoint(), b.getParametros().getCO2().getHisteresis());
                    } else {
                        b.activarSalida(Bioreactor.Salida.VENTEO_CO2, 10);
                    }

                    if (b.isEstadoControlpH()) {
                        controladorPH.get(i).controlar();
                    } else {
                        controladorPH.get(i).detenerBombasPH();
                        controladoresBombas.get(i).actualizarTodas();
                    }
                    
                    //System.out.println(b.getId()+":"+b.getBomba(0).isEstadoControl());
                    //System.out.println(b.getId()+":"+b.leerSalida(Bioreactor.Salida.BOMBA_PERISTALTICA_1));
                    
                    if (b.isEstadoControlAgitacion()) {
                        controladorAgitacion.get(i).controlar();
                    } else {
                        controladorAgitacion.get(i).detener();
                    }

                    if (b.isEstadoControlTemperatura()) {
                        if (b.getCapacidadLitros() < 3) {
                            controladorTemperatura.get(i).controlarResistencia();
                        } else {
                            controladorTemperatura.get(i).controlarIntercambiador();
                        }
                    } else {
                        controladorTemperatura.get(i).detenerIntercambiador();
                        if (!b.isEstadoControlEsterilizacion()) {
                            controladorTemperatura.get(i).detenerResistencia();
                        }
                    }

                    if (b.isEstadoControlOD()) {
                        controladorOD.get(i).controlar();
                    } else {
                        controladorOD.get(i).detener();
                    }

                    if (b.isEstadoControlEsterilizacion()) {
                        controladorEsterilizacion.get(i).configurar(b.getParametros().getEsterlizacion().getSetpoint(), b.getParametros().getEsterlizacion().getHisteresis(),
                                b.getParametros().getEsterlizacion().getDesvio(), b.getParametros().getEsterlizacion().getTiempoSegundos());
                        if (b.getCapacidadLitros() < 3) {
                            controladorEsterilizacion.get(i).controlarResistencia();
                        } else {
                            controladorEsterilizacion.get(i).controlar();
                        }
                    } else {
                        if(b.getParametros().getTemperatura().isControlIntercambiador())
                            controladorEsterilizacion.get(i).cancelar();    
                        if (!b.isEstadoControlTemperatura()) {
                            controladorTemperatura.get(i).detenerResistencia();
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ActualizarCampos();

            Calendar cal = new GregorianCalendar();
            Formatter formato2H = new Formatter();  // Formato de la hora
            Formatter formato2M = new Formatter();
            Formatter formato2S = new Formatter();
            int hora = cal.get(Calendar.HOUR_OF_DAY);
            int minuto = cal.get(Calendar.MINUTE);
            int segundo = cal.get(Calendar.SECOND);
            formato2H.format("%02d", hora);
            String hora2 = formato2H.toString();    // String de la hora dos cifras
            formato2M.format("%02d", minuto);
            String minuto2 = formato2M.toString();  // String de minutos dos cifras
            formato2S.format("%02d", segundo);
            String segundo2 = formato2S.toString(); // String de segundos dos cifras

            if (Variables.login) {
                com.views.InterfazPrincipal.Hora.setText("Hora y Fecha:  " + hora2 + ":" + minuto2 + ":" + segundo2 + "  --  " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) + " -- Usuario: " + Variables.usuario);
                if (InterfazPrincipal.Biorreactor1 != null) {
                    com.views.InterfazFermentador.Hora.setText("Hora y Fecha:  " + hora2 + ":" + minuto2 + ":" + segundo2 + "  --  " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) + " -- Usuario: " + Variables.usuario);
                }
            } else {
                com.views.InterfazPrincipal.Hora.setText("Hora y Fecha:  " + hora2 + ":" + minuto2 + ":" + segundo2 + "  --  " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
                com.views.InterfazFermentador.Hora.setText("Hora y Fecha:  " + hora2 + ":" + minuto2 + ":" + segundo2 + "  --  " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));

            }
        }
    }

    public void cargarIface() {
        try {
            iface.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        iface.set_instance(0);
        iface.set_vidpid("vid_04d8&pid_000b");
    }

    public byte[] establecerComunicacion(Bioreactor b, boolean imprime) {
        byte tmp[] = new byte[64];
        for (int i = 0; i < 64; i++) {
            tmp[i] = 0;
        }
        iface.QWrite(b.getTramaDeSalida(), 64, 10);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        tmp = iface.QRead(64, 10);
        if (tmp.length != 0) {
            while (tmp[0] != b.getId() && tmp[30] != b.getId()) {
                if (imprime) {
                    System.out.println("Esperando Reactor " + b.getId());
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                tmp = iface.QRead(64, 10);
                if (tiempoespera >= 50000) {
                    tiempoespera = 0;
                    break;
                }
                tiempoespera++;
                for (int i = 0; i < 32; i++) {
                    if (imprime) {
                        System.out.print(tmp[i]);
                        System.out.print("-");
                    }
                }
                if (imprime) {
                    System.out.println("");
                }
            }
            tiempoespera = 0;
            if (imprime) {
                System.out.println("Lectura Exitosa");
            }
        } else {
            if (tiempoespera >= 50) {
                Variables.añadirEvento("Perdida de comunicación");
                JOptionPane.showMessageDialog(null, "No hay comunicación con Maestro\n Verifique el interruptor de Encendido", "Error", JOptionPane.ERROR_MESSAGE);
                tiempoespera = 0;
            }
            tiempoespera++;
            tmp = new byte[64];
            for (int i = 0; i < 64; i++) {
                tmp[i] = 0;
            }
        }
        return tmp;
    }

    public void simulaLecturas() {
        DecimalFormat decimales2 = new DecimalFormat("0.0");

        Variables.valorTemperatura += 0.1;
        Bioreactor bio1 = Variables.bioreactores.get(1);

        if (bio1.leerSalida(Bioreactor.Salida.SUMINISTRO_VAPOR) == 5) {
            bio1.actualizarEntrada(Bioreactor.Entrada.PRESION_PRE_CAMARA, presionPreCamara++);
        } else {
            if (presionPreCamara >= 0) {
                bio1.actualizarEntrada(Bioreactor.Entrada.PRESION_PRE_CAMARA, presionPreCamara--);
            }
        }
        bio1.actualizarEntrada(Bioreactor.Entrada.ENTRADA_DIGITAL_1, 5);
        if (bio1.isEstadoControlTemperatura()) {
            if (bio1.leerSalida(Bioreactor.Salida.ENTRADA_INTERCAMBIADOR) == 5) {
                bio1.actualizarEntrada(Bioreactor.Entrada.TEMPERATURA_1, temperatura += 1.1);
            } else {
                bio1.actualizarEntrada(Bioreactor.Entrada.TEMPERATURA_1, temperatura -= 1.1);

            }
        } else {
            bio1.actualizarEntrada(Bioreactor.Entrada.TEMPERATURA_1, 98.5);
        }

    }

    public void ActualizarCampos() {

        // Promedio de Temperatura-----------------------------------------------------------------------------------------------------------------------------
        DecimalFormat decimales2 = new DecimalFormat("0.0");
        DecimalFormat decimal = new DecimalFormat("000");//Formato para mostrar la presion

        //for (Bioreactor bio : Variables.bioreactores) {
        for (int i = 0; i < Variables.bioreactores.size(); i++) {
            Bioreactor bio = Variables.bioreactores.get(i);
            //Actualiza Label de Interfaz Principal
            if (bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1) < 5.0 || bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1) > 200.0) {
                LabelUpdater.actualizarLabel("Temperatura", bio.getId() - 100, "---");
            } else {
                LabelUpdater.actualizarLabel("Temperatura", bio.getId() - 100, "" + decimales2.format(bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1)));
            }

            if (bio.leerEntrada(Bioreactor.Entrada.PH) < 0.1 || bio.leerEntrada(Bioreactor.Entrada.PH) > 18.0) {
                LabelUpdater.actualizarLabel("pH", bio.getId() - 100, "---");
            } else {
                LabelUpdater.actualizarLabel("pH", bio.getId() - 100, "" + decimales2.format(bio.leerEntrada(Bioreactor.Entrada.PH)));
            }
            LabelUpdater.actualizarLabel("CO2", bio.getId() - 100, "" + decimales2.format(bio.leerEntrada(Bioreactor.Entrada.CO2)));
            LabelUpdater.actualizarLabel("Agitacion", bio.getId() - 100, "" + decimal.format(bio.leerEntrada(Bioreactor.Entrada.RPM_CH1)));
            ///////////////////////////////////////////////

            if (Variables.idBioreactor == bio.getId()) {
                double temp1 = bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_1);
                double temp2 = bio.leerEntrada(Bioreactor.Entrada.TEMPERATURA_2);
                double valorPH = bio.leerEntrada(Bioreactor.Entrada.PH);
                double valorOD = bio.leerEntrada(Bioreactor.Entrada.OD);

                boolean tempOK = temp1 >= 5.0 && temp1 <= 200.0;
                boolean temp2OK = temp2 >= 5.0 && temp2 <= 200.0;
                boolean phOK = valorPH >= 0.1 && valorPH <= 18.0;
                boolean odOK = valorOD >= 0.1 && valorOD <= 200.0;

                if (com.views.Control.PointValueTemperatura != null) {
                    com.views.Control.PointValueTemperatura.setText(tempOK ? decimales2.format(temp1) : "---");
                    com.views.Control.PointValueEsterilizacion.setText(tempOK ? decimales2.format(temp1) : "---");
                    com.views.Control.PointValuepH.setText(phOK ? decimales2.format(valorPH) : "---");
                    com.views.Control.PointValueOD.setText(odOK ? decimales2.format(valorOD) : "---");
                    com.views.Control.PointValueAgitador.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.RPM_CH1)));  // Imprime valor en promedio
                    com.views.Control.botonNivelAlto.setBackground(bio.leerEntrada(Bioreactor.Entrada.NIVEL_ALTO) < 60 ? Color.GREEN : Color.RED);
                    com.views.Control.botonNivelMedio.setBackground(bio.leerEntrada(Bioreactor.Entrada.NIVEL_MEDIO) < 60 ? Color.GREEN : Color.RED);
                    com.views.Control.botonNivelBajo.setBackground(bio.leerEntrada(Bioreactor.Entrada.NIVEL_BAJO) < 60 ? Color.GREEN : Color.RED);
                    com.views.Control.pointValuePresionCamara.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.PRESION_CAMARA)));
                    com.views.Control.pointValuePresionPrecamara.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.PRESION_PRE_CAMARA)));
                    com.views.Control.PointValueCO2.setText(Double.toString(bio.leerEntrada(Bioreactor.Entrada.CO2)));
                    com.views.Control.PointValueTiempoEsterilizacion.setText(controladorEsterilizacion.get(i).getTiempoFormateado());
                }

                if (Variables.testComponentes) {
                    com.views.TestComponentes.PointValueTemperatura.setText(tempOK ? "" + decimales2.format(temp1) + " °C" : "---");
                    com.views.TestComponentes.PointValueTemperatura2.setText(temp2OK ? decimales2.format(temp2) + " °C" : "---");
                    com.views.TestComponentes.PointValuepH.setText(phOK ? decimales2.format(valorPH) : "---");
                    com.views.TestComponentes.PointValueOD.setText(odOK ? "" + decimales2.format(valorOD) + " %" : "---");
                    com.views.TestComponentes.PointValueAgitador.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.RPM_CH1)));
                    com.views.TestComponentes.PointValueRPMCH2.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.RPM_CH2)));

                    com.views.TestComponentes.botonNivelAlto.setText(bio.leerEntrada(Bioreactor.Entrada.NIVEL_ALTO) < 60 ? "Activo" : "No activo");
                    com.views.TestComponentes.botonNivelMedio.setText(bio.leerEntrada(Bioreactor.Entrada.NIVEL_MEDIO) < 60 ? "Activo" : "No activo");
                    com.views.TestComponentes.botonNivelBajo.setText(bio.leerEntrada(Bioreactor.Entrada.NIVEL_BAJO) < 60 ? "Activo" : "No activo");
                    com.views.TestComponentes.PointValueIN1.setText(bio.leerEntrada(Bioreactor.Entrada.ENTRADA_DIGITAL_1) == 5 ? "Activo" : "No activo");
                    com.views.TestComponentes.PointValueIN2.setText(bio.leerEntrada(Bioreactor.Entrada.ENTRADA_DIGITAL_2) == 5 ? "Activo" : "No activo");

                    com.views.TestComponentes.PointValuePresionCamara.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.PRESION_CAMARA)) + " kpa");
                    com.views.TestComponentes.PointValuePresionPrecamara.setText("" + (int) (bio.leerEntrada(Bioreactor.Entrada.PRESION_PRE_CAMARA)) + " kpa");

                    com.views.TestComponentes.PointValueCO2.setText(Double.toString(bio.leerEntrada(Bioreactor.Entrada.CO2)));
                }
            }
        }
    }
}

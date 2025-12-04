/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.model.Bioreactor;
import com.model.BombaPeristaltica;
import com.views.InterfazPrincipal;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanDavid
 */
public class Variables implements Serializable {

    private static final long serialVersionUID = 01;
    public static List<Bioreactor> bioreactores;
    public static List<BombaPeristaltica> bombasPeristalticas;
    public static boolean testComponentes=false;
    // Setpoints
    public static double SPpH = 0.0, SPOD = 0.0, SPCO2 = 0.0, SPTemperatura = 0.0, SPRPM = 0.0, SPBombaMedio = 50.0, SPBombaAcido = 50, SPBombaBase = 50,
            SPBombaFoam = 50, SPpH2 = 0.0, SPOD2 = 0.0, SPCO22 = 0.0, SPTemperatura2 = 0.0, SPRPM2 = 0.0, SPBombaMedio2 = 50.0, SPBombaAcido2 = 50, SPBombaBase2 = 50,
            SPBombaFoam2 = 50, SPEsterilizacion = 0;
    // Valores Actuales (Point Value)
    public static double PVpH = 0.0, PVOD = 0.0, PVRedox = 0.0, PVCO2 = 0.0, PVTemperatura = 30.0, PVRPM = 0.0, PVBombaMedio = 0.0, PVpH2 = 0.0, PVOD2 = 0.0, PVCO22 = 0.0,
            PVTemperatura2 = 0.0, PVRPM2 = 0.0, PVBombaMedio2 = 0.0, PresionCamara = 80.0, PresionPreCamara = 80.0, PresionAtmosferica = 0.0;
    // Periodos programados de las bombas (Maximo 20 segundos)
    public static double TAcido = 10.0, TBase = 10.0, TFoam = 10.0, TAcido2 = 10.0, TBase2 = 10.0, TFoam2 = 10.0,
            valorPc = 75, valorPp = 75;
    // Estados de controles y distintas Variables
    public static boolean estadoControlpH = false, estadoControlOD = false, estadoControlCO2 = false, estadoControlAgitador = false,
            estadoControlTemperatura = false, estadoControlBombaMedio = false, estadoControlBombaAcido = false, estadoControlBombaBase = false,
            estadoControlBombaFoam = false, estadoControlFoam = false, estadoAdquisicion = false, SelecMinutos = false, SelecSegundos = true,
            ajusteTemperatura = false, ajusteAgitador = false, ajustepH = false, ajusteOD = false, ajusteCO2 = false, estadoEVAire = false, ajusteRedox = false,
            modbusRedox = false, modbuspH = false, modbusOD = false, nivelFoam = false,
            estadoEVOxigeno = false, estadoEVCO2 = false, estadoEVNitrogeno = false, estadoControlEsterilizacion = false, ajusteEsterilizacion = false, ajustePresion = false,
            estadoControlpH2 = false, estadoControlOD2 = false, estadoControlCO22 = false, estadoControlAgitador2 = false,
            estadoControlTemperatura2 = false, estadoControlBombaMedio2 = false, estadoControlBombaAcido2 = false, estadoControlBombaBase2 = false,
            estadoControlBombaFoam2 = false, estadoControlFoam2 = false, estadoAdquisicion2 = false, SelecMinutos2 = true, SelecSegundos2 = false,
            ajusteTemperatura2 = false, ajusteAgitador2 = false, ajustepH2 = false, ajusteOD2 = false, ajusteCO22 = false, estadoEVAire2 = false,
            estadoEVOD2 = false, estadoEVCO22 = false, estadoValvulaManual = false, estadoAguaProceso = false, estadoLuzMirilla = false,
            estadoAireChaqueta = false, estadoVaporChaqueta = false, estadoSalidaAgua = false, estadoVaporIntercambiador = false, estadoRecirculacion = false,
            estadoDesfogue = false, estadoAguaChaqueta = false, estadoBombaAgua = false, estadoPurga = false, errorEsterilizacion = false,
            estadoBomba1 = false, estadoBomba2 = false, estadoBomba3 = false, estadoBomba4 = false;
    // Ajustes de histeresis y de banda para controles de gases y ph respectivamente.
    public static double Banda = 0.1, HisteresisOD = 1.0, HisteresisCO2 = 1.0, ajustePresionCamara = 0, ajustePresionPreCamara = 0,
            Banda2 = 0.1, HisteresisOD2 = 1.0, HisteresisCO22 = 1.0;
    public static int flancoOD = 0, flancoCO2 = 0, tipogas = 0, SPTiempo = 0, indicePromedioPc = 0, indicePromedioPp = 0,
            flancoOD2 = 0, flancoCO22 = 0, tipogas2 = 0, mediaPresion = 0, idBioreactor = 1;
    // Ajustes control de temperatura
    public static double ErrorTemperatura = 0, desvio = 0.0, Temporal = 0.0, Tpulso = 0.0, Tciclo = 10.0, Ganancia = 5, OffsetTemperatura = 0.0,
            PromedioTemperatura = 4.0, valorTemperatura = 0.0, Histeresis = 0.0, temperaturaInicial = 0.0, temperaturaFinal = 0.0,
            ErrorTemperatura2 = 0, desvio2 = 0.0, Temporal2 = 0.0, Tpulso2 = 0.0, Tciclo2 = 10.0, Ganancia2 = 5, OffsetTemperatura2 = 0.0,
            PromedioTemperatura2 = 4.0, valorTemperatura2 = 0.0, Coeficiente = 0.0, Ttrampa = 0, temperaturaControl = 0, valorRedox = 0.0, valorRedox2 = 0.0;
    public static int indicePromedioTemperatura = 0, indicePromedioTemperatura2 = 0, pulsosPurga = 0, pulsosPurgaProgramados = 0, retardo = 0, duracionControl = 0;
    public static double[] TemperaturaProm = new double[64], TemperaturaProm2 = new double[64];
    public static double[] PcProm = new double[50], PpProm = new double[50];
    // Ajustes control de Velocidad
    public static double BandaAgitador = 0.0, VelocidadRespuesta = 0.0, OffsetRPM = 0.0, LecturaRPM = 0.0, PromedioRPM = 1.0, valorRPM = 0.0,
            BandaAgitador2 = 0.0, VelocidadRespuesta2 = 0.0, OffsetRPM2 = 0.0, LecturaRPM2 = 0.0, PromedioRPM2 = 1.0, valorRPM2 = 0.0;
    public static double[] RPMProm = new double[64], RPMProm2 = new double[64];
    public static int indicePromedioRPM = 0, indicePromedioRPM2 = 0;
    // Ajustes control de pH
    public static double OffsetpH = 0.0, PromediopH = 0.0, valorpH = 0.0,
            OffsetpH2 = 0.0, PromediopH2 = 0.0, valorpH2 = 0.0;
    public static double[] pHProm = new double[64], pHProm2 = new double[64];
    public static int indicePromediopH = 0, indicePromediopH2 = 0;
    // Ajustes control Oxigeno Disuelto(OD)
    public static double OffsetOD = 0.0, PromedioOD = 0.0, valorOD = 0.0,
            OffsetOD2 = 0.0, PromedioOD2 = 0.0, valorOD2 = 0.0;
    // Ajustes control Redox
    public static double OffsetRedox = 0.0, PromedioRedox = 0.0, mRedox = 0, bRedox = 0;
    public static double[] RedoxProm = new double[64], RedoxProm2 = new double[64];
    public static double[] ODProm = new double[64], ODProm2 = new double[64];
    public static int indicePromedioOD = 0, indicePromedioRedox = 0, indicePromedioOD2 = 0, purgaChaquetaON = 5, purgaChaquetaOFF = 300, purgaCamaraON = 12, purgaCamaraOFF = 66;
    // Ajustes control de CO2
    public static double OffsetCO2 = 0.0, PromedioCO2 = 0.0, valorCO2 = 0.0;
    public static double[] CO2Prom = new double[64];
    public static int indicePromedioCO2 = 0;
    // Variables para comunicaciòn USB
    public static byte[] PC_Tx = new byte[64];
    public static int[] PC_Rx = new int[64];
    public static int TMuestreo = 1000, IngresoDatos = 0, hilos = 0;

    public static boolean nivelReserva = false, controlCascada = false, nivel2 = false, nivel3 = false;
    public static long tInicial = 0, tFinal = 0;
    public static boolean estadoControlBomba1;
    public static boolean estadoControlBomba2;
    public static boolean estadoControlBomba3;
    public static boolean estadoControlBomba4;
    public static boolean runCalibra = false;
    public static boolean runBomba1 = false;
    public static boolean runBomba2 = false;
    public static boolean runBomba3 = false;
    public static boolean runBomba4 = false;
    public static int TBomba1 = 0;
    public static int SPBomba1 = 0;
    public static int TBomba2 = 0;
    public static int SPBomba2 = 0;
    public static int TBomba3 = 0;
    public static int SPBomba3 = 0;
    public static int TBomba4 = 0;
    public static int SPBomba4 = 0;
    public static boolean asignaAcido = false, asignaBase = false, asignaFoam = false, asignaNivel2S = false, asignaNivel2H = false;
    public static String asignacionBomba1 = "Ninguno", asignacionBomba2 = "Ninguno", asignacionBomba3 = "Ninguno",
            asignacionBomba4 = "Ninguno", usuario = null, contraseña = null, rol = "ADMIN", bioreactor = "";
    public static boolean estadoOn1 = false, estadoOn2 = false, estadoOn3 = false, estadoOn4 = false, login = true, estadoPeltierp = false, estadoPeltiern = false;
    public static boolean calibraBomba1 = false, calibraBomba2 = false, calibraBomba3 = false, calibraBomba4 = false;
    public static double totalBomba1 = 0, totalBomba2 = 0, totalBomba3 = 0, totalBomba4 = 0;
    public static double tBomba1 = 0, tBomba2 = 0, tBomba3 = 0, tBomba4 = 0;
    public static double calibracionBomba1 = 0, calibracionBomba2 = 0, calibracionBomba3 = 0, calibracionBomba4 = 0;
    public static boolean enfria;
    public static double pHinf = 4.0, pHsup = 7.0;
    public static double derivativo = 120, integral = 1;
    public static double mpH = 0, bpH = 0;
    public static double mOD = 0.2545, bOD = -50.0;
    public static double mTem = 0.1283, bTem = -24.95;

    //Variables planta biorreactores 1
    public static boolean estadoAC1 = false, estadoAC2 = false, estadoAC3 = false, estadoAC4 = false, estadoAC5 = false, estadoAC6 = false, estadoAC7 = false,
            estadoAC8 = false, estadoAC9 = false, estadoAC10 = false, estadoAC11 = false, estadoAC12 = false, estadoBuzzer = false, estadoSSR = false;
    public static boolean estadoEnfriaChaqueta = false, estadoSacaCondensado = false, estadoEsterilizando = false;
    public static double presionPurgaCO2 = 0, histeresisPurgaCO2 = 0;
    public static int SPtiempoEsterilizacion = 0, SPminutosEsterilizacion = 0, SPsegundosEsterilizacion = 0, minutosEsterilizacion = 0, segundosEsterilizacion = 0;
    public static boolean nivelAlto = false, nivelMedio = false, nivelBajo = false;

    public static boolean asignaNivelAlto = false, asignaNivelMedio = false, asignaNivelBajo = false;

    public static boolean nivelReservorio = false;

    //--------------------Variables Biorreactor 2----------------------//
    // Setpoints
    public static double SPpH_B2 = 0.0, SPOD_B2 = 0.0, SPCO2_B2 = 0.0, SPTemperatura_B2 = 0.0, SPRPM_B2 = 0.0, SPBombaMedio_B2 = 50.0, SPBombaAcido_B2 = 50, SPBombaBase_B2 = 50,
            SPBombaFoam_B2 = 50, SPpH2_B2 = 0.0, SPOD2_B2 = 0.0, SPCO22_B2 = 0.0, SPTemperatura2_B2 = 0.0, SPRPM2_B2 = 0.0, SPBombaMedio2_B2 = 50.0, SPBombaAcido2_B2 = 50, SPBombaBase2_B2 = 50,
            SPBombaFoam2_B2 = 50, SPEsterilizacion_B2 = 0;
    // Valores Actuales (Point Value)
    public static double PVpH_B2 = 0.0, PVOD_B2 = 0.0, PVRedox_B2 = 0.0, PVCO2_B2 = 0.0, PVTemperatura_B2 = 30.0, PVRPM_B2 = 0.0, PVBombaMedio_B2 = 0.0, PVpH2_B2 = 0.0, PVOD2_B2 = 0.0, PVCO22_B2 = 0.0,
            PVTemperatura2_B2 = 0.0, PVRPM2_B2 = 0.0, PVBombaMedio2_B2 = 0.0, PresionCamara_B2 = 80.0, PresionPreCamara_B2 = 80.0, PresionAtmosferica_B2 = 0.0;
    // Periodos programados de las bombas (Maximo 20 segundos)
    public static double TAcido_B2 = 10.0, TBase_B2 = 10.0, TFoam_B2 = 10.0, TAcido2_B2 = 10.0, TBase2_B2 = 10.0, TFoam2_B2 = 10.0,
            valorPc_B2 = 75, valorPp_B2 = 75;
    // Estados de controles y distintas Variables
    public static boolean estadoControlpH_B2 = false, estadoControlOD_B2 = false, estadoControlCO2_B2 = false, estadoControlAgitador_B2 = false,
            estadoControlTemperatura_B2 = false, estadoControlBombaMedio_B2 = false, estadoControlBombaAcido_B2 = false, estadoControlBombaBase_B2 = false,
            estadoControlBombaFoam_B2 = false, estadoControlFoam_B2 = false, estadoAdquisicion_B2 = false, SelecMinutos_B2 = false, SelecSegundos_B2 = true,
            ajusteTemperatura_B2 = false, ajusteAgitador_B2 = false, ajustepH_B2 = false, ajusteOD_B2 = false, ajusteCO2_B2 = false, estadoEVAire_B2 = false, ajusteRedox_B2 = false,
            modbusRedox_B2 = false, modbuspH_B2 = false, modbusOD_B2 = false, nivelFoam_B2 = false,
            estadoEVOxigeno_B2 = false, estadoEVCO2_B2 = false, estadoEVNitrogeno_B2 = false, estadoControlEsterilizacion_B2 = false, ajusteEsterilizacion_B2 = false, ajustePresion_B2 = false,
            estadoControlpH2_B2 = false, estadoControlOD2_B2 = false, estadoControlCO22_B2 = false, estadoControlAgitador2_B2 = false,
            estadoControlTemperatura2_B2 = false, estadoControlBombaMedio2_B2 = false, estadoControlBombaAcido2_B2 = false, estadoControlBombaBase2_B2 = false,
            estadoControlBombaFoam2_B2 = false, estadoControlFoam2_B2 = false, estadoAdquisicion2_B2 = false, SelecMinutos2_B2 = true, SelecSegundos2_B2 = false,
            ajusteTemperatura2_B2 = false, ajusteAgitador2_B2 = false, ajustepH2_B2 = false, ajusteOD2_B2 = false, ajusteCO22_B2 = false, estadoEVAire2_B2 = false,
            estadoEVOD2_B2 = false, estadoEVCO22_B2 = false, estadoValvulaManual_B2 = false, estadoAguaProceso_B2 = false, estadoLuzMirilla_B2 = false,
            estadoAireChaqueta_B2 = false, estadoVaporChaqueta_B2 = false, estadoSalidaAgua_B2 = false, estadoVaporIntercambiador_B2 = false, estadoRecirculacion_B2 = false,
            estadoDesfogue_B2 = false, estadoAguaChaqueta_B2 = false, estadoBombaAgua_B2 = false, estadoPurga_B2 = false, errorEsterilizacion_B2 = false,
            estadoBomba1_B2 = false, estadoBomba2_B2 = false, estadoBomba3_B2 = false, estadoBomba4_B2 = false;
    // Ajustes de histeresis y de banda para controles de gases y ph respectivamente.
    public static double Banda_B2 = 0.1, HisteresisOD_B2 = 1.0, HisteresisCO2_B2 = 1.0, ajustePresionCamara_B2 = 0, ajustePresionPreCamara_B2 = 0,
            Banda2_B2 = 0.1, HisteresisOD2_B2 = 1.0, HisteresisCO22_B2 = 1.0;
    public static int flancoOD_B2 = 0, flancoCO2_B2 = 0, tipogas_B2 = 0, SPTiempo_B2 = 0, indicePromedioPc_B2 = 0, indicePromedioPp_B2 = 0,
            flancoOD2_B2 = 0, flancoCO22_B2 = 0, tipogas2_B2 = 0, mediaPresion_B2 = 0, idBioreactor_B2 = 1;
    // Ajustes control de temperatura
    public static double ErrorTemperatura_B2 = 0, desvio_B2 = 0.0, Temporal_B2 = 0.0, Tpulso_B2 = 0.0, Tciclo_B2 = 10.0, Ganancia_B2 = 5, OffsetTemperatura_B2 = 0.0,
            PromedioTemperatura_B2 = 4.0, valorTemperatura_B2 = 0.0, Histeresis_B2 = 0.0, temperaturaInicial_B2 = 0.0, temperaturaFinal_B2 = 0.0,
            ErrorTemperatura2_B2 = 0, desvio2_B2 = 0.0, Temporal2_B2 = 0.0, Tpulso2_B2 = 0.0, Tciclo2_B2 = 10.0, Ganancia2_B2 = 5, OffsetTemperatura2_B2 = 0.0,
            PromedioTemperatura2_B2 = 4.0, valorTemperatura2_B2 = 0.0, Coeficiente_B2 = 0.0, Ttrampa_B2 = 0, temperaturaControl_B2 = 0, valorRedox_B2 = 0.0, valorRedox2_B2 = 0.0;
    public static int indicePromedioTemperatura_B2 = 0, indicePromedioTemperatura2_B2 = 0, pulsosPurga_B2 = 0, pulsosPurgaProgramados_B2 = 0, retardo_B2 = 0, duracionControl_B2 = 0;
    public static double[] TemperaturaProm_B2 = new double[64], TemperaturaProm2_B2 = new double[64];
    public static double[] PcProm_B2 = new double[50], PpProm_B2 = new double[50];
    // Ajustes control de Velocidad
    public static double BandaAgitador_B2 = 0.0, VelocidadRespuesta_B2 = 0.0, OffsetRPM_B2 = 0.0, LecturaRPM_B2 = 0.0, PromedioRPM_B2 = 1.0, valorRPM_B2 = 0.0,
            BandaAgitador2_B2 = 0.0, VelocidadRespuesta2_B2 = 0.0, OffsetRPM2_B2 = 0.0, LecturaRPM2_B2 = 0.0, PromedioRPM2_B2 = 1.0, valorRPM2_B2 = 0.0;
    public static double[] RPMProm_B2 = new double[64], RPMProm2_B2 = new double[64];
    public static int indicePromedioRPM_B2 = 0, indicePromedioRPM2_B2 = 0;
    // Ajustes control de pH
    public static double OffsetpH_B2 = 0.0, PromediopH_B2 = 0.0, valorpH_B2 = 0.0,
            OffsetpH2_B2 = 0.0, PromediopH2_B2 = 0.0, valorpH2_B2 = 0.0;
    public static double[] pHProm_B2 = new double[64], pHProm2_B2 = new double[64];
    public static int indicePromediopH_B2 = 0, indicePromediopH2_B2 = 0;
    // Ajustes control Oxigeno Disuelto(OD)
    public static double OffsetOD_B2 = 0.0, PromedioOD_B2 = 0.0, valorOD_B2 = 0.0,
            OffsetOD2_B2 = 0.0, PromedioOD2_B2 = 0.0, valorOD2_B2 = 0.0;
    // Ajustes control Redox
    public static double OffsetRedox_B2 = 0.0, PromedioRedox_B2 = 0.0, mRedox_B2 = 0, bRedox_B2 = 0;
    public static double[] RedoxProm_B2 = new double[64], RedoxProm2_B2 = new double[64];
    public static double[] ODProm_B2 = new double[64], ODProm2_B2 = new double[64];
    public static int indicePromedioOD_B2 = 0, indicePromedioRedox_B2 = 0, indicePromedioOD2_B2 = 0, purgaChaquetaON_B2 = 5, purgaChaquetaOFF_B2 = 300, purgaCamaraON_B2 = 12, purgaCamaraOFF_B2 = 66;
    // Ajustes control de CO2
    public static double OffsetCO2_B2 = 0.0, PromedioCO2_B2 = 0.0, valorCO2_B2 = 0.0;
    public static double[] CO2Prom_B2 = new double[64];
    public static int indicePromedioCO2_B2 = 0;
    // Variables para comunicaciòn USB
    public static byte[] PC_Tx_B2 = new byte[64];
    public static int[] PC_Rx_B2 = new int[64];
    public static int TMuestreo_B2 = 1000, IngresoDatos_B2 = 0, hilos_B2 = 0;

    public static boolean nivelReserva_B2 = false, controlCascada_B2 = false, nivel2_B2 = false, nivel3_B2 = false;
    public static long tInicial_B2 = 0, tFinal_B2 = 0;

    public static boolean estadoControlBomba1_B2;
    public static boolean estadoControlBomba2_B2;
    public static boolean estadoControlBomba3_B2;
    public static boolean estadoControlBomba4_B2;
    public static boolean runCalibra_B2 = false;
    public static boolean runBomba1_B2 = false;
    public static boolean runBomba2_B2 = false;
    public static boolean runBomba3_B2 = false;
    public static boolean runBomba4_B2 = false;
    public static int TBomba1_B2 = 0;
    public static int SPBomba1_B2 = 0;
    public static int TBomba2_B2 = 0;
    public static int SPBomba2_B2 = 0;
    public static int TBomba3_B2 = 0;
    public static int SPBomba3_B2 = 0;
    public static int TBomba4_B2 = 0;
    public static int SPBomba4_B2 = 0;
    public static boolean asignaAcido_B2 = false, asignaBase_B2 = false, asignaFoam_B2 = false, asignaNivel2S_B2 = false, asignaNivel2H_B2 = false;
    public static String asignacionBomba1_B2 = "Ninguno", asignacionBomba2_B2 = "Ninguno", asignacionBomba3_B2 = "Ninguno",
            asignacionBomba4_B2 = "Ninguno", usuario_B2 = null, contraseña_B2 = "", rol_B2 = "", bioreactor_B2 = "";
    public static boolean estadoOn1_B2 = false, estadoOn2_B2 = false, estadoOn3_B2 = false, estadoOn4_B2 = false, login_B2 = false, estadoPeltierp_B2 = false, estadoPeltiern_B2 = false;
    public static boolean calibraBomba1_B2 = false, calibraBomba2_B2 = false, calibraBomba3_B2 = false, calibraBomba4_B2 = false;
    public static double totalBomba1_B2 = 0, totalBomba2_B2 = 0, totalBomba3_B2 = 0, totalBomba4_B2 = 0;
    public static double tBomba1_B2 = 0, tBomba2_B2 = 0, tBomba3_B2 = 0, tBomba4_B2 = 0;
    public static double calibracionBomba1_B2 = 0, calibracionBomba2_B2 = 0, calibracionBomba3_B2 = 0, calibracionBomba4_B2 = 0;
    public static boolean enfria_B2;
    public static double pHinf_B2 = 4.0, pHsup_B2 = 7.0;
    public static double derivativo_B2 = 120, integral_B2 = 1;
    public static double mpH_B2 = 0, bpH_B2 = 0;
    public static double mOD_B2 = 0.2545, bOD_B2 = -50.0;
    public static double mTem_B2 = 0.1283, bTem_B2 = -24.95;

    //Variables planta biorreactores 2
    //Variables planta biorreactores 2
    public static boolean estadoAC1_B2 = false, estadoAC2_B2 = false, estadoAC3_B2 = false, estadoAC4_B2 = false, estadoAC5_B2 = false, estadoAC6_B2 = false, estadoAC7_B2 = false,
            estadoAC8_B2 = false, estadoAC9_B2 = false, estadoAC10_B2 = false, estadoAC11_B2 = false, estadoAC12_B2 = false, estadoBuzzer_B2 = false, estadoSSR_B2 = false;
    public static boolean estadoEnfriaChaqueta_B2 = false, estadoSacaCondensado_B2 = false, estadoEsterilizando_B2 = false;
    public static double presionPurgaCO2_B2 = 0, histeresisPurgaCO2_B2 = 0;
    public static int SPtiempoEsterilizacion_B2 = 0, SPminutosEsterilizacion_B2 = 0, SPsegundosEsterilizacion_B2 = 0, minutosEsterilizacion_B2 = 0, segundosEsterilizacion_B2 = 0;
    public static boolean nivelAlto_B2 = false, nivelMedio_B2 = false, nivelBajo_B2 = false;

    public static boolean asignaNivelAlto_B2 = false, asignaNivelMedio_B2 = false, asignaNivelBajo_B2 = false;

    //-------------------------------Fin Variables Biorreactor 2----------------------------------//
    //-----------------------------Variables Biorreactor 3--------------------------------------//
    public static double SPpH_B3 = 0.0, SPOD_B3 = 0.0, SPCO2_B3 = 0.0, SPTemperatura_B3 = 0.0, SPRPM_B3 = 0.0, SPBombaMedio_B3 = 50.0, SPBombaAcido_B3 = 50, SPBombaBase_B3 = 50,
            SPBombaFoam_B3 = 50, SPpH2_B3 = 0.0, SPOD2_B3 = 0.0, SPCO22_B3 = 0.0, SPTemperatura2_B3 = 0.0, SPRPM2_B3 = 0.0, SPBombaMedio2_B3 = 50.0, SPBombaAcido2_B3 = 50, SPBombaBase2_B3 = 50,
            SPBombaFoam2_B3 = 50, SPEsterilizacion_B3 = 0;
    // Valores Actuales (Point Value)
    public static double PVpH_B3 = 0.0, PVOD_B3 = 0.0, PVRedox_B3 = 0.0, PVCO2_B3 = 0.0, PVTemperatura_B3 = 30.0, PVRPM_B3 = 0.0, PVBombaMedio_B3 = 0.0, PVpH2_B3 = 0.0, PVOD2_B3 = 0.0, PVCO22_B3 = 0.0,
            PVTemperatura2_B3 = 0.0, PVRPM2_B3 = 0.0, PVBombaMedio2_B3 = 0.0, PresionCamara_B3 = 80.0, PresionPreCamara_B3 = 80.0, PresionAtmosferica_B3 = 0.0;
    // Periodos programados de las bombas (Maximo 20 segundos)
    public static double TAcido_B3 = 10.0, TBase_B3 = 10.0, TFoam_B3 = 10.0, TAcido2_B3 = 10.0, TBase2_B3 = 10.0, TFoam2_B3 = 10.0,
            valorPc_B3 = 75, valorPp_B3 = 75;
    // Estados de controles y distintas Variables
    public static boolean estadoControlpH_B3 = false, estadoControlOD_B3 = false, estadoControlCO2_B3 = false, estadoControlAgitador_B3 = false,
            estadoControlTemperatura_B3 = false, estadoControlBombaMedio_B3 = false, estadoControlBombaAcido_B3 = false, estadoControlBombaBase_B3 = false,
            estadoControlBombaFoam_B3 = false, estadoControlFoam_B3 = false, estadoAdquisicion_B3 = false, SelecMinutos_B3 = false, SelecSegundos_B3 = true,
            ajusteTemperatura_B3 = false, ajusteAgitador_B3 = false, ajustepH_B3 = false, ajusteOD_B3 = false, ajusteCO2_B3 = false, estadoEVAire_B3 = false, ajusteRedox_B3 = false,
            modbusRedox_B3 = false, modbuspH_B3 = false, modbusOD_B3 = false, nivelFoam_B3 = false,
            estadoEVOxigeno_B3 = false, estadoEVCO2_B3 = false, estadoEVNitrogeno_B3 = false, estadoControlEsterilizacion_B3 = false, ajusteEsterilizacion_B3 = false, ajustePresion_B3 = false,
            estadoControlpH2_B3 = false, estadoControlOD2_B3 = false, estadoControlCO22_B3 = false, estadoControlAgitador2_B3 = false,
            estadoControlTemperatura2_B3 = false, estadoControlBombaMedio2_B3 = false, estadoControlBombaAcido2_B3 = false, estadoControlBombaBase2_B3 = false,
            estadoControlBombaFoam2_B3 = false, estadoControlFoam2_B3 = false, estadoAdquisicion2_B3 = false, SelecMinutos2_B3 = true, SelecSegundos2_B3 = false,
            ajusteTemperatura2_B3 = false, ajusteAgitador2_B3 = false, ajustepH2_B3 = false, ajusteOD2_B3 = false, ajusteCO22_B3 = false, estadoEVAire2_B3 = false,
            estadoEVOD2_B3 = false, estadoEVCO22_B3 = false, estadoValvulaManual_B3 = false, estadoAguaProceso_B3 = false, estadoLuzMirilla_B3 = false,
            estadoAireChaqueta_B3 = false, estadoVaporChaqueta_B3 = false, estadoSalidaAgua_B3 = false, estadoVaporIntercambiador_B3 = false, estadoRecirculacion_B3 = false,
            estadoDesfogue_B3 = false, estadoAguaChaqueta_B3 = false, estadoBombaAgua_B3 = false, estadoPurga_B3 = false, errorEsterilizacion_B3 = false,
            estadoBomba1_B3 = false, estadoBomba2_B3 = false, estadoBomba3_B3 = false, estadoBomba4_B3 = false;
    // Ajustes de histeresis y de banda para controles de gases y ph respectivamente.
    public static double Banda_B3 = 0.1, HisteresisOD_B3 = 1.0, HisteresisCO2_B3 = 1.0, ajustePresionCamara_B3 = 0, ajustePresionPreCamara_B3 = 0,
            Banda2_B3 = 0.1, HisteresisOD2_B3 = 1.0, HisteresisCO22_B3 = 1.0;
    public static int flancoOD_B3 = 0, flancoCO2_B3 = 0, tipogas_B3 = 0, SPTiempo_B3 = 0, indicePromedioPc_B3 = 0, indicePromedioPp_B3 = 0,
            flancoOD2_B3 = 0, flancoCO22_B3 = 0, tipogas2_B3 = 0, mediaPresion_B3 = 0, idBioreactor_B3 = 1;
    // Ajustes control de temperatura
    public static double ErrorTemperatura_B3 = 0, desvio_B3 = 0.0, Temporal_B3 = 0.0, Tpulso_B3 = 0.0, Tciclo_B3 = 10.0, Ganancia_B3 = 5, OffsetTemperatura_B3 = 0.0,
            PromedioTemperatura_B3 = 4.0, valorTemperatura_B3 = 0.0, Histeresis_B3 = 0.0, temperaturaInicial_B3 = 0.0, temperaturaFinal_B3 = 0.0,
            ErrorTemperatura2_B3 = 0, desvio2_B3 = 0.0, Temporal2_B3 = 0.0, Tpulso2_B3 = 0.0, Tciclo2_B3 = 10.0, Ganancia2_B3 = 5, OffsetTemperatura2_B3 = 0.0,
            PromedioTemperatura2_B3 = 4.0, valorTemperatura2_B3 = 0.0, Coeficiente_B3 = 0.0, Ttrampa_B3 = 0, temperaturaControl_B3 = 0, valorRedox_B3 = 0.0, valorRedox2_B3 = 0.0;
    public static int indicePromedioTemperatura_B3 = 0, indicePromedioTemperatura2_B3 = 0, pulsosPurga_B3 = 0, pulsosPurgaProgramados_B3 = 0, retardo_B3 = 0, duracionControl_B3 = 0;
    public static double[] TemperaturaProm_B3 = new double[64], TemperaturaProm2_B3 = new double[64];
    public static double[] PcProm_B3 = new double[50], PpProm_B3 = new double[50];
    // Ajustes control de Velocidad
    public static double BandaAgitador_B3 = 0.0, VelocidadRespuesta_B3 = 0.0, OffsetRPM_B3 = 0.0, LecturaRPM_B3 = 0.0, PromedioRPM_B3 = 1.0, valorRPM_B3 = 0.0,
            BandaAgitador2_B3 = 0.0, VelocidadRespuesta2_B3 = 0.0, OffsetRPM2_B3 = 0.0, LecturaRPM2_B3 = 0.0, PromedioRPM2_B3 = 1.0, valorRPM2_B3 = 0.0;
    public static double[] RPMProm_B3 = new double[64], RPMProm2_B3 = new double[64];
    public static int indicePromedioRPM_B3 = 0, indicePromedioRPM2_B3 = 0;
    // Ajustes control de pH
    public static double OffsetpH_B3 = 0.0, PromediopH_B3 = 0.0, valorpH_B3 = 0.0,
            OffsetpH2_B3 = 0.0, PromediopH2_B3 = 0.0, valorpH2_B3 = 0.0;
    public static double[] pHProm_B3 = new double[64], pHProm2_B3 = new double[64];
    public static int indicePromediopH_B3 = 0, indicePromediopH2_B3 = 0;
    // Ajustes control Oxigeno Disuelto(OD)
    public static double OffsetOD_B3 = 0.0, PromedioOD_B3 = 0.0, valorOD_B3 = 0.0,
            OffsetOD2_B3 = 0.0, PromedioOD2_B3 = 0.0, valorOD2_B3 = 0.0;
    // Ajustes control Redox
    public static double OffsetRedox_B3 = 0.0, PromedioRedox_B3 = 0.0, mRedox_B3 = 0, bRedox_B3 = 0;
    public static double[] RedoxProm_B3 = new double[64], RedoxProm2_B3 = new double[64];
    public static double[] ODProm_B3 = new double[64], ODProm2_B3 = new double[64];
    public static int indicePromedioOD_B3 = 0, indicePromedioRedox_B3 = 0, indicePromedioOD2_B3 = 0, purgaChaquetaON_B3 = 5, purgaChaquetaOFF_B3 = 300, purgaCamaraON_B3 = 12, purgaCamaraOFF_B3 = 66;
    // Ajustes control de CO2
    public static double OffsetCO2_B3 = 0.0, PromedioCO2_B3 = 0.0, valorCO2_B3 = 0.0;
    public static double[] CO2Prom_B3 = new double[64];
    public static int indicePromedioCO2_B3 = 0;
    // Variables para comunicaciòn USB
    public static byte[] PC_Tx_B3 = new byte[64];
    public static int[] PC_Rx_B3 = new int[64];
    public static int TMuestreo_B3 = 1000, IngresoDatos_B3 = 0, hilos_B3 = 0;

    public static boolean nivelReserva_B3 = false, controlCascada_B3 = false, nivel2_B3 = false, nivel3_B3 = false;
    public static long tInicial_B3 = 0, tFinal_B3 = 0;
    public static boolean estadoControlBomba1_B3;
    public static boolean estadoControlBomba2_B3;
    public static boolean estadoControlBomba3_B3;
    public static boolean estadoControlBomba4_B3;
    public static boolean runCalibra_B3 = false;
    public static boolean runBomba1_B3 = false;
    public static boolean runBomba2_B3 = false;
    public static boolean runBomba3_B3 = false;
    public static boolean runBomba4_B3 = false;
    public static int TBomba1_B3 = 0;
    public static int SPBomba1_B3 = 0;
    public static int TBomba2_B3 = 0;
    public static int SPBomba2_B3 = 0;
    public static int TBomba3_B3 = 0;
    public static int SPBomba3_B3 = 0;
    public static int TBomba4_B3 = 0;
    public static int SPBomba4_B3 = 0;
    public static boolean asignaAcido_B3 = false, asignaBase_B3 = false, asignaFoam_B3 = false, asignaNivel2S_B3 = false, asignaNivel2H_B3 = false;
    public static String asignacionBomba1_B3 = "Ninguno", asignacionBomba2_B3 = "Ninguno", asignacionBomba3_B3 = "Ninguno",
            asignacionBomba4_B3 = "Ninguno", usuario_B3 = null, contraseña_B3 = "", rol_B3 = "", bioreactor_B3 = "";
    public static boolean estadoOn1_B3 = false, estadoOn2_B3 = false, estadoOn3_B3 = false, estadoOn4_B3 = false, login_B3 = false, estadoPeltierp_B3 = false, estadoPeltiern_B3 = false;
    public static boolean calibraBomba1_B3 = false, calibraBomba2_B3 = false, calibraBomba3_B3 = false, calibraBomba4_B3 = false;
    public static double totalBomba1_B3 = 0, totalBomba2_B3 = 0, totalBomba3_B3 = 0, totalBomba4_B3 = 0;
    public static double tBomba1_B3 = 0, tBomba2_B3 = 0, tBomba3_B3 = 0, tBomba4_B3 = 0;
    public static double calibracionBomba1_B3 = 0, calibracionBomba2_B3 = 0, calibracionBomba3_B3 = 0, calibracionBomba4_B3 = 0;
    public static boolean enfria_B3;
    public static double pHinf_B3 = 4.0, pHsup_B3 = 7.0;
    public static double derivativo_B3 = 120, integral_B3 = 1;
    public static double mpH_B3 = 0, bpH_B3 = 0;
    public static double mOD_B3 = 0.2545, bOD_B3 = -50.0;
    public static double mTem_B3 = 0.1283, bTem_B3 = -24.95;

    //Variables planta biorreactores 3
    public static boolean estadoAC1_B3 = false, estadoAC2_B3 = false, estadoAC3_B3 = false, estadoAC4_B3 = false, estadoAC5_B3 = false, estadoAC6_B3 = false, estadoAC7_B3 = false,
            estadoAC8_B3 = false, estadoAC9_B3 = false, estadoAC10_B3 = false, estadoAC11_B3 = false, estadoAC12_B3 = false, estadoBuzzer_B3 = false, estadoSSR_B3 = false;
    public static boolean estadoEnfriaChaqueta_B3 = false, estadoSacaCondensado_B3 = false, estadoEsterilizando_B3 = false;
    public static double presionPurgaCO2_B3 = 0, histeresisPurgaCO2_B3 = 0;
    public static int SPtiempoEsterilizacion_B3 = 0, SPminutosEsterilizacion_B3 = 0, SPsegundosEsterilizacion_B3 = 0, minutosEsterilizacion_B3 = 0, segundosEsterilizacion_B3 = 0;
    public static boolean nivelAlto_B3 = false, nivelMedio_B3 = false, nivelBajo_B3 = false;

    public static boolean asignaNivelAlto_B3 = false, asignaNivelMedio_B3 = false, asignaNivelBajo_B3 = false;

    //-------------------------------------Fin Variables Biorreactor 3-----------------------------------------------------//
    public static List<String> usuarios = new ArrayList<>();
    public static List<String> claves = new ArrayList<>();
    public static List<String> roles = new ArrayList<>();
    public static List<String> fechas = new ArrayList<>();
    public static List<String> fechaEvento = new ArrayList<>();
    public static List<String> usuarioEvento = new ArrayList<>();
    public static List<String> evento = new ArrayList<>();

    public static void añadirEvento(String event) {
        Calendar cal = new GregorianCalendar();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        int segundo = cal.get(Calendar.SECOND);
        Formatter formato2H = new Formatter();  // Formato de la hora
        formato2H.format("%02d", hora);
        String hora2 = formato2H.toString();    // String de la hora dos cifras
        Formatter formato2M = new Formatter();
        formato2M.format("%02d", minuto);
        String minuto2 = formato2M.toString();  // String de minutos dos cifras
        Formatter formato2S = new Formatter();
        formato2S.format("%02d", segundo);
        String segundo2 = formato2S.toString(); // String de segundos dos cifras
        fechaEvento.add(cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR) + "-- " + (hora2 + ":" + minuto2 + ":" + segundo2));
        usuarioEvento.add(usuario);
        evento.add(event);
        File f = new File("Config.con");
        com.info.GuardarArchivo s = new com.info.GuardarArchivo();
        for (Bioreactor bio : Variables.bioreactores) {
            try {
                bio.guardar("bioreactor_config_" + bio.getId() + ".dat");
            } catch (IOException ex) {
                Logger.getLogger(Variables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            s.startSaving();
            s.endSaving(f);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No puede guardar Evento", "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("FECHAS:" + fechaEvento + " USER:" + usuarioEvento + " EVENT:" + evento);
    }
}

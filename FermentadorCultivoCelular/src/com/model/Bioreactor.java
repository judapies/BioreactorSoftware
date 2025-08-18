/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.EnumMap;
import java.util.Map;
import com.controller.SensorManager;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author judapies
 */
public class Bioreactor implements IBioreactor, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @param tiempoEsterilizacionConfigurado the
     * tiempoEsterilizacionConfigurado to set
     */
    public void setTiempoEsterilizacionConfigurado(double tiempoEsterilizacionConfigurado) {
        this.tiempoEsterilizacionConfigurado = tiempoEsterilizacionConfigurado;
    }

    /**
     * @param capacidadLitros the capacidadLitros to set
     */
    public void setCapacidadLitros(int capacidadLitros) {
        this.capacidadLitros = capacidadLitros;
    }

    // === Enums para salidas y entradas ===
    public enum Salida {
        SUMINISTRO_VAPOR,
        RECIRCULACION,
        ENTRADA_INTERCAMBIADOR,
        SALIDA_INTERCAMBIADOR,
        BOMBA_ENCARCELACION,
        DRENAJE,
        SUMINISTRO_AGUA_ENFRIAMIENTO,
        SALIDA_AGUA_ENFRIAMIENTO,
        BOMBA_RECIRCULACION,
        INGRESO_CO2,
        VENTEO_CO2,
        DESFOGUE_VAPOR,
        SSR,
        BOMBA_PERISTALTICA_1,
        BOMBA_PERISTALTICA_2,
        BOMBA_PERISTALTICA_3,
        PUENTE_H_LSB,
        PUENTE_H_HSB,
        AGITADOR_LSB,
        AGITADOR_MSB,
        BUZZER
    }

    public enum Entrada {

        TEMPERATURA_1,
        TEMPERATURA_2,
        PH,
        OD,
        RPM_CH1,
        RPM_CH2,
        PRESION_CAMARA,
        PRESION_PRE_CAMARA,
        NIVEL_ALTO,
        NIVEL_MEDIO,
        NIVEL_BAJO,
        CO2,
        ENTRADA_DIGITAL_1,
        ENTRADA_DIGITAL_2,
        ENTRADA_DIGITAL_3
    }

    // === Atributos del bioreactor ===
    private final int id;
    private int capacidadLitros;
    private static final int ON = 5;
    private static final int OFF = 10;
    private final SensorManager sensorManager;
    private final List<BombaPeristaltica> bombas;
    private boolean estadoControlpH = false;
    private boolean estadoControlOD = false;
    private boolean estadoControlTemperatura = false;
    private boolean estadoControlCO2 = false;
    private boolean estadoControlAgitacion = false;
    private boolean estadoControlEsterilizacion = false;
    private double tiempoEsterilizacionConfigurado = 300; // por ejemplo, 5 minutos
    private long tiempoInicioEsterilizacion = -1;
    private boolean esterilizando = false;

    private final Map<Salida, Integer> salidas;
    private final Map<Entrada, Double> entradas;

    private final ParametrosConfiguracionBioreactor parametros;

    /**
     * Crea una nueva instancia de Bioreactor con su configuración inicial.
     *
     * @param id Identificador único del biorreactor.
     * @param capacidadLitros Capacidad en litros del biorreactor (por ejemplo,
     * 5 o 30).
     * @param alpha Coeficiente de suavizado para el filtro de entrada del
     * SensorManager.
     */
    public Bioreactor(int id, int capacidadLitros, double alpha) {
        this.id = id;
        this.capacidadLitros = capacidadLitros;
        this.salidas = new EnumMap<>(Salida.class);
        this.entradas = new EnumMap<>(Entrada.class);
        this.sensorManager = new SensorManager(alpha);  // filtro individual para cada sensor
        bombas = new ArrayList<>();
        bombas.add(new BombaPeristaltica("BOMBA_PERISTALTICA_1", Salida.BOMBA_PERISTALTICA_1, this));
        bombas.add(new BombaPeristaltica("BOMBA_PERISTALTICA_2", Salida.BOMBA_PERISTALTICA_2, this));
        bombas.add(new BombaPeristaltica("BOMBA_PERISTALTICA_3", Salida.BOMBA_PERISTALTICA_3, this));

        this.parametros = new ParametrosConfiguracionBioreactor();

         // Inicializar salidas como falsas (apagadas)
        for (Salida s : Salida.values()) {
            salidas.put(s, 10);
        }

        // Inicializar entradas con 0.0
        for (Entrada e : Entrada.values()) {
            entradas.put(e, 0.0);
        }
    }

    // === Métodos del IBioreactor ===
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCapacidadLitros() {
        return capacidadLitros;
    }

    @Override
    public void actualizarPresion(double presion) {
        entradas.put(Entrada.PRESION_CAMARA, presion);
    }

    @Override
    public void actualizarEntradaDigital(int index, boolean estado) {
        Entrada[] entradasDigitales = {
            Entrada.NIVEL_ALTO,
            Entrada.NIVEL_MEDIO,
            Entrada.NIVEL_BAJO,
            Entrada.ENTRADA_DIGITAL_1,
            Entrada.ENTRADA_DIGITAL_2,
            Entrada.ENTRADA_DIGITAL_3
        };
        if (index >= 0 && index < entradasDigitales.length) {
            entradas.put(entradasDigitales[index], estado ? 1.0 : 0.0);
        }
    }

    public void actualizarEntrada(Entrada e, double valor) {
        entradas.put(e, valor);
    }

    public double leerEntrada(Entrada e) {
        return entradas.get(e);
    }

    public void activarSalida(Salida s, Integer estado) {
        salidas.put(s, estado);
    }

    public Integer leerSalida(Salida s) {
        return salidas.get(s);
    }

    /**
     * Devuelve un vector de bytes que representa el estado de las salidas del
     * bioreactor. La posición 0 contiene el ID del bioreactor. A partir de la
     * posición 1, cada byte representa una salida: - 5 si la salida está
     * activada (true) - 10 si la salida está desactivada (false)
     *
     * @return Un arreglo byte[] codificado.
     */
    @Override
    public byte[] getTramaDeSalida() {
        Salida[] todasLasSalidas = Salida.values();
        byte[] vector = new byte[64];//Tamaño del buffer del usb 

        // Posición 0 = ID del bioreactor
        vector[0] = (byte) id;
        vector[30] = (byte) id;

        // Rellenar el resto según el estado de cada salida
        for (int i = 0; i < todasLasSalidas.length; i++) {
            int estado = salidas.get(todasLasSalidas[i]);
            vector[i + 1] = (byte) estado;
        }

        return vector;
    }

    /**
     * Actualiza las entradas del bioreactor desde un arreglo de bytes recibido
     * desde USB. El orden y formato de los datos debe coincidir con el
     * protocolo acordado.
     *
     * @param data Vector de 23 bytes donde: [0-1] = Temperatura 1 (entero y
     * decimal) [2-3] = Temperatura 2 [4-5] = pH [6-7] = OD [8-9] = RPM CH1 (LSB
     * + HSB) [10-11]= RPM CH2 (LSB + HSB) [12-13]= Presión cámara (LSB + HSB)
     * [14-15]= Presión precámara (LSB + HSB) [16] = Nivel alto (0/1) [17] =
     * Nivel medio [18] = Nivel bajo [19-20]= CO2 [21] = Entrada digital 1
     */
    public void actualizarEntradasDesdeTrama(byte[] data) {
        if (data.length < 25) {
            return;
        }

        double temp1 = data[1] + data[2] / 10.0;
        double temp2 = data[3] + data[4] / 10.0;
        double ph = data[5] + data[6] / 100.0;
        double od = data[7] + data[8] / 100.0;

        int rpmCH1 = ((data[10] & 0xFF) << 8) | (data[9] & 0xFF);
        int rpmCH2 = ((data[12] & 0xFF) << 8) | (data[11] & 0xFF);

        int presionCamara = ((data[14] & 0xFF) << 8) | (data[13] & 0xFF);
        int presionPreCamara = ((data[16] & 0xFF) << 8) | (data[15] & 0xFF);

        double co2 = data[20] + data[21] / 1000.0;

        actualizarEntrada(Entrada.TEMPERATURA_1, procesarLectura("temperatura", temp1));
        actualizarEntrada(Entrada.TEMPERATURA_2, procesarLectura("temperatura2", temp2));
        actualizarEntrada(Entrada.PH, procesarLectura("pH", ph));
        actualizarEntrada(Entrada.OD, procesarLectura("OD", od));
        actualizarEntrada(Entrada.RPM_CH1, procesarLectura("rpmCH1", rpmCH1));
        actualizarEntrada(Entrada.RPM_CH2, procesarLectura("rpmCH2", rpmCH2));
        actualizarEntrada(Entrada.PRESION_CAMARA, procesarLectura("presionCamara", presionCamara));
        actualizarEntrada(Entrada.PRESION_PRE_CAMARA, procesarLectura("presionPreCamara", presionPreCamara));
        actualizarEntrada(Entrada.CO2, procesarLectura("CO2", co2));
        actualizarEntrada(Entrada.NIVEL_ALTO, data[17]);
        actualizarEntrada(Entrada.NIVEL_MEDIO, data[18]);
        actualizarEntrada(Entrada.NIVEL_BAJO, data[19]);
        actualizarEntrada(Entrada.ENTRADA_DIGITAL_1, data[22]);
        actualizarEntrada(Entrada.ENTRADA_DIGITAL_2, data[23]);
        actualizarEntrada(Entrada.ENTRADA_DIGITAL_3, data[24]);
    }
    
    /**
     * Realiza el proceso de desfogue del biorreactor.
     *
     *
     * Las salidas se configuran de la siguiente forma: - Todas las salidas de
     * proceso → apagadas (`OFF`) - Salida {@link Salida#DESFOGUE_VAPOR} →
     * activada (`ON`)
     *
     */
    @Override
    public void desfogar() {
        activarSalida(Salida.SUMINISTRO_VAPOR, OFF);
        activarSalida(Salida.RECIRCULACION, OFF);
        activarSalida(Salida.ENTRADA_INTERCAMBIADOR, OFF);
        activarSalida(Salida.SALIDA_INTERCAMBIADOR, OFF);
        activarSalida(Salida.BOMBA_ENCARCELACION, OFF);
        activarSalida(Salida.DRENAJE, OFF);
        activarSalida(Salida.SUMINISTRO_AGUA_ENFRIAMIENTO, OFF);
        activarSalida(Salida.SALIDA_AGUA_ENFRIAMIENTO, OFF);
        activarSalida(Salida.BOMBA_RECIRCULACION, OFF);
        activarSalida(Salida.INGRESO_CO2, OFF);
        activarSalida(Salida.VENTEO_CO2, OFF);
        activarSalida(Salida.DESFOGUE_VAPOR, ON);
    }

    /**
     * Activa el proceso de enfriamiento del biorreactor. Enciende las salidas
     * de agua de enfriamiento y apaga el resto.
     */
    @Override
    public void enfriar() {
        activarSalida(Salida.SUMINISTRO_VAPOR, OFF);
        activarSalida(Salida.RECIRCULACION, OFF);
        activarSalida(Salida.ENTRADA_INTERCAMBIADOR, OFF);
        activarSalida(Salida.SALIDA_INTERCAMBIADOR, OFF);
        activarSalida(Salida.BOMBA_ENCARCELACION, OFF);
        activarSalida(Salida.DRENAJE, OFF);
        activarSalida(Salida.SUMINISTRO_AGUA_ENFRIAMIENTO, ON);
        activarSalida(Salida.SALIDA_AGUA_ENFRIAMIENTO, ON);
        activarSalida(Salida.BOMBA_RECIRCULACION, OFF);
        activarSalida(Salida.INGRESO_CO2, OFF);
        activarSalida(Salida.VENTEO_CO2, OFF);
        activarSalida(Salida.DESFOGUE_VAPOR, OFF);
    }

    /**
     * Controla el llenado del reservorio de recirculación. Si la entrada
     * digital 1 está desactivada (OFF), se activa la recirculación y el
     * suministro de agua. Si la entrada digital 1 está activada (ON), se apagan
     * todas las salidas.
     */
    public boolean llenadoReservorio() {
        if (leerEntrada(Entrada.ENTRADA_DIGITAL_1) >=128) {
            activarSalida(Salida.SUMINISTRO_VAPOR, OFF);
            activarSalida(Salida.RECIRCULACION, ON);
            activarSalida(Salida.ENTRADA_INTERCAMBIADOR, OFF);
            activarSalida(Salida.SALIDA_INTERCAMBIADOR, OFF);
            activarSalida(Salida.BOMBA_ENCARCELACION, OFF);
            activarSalida(Salida.DRENAJE, OFF);
            activarSalida(Salida.SUMINISTRO_AGUA_ENFRIAMIENTO, ON);
            activarSalida(Salida.SALIDA_AGUA_ENFRIAMIENTO, OFF);
            activarSalida(Salida.BOMBA_RECIRCULACION, OFF);
            activarSalida(Salida.DESFOGUE_VAPOR, OFF);
            return false;
        } else {
            activarSalida(Salida.SUMINISTRO_VAPOR, OFF);
            activarSalida(Salida.RECIRCULACION, OFF);
            activarSalida(Salida.ENTRADA_INTERCAMBIADOR, OFF);
            activarSalida(Salida.SALIDA_INTERCAMBIADOR, OFF);
            activarSalida(Salida.BOMBA_ENCARCELACION, OFF);
            activarSalida(Salida.DRENAJE, OFF);
            activarSalida(Salida.SUMINISTRO_AGUA_ENFRIAMIENTO, OFF);
            activarSalida(Salida.SALIDA_AGUA_ENFRIAMIENTO, OFF);
            activarSalida(Salida.BOMBA_RECIRCULACION, OFF);
            activarSalida(Salida.DESFOGUE_VAPOR, OFF);
            return true;
        }
    }

    /**
     * Activa la recirculación de agua por la chaqueta del biorreactor. Enciende
     * las bombas y la recirculación; apaga el resto de salidas.
     */
    @Override
    public void recircularAgua() {
        activarSalida(Salida.SUMINISTRO_VAPOR, OFF);
        activarSalida(Salida.RECIRCULACION, ON);
        activarSalida(Salida.BOMBA_ENCARCELACION, ON);
        activarSalida(Salida.DRENAJE, OFF);
        activarSalida(Salida.SUMINISTRO_AGUA_ENFRIAMIENTO, OFF);
        activarSalida(Salida.SALIDA_AGUA_ENFRIAMIENTO, OFF);
        activarSalida(Salida.BOMBA_RECIRCULACION, ON);
        activarSalida(Salida.DESFOGUE_VAPOR, OFF);
    }

    /**
     * Inicia el calentamiento del agua en la chaqueta. Abre la entrada de vapor
     * al intercambiador y cierra la salida.
     */
    public void calentamientoAguaIntercambiador() {
        activarSalida(Salida.ENTRADA_INTERCAMBIADOR, ON);
        activarSalida(Salida.SALIDA_INTERCAMBIADOR, ON);
    }

    /**
     * Libera el vapor del intercambiador de calor. Cierra la entrada de vapor y
     * abre la salida.
     */
    public void detieneCalentamientoIntercambiador() {
        activarSalida(Salida.ENTRADA_INTERCAMBIADOR, OFF);
        activarSalida(Salida.SALIDA_INTERCAMBIADOR, ON);
    }

    /**
     * Controla el venteo de CO2 con histéresis según la presión de la cámara.
     * Activa la salida si la presión supera el setpoint, y la desactiva cuando
     * baja por debajo del setpoint menos la histéresis.
     *
     * @param setpoint Valor objetivo de presión en la cámara.
     * @param histeresis Margen inferior para evitar oscilaciones.
     */
    @Override
    public void controlarVenteoCO2(double setpoint, double histeresis) {
        double presion = leerEntrada(Entrada.PRESION_CAMARA);

        if (presion >= setpoint) {
            activarSalida(Salida.VENTEO_CO2, ON);
        } else if (presion <= (setpoint - histeresis)) {
            activarSalida(Salida.VENTEO_CO2, OFF);
        }
    }

    /**
     * Activa o desactiva la entrada de CO2.
     *
     * @param activar Valor ON (5) para activar o OFF (10) para desactivar la
     * salida.
     */
    @Override
    public void activarCO2(Integer activar) {
        activarSalida(Salida.INGRESO_CO2, activar);
    }

    public double procesarLectura(String sensor, double valor) {
        return sensorManager.filtrar(sensor, valor);
    }

    public void resetearFiltros() {
        sensorManager.resetearTodos();
    }

    public BombaPeristaltica getBomba(int pos) {
        return bombas.get(pos);
    }

    public ParametrosConfiguracionBioreactor getParametros() {
        return parametros;
    }

    public List<BombaPeristaltica> getBombas() {
        return bombas;
    }

    public int getBombasSize() {
        return bombas.size();
    }

    public void guardar(String archivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
        out.writeObject(this);
        out.close();
    }

    public static Bioreactor cargar(String archivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo));
        Bioreactor b = (Bioreactor) in.readObject();
        in.close();
        return b;
    }

    public Salida convertirASalida(String nombre) {
        try {
            return Salida.valueOf(nombre.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Salida inválida: " + nombre);
            return null;
        }
    }

    /**
     * @return the estadoControlpH
     */
    public boolean isEstadoControlpH() {
        return estadoControlpH;
    }

    /**
     * @param estadoControlpH the estadoControlpH to set
     */
    public void setEstadoControlpH(boolean estadoControlpH) {
        this.estadoControlpH = estadoControlpH;
    }

    /**
     * @return the estadoControlOD
     */
    public boolean isEstadoControlOD() {
        return estadoControlOD;
    }

    /**
     * @param estadoControlOD the estadoControlOD to set
     */
    public void setEstadoControlOD(boolean estadoControlOD) {
        this.estadoControlOD = estadoControlOD;
    }

    /**
     * @return the estadoControlTemperatura
     */
    public boolean isEstadoControlTemperatura() {
        return estadoControlTemperatura;
    }

    /**
     * @param estadoControlTemperatura the estadoControlTemperatura to set
     */
    public void setEstadoControlTemperatura(boolean estadoControlTemperatura) {
        this.estadoControlTemperatura = estadoControlTemperatura;
    }

    /**
     * @return the estadoControlCO2
     */
    public boolean isEstadoControlCO2() {
        return estadoControlCO2;
    }

    /**
     * @param estadoControlCO2 the estadoControlCO2 to set
     */
    public void setEstadoControlCO2(boolean estadoControlCO2) {
        this.estadoControlCO2 = estadoControlCO2;
    }

    /**
     * @return the estadoControlAgitacion
     */
    public boolean isEstadoControlAgitacion() {
        return estadoControlAgitacion;
    }

    /**
     * @param estadoControlAgitacion the estadoControlAgitacion to set
     */
    public void setEstadoControlAgitacion(boolean estadoControlAgitacion) {
        this.estadoControlAgitacion = estadoControlAgitacion;
    }

    /**
     * @return the estadoControlEsterilizacion
     */
    public boolean isEstadoControlEsterilizacion() {
        return estadoControlEsterilizacion;
    }

    /**
     * @param estadoControlEsterilizacion the estadoControlEsterilizacion to set
     */
    public void setEstadoControlEsterilizacion(boolean estadoControlEsterilizacion) {
        this.estadoControlEsterilizacion = estadoControlEsterilizacion;
    }

    /**
     * Retorna el tiempo transcurrido desde que inició la esterilización, en
     * segundos. Si no ha iniciado, devuelve 0.
     */
    public long getTiempoEsterilizacionTranscurrido() {
        if (!esterilizando || tiempoInicioEsterilizacion <= 0) {
            return 0;
        }
        return (System.currentTimeMillis() - tiempoInicioEsterilizacion) / 1000;
    }

    /**
     * Retorna el tiempo restante para completar la esterilización (en
     * segundos). Si no está en proceso, devuelve 0.
     */
    public long getTiempoEsterilizacionRestante() {
        if (!esterilizando || tiempoInicioEsterilizacion <= 0) {
            return 0;
        }

        long transcurrido = (System.currentTimeMillis() - tiempoInicioEsterilizacion) / 1000;
        long restante = (long) tiempoEsterilizacionConfigurado - transcurrido;

        return Math.max(restante, 0); // nunca devuelve negativo
    }

    /**
     * Retorna un String con el formato "MM:SS / MM:SS" indicando el tiempo
     * transcurrido y el tiempo total de esterilización.
     */
    public String getTiempoEsterilizacionFormateado() {
        long transcurrido = getTiempoEsterilizacionTranscurrido();
        long total = (long) tiempoEsterilizacionConfigurado;

        long minTrans = transcurrido / 60;
        long segTrans = transcurrido % 60;

        long minTotal = total / 60;
        long segTotal = total % 60;

        return String.format("%02d:%02d / %02d:%02d", minTrans, segTrans, minTotal, segTotal);
    }

}

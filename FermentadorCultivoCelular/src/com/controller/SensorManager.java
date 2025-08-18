/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.util.AlphaFilter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class SensorManager implements Serializable{

    private static final long serialVersionUID = 1L;
    private final Map<String, AlphaFilter> filtros;

    /**
     * Constructor de la clase SensorManager.
     * <p>
     * Inicializa un conjunto de filtros tipo {@link AlphaFilter} para cada
     * sensor del sistema. Este filtro aplica un suavizado exponencial a las
     * lecturas de sensores con un factor de atenuación definido por el
     * parámetro {@code alpha}.
     *
     * @param alpha Valor del coeficiente de suavizado para los filtros. Debe
     * estar entre 0 y 1. Un valor cercano a 1 da más peso a las lecturas
     * recientes, mientras que uno más bajo prioriza la estabilidad.
     *
     * <p>
     * Sensores configurados con filtro:</p>
     * <ul>
     * <li>temperatura</li>
     * <li>temperatura2</li>
     * <li>pH</li>
     * <li>OD (Oxígeno disuelto)</li>
     * <li>CO2</li>
     * <li>rpmCH1 (revoluciones por minuto)</li>
     * <li>rpmCH2 (revoluciones por minuto)</li>
     * <li>presionCamara</li>
     * <li>presionPreCamara</li>
     * </ul>
     */
    public SensorManager(double alpha) {
        filtros = new HashMap<>();

        // Crear un filtro para cada sensor
        filtros.put("temperatura", new AlphaFilter(alpha));
        filtros.put("temperatura2", new AlphaFilter(alpha));
        filtros.put("pH", new AlphaFilter(alpha));
        filtros.put("OD", new AlphaFilter(alpha));
        filtros.put("CO2", new AlphaFilter(alpha));
        filtros.put("rpmCH1", new AlphaFilter(alpha));
        filtros.put("rpmCH2", new AlphaFilter(alpha));
        filtros.put("presionCamara", new AlphaFilter(alpha));
        filtros.put("presionPreCamara", new AlphaFilter(alpha));
    }

    /**
     * Aplica el filtro correspondiente al sensor especificado sobre la lectura
     * dada.
     * <p>
     * Utiliza un filtro de suavizado exponencial para reducir el ruido de la
     * señal.
     *
     * @param sensor Nombre del sensor (por ejemplo, "temperatura", "pH", "OD",
     * etc.).
     * @param lectura Valor actual leído del sensor.
     * @return Valor filtrado de la lectura.
     * @throws IllegalArgumentException Si el nombre del sensor no está
     * registrado.
     */
    public double filtrar(String sensor, double lectura) {
        AlphaFilter filtro = filtros.get(sensor);
        if (filtro == null) {
            throw new IllegalArgumentException("Sensor no reconocido: " + sensor);
        }
        return filtro.filter(lectura);
    }

    /**
     * Reinicia el filtro asociado al sensor indicado.
     * <p>
     * Esto descarta el estado interno acumulado del filtro, útil al iniciar un
     * nuevo lote de datos o cuando hay una lectura errónea.
     *
     * @param sensor Nombre del sensor cuyo filtro se desea reiniciar.
     */
    public void resetearFiltro(String sensor) {
        AlphaFilter filtro = filtros.get(sensor);
        if (filtro != null) {
            filtro.reset();
        }
    }

    /**
     * Reinicia todos los filtros registrados para todos los sensores.
     * <p>
     * Es útil cuando se desea limpiar todo el estado histórico de filtrado (por
     * ejemplo, al reiniciar el sistema o iniciar una nueva sesión de medición).
     */
    public void resetearTodos() {
        for (AlphaFilter filtro : filtros.values()) {
            filtro.reset();
        }
    }
}

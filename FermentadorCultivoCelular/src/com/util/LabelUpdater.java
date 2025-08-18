/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

/**
 *
 * @author PC
 */
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LabelUpdater {

    /**
     * Actualiza el texto de un JLabel estático de la clase InterfazPrincipal,
     * con base en el tipo (pH, CO2, Temperatura, etc.) y el ID del bioreactor.
     *
     * @param tipo Ej: "Temperatura", "pH", "Agitacion", "CO2"
     * @param idBioreactor 1 para Biorreactor1, 2 para Biorreactor2, etc.
     * @param texto Texto que se desea mostrar
     */
    public static void actualizarLabel(String tipo, int idBioreactor, String texto) {
        try {
            String nombreCampo;
            if (idBioreactor == 1) {
                nombreCampo = "PointValue" + tipo;
            } else {
                nombreCampo = "PointValue" + tipo + "_B" + idBioreactor;
            }

            Class<?> clase = Class.forName("com.views.InterfazPrincipal");
            Field campo = clase.getField(nombreCampo);

            JLabel label = (JLabel) campo.get(null);
            if (label != null) {
                label.setText(texto);
            } else {
                System.err.println("Campo encontrado pero JLabel es null: " + nombreCampo);
            }

        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            System.err.println(" Error al actualizar el JLabel para tipo: " + tipo + ", id: " + idBioreactor);
        }
    }

    /**
     * Actualiza un JLabel estático en la clase com.views.TestComponentes, con
     * base en el texto de un JButton (ej: "BIORREACTOR1").
     *
     * @param textoBoton Texto del botón, que define qué JLabel actualizar (ej:
     * "BIORREACTOR1")
     * @param id ID del bioreactor
     * @return String con el texto del boton
     */
    public static String obtenerTextoDesdeBoton(String textoBoton, int id) {
        try {
            String nombreCampo = textoBoton.toUpperCase() + id; // Ej: BIORREACTOR1
            System.out.println("Buscando campo: " + nombreCampo);

            Class<?> clase = Class.forName("com.views.TestComponentes");
            Field campo = clase.getField(nombreCampo);

            Object obj = campo.get(null); // porque el campo es estático

            if (obj != null && obj instanceof JButton) {
                JButton boton = (JButton) obj;
                return boton.getText();
            } else {
                System.err.println("Campo no es un JButton o es null: " + nombreCampo);
            }

        } catch (Exception e) {
            System.err.println("Error accediendo al botón: " + textoBoton + " → " + e.getMessage());
        }

        return null;
    }

}

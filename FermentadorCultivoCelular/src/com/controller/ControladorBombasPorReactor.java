/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Activador;
import com.model.Bioreactor;
import com.model.BombaPeristaltica;
import com.model.DutySupplier;
import com.model.EstadoGetter;
import com.model.TiempoSupplier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ControladorBombasPorReactor {

    private Bioreactor bioreactor;
    private List<ControladorBombaPeristaltica> controladores;
    private String[] nombres = {"BOMBA_PERISTALTICA_1", "BOMBA_PERISTALTICA_2", "BOMBA_PERISTALTICA_3"};

    public ControladorBombasPorReactor(final Bioreactor bioreactor) {
        this.bioreactor = bioreactor;
        this.controladores = new ArrayList<ControladorBombaPeristaltica>();

        for (int i = 0; i < nombres.length; i++) {
            final String nombreBomba = nombres[i];
            final BombaPeristaltica bomba = bioreactor.getBomba(i);

            ControladorBombaPeristaltica controlador = new ControladorBombaPeristaltica(
                    nombreBomba,
                    new EstadoGetter() {
                        @Override
                        public boolean get() {
                            return bomba.isEstadoControl();                            
                        }
                    },
                    new TiempoSupplier() {
                        @Override
                        public double get() {
                            return bomba.getTiempoEncendidoSegundos();
                        }
                    },
                    new DutySupplier() {
                        @Override
                        public double get() {
                            return bomba.getPorcentajeDuty();
                        }
                    },
                    new Activador() {
                        @Override
                        public void accept(int estado) {
                            bioreactor.activarSalida(bioreactor.convertirASalida(nombreBomba), estado);
                        }
                    },
                    new Runnable() {
                        @Override
                        public void run() {
                            bomba.cerrarCiclo();
                        }
                    },
                    new Runnable() {
                        @Override
                        public void run() {
                            bomba.iniciarCiclo(); // ✅ Aquí sí se registra el tiempo de inicio
                        }
                    }
            );

            controladores.add(controlador);
        }
    }

    public void actualizarTodas() {
        for (int i = 0; i < controladores.size(); i++) {
            controladores.get(i).actualizar();
        }
    }
    
    public int getBombas(){
        return nombres.length;
    }

    private void tiemposBomba(int id, String nombre) {
        System.out.println("Guardando tiempo de " + nombre + " en reactor " + id);
    }
}

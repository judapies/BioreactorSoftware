/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.info;

import com.control.Variables;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;


public class GuardarArchivo {
    private Vector<ArrayList> guardar;
    private Vector<ArrayList> guardarTabla;
        
    private ArrayList<ArrayList<JTable>> tablas;
    private ArrayList<Integer> enteros;
    private ArrayList<Boolean> binarios;
    private ArrayList<Double> flotantes;    
    private ArrayList<String> vectorString;
    private ArrayList<List<String>> vectores;

    public void startSaving(ArrayList<JTable> tabla) throws IOException {
        tablas = new ArrayList<>();
        guardarTabla = new Vector<>();
        
        tablas.add(0,tabla);
        guardarTabla.add(0, tablas);
    }
    
    public void startSaving() throws IOException {
        guardar = new Vector<>();
        enteros = new ArrayList<>();
        binarios = new ArrayList<>();
        flotantes = new ArrayList<>();
        //graficamovimiento = new ArrayList<>();
        vectorString = new ArrayList<>();
        vectores = new ArrayList<>();
                               
        flotantes.add(0,Variables.mpH);flotantes.add(1,Variables.bpH);flotantes.add(2,Variables.mOD);flotantes.add(3,Variables.bOD);
        flotantes.add(4,Variables.mTem);flotantes.add(5,Variables.bTem);flotantes.add(6,Variables.derivativo);flotantes.add(7,Variables.integral);
        flotantes.add(8,Variables.SPOD);flotantes.add(9,Variables.Temporal2);flotantes.add(10,Variables.SPpH);flotantes.add(11,Variables.SPpH2);
        flotantes.add(12,Variables.SPTemperatura);flotantes.add(13,Variables.mpH);flotantes.add(14,Variables.SPRPM);flotantes.add(15,Variables.bpH);
        flotantes.add(16,Variables.TAcido);flotantes.add(17,Variables.TAcido2);flotantes.add(18,Variables.TBase);flotantes.add(19,Variables.TBase2);
        flotantes.add(20,Variables.TFoam);flotantes.add(21,Variables.TFoam2);flotantes.add(22,Variables.Tciclo);flotantes.add(23,Variables.Tciclo2);
        flotantes.add(24,Variables.Banda);flotantes.add(25,Variables.Banda2);flotantes.add(26,Variables.BandaAgitador);flotantes.add(27,Variables.VelocidadRespuesta);
        flotantes.add(28,Variables.VelocidadRespuesta2);flotantes.add(29,Variables.OffsetRPM);flotantes.add(30,Variables.OffsetRPM2);flotantes.add(31,Variables.OffsetOD);
        flotantes.add(32,Variables.OffsetOD2);flotantes.add(33,Variables.OffsetTemperatura);flotantes.add(34,Variables.OffsetTemperatura2);flotantes.add(35,Variables.OffsetpH);
        flotantes.add(36,Variables.OffsetpH2);flotantes.add(37,Variables.PromedioRPM);flotantes.add(38,Variables.PromedioRPM2);flotantes.add(39,Variables.desvio);
        flotantes.add(40,Variables.desvio2);flotantes.add(41,Variables.Ganancia);flotantes.add(42,Variables.Ganancia2);flotantes.add(43,Variables.PromedioTemperatura);
        flotantes.add(44,Variables.PromedioTemperatura2);flotantes.add(45,Variables.PromedioOD);flotantes.add(46,Variables.PromedioOD2);flotantes.add(47,Variables.PromediopH);
        flotantes.add(48,Variables.PromediopH2);flotantes.add(49,Variables.Histeresis);flotantes.add(50,Variables.HisteresisOD2);flotantes.add(51,Variables.BandaAgitador2);
        flotantes.add(52,Variables.Coeficiente);flotantes.add(53,Variables.calibracionBomba1);flotantes.add(54,Variables.calibracionBomba2);flotantes.add(55,Variables.calibracionBomba3);
        flotantes.add(56,Variables.calibracionBomba4);flotantes.add(57,Variables.OffsetRedox);flotantes.add(58,Variables.mRedox);flotantes.add(59,Variables.bRedox);
        flotantes.add(60,Variables.PromedioRedox);flotantes.add(61,Variables.temperaturaFinal);flotantes.add(62,Variables.PresionAtmosferica);flotantes.add(63,Variables.Ttrampa);
        flotantes.add(64,Variables.SPEsterilizacion);flotantes.add(65,Variables.presionPurgaCO2);flotantes.add(66,Variables.HisteresisCO2);
        
        enteros.add(0,Variables.duracionControl);enteros.add(1,Variables.tipogas);enteros.add(2,Variables.TBomba1);
        enteros.add(3,Variables.TBomba2);enteros.add(4,Variables.TBomba3);enteros.add(5,Variables.TBomba2);
        enteros.add(6,Variables.SPBomba1);enteros.add(7,Variables.SPBomba2);enteros.add(8,Variables.SPBomba3);
        enteros.add(9,Variables.SPBomba4);enteros.add(10,Variables.retardo);enteros.add(11,Variables.SPTiempo);
        enteros.add(12,Variables.mediaPresion);enteros.add(13,Variables.pulsosPurgaProgramados);
        enteros.add(14,Variables.SPminutosEsterilizacion);enteros.add(15,Variables.SPsegundosEsterilizacion);
        //System.out.println("Minutos Esterilizacion Guardados: "+Variables.SPminutosEsterilizacion);
        
        vectorString.add(0,Variables.asignacionBomba1);
        vectorString.add(1,Variables.asignacionBomba2);
        vectorString.add(2,Variables.asignacionBomba3);
        vectorString.add(3,Variables.asignacionBomba4);
        
        vectores.add(0,Variables.usuarios);
        vectores.add(1,Variables.claves);
        vectores.add(2,Variables.roles);
        vectores.add(3,Variables.fechas);
        vectores.add(4,Variables.fechaEvento);
        vectores.add(5,Variables.usuarioEvento);
        vectores.add(6,Variables.evento);
        
        binarios.add(0,Variables.modbuspH);
        binarios.add(1,Variables.modbusOD);
        binarios.add(2,Variables.modbusRedox);
                                                          
        guardar.add(0, flotantes);
        guardar.add(1, enteros);
        guardar.add(2, vectorString);
        guardar.add(3, vectores);
        guardar.add(4, binarios);
    }
    
    public void endSaving(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
        }
        try(FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(guardar);
            oos.flush();
            System.out.println("guardar ok");
        }
    }
    
    public void saveData(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
        }
        try(FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(guardarTabla);
            oos.flush();
            System.out.println("Guardo tabla correctamente ");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.info;

import com.control.Variables;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;

public class AbrirArchivo {
    private ArrayList<ArrayList<JTable>> tablas;
    
    private Vector<ArrayList> abrirTabla;
    private Vector<ArrayList> abrir;
    private ArrayList<Integer> enteros;
    private ArrayList<Boolean> binarios;
    private ArrayList<Double> flotantes;
    private ArrayList<String> vString;
    private ArrayList<List<String>> vectores;
       	
    @SuppressWarnings("unchecked")
        
    public void openFile(File f) throws IOException, ClassNotFoundException {
        abrirTabla = new Vector<>();
        tablas = new ArrayList<>();
        System.out.println("Abrio tabla");
        
        FileInputStream fis=new FileInputStream(f);
        ObjectInputStream ois=new ObjectInputStream(fis);
        abrirTabla=(Vector<ArrayList>) ois.readObject();
    }
    
    public void startOpenning(File f) throws IOException, ClassNotFoundException {
        abrir = new Vector<>();
        enteros = new ArrayList<>();
        binarios = new ArrayList<>();
        flotantes = new ArrayList<>();
        vString=new ArrayList<>();
        vectores=new ArrayList<>();
        FileInputStream fis=new FileInputStream(f);
        ObjectInputStream ois=new ObjectInputStream(fis);
        abrir=(Vector<ArrayList>) ois.readObject();
    }

    public ArrayList<ArrayList<JTable>> fileConvert() throws IOException {
        tablas = abrirTabla.elementAt(0);
        return tablas;
    }
    
    public void startConverting() throws IOException {
        enteros = abrir.elementAt(1);
        flotantes = abrir.elementAt(0);
        vString = abrir.elementAt(2);
        vectores = abrir.elementAt(3);
        binarios = abrir.elementAt(4);
        
        Variables.mpH=flotantes.get(0);Variables.bpH=flotantes.get(1);Variables.mOD=flotantes.get(2);Variables.bOD=flotantes.get(3);
        Variables.mTem=flotantes.get(4);Variables.bTem=flotantes.get(5);Variables.derivativo=flotantes.get(6);Variables.integral=flotantes.get(7);
        Variables.SPOD=flotantes.get(8);Variables.Temporal2=flotantes.get(9);Variables.SPpH=flotantes.get(10);Variables.SPpH2=flotantes.get(11);
        Variables.SPTemperatura=flotantes.get(12);Variables.mpH=flotantes.get(13);Variables.SPRPM=flotantes.get(14);Variables.bpH=flotantes.get(15);
        Variables.TAcido=flotantes.get(16);Variables.TAcido2=flotantes.get(17);Variables.TBase=flotantes.get(18);Variables.TBase2=flotantes.get(19);
        Variables.TFoam=flotantes.get(20);Variables.TFoam2=flotantes.get(21);Variables.Tciclo=flotantes.get(22);Variables.Tciclo2=flotantes.get(23);
        Variables.Banda=flotantes.get(24);Variables.Banda2=flotantes.get(25);Variables.BandaAgitador=flotantes.get(26);Variables.VelocidadRespuesta=flotantes.get(27);
        Variables.VelocidadRespuesta2=flotantes.get(28);Variables.OffsetRPM=flotantes.get(29);Variables.OffsetRPM2=flotantes.get(30);Variables.OffsetOD=flotantes.get(31);
        Variables.OffsetOD2=flotantes.get(32);Variables.OffsetTemperatura=flotantes.get(33);Variables.OffsetTemperatura2=flotantes.get(34);Variables.OffsetpH=flotantes.get(35);
        Variables.OffsetpH2=flotantes.get(36);Variables.PromedioRPM=flotantes.get(37);Variables.PromedioRPM2=flotantes.get(38);Variables.desvio=flotantes.get(39);
        Variables.desvio2=flotantes.get(40);Variables.Ganancia=flotantes.get(41);Variables.Ganancia2=flotantes.get(42);Variables.PromedioTemperatura=flotantes.get(43);
        Variables.PromedioTemperatura2=flotantes.get(44);Variables.PromedioOD=flotantes.get(45);Variables.PromedioOD2=flotantes.get(46);Variables.PromediopH=flotantes.get(47);
        Variables.PromediopH2=flotantes.get(48);Variables.Histeresis=flotantes.get(49);Variables.HisteresisOD2=flotantes.get(50);Variables.BandaAgitador2=flotantes.get(51);
        Variables.Coeficiente=flotantes.get(52);Variables.calibracionBomba1=flotantes.get(53);Variables.calibracionBomba2=flotantes.get(54);Variables.calibracionBomba3=flotantes.get(55);
        Variables.calibracionBomba4=flotantes.get(56);Variables.OffsetRedox=flotantes.get(57);Variables.mRedox=flotantes.get(58);Variables.bRedox=flotantes.get(59);
        Variables.PromedioRedox=flotantes.get(60);Variables.temperaturaFinal=flotantes.get(61);Variables.PresionAtmosferica=flotantes.get(62);Variables.Ttrampa=flotantes.get(63);
        Variables.SPEsterilizacion=flotantes.get(64);Variables.presionPurgaCO2=flotantes.get(65);Variables.HisteresisCO2=flotantes.get(66);
        
        //------lIMITA VALORES-------------//
        
        if(Variables.mpH==0)Variables.mpH=8.75;if(Variables.bpH==0)Variables.bpH=-3.5;if(Variables.temperaturaControl<30 || Variables.temperaturaControl>90)Variables.temperaturaControl=50;
        if(Variables.mTem==0)Variables.mTem=1.0; if(Variables.bTem==0)Variables.bTem=1.0;if(Variables.derivativo<1 || Variables.derivativo>150)Variables.derivativo=40.0;
        if(Variables.integral<1 || Variables.integral>10)Variables.integral=1;if(Variables.SPpH<=0 || Variables.SPpH>14)Variables.SPpH=7.0;
        if(Variables.SPTemperatura<5 || Variables.SPTemperatura>80)Variables.SPTemperatura=50.0;if(Variables.SPRPM<100 || Variables.SPRPM>1750)Variables.SPRPM=150.0;
        if(Variables.Tciclo>50 || Variables.Tciclo<5)Variables.Tciclo=10;if(Variables.PromedioRPM<1 || Variables.PromedioRPM>Variables.RPMProm.length)Variables.PromedioRPM=1;
        if(Variables.desvio<0.1 || Variables.desvio>10)Variables.desvio=0.5;if(Variables.desvio2<0.1 || Variables.desvio2>20)Variables.desvio2=10.0;
        if(Variables.Ganancia<1 || Variables.Ganancia>20)Variables.Ganancia=5.0;
        if(Variables.PromedioTemperatura<1 || Variables.PromedioTemperatura>Variables.TemperaturaProm.length)Variables.PromedioTemperatura=1.0;
        if(Variables.PromediopH<1 || Variables.PromediopH>Variables.pHProm.length)Variables.PromediopH=1.0;
        if(Variables.Histeresis>10)Variables.Histeresis=0.2;if(Variables.PromedioRedox==0)Variables.PromedioRedox=10;
        if(Variables.SPEsterilizacion>140)Variables.SPEsterilizacion=121.0;if(Variables.presionPurgaCO2>140)Variables.presionPurgaCO2=101.0;if(Variables.Histeresis>50)Variables.Histeresis=10.0;
        
        //------Obtener enteros-----------//
        
        Variables.duracionControl = enteros.get(0);Variables.tipogas = enteros.get(1);Variables.TBomba1 = enteros.get(2);
        Variables.TBomba2 = enteros.get(3);Variables.TBomba3 = enteros.get(4);Variables.TBomba2 = enteros.get(5);
        Variables.SPBomba1 = enteros.get(6);Variables.SPBomba2 = enteros.get(7);Variables.SPBomba3 = enteros.get(8);
        Variables.SPBomba4 = enteros.get(9);Variables.retardo = enteros.get(10);Variables.SPTiempo = enteros.get(11);
        Variables.mediaPresion = enteros.get(12);Variables.pulsosPurgaProgramados = enteros.get(13);
        Variables.SPminutosEsterilizacion = enteros.get(14);Variables.SPsegundosEsterilizacion = enteros.get(15);
        
        if(Variables.duracionControl<1)Variables.duracionControl=30;
        if(Variables.TBomba1<1 || Variables.TBomba1>100)Variables.TBomba1=10;
        if(Variables.TBomba2<1 || Variables.TBomba2>100)Variables.TBomba2=10;
        if(Variables.TBomba3<1 || Variables.TBomba3>100)Variables.TBomba2=10;
        if(Variables.TBomba4<1 || Variables.TBomba4>100)Variables.TBomba2=10;
        if(Variables.SPBomba1<1 || Variables.SPBomba1>100)Variables.SPBomba1=10;
        if(Variables.SPBomba2<1 || Variables.SPBomba2>100)Variables.SPBomba2=10;
        if(Variables.SPBomba3<1 || Variables.SPBomba3>100)Variables.SPBomba3=10;
        if(Variables.SPBomba4<1 || Variables.SPBomba4>100)Variables.SPBomba4=10;
        
        if(Variables.SPminutosEsterilizacion>99)Variables.SPminutosEsterilizacion=20;if(Variables.SPsegundosEsterilizacion!=0)Variables.SPsegundosEsterilizacion=0;
        
        Variables.asignacionBomba1=vString.get(0);
        Variables.asignacionBomba2=vString.get(1);
        Variables.asignacionBomba3=vString.get(2);
        Variables.asignacionBomba4=vString.get(3);
        
        Variables.modbuspH=binarios.get(0);
        Variables.modbusOD=binarios.get(1);
        Variables.modbusRedox=binarios.get(2);
                
        Variables.usuarios=vectores.get(0);
        Variables.claves=vectores.get(1);
        Variables.roles=vectores.get(2);
        Variables.fechas=vectores.get(3);
        Variables.fechaEvento=vectores.get(4);
        Variables.usuarioEvento=vectores.get(5);
        Variables.evento=vectores.get(6);
    }
}

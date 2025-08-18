/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graphs;

import java.util.ArrayList;
import java.util.List;
import com.control.Variables;
import static com.views.InterfazFermentador.modelo2;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Free
 */
public class Graficar {

    public static List<String> datosHora = new ArrayList<>();
    public static List<String> datosFecha = new ArrayList<>();
    public static List<Double> datosTemperatura = new ArrayList<>();
    public static List<Double> datosRPM = new ArrayList<>();
    public static List<Double> datosPH = new ArrayList<>();
    public static List<Double> datosOD = new ArrayList<>();
    public static List<Double> datosRedox = new ArrayList<>();
    public static List<Double> datosTiempo = new ArrayList<>();
    public static List<Double> datosTime = new ArrayList<>();
    public static List<Double> datosT = new ArrayList<>();
    public static List<Double> datospH = new ArrayList<>();
    public static List<Double> datosrpm = new ArrayList<>();
    public static List<Double> datosredox = new ArrayList<>();
    public static List<Double> datosod = new ArrayList<>();
    public static double n = 0,nn=0, nMuestras = 100;
    public static int tamaño = 0;

    public static void graficarComparacion(ArrayList<JTable> tb, JInternalFrame f1,int data) {        
        datosod.clear();
        datosredox.clear();
        datosod.clear();
        datospH.clear();
        datosT.clear();
        datosrpm.clear();
        datosTime.clear();
        nn = 0;
        tamaño = 0;
        int rowCount = tb.get(0).getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[tb.get(0).getColumnCount()];
            for (int j = 0; j < tb.get(0).getColumnCount(); j++) {
                rowData[j] = tb.get(0).getValueAt(i, j);
            }
            datosT.add(Double.parseDouble((String) rowData[2]));
            datosrpm.add(Double.parseDouble((String) rowData[3]));
            datospH.add(Double.parseDouble((String) rowData[4]));
            datosod.add(Double.parseDouble((String) rowData[5]));
            datosredox.add(Double.parseDouble((String) rowData[6]));
            crearTime(data);
            datosTime.add(nn);
            nn++;
            //CargaTabla();
            actualizarGrafica2(f1);
        }
    }

    public static void graficar(ArrayList<JTable> tb) {
        int rowCount = tb.get(0).getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[tb.get(0).getColumnCount()];
            for (int j = 0; j < tb.get(0).getColumnCount(); j++) {
                rowData[j] = tb.get(0).getValueAt(i, j);
            }
            datosFecha.add((String) rowData[0]);
            datosHora.add((String) rowData[1]);
            datosTemperatura.add(Double.parseDouble((String) rowData[2]));
            datosRPM.add(Double.parseDouble((String) rowData[3]));
            datosPH.add(Double.parseDouble((String) rowData[4]));
            datosOD.add(Double.parseDouble((String) rowData[5]));
            datosRedox.add(Double.parseDouble((String) rowData[6]));
            crearTiempo();
            datosTiempo.add(n);
            n++;
            CargaTabla(datosFecha,datosHora);
            actualizarGrafica();
        }
    }

    public static void graficar() {
        datosTemperatura.add(Variables.valorTemperatura);
        datosRPM.add(Variables.valorRPM);
        datosPH.add(Variables.valorpH * 10.0);
        datosOD.add(Variables.valorOD);
        datosRedox.add(Variables.valorRedox);
        crearTiempo();
        datosTiempo.add(n);
        //mostrarDatos();
        n++;
        CargaTabla();
        actualizarGrafica();
    }

    public static void CargaTabla() {
        DecimalFormat decimales2 = new DecimalFormat("0.00");
        DecimalFormat decimales = new DecimalFormat("0.0");
        DecimalFormat decimal = new DecimalFormat("000");
        tamaño = datosTiempo.size();
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
        String[] VectorLectura;
        VectorLectura = new String[10];
        VectorLectura[0] = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        VectorLectura[1] = (hora2 + ":" + minuto2 + ":" + segundo2);
        VectorLectura[2] = decimales.format((double)datosTemperatura.get(tamaño - 1));
        VectorLectura[3] = decimal.format(datosRPM.get(tamaño - 1));
        VectorLectura[4] = decimales2.format(datosPH.get(tamaño - 1)/10.0);
        VectorLectura[5] = decimales.format(datosOD.get(tamaño - 1));        
        VectorLectura[6] = decimales.format(datosRedox.get(tamaño - 1));                
        modelo2.addRow(VectorLectura);
    }
    
    public static void CargaTabla(List<String> fecha, List<String> hora) {
        DecimalFormat decimales2 = new DecimalFormat("0.00");
        DecimalFormat decimales = new DecimalFormat("0.0");
        DecimalFormat decimal = new DecimalFormat("000");
        tamaño = datosTiempo.size();
        String[] VectorLectura;
        VectorLectura = new String[10];
        VectorLectura[0] = datosFecha.get(tamaño-1);
        VectorLectura[1] = datosHora.get(tamaño-1);
        VectorLectura[2] = decimales.format(datosTemperatura.get(tamaño - 1));
        VectorLectura[3] = decimal.format(datosRPM.get(tamaño - 1));
        VectorLectura[4] = decimales2.format(datosPH.get(tamaño - 1)/10.0);
        VectorLectura[5] = decimales.format(datosOD.get(tamaño - 1));        
        VectorLectura[6] = decimales.format(datosRedox.get(tamaño - 1));                
        modelo2.addRow(VectorLectura);
    }

    private static void crearTime(int data) {
        datosTime.clear();
        for (double i = 0; i < nn; i++) {
            datosTime.add(i);
        }
    }
    
    private static void crearTiempo() {
        datosTiempo.clear();
        for (double i = 0; i < n; i++) {
            datosTiempo.add(i);
        }
    }

    private static void mostrarDatos() {
        System.out.println(datosTiempo.size());
        System.out.println("T:\tTemp::\tRPM:\tpH:\tO2:\tCO2:");
        for (int i = 0; i < datosTiempo.size(); i++) {
            System.out.print(datosTiempo.get(i) + "\t");
            System.out.print(datosTemperatura.get(i) + "\t");
            System.out.print(datosRPM.get(i) + "\t");
            System.out.print(datosPH.get(i) + "\t");
            System.out.print(datosOD.get(i) + "\t");
        }
    }

    private static XYDataset crearDataset() {
        final XYSeries seriesTemp = new XYSeries("Temperatura");
        final XYSeries seriesRPM = new XYSeries("RPM");
        final XYSeries seriesPH = new XYSeries("pH*10");
        final XYSeries seriesOD = new XYSeries("OD");
        final XYSeries seriesRedox = new XYSeries("Redox");

        if (datosTemperatura.isEmpty()) {
            seriesTemp.clear();
            seriesPH.clear();
            seriesRPM.clear();
            seriesOD.clear();
            seriesRedox.clear();
        }
        for (int i = 0; i < datosTemperatura.size(); i++) {
            seriesTemp.add(datosTiempo.get(i), datosTemperatura.get(i));
            seriesPH.add(datosTiempo.get(i), datosPH.get(i));
            seriesRPM.add(datosTiempo.get(i), datosRPM.get(i));
            seriesOD.add(datosTiempo.get(i), datosOD.get(i));
            seriesRedox.add(datosTiempo.get(i), datosRedox.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesTemp);
        dataset.addSeries(seriesRPM);
        dataset.addSeries(seriesPH);
        dataset.addSeries(seriesOD);
        dataset.addSeries(seriesRedox);

        return dataset;
    }

    private static XYDataset crearDatasetCompara() {
        final XYSeries seriesTemp = new XYSeries("Temperatura");
        final XYSeries seriesRPM = new XYSeries("RPM");
        final XYSeries seriesPH = new XYSeries("pH*10");
        final XYSeries seriesOD = new XYSeries("OD");
        final XYSeries seriesRedox = new XYSeries("Redox");

        if (datosT.isEmpty()) {
            seriesTemp.clear();
            seriesPH.clear();
            seriesRPM.clear();
            seriesOD.clear();
            seriesRedox.clear();
        }
        for (int i = 0; i < datosT.size(); i++) {
            seriesTemp.add(datosTime.get(i), datosT.get(i));
            seriesPH.add(datosTime.get(i), datospH.get(i));
            seriesRPM.add(datosTime.get(i), datosrpm.get(i));
            seriesOD.add(datosTime.get(i), datosod.get(i));
            seriesRedox.add(datosTime.get(i), datosredox.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesTemp);
        dataset.addSeries(seriesRPM);
        dataset.addSeries(seriesPH);
        dataset.addSeries(seriesOD);
        dataset.addSeries(seriesRedox);

        return dataset;
    }
    
    private static JFreeChart crearChart(XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Variables de proceso", "Tiempo", "Variable", dataset, PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );
        chart.setBackgroundPaint(Color.white);
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);   // línea continua en serie 0
        renderer.setSeriesLinesVisible(1, true);   // línea continua en serie 1
        renderer.setSeriesLinesVisible(2, true);   // línea continua en serie 2
        renderer.setSeriesLinesVisible(3, true);   // línea continua en serie 3
        renderer.setSeriesLinesVisible(4, true);   // línea continua en serie 4
        renderer.setSeriesShapesVisible(0, false);  // No muestra el punto en el valor en serie 0
        renderer.setSeriesShapesVisible(1, false);  // 1. muestra el punto en el valor en serie 1
        renderer.setSeriesShapesVisible(2, false);  // 1. muestra el punto en el valor en serie 2
        renderer.setSeriesShapesVisible(3, false);  // 1. muestra el punto en el valor en serie 3
        renderer.setSeriesShapesVisible(4, false);  // 1. muestra el punto en el valor en serie 4
        plot.setRenderer(renderer);
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    public static void actualizarGrafica() {
        final XYDataset dataset = crearDataset();
        final JFreeChart chart = crearChart(dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRangeAxis().setRange(0, 20);
        plot.getRangeAxis().setAutoRange(true);
        final ChartPanel chartPanel = new ChartPanel(chart);

        int ancho = com.views.Monitor.Grafica.getWidth();
        int alto = com.views.Monitor.Grafica.getHeight();
        chartPanel.setPreferredSize(new java.awt.Dimension(988, 502));
        com.views.Monitor.Grafica.setContentPane(chartPanel);
        //setContentPane(chartPanel);
    }

    public static void actualizarGrafica2(JInternalFrame f1) {
        final XYDataset dataset = crearDatasetCompara();
        final JFreeChart chart = crearChart(dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRangeAxis().setRange(0, 20);
        plot.getRangeAxis().setAutoRange(true);
        final ChartPanel chartPanel = new ChartPanel(chart);

        int ancho = f1.getWidth();
        int alto = f1.getHeight();
        chartPanel.setPreferredSize(new java.awt.Dimension(590, 600));
        f1.setContentPane(chartPanel);
        //setContentPane(chartPanel);
    }
}

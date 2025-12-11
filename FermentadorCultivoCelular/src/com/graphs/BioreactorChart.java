package com.graphs;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
 * Una instancia de esta clase representa las curvas de un SOLO bioreactor.
 */
public class BioreactorChart {

    private final String bioreactorId;

    private final XYSeries seriesTemp;
    private final XYSeries seriesRPM;
    private final XYSeries seriesPH;   // pH*10
    private final XYSeries seriesOD;
    private final XYSeries seriesCO2;

    private final XYSeriesCollection dataset;
    private final JFreeChart chart;
    private final ChartPanel chartPanel;

    // índice de tiempo (0,1,2,...)
    private double sampleIndex = 0;

    // tabla asociada (una por bioreactor)
    private final DefaultTableModel tableModel;

    // formateadores
    private final DecimalFormat dfPH = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat("0.0");
    private final DecimalFormat dfRPM = new DecimalFormat("000");
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

    public BioreactorChart(String bioreactorId, DefaultTableModel tableModel) {
        this.bioreactorId = bioreactorId;
        this.tableModel = tableModel;

        this.seriesTemp = new XYSeries("Temperatura");
        this.seriesRPM = new XYSeries("RPM");
        this.seriesPH = new XYSeries("pH*10");
        this.seriesOD = new XYSeries("OD");
        this.seriesCO2 = new XYSeries("CO2");

        this.dataset = new XYSeriesCollection();
        dataset.addSeries(seriesTemp);
        dataset.addSeries(seriesRPM);
        dataset.addSeries(seriesPH);
        dataset.addSeries(seriesOD);
        dataset.addSeries(seriesCO2);

        this.chart = crearChart(dataset);
        this.chartPanel = new ChartPanel(chart);
        this.chartPanel.setPreferredSize(new java.awt.Dimension(988, 502));
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void clear() {
        seriesTemp.clear();
        seriesRPM.clear();
        seriesPH.clear();
        seriesOD.clear();
        seriesCO2.clear();
        sampleIndex = 0;

        if (tableModel != null) {
            tableModel.setRowCount(0);
        }
    }

    /**
     * Añade una muestra nueva (la fecha/hora se toman del sistema).
     */
    public void addSample(double temperatura,
            double rpm,
            double pH,
            double od,
            double co2) {

        double t = sampleIndex;

        seriesTemp.add(t, temperatura);
        seriesRPM.add(t, rpm);
        seriesPH.add(t, pH * 10.0);
        seriesOD.add(t, od);
        seriesCO2.add(t, co2);

        sampleIndex++;

        if (tableModel != null) {
            addRowToTable(new Date(), temperatura, rpm, pH, od, co2);
        }
    }

    /**
     * Reconstruir gráfica desde una tabla histórica (mismo formato que tu tabla
     * vieja).
     */
    public void loadFromTable(JTable table) {
        // Limpiar series y modelo interno
        clear();

        int rowCount = table.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object fechaObj = table.getValueAt(i, 0);
            Object horaObj = table.getValueAt(i, 1);
            Object tempObj = table.getValueAt(i, 2);
            Object rpmObj = table.getValueAt(i, 3);
            Object phObj = table.getValueAt(i, 4);
            Object odObj = table.getValueAt(i, 5);
            Object co2Obj = table.getValueAt(i, 6);

            double temp = parseDoubleSafe(tempObj);
            double rpm = parseDoubleSafe(rpmObj);
            double ph = parseDoubleSafe(phObj);
            double od = parseDoubleSafe(odObj);
            double co2 = parseDoubleSafe(co2Obj);

            // reconstruir eje X como índice de muestra
            double t = sampleIndex;

            seriesTemp.add(t, temp);
            seriesRPM.add(t, rpm);
            seriesPH.add(t, ph * 10.0);   // como hacías antes
            seriesOD.add(t, od);
            seriesCO2.add(t, co2);

            sampleIndex++;

            if (tableModel != null) {
                // reconstruir también el modelo interno si lo usas
                Object[] fila = new Object[]{
                    (fechaObj != null ? fechaObj.toString() : ""),
                    (horaObj != null ? horaObj.toString() : ""),
                    String.valueOf(temp),
                    String.valueOf((int) rpm),
                    String.valueOf(ph),
                    String.valueOf(od),
                    String.valueOf(co2)
                };
                tableModel.addRow(fila);
            }
        }
    }

    private JFreeChart crearChart(XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Variables de proceso - " + bioreactorId,
                "Tiempo (muestras)",
                "Variable",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesLinesVisible(3, true);
        renderer.setSeriesLinesVisible(4, true);

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesShapesVisible(4, false);

        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRange(true);

        return chart;
    }

    private void addRowToTable(Date now,
            double temperatura,
            double rpm,
            double pH,
            double od,
            double co2) {

        if (tableModel == null) {
            return;
        }

        String[] row = new String[7];

        row[0] = dateFormatter.format(now);
        row[1] = timeFormatter.format(now);
        row[2] = df.format(temperatura);
        row[3] = dfRPM.format(rpm);
        row[4] = dfPH.format(pH);
        row[5] = df.format(od);
        row[6] = df.format(co2);

        tableModel.addRow(row);
    }

    private double parseDoubleSafe(Object value) {
        if (value == null) {
            return 0.0;
        }
        String s = value.toString().trim();
        if (s.isEmpty()) {
            return 0.0;
        }

        // Aceptar tanto "98,5" como "98.5"
        s = s.replace(',', '.');

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            // Si quieres, aquí puedes hacer un log con el valor original
            System.err.println("No se pudo parsear número: \"" + value + "\" -> se usa 0.0");
            return 0.0; // o Double.NaN, lo que prefieras
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.comunicacion.Hilo;
import com.control.Variables;
import com.model.Bioreactor;
import com.util.LabelUpdater;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanDavid
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    private static final long serialVersionUID = 01;
    public static DefaultTableModel modelo;
    public static DefaultTableModel modelo2;
    public static Recetas cascada;
    private final int seleccion = 0;
    Imagen2 Img = new Imagen2();
    com.info.Batch abrir = new com.info.Batch();
    com.keyboard.Password abrir_clave;
    Eventos ev;
    public static InterfazFermentador Biorreactor1 = null;
    Control controla;

    /**
     * Creates new form InterfazFermentador
     */
    @SuppressWarnings({"CallToThreadStartDuringObjectConstruction", "static-access", "OverridableMethodCallInConstructor"})
    public InterfazPrincipal() {
        initComponents();
        CargarDatos();
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/com/images/logo.png")).getImage());
        modelo2 = new DefaultTableModel();
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        this.setLayout(null);
        setLocationRelativeTo(null);
        abrir_clave = new com.keyboard.Password();
        jPanel1.setBackground(Color.WHITE);        
        jPanel1.setLayout(null);
        new Thread(new Hilo()).start();
        Imagen Imagen1 = new Imagen();
        jPanel1.add(Imagen1);
    }

    public void Close() {
        WindowEvent winclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winclose);
    }

    public class Imagen2 extends javax.swing.JPanel {

        private static final long serialVersionUID = 01;

        public Imagen2() {
            //this.setSize(panel.getWidth()-200, panel.getHeight()-10); //se selecciona el tamaño del panel
            this.setSize(1280, 629); //se selecciona el tamaño del panel
        }

        @Override
        public void paint(Graphics grafico) {
            Dimension height = getSize();
            ImageIcon Img = new ImageIcon(getClass().getResource("/com/images/fondo.jpg"));
            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
            setOpaque(false);
            super.paintComponent(grafico);
        }
    }

    public class Imagen extends javax.swing.JPanel {

        private static final long serialVersionUID = 01;

        public Imagen() {
            //this.setSize(panel.getWidth()-200, panel.getHeight()-10); //se selecciona el tamaño del panel
            this.setSize(1280, 800); //se selecciona el tamaño del panel
        }

        @Override
        public void paint(Graphics grafico) {
            Dimension height = getSize();
            ImageIcon Img = new ImageIcon(getClass().getResource("/com/images/Fondo_Planta_biorreactores.png"));
            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
            setOpaque(false);
            super.paintComponent(grafico);
        }
    }

    public void CargarDatos() {
        if (Variables.bioreactores == null) {
            Variables.bioreactores = new ArrayList<>();
        }
        for (int i = 101; i < 104; i++) {
        //for (int i = 101; i < 102; i++) {//Modificado para prueba con solo un esclavo-26/11/2025
            File f = new File("bioreactor_config_" + i + ".dat");
            if (f.exists()) {
                try {
                    Variables.bioreactores.add(Bioreactor.cargar("bioreactor_config_" + i + ".dat"));
                    System.out.println("Cargando");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No puede abrir Info", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                Variables.bioreactores.add(new Bioreactor(i, 30, 0.2));//id 101 de 1L
                System.out.println("No cargo");
            }
        }
        Variables.bioreactores.get(0).setCapacidadLitros(1);
        //Variables.bioreactores.get(1).setCapacidadLitros(5);
        for (Bioreactor bio : Variables.bioreactores) {
            bio.setEstadoControlAgitacion(false);
            bio.setEstadoControlCO2(false);
            bio.setEstadoControlEsterilizacion(false);
            bio.setEstadoControlOD(false);
            bio.setEstadoControlTemperatura(false);
            bio.setEstadoControlpH(false);
            System.out.println(bio.getId());
            System.out.println(bio.getCapacidadLitros());
            System.out.println(bio.getBomba(0).getPorcentajeDuty());
            System.out.println(bio.getBomba(0).getTiempoEncendidoSegundos());
            System.out.println(bio.getBomba(0).getParametros().getAsignacionBomba());
        }
        AbreConfig();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BotonBio_1 = new javax.swing.JButton();
        BotonBio_2 = new javax.swing.JButton();
        BotonBio_3 = new javax.swing.JButton();
        BotonBio_4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Temperatura = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PointValuepH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PointValueCO2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PointValueAgitacion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Temperatura12 = new javax.swing.JLabel();
        Temperatura13 = new javax.swing.JLabel();
        Temperatura14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Temperatura15 = new javax.swing.JLabel();
        PointValueTemperatura = new javax.swing.JLabel();
        Temperatura4 = new javax.swing.JLabel();
        Temperatura6 = new javax.swing.JLabel();
        Temperatura3 = new javax.swing.JLabel();
        Temperatura7 = new javax.swing.JLabel();
        Temperatura5 = new javax.swing.JLabel();
        Temperatura8 = new javax.swing.JLabel();
        PointValueAgitacion_B3 = new javax.swing.JLabel();
        PointValueCO2_B3 = new javax.swing.JLabel();
        Temperatura9 = new javax.swing.JLabel();
        Temperatura10 = new javax.swing.JLabel();
        PointValueTemperatura_B3 = new javax.swing.JLabel();
        PointValuepH_B3 = new javax.swing.JLabel();
        PointValueAgitacion_B2 = new javax.swing.JLabel();
        PointValueCO2_B2 = new javax.swing.JLabel();
        PointValuepH_B2 = new javax.swing.JLabel();
        PointValueTemperatura_B2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Batch = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Eventos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        AjusteControlpH = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        AjusteControlOD = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        TestComponentes = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        Identificarse = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        CrearUsuario = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        info = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        soporte = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Hora = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biorreactor JP");
        setMinimumSize(new java.awt.Dimension(1280, 800));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 775));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 775));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(null);

        BotonBio_1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BotonBio_1.setBorderPainted(false);
        BotonBio_1.setContentAreaFilled(false);
        BotonBio_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBio_1ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonBio_1);
        BotonBio_1.setBounds(55, 212, 225, 435);

        BotonBio_2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BotonBio_2.setBorderPainted(false);
        BotonBio_2.setContentAreaFilled(false);
        BotonBio_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBio_2ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonBio_2);
        BotonBio_2.setBounds(370, 212, 225, 435);

        BotonBio_3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BotonBio_3.setBorderPainted(false);
        BotonBio_3.setContentAreaFilled(false);
        BotonBio_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBio_3ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonBio_3);
        BotonBio_3.setBounds(702, 212, 225, 435);

        BotonBio_4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BotonBio_4.setBorderPainted(false);
        BotonBio_4.setContentAreaFilled(false);
        BotonBio_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBio_4ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonBio_4);
        BotonBio_4.setBounds(1016, 212, 225, 435);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Windows-Turn-Off-icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1211, 26, 44, 43);

        Temperatura.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura.setText("RMP");
        jPanel1.add(Temperatura);
        Temperatura.setBounds(230, 190, 40, 15);

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("TEMPERATURA:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 100, 120, 20);

        PointValuepH.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValuepH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValuepH.setText("0.0");
        jPanel1.add(PointValuepH);
        PointValuepH.setBounds(170, 130, 50, 19);

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("pH:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 130, 100, 20);

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("CO2:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(60, 160, 100, 20);

        PointValueCO2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueCO2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueCO2.setText("0.0");
        jPanel1.add(PointValueCO2);
        PointValueCO2.setBounds(170, 160, 50, 19);

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("AGITACIÓN:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(60, 190, 100, 20);

        PointValueAgitacion.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueAgitacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueAgitacion.setText("00");
        jPanel1.add(PointValueAgitacion);
        PointValueAgitacion.setBounds(170, 190, 50, 19);

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TEMPERATURA:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(360, 100, 120, 19);

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("pH:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(360, 130, 120, 19);

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("CO2:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(360, 160, 120, 19);

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("AGITACIÓN:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(360, 190, 120, 19);

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("CO2:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(690, 160, 120, 19);

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("TEMPERATURA:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(690, 100, 120, 19);

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("AGITACIÓN:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(690, 190, 120, 19);

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("pH:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(690, 130, 120, 19);

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("AGITACIÓN:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(1030, 190, 120, 19);

        Temperatura12.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        Temperatura12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Temperatura12.setText("----");
        jPanel1.add(Temperatura12);
        Temperatura12.setBounds(1140, 100, 70, 19);

        Temperatura13.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        Temperatura13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Temperatura13.setText("----");
        jPanel1.add(Temperatura13);
        Temperatura13.setBounds(1140, 160, 70, 19);

        Temperatura14.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        Temperatura14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Temperatura14.setText("----");
        jPanel1.add(Temperatura14);
        Temperatura14.setBounds(1140, 190, 70, 19);

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("pH:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(1030, 130, 120, 19);

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("CO2:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(1030, 160, 120, 19);

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("TEMPERATURA:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(1030, 100, 120, 19);

        Temperatura15.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        Temperatura15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Temperatura15.setText("----");
        jPanel1.add(Temperatura15);
        Temperatura15.setBounds(1140, 130, 70, 19);

        PointValueTemperatura.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueTemperatura.setText("0.0");
        jPanel1.add(PointValueTemperatura);
        PointValueTemperatura.setBounds(170, 100, 50, 19);

        Temperatura4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura4.setText("°C");
        jPanel1.add(Temperatura4);
        Temperatura4.setBounds(230, 100, 30, 18);

        Temperatura6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura6.setText("%");
        jPanel1.add(Temperatura6);
        Temperatura6.setBounds(230, 160, 30, 18);

        Temperatura3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura3.setText("RMP");
        jPanel1.add(Temperatura3);
        Temperatura3.setBounds(550, 190, 40, 18);

        Temperatura7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura7.setText("%");
        jPanel1.add(Temperatura7);
        Temperatura7.setBounds(550, 160, 40, 18);

        Temperatura5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura5.setText("°C");
        jPanel1.add(Temperatura5);
        Temperatura5.setBounds(550, 100, 40, 18);

        Temperatura8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura8.setText("°C");
        jPanel1.add(Temperatura8);
        Temperatura8.setBounds(880, 100, 40, 18);

        PointValueAgitacion_B3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueAgitacion_B3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueAgitacion_B3.setText("00");
        jPanel1.add(PointValueAgitacion_B3);
        PointValueAgitacion_B3.setBounds(830, 190, 40, 19);

        PointValueCO2_B3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueCO2_B3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueCO2_B3.setText("0.0");
        jPanel1.add(PointValueCO2_B3);
        PointValueCO2_B3.setBounds(830, 160, 40, 19);

        Temperatura9.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura9.setText("RMP");
        jPanel1.add(Temperatura9);
        Temperatura9.setBounds(880, 190, 40, 18);

        Temperatura10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Temperatura10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Temperatura10.setText("%");
        jPanel1.add(Temperatura10);
        Temperatura10.setBounds(880, 160, 40, 18);

        PointValueTemperatura_B3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueTemperatura_B3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueTemperatura_B3.setText("0.0");
        jPanel1.add(PointValueTemperatura_B3);
        PointValueTemperatura_B3.setBounds(800, 100, 70, 19);

        PointValuepH_B3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValuepH_B3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValuepH_B3.setText("0.0");
        jPanel1.add(PointValuepH_B3);
        PointValuepH_B3.setBounds(800, 130, 70, 19);

        PointValueAgitacion_B2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueAgitacion_B2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueAgitacion_B2.setText("00");
        jPanel1.add(PointValueAgitacion_B2);
        PointValueAgitacion_B2.setBounds(490, 190, 50, 19);

        PointValueCO2_B2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueCO2_B2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueCO2_B2.setText("0.0");
        jPanel1.add(PointValueCO2_B2);
        PointValueCO2_B2.setBounds(490, 160, 50, 19);

        PointValuepH_B2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValuepH_B2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValuepH_B2.setText("0.0");
        jPanel1.add(PointValuepH_B2);
        PointValuepH_B2.setBounds(490, 130, 50, 19);

        PointValueTemperatura_B2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        PointValueTemperatura_B2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PointValueTemperatura_B2.setText("0.0");
        jPanel1.add(PointValueTemperatura_B2);
        PointValueTemperatura_B2.setBounds(490, 100, 50, 19);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(1280, 51));

        jMenu1.setText("Info");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Batch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Batch.setText("Batch");
        Batch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BatchActionPerformed(evt);
            }
        });
        jMenu1.add(Batch);
        jMenu1.add(jSeparator1);

        Eventos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Eventos.setText("Eventos");
        Eventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EventosActionPerformed(evt);
            }
        });
        jMenu1.add(Eventos);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Actions-edit-clear-list-icon.png"))); // NOI18N
        jMenu2.setText("Administrar");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        AjusteControlpH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlpH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Pictogrammers-Material-Ph.16.png"))); // NOI18N
        AjusteControlpH.setText("Calibrar sensor pH");
        AjusteControlpH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlpHActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlpH);
        jMenu2.add(jSeparator5);

        AjusteControlOD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/icons8-water-steam-48.png"))); // NOI18N
        AjusteControlOD.setText("Calibrar sensor OD");
        AjusteControlOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlODActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlOD);
        jMenu2.add(jSeparator6);

        TestComponentes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TestComponentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Actions-edit-clear-list-icon.png"))); // NOI18N
        TestComponentes.setText("Test Componentes");
        TestComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestComponentesActionPerformed(evt);
            }
        });
        jMenu2.add(TestComponentes);

        jMenuBar1.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Oxygen-Icons.org-Oxygen-Apps-preferences-desktop-user-password.16.png"))); // NOI18N
        jMenu5.setText("Usuarios");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(97, 50));

        Identificarse.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Identificarse.setText("Identificarse");
        Identificarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdentificarseActionPerformed(evt);
            }
        });
        jMenu5.add(Identificarse);
        jMenu5.add(jSeparator10);

        CrearUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CrearUsuario.setText("Gestionar Usuarios");
        CrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearUsuarioActionPerformed(evt);
            }
        });
        jMenu5.add(CrearUsuario);
        jMenu5.add(jSeparator11);

        jMenuBar1.add(jMenu5);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Icons-Land-Vista-People-Occupations-Technical-Support-Representative-Male-Light.16.png"))); // NOI18N
        jMenu4.setText("Ayuda");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        info.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Kyo-Tux-Delikate-Info.16.png"))); // NOI18N
        info.setText("Info");
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        jMenu4.add(info);
        jMenu4.add(jSeparator8);

        soporte.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        soporte.setText("Soporte");
        soporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soporteActionPerformed(evt);
            }
        });
        jMenu4.add(soporte);
        jMenu4.add(jSeparator9);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Asistencia Tecnica");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Planta de biorreactores - JPINGLOBAL");
        jMenu3.setToolTipText("");
        jMenu3.setContentAreaFilled(false);
        jMenu3.setEnabled(false);
        jMenu3.setFocusable(false);
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jMenu3.setRequestFocusEnabled(false);
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        Hora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Actions-edit-clear-list-icon.png"))); // NOI18N
        Hora.setText("Hora y Fecha:");
        Hora.setContentAreaFilled(false);
        Hora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Hora.setEnabled(false);
        Hora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Hora.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Hora.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Hora.setPreferredSize(new java.awt.Dimension(650, 25));
        Hora.setRequestFocusEnabled(false);
        Hora.setVerifyInputWhenFocusTarget(false);
        jMenuBar1.add(Hora);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BatchActionPerformed
        if (!abrir.isVisible()) {
            abrir.setVisible(true);
        }
    }//GEN-LAST:event_BatchActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        File f = new File("Config.con");
        com.info.GuardarArchivo s = new com.info.GuardarArchivo();
        try {
            s.startSaving();
            s.endSaving(f);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No puede guardar Info", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    @SuppressWarnings("static-access")
    private void AjusteControlpHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteControlpHActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        abrir_clave.setVisible(true);
        com.keyboard.Password.jPasswordField1.setText(null);
        com.keyboard.Password.jTextField1.setText(null);
        Variables.ajustepH = true;
    }//GEN-LAST:event_AjusteControlpHActionPerformed

    private void AjusteControlODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteControlODActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        abrir_clave.setVisible(true);
        com.keyboard.Password.jPasswordField1.setText(null);
        com.keyboard.Password.jTextField1.setText(null);
        Variables.ajusteOD = true;
    }//GEN-LAST:event_AjusteControlODActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
        JOptionPane.showMessageDialog(null, "Para soporte tecnico por favor comunicarse al +573185103822", "JP Bioingenieria SAS 2023", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_infoActionPerformed

    private void soporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soporteActionPerformed
        JOptionPane.showMessageDialog(null, "Para soporte tecnico por favor comunicarse al +57 3185325158 \r\n" + "www.jpinglobal.com", "JP Bioingenieria SAS", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_soporteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try { // Instrucciones para hacer que el computador se apague.
            Runtime.getRuntime().exec("AnyDesk.exe");
        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void IdentificarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdentificarseActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
            
        }
        abrir_clave.setVisible(true);
        com.keyboard.Password.jPasswordField1.setText(null);
        com.keyboard.Password.jTextField1.setText(null);
    }//GEN-LAST:event_IdentificarseActionPerformed

    private void CrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearUsuarioActionPerformed
        if (Variables.login && Variables.usuario.equals("JPINGLOBAL")) {
            com.views.Usuarios tb = new Usuarios();
            tb.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar como super usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CrearUsuarioActionPerformed

    private void EventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EventosActionPerformed
        try {
            if (ev == null) {
                ev = new Eventos();
            }
            if (!ev.isVisible()) {
                ev.setVisible(true);
                com.views.Eventos.cargaTabla();
            } else {
                ev.toFront();
            }
        } catch (Exception ex) {
            Logger.getLogger(InterfazPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EventosActionPerformed

    private void TestComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestComponentesActionPerformed
        boolean estado = false;
        for (Bioreactor bio : Variables.bioreactores) {
            if (!bio.isEstadoControlAgitacion() && !bio.isEstadoControlEsterilizacion() && !bio.isEstadoControlOD() && !bio.isEstadoControlTemperatura() && !bio.isEstadoControlpH()) {
                estado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Desactive todos los controles del biorreactor " + (bio.getId() - 100));
                estado = false;
                break;
            }
        }
        if (estado) {
            TestComponentes Test = new TestComponentes();
            Test.setVisible(true);
            Variables.testComponentes = true;
            for (Bioreactor bio : Variables.bioreactores) {
                if (bio.getId() == Variables.idBioreactor) {
                    com.views.TestComponentes.tituloTest.setText(LabelUpdater.obtenerTextoDesdeBoton("BIORREACTOR", bio.getId() - 100));
                    com.views.TestComponentes.AC1.setBackground(bio.leerSalida(Bioreactor.Salida.SUMINISTRO_VAPOR) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC2.setBackground(bio.leerSalida(Bioreactor.Salida.RECIRCULACION) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC3.setBackground(bio.leerSalida(Bioreactor.Salida.ENTRADA_INTERCAMBIADOR) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC4.setBackground(bio.leerSalida(Bioreactor.Salida.SALIDA_INTERCAMBIADOR) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC5.setBackground(bio.leerSalida(Bioreactor.Salida.BOMBA_ENCARCELACION) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC6.setBackground(bio.leerSalida(Bioreactor.Salida.DRENAJE) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC7.setBackground(bio.leerSalida(Bioreactor.Salida.SUMINISTRO_AGUA_ENFRIAMIENTO) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC8.setBackground(bio.leerSalida(Bioreactor.Salida.SALIDA_AGUA_ENFRIAMIENTO) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC9.setBackground(bio.leerSalida(Bioreactor.Salida.BOMBA_RECIRCULACION) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC10.setBackground(bio.leerSalida(Bioreactor.Salida.INGRESO_CO2) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC11.setBackground(bio.leerSalida(Bioreactor.Salida.VENTEO_CO2) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.AC12.setBackground(bio.leerSalida(Bioreactor.Salida.DESFOGUE_VAPOR) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.SSR.setBackground(bio.leerSalida(Bioreactor.Salida.SSR) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.BOMBA1.setBackground(bio.leerSalida(Bioreactor.Salida.BOMBA_PERISTALTICA_1) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.BOMBA2.setBackground(bio.leerSalida(Bioreactor.Salida.BOMBA_PERISTALTICA_2) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.BOMBA3.setBackground(bio.leerSalida(Bioreactor.Salida.BOMBA_PERISTALTICA_3) == 5 ? Color.green : Color.red);
                    com.views.TestComponentes.BUZZER.setBackground(bio.leerSalida(Bioreactor.Salida.BUZZER) == 5 ? Color.green : Color.red);
                }
            }
        }
    }//GEN-LAST:event_TestComponentesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BotonBio_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBio_4ActionPerformed
        /*VariablesGenerales.IDbiorreactor="Biorreactor4";
         InterfazFermentador Biorreactor = new InterfazFermentador();
         Biorreactor.setVisible(true);*/
        JOptionPane.showMessageDialog(null, "Biorreactor No disponible");
    }//GEN-LAST:event_BotonBio_4ActionPerformed

    private void BotonBio_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBio_3ActionPerformed
        Variables.idBioreactor = 103;
        if (Biorreactor1 == null || !Biorreactor1.isDisplayable()) {
            Biorreactor1 = new InterfazFermentador();
            Biorreactor1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para permitir reabrir si se cerró
            Biorreactor1.setVisible(true);
            actualizaCampos();
        } else {
            if (!Biorreactor1.isVisible()) {
                Biorreactor1.setVisible(true);
                actualizaCampos();
            }
            Biorreactor1.toFront(); // traer al frente
            Biorreactor1.setState(JFrame.NORMAL); // restaurar si estaba minimizada
        }
    }//GEN-LAST:event_BotonBio_3ActionPerformed

    private void BotonBio_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBio_2ActionPerformed
        Variables.idBioreactor = 102;
        if (Biorreactor1 == null || !Biorreactor1.isDisplayable()) {
            Biorreactor1 = new InterfazFermentador();
            Biorreactor1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para permitir reabrir si se cerró
            Biorreactor1.setVisible(true);
            actualizaCampos();
        } else {
            if (!Biorreactor1.isVisible()) {
                Biorreactor1.setVisible(true);
                actualizaCampos();
            }
            Biorreactor1.toFront(); // traer al frente
            Biorreactor1.setState(JFrame.NORMAL); // restaurar si estaba minimizada
        }
    }//GEN-LAST:event_BotonBio_2ActionPerformed

    private void BotonBio_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBio_1ActionPerformed
        Variables.idBioreactor = 101;
        if (Biorreactor1 == null || !Biorreactor1.isDisplayable()) {
            Biorreactor1 = new InterfazFermentador();
            Biorreactor1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para permitir reabrir si se cerró
            Biorreactor1.setVisible(true);
            actualizaCampos();
        } else {
            if (!Biorreactor1.isVisible()) {
                Biorreactor1.setVisible(true);
                actualizaCampos();
            }
            Biorreactor1.toFront(); // traer al frente
            Biorreactor1.setState(JFrame.NORMAL); // restaurar si estaba minimizada
        }
    }//GEN-LAST:event_BotonBio_1ActionPerformed

    public void actualizaCampos() {
        for (Bioreactor bio : Variables.bioreactores) {
            if (Variables.idBioreactor == bio.getId()) {
                com.views.Control.SetPointEsterilizacion.setText(Double.toString(bio.getParametros().getEsterlizacion().getSetpoint()));
                com.views.Control.SetPointTiempo.setText(Integer.toString(bio.getParametros().getEsterlizacion().getTiempoMinutos()));
                com.views.Control.SetPointAgitador.setText(Integer.toString((int) bio.getParametros().getAgitacion().getSetpoint()));
                com.views.Control.SetPointTemperatura.setText(Double.toString(bio.getParametros().getTemperatura().getSetpoint()));
                com.views.Control.SetPointpH.setText(Double.toString(bio.getParametros().getPh().getSetpoint()));
                com.views.Control.SetPointOD2.setText(Double.toString(bio.getParametros().getOd().getSetpoint()));
                com.views.Control.SetPointPresion.setText(Double.toString(bio.getParametros().getCO2().getSetpoint()));
                com.views.Control.SetPointHisteresis.setText(Double.toString(bio.getParametros().getCO2().getHisteresis()));
                if (bio.isEstadoControlCO2()) {
                    com.views.Control.InicioCO.setText("Detener");
                    com.views.Control.InicioCO.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioCO.setText("Iniciar");
                    com.views.Control.InicioCO.setBackground(Color.GREEN);
                }
                if (bio.isEstadoControlEsterilizacion()) {
                    com.views.Control.InicioEsterilizacion.setText("Detener");
                    com.views.Control.InicioEsterilizacion.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioEsterilizacion.setText("Iniciar");
                    com.views.Control.InicioEsterilizacion.setBackground(Color.GREEN);
                }
                if (bio.isEstadoControlAgitacion()) {
                    com.views.Control.InicioControlAgitacion.setText("Detener");
                    com.views.Control.InicioControlAgitacion.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioControlAgitacion.setText("Iniciar");
                    com.views.Control.InicioControlAgitacion.setBackground(Color.GREEN);
                }
                if (bio.isEstadoControlpH()) {
                    com.views.Control.InicioControlpH.setText("Detener");
                    com.views.Control.InicioControlpH.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioControlpH.setText("Iniciar");
                    com.views.Control.InicioControlpH.setBackground(Color.GREEN);
                }
                if (bio.isEstadoControlTemperatura()) {
                    com.views.Control.InicioControlTemperatura.setText("Detener");
                    com.views.Control.InicioControlTemperatura.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioControlTemperatura.setText("Iniciar");
                    com.views.Control.InicioControlTemperatura.setBackground(Color.GREEN);
                }
                if (bio.isEstadoControlOD()) {
                    com.views.Control.InicioControlOD.setText("Detener");
                    com.views.Control.InicioControlOD.setBackground(Color.RED);
                } else {
                    com.views.Control.InicioControlOD.setText("Iniciar");
                    com.views.Control.InicioControlOD.setBackground(Color.GREEN);
                }
            }
        }
    }

    public void AbreConfig() {
        File f = new File("Config.con");
        if (f.exists()) {
            com.info.AbrirArchivo o = new com.info.AbrirArchivo();
            try {
                o.startOpenning(f);
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No puede abrir Info", "Error", JOptionPane.ERROR_MESSAGE);
            }
            try {
                o.startConverting();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No puede abrir Info", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*
         try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         if ("Nimbus".equals(info.getName())) {
         javax.swing.UIManager.setLookAndFeel(info.getClassName());
         break;
         }
         }
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(InterfazFermentador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         */
        /* Create and display the form */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AjusteControlOD;
    private javax.swing.JMenuItem AjusteControlpH;
    private javax.swing.JMenuItem Batch;
    private javax.swing.JButton BotonBio_1;
    private javax.swing.JButton BotonBio_2;
    private javax.swing.JButton BotonBio_3;
    private javax.swing.JButton BotonBio_4;
    private javax.swing.JMenuItem CrearUsuario;
    private javax.swing.JMenuItem Eventos;
    public static javax.swing.JMenu Hora;
    private javax.swing.JMenuItem Identificarse;
    public static javax.swing.JLabel PointValueAgitacion;
    public static javax.swing.JLabel PointValueAgitacion_B2;
    public static javax.swing.JLabel PointValueAgitacion_B3;
    public static javax.swing.JLabel PointValueCO2;
    public static javax.swing.JLabel PointValueCO2_B2;
    public static javax.swing.JLabel PointValueCO2_B3;
    public static javax.swing.JLabel PointValueTemperatura;
    public static javax.swing.JLabel PointValueTemperatura_B2;
    public static javax.swing.JLabel PointValueTemperatura_B3;
    public static javax.swing.JLabel PointValuepH;
    public static javax.swing.JLabel PointValuepH_B2;
    public static javax.swing.JLabel PointValuepH_B3;
    public static javax.swing.JLabel Temperatura;
    public static javax.swing.JLabel Temperatura10;
    public static javax.swing.JLabel Temperatura12;
    public static javax.swing.JLabel Temperatura13;
    public static javax.swing.JLabel Temperatura14;
    public static javax.swing.JLabel Temperatura15;
    public static javax.swing.JLabel Temperatura3;
    public static javax.swing.JLabel Temperatura4;
    public static javax.swing.JLabel Temperatura5;
    public static javax.swing.JLabel Temperatura6;
    public static javax.swing.JLabel Temperatura7;
    public static javax.swing.JLabel Temperatura8;
    public static javax.swing.JLabel Temperatura9;
    private javax.swing.JMenuItem TestComponentes;
    private javax.swing.JMenuItem info;
    private javax.swing.JButton jButton1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem soporte;
    // End of variables declaration//GEN-END:variables
}

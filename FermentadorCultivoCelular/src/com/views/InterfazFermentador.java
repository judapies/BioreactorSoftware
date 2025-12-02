/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.control.Variables;
import com.graphs.Graficar;
import com.keyboard.JNumBoardPane;
import com.keyboard.Password;
import com.model.Bioreactor;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanDavid
 */
public class InterfazFermentador extends javax.swing.JFrame {

    private static final long serialVersionUID = 01;
    public static DefaultTableModel modelo;
    public static DefaultTableModel modelo2;
    public static Recetas cascada;
    private int seleccion = 0;
    private int liAgit = 0;
    private int lsAgit = 0;
    private int liAire = 0;
    private int lsAire = 0;
    private int liO2 = 0;
    private int lsO2 = 0;
    private int tiempo = 0;
    public static BombasPeristalticas bombas;
    Control controla;
    Monitor monitor;
    Imagen2 Img = new Imagen2();
    com.info.Batch abrir = new com.info.Batch();
    Password abrir_clave = new Password();
    Eventos ev;
    private JNumBoardPane tecladoNumerico;
    private JPopupMenu pop;

    /**
     * Creates new form InterfazFermentador
     */
    @SuppressWarnings({"CallToThreadStartDuringObjectConstruction", "static-access", "OverridableMethodCallInConstructor"})
    public InterfazFermentador() {
        initComponents();
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/com/images/logo.png")).getImage());
        modelo2 = new DefaultTableModel();
        tabla2.setModel(modelo2);
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        modelo2.addColumn("");
        this.setLayout(null);
        setLocationRelativeTo(null);
        //CargarDatos();
        abrir_clave = new com.keyboard.Password();
        jPanel1.setBackground(Color.WHITE);
        jPanel2.setBackground(Color.WHITE);
        jPanel2.removeAll();
        jPanel2.repaint();
        controla = new Control();
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel2.add(controla);
        controla.setBounds(1, 1, 1280, 629);
        controla.repaint();
        controla.updateUI();
        Imagen Imagen1 = new Imagen();
        com.views.Control.jPanel1.add(Imagen1);
        com.views.Control.jPanel1.repaint();

        Off.setBackground(Color.WHITE);
        monitor = new Monitor();
        //bombas = new BombasPeristalticas();

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
            this.setSize(1280, 629); //se selecciona el tamaño del panel
        }

        @Override
        public void paint(Graphics grafico) {
            ImageIcon Img;
            Dimension height = getSize();
            if (Variables.idBioreactor == 101) {
                Img = new ImageIcon(getClass().getResource("/com/images/Fondo_Bio1L.png"));
                grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);                
            } else if (Variables.idBioreactor == 102) {
                Img = new ImageIcon(getClass().getResource("/com/images/Fondo_Bio5L.png"));
                grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);                
            } else if (Variables.idBioreactor == 103) {
                Img = new ImageIcon(getClass().getResource("/com/images/Fondo_Bio30L.png"));
                grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);                
            }
            setOpaque(false);
            super.paintComponent(grafico);
        }
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
        Control = new javax.swing.JButton();
        BombasPeristalticas = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        Monitor = new javax.swing.JButton();
        Off = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Batch = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Eventos = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        AjusteControlTemperatura = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        AjusteControlAgitacion = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        AjusteControlpH = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        AjusteControlOD = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        AjusteEsterilizacion = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        AjustePresion = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        Fermentador = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
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
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 800));

        Control.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Control.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Control-Panel-icon.png"))); // NOI18N
        Control.setText("Control");
        Control.setMaximumSize(new java.awt.Dimension(141, 83));
        Control.setPreferredSize(new java.awt.Dimension(141, 83));
        Control.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ControlActionPerformed(evt);
            }
        });

        BombasPeristalticas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BombasPeristalticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Bomba_Peristaltica.jpg"))); // NOI18N
        BombasPeristalticas.setText("Bombas Peristalticas");
        BombasPeristalticas.setMaximumSize(new java.awt.Dimension(245, 83));
        BombasPeristalticas.setPreferredSize(new java.awt.Dimension(245, 83));
        BombasPeristalticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BombasPeristalticasActionPerformed(evt);
            }
        });

        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 800));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Monitor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Monitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/business-summary-icon.png"))); // NOI18N
        Monitor.setText("Monitor");
        Monitor.setMaximumSize(new java.awt.Dimension(145, 83));
        Monitor.setPreferredSize(new java.awt.Dimension(145, 83));
        Monitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonitorActionPerformed(evt);
            }
        });

        Off.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/btn_regresar_azul_48x48.png"))); // NOI18N
        Off.setContentAreaFilled(false);
        Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(Control, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(Monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(BombasPeristalticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(Off, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Off))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(Monitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BombasPeristalticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

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
        jMenu1.add(jSeparator12);
        jMenu1.add(jSeparator13);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Actions-edit-clear-list-icon.png"))); // NOI18N
        jMenu2.setText("Administrar");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        AjusteControlTemperatura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlTemperatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/temperature-4-icon.png"))); // NOI18N
        AjusteControlTemperatura.setText("Control Temperatura");
        AjusteControlTemperatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlTemperaturaActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlTemperatura);
        jMenu2.add(jSeparator3);

        AjusteControlAgitacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlAgitacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Industry-Stepper-Motor-icon.png"))); // NOI18N
        AjusteControlAgitacion.setText("Control Agitación");
        AjusteControlAgitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlAgitacionActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlAgitacion);
        jMenu2.add(jSeparator4);

        AjusteControlpH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlpH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Pictogrammers-Material-Ph.16.png"))); // NOI18N
        AjusteControlpH.setText("Control pH");
        AjusteControlpH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlpHActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlpH);
        jMenu2.add(jSeparator5);

        AjusteControlOD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteControlOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/icons8-water-steam-48.png"))); // NOI18N
        AjusteControlOD.setText("OD");
        AjusteControlOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteControlODActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteControlOD);
        jMenu2.add(jSeparator6);

        AjusteEsterilizacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjusteEsterilizacion.setText("Esterilización");
        AjusteEsterilizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjusteEsterilizacionActionPerformed(evt);
            }
        });
        jMenu2.add(AjusteEsterilizacion);
        jMenu2.add(jSeparator7);

        AjustePresion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjustePresion.setText("Presión");
        AjustePresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjustePresionActionPerformed(evt);
            }
        });
        jMenu2.add(AjustePresion);
        jMenu2.add(jSeparator14);

        Fermentador.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Fermentador.setText("Seleccione Biorreactor");
        Fermentador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FermentadorActionPerformed(evt);
            }
        });
        jMenu2.add(Fermentador);
        jMenu2.add(jSeparator10);
        jMenu2.add(jSeparator11);

        jMenuBar1.add(jMenu2);

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

        jMenu3.setText("       JP-Biolab     Fermentors & Bioreactors");
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("static-access")
    private void BombasPeristalticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BombasPeristalticasActionPerformed
        jPanel2.removeAll();
        jPanel2.repaint();
        if (bombas == null) {
            bombas = new BombasPeristalticas();
        }

        com.views.BombasPeristalticas.jPanel1.add(Img);
        for (Bioreactor bio : Variables.bioreactores) {
            if (Variables.idBioreactor == bio.getId()) {
                //FALTA ASIGNAR EL ESTADO DE lOS BOTONES
                com.views.BombasPeristalticas.runBomba1.setBackground(bio.getBomba(0).isRunBomba() ? Color.GREEN : Color.RED);
                com.views.BombasPeristalticas.runBomba1.setOpaque(true);
                com.views.BombasPeristalticas.runBomba2.setBackground(bio.getBomba(1).isRunBomba() ? Color.GREEN : Color.RED);
                com.views.BombasPeristalticas.runBomba2.setOpaque(true);
                com.views.BombasPeristalticas.runBomba3.setBackground(bio.getBomba(2).isRunBomba() ? Color.GREEN : Color.RED);
                com.views.BombasPeristalticas.runBomba3.setOpaque(true);

                com.views.BombasPeristalticas.bomba1On.setBackground(bio.getBomba(0).isEstadoOn() ? Color.GREEN : Color.GRAY);
                com.views.BombasPeristalticas.bomba1On.setOpaque(true);
                com.views.BombasPeristalticas.bomba2On.setBackground(bio.getBomba(1).isEstadoOn() ? Color.GREEN : Color.GRAY);
                com.views.BombasPeristalticas.bomba2On.setOpaque(true);
                com.views.BombasPeristalticas.bomba3On.setBackground(bio.getBomba(2).isEstadoOn() ? Color.GREEN : Color.GRAY);
                com.views.BombasPeristalticas.bomba3On.setOpaque(true);

                com.views.BombasPeristalticas.bomba1Off.setBackground(bio.getBomba(0).isEstadoOn() ? Color.GRAY : Color.RED);
                com.views.BombasPeristalticas.bomba1Off.setOpaque(true);
                com.views.BombasPeristalticas.bomba2Off.setBackground(bio.getBomba(1).isEstadoOn() ? Color.GRAY : Color.RED);
                com.views.BombasPeristalticas.bomba2Off.setOpaque(true);
                com.views.BombasPeristalticas.bomba3Off.setBackground(bio.getBomba(2).isEstadoOn() ? Color.GRAY : Color.RED);
                com.views.BombasPeristalticas.bomba3Off.setOpaque(true);

                com.views.BombasPeristalticas.SetPointBomba1.setText("" + bio.getBomba(0).getPorcentajeDuty());
                com.views.BombasPeristalticas.SetPointBomba2.setText("" + bio.getBomba(1).getPorcentajeDuty());
                com.views.BombasPeristalticas.SetPointBomba3.setText("" + bio.getBomba(2).getPorcentajeDuty());

                com.views.BombasPeristalticas.PeriodoBomba1.setText("" + bio.getBomba(0).getTiempoEncendidoSegundos());
                com.views.BombasPeristalticas.PeriodoBomba2.setText("" + bio.getBomba(1).getTiempoEncendidoSegundos());
                com.views.BombasPeristalticas.PeriodoBomba3.setText("" + bio.getBomba(2).getTiempoEncendidoSegundos());

                com.views.BombasPeristalticas.asignacionBomba1.setText("" + bio.getBomba(0).getParametros().getAsignacionBomba());
                com.views.BombasPeristalticas.asignacionBomba2.setText("" + bio.getBomba(1).getParametros().getAsignacionBomba());
                com.views.BombasPeristalticas.asignacionBomba3.setText("" + bio.getBomba(2).getParametros().getAsignacionBomba());
            }
        }
        com.views.BombasPeristalticas.jPanel1.repaint();

        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel2.add(bombas);
        bombas.setBounds(10, 1, 1280, 629);
        bombas.repaint();
        bombas.updateUI();
    }//GEN-LAST:event_BombasPeristalticasActionPerformed

    @SuppressWarnings("static-access")
    private void ControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ControlActionPerformed
        jPanel2.removeAll();
        jPanel2.repaint();

        Imagen Imagen = new Imagen();
        com.views.Control.jPanel1.add(Imagen);
        com.views.Control.jPanel1.repaint();
        for (Bioreactor bio : Variables.bioreactores) {
            if (Variables.idBioreactor == bio.getId()) {
                com.views.Control.SetPointAgitador.setText(Integer.toString((int) bio.getParametros().getAgitacion().getSetpoint()));
                com.views.Control.SetPointTemperatura.setText(Double.toString(bio.getParametros().getTemperatura().getSetpoint()));
                com.views.Control.SetPointpH.setText(Double.toString(bio.getParametros().getPh().getSetpoint()));
                com.views.Control.SetPointOD2.setText(Double.toString(bio.getParametros().getOd().getSetpoint()));
                
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
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel2.add(controla);
        controla.setBounds(1, 1, 1280, 629);
        controla.repaint();
        controla.updateUI();
    }//GEN-LAST:event_ControlActionPerformed

    @SuppressWarnings("static-access")
    private void MonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonitorActionPerformed
        jPanel2.removeAll();
        jPanel2.repaint();
        com.views.Monitor.jPanel1.add(Img);
        com.views.Monitor.jPanel1.repaint();
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel2.add(monitor);
        monitor.setBounds(10, 1, 1280, 629);
        monitor.repaint();
        monitor.updateUI();
        Graficar.actualizarGrafica();
        com.views.Monitor.jSpinner1.setValue(Variables.TMuestreo / 1000);
        //new TecladoVirtual.Teclados( this, true).setVisible(true);
    }//GEN-LAST:event_MonitorActionPerformed

    private void OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OffActionPerformed
        dispose();
    }//GEN-LAST:event_OffActionPerformed

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
        if (!abrir_clave.isVisible()) {
            Variables.ajustepH = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
    }//GEN-LAST:event_AjusteControlpHActionPerformed

    @SuppressWarnings("static-access")
    private void AjusteControlAgitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteControlAgitacionActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }if (!abrir_clave.isVisible()) {
            Variables.ajusteAgitador = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
    }//GEN-LAST:event_AjusteControlAgitacionActionPerformed

    @SuppressWarnings("static-access")
    private void AjusteControlTemperaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteControlTemperaturaActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        if (!abrir_clave.isVisible()) {
            Variables.ajusteTemperatura = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
    }//GEN-LAST:event_AjusteControlTemperaturaActionPerformed

    private void AjusteControlODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteControlODActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        if (!abrir_clave.isVisible()) {
            Variables.ajusteOD = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
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
            Logger.getLogger(InterfazFermentador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            Logger.getLogger(InterfazFermentador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EventosActionPerformed

    private void AjusteEsterilizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjusteEsterilizacionActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        if (!abrir_clave.isVisible()) {
            Variables.ajusteEsterilizacion = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
    }//GEN-LAST:event_AjusteEsterilizacionActionPerformed

    private void FermentadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FermentadorActionPerformed
        Variables.bioreactor = (String) JOptionPane.showInputDialog(null, "Seleccione el ID del Biorreactor", "ID del Biorreactor", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"}, "Seleccione");
        if (Variables.bioreactor == null) {
            Variables.bioreactor = "1";
        }

        Variables.idBioreactor = Integer.parseInt(Variables.bioreactor);
        if (Variables.idBioreactor > 1) {
            JOptionPane.showMessageDialog(null, "No encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_FermentadorActionPerformed

    private void AjustePresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjustePresionActionPerformed
        if (abrir_clave == null || !abrir_clave.isDisplayable()) {
            abrir_clave = new com.keyboard.Password();
        }
        if (!abrir_clave.isVisible()) {
            Variables.ajustePresion = true;
            abrir_clave.setVisible(true);
            com.keyboard.Password.jPasswordField1.setText(null);
            com.keyboard.Password.jTextField1.setText(null);
        }
    }//GEN-LAST:event_AjustePresionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazFermentador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AjusteControlAgitacion;
    private javax.swing.JMenuItem AjusteControlOD;
    private javax.swing.JMenuItem AjusteControlTemperatura;
    private javax.swing.JMenuItem AjusteControlpH;
    private javax.swing.JMenuItem AjusteEsterilizacion;
    private javax.swing.JMenuItem AjustePresion;
    private javax.swing.JMenuItem Batch;
    private javax.swing.JButton BombasPeristalticas;
    private javax.swing.JButton Control;
    private javax.swing.JMenuItem Eventos;
    private javax.swing.JMenuItem Fermentador;
    public static javax.swing.JMenu Hora;
    private javax.swing.JButton Monitor;
    private javax.swing.JButton Off;
    private javax.swing.JMenuItem info;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem soporte;
    private javax.swing.JTable tabla;
    public static javax.swing.JTable tabla2;
    // End of variables declaration//GEN-END:variables
}

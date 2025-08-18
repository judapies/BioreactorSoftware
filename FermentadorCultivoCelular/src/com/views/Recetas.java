/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views;

import com.keyboard.JNumBoardPane;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author JP Electronica
 */
public class Recetas extends javax.swing.JPanel {
    private static final long serialVersionUID = 01;
    private int seleccion;
    private int liAgit;
    private int lsAgit;
    private int liAire;
    private int lsAire;
    private int liO2;
    private int lsO2;
    private int tiempo;
    private JNumBoardPane tecladoNumerico;
    private JPopupMenu pop;
    /**
     * Creates new form cascada
     */
    public int getSeleccion(){
        return this.seleccion;
    }
    public int getLIAgitacion(){
        return this.liAgit;
    }
    public int getLSAgitacion(){
        return this.lsAgit;
    }
    public int getLIAire(){
        return this.liAire;
    }
    public int getLSAire(){
        return this.lsAire;
    }
    public int getLIO2(){
        return this.liO2;
    }
    public int getLSO2(){
        return this.lsO2;
    }
    public int getTiempo(){
        return this.tiempo;
    }
    
    public Recetas(int seleccion,int liAgit,int lsAgit, int liAire,int lsAire,int liO2,int lsO2,int tiempo) {
        initComponents();
        System.out.println("Entro Cascada");
        panelParametros.setBackground(Color.white);
        panelSeleccion.setBackground(Color.white);
        this.seleccion=seleccion;
        this.liAgit=liAgit;
        this.lsAgit=lsAgit;
        this.liAire=liAire;
        this.lsAire=lsAire;
        this.liO2=liO2;
        this.lsO2=lsO2;
        this.tiempo=tiempo;
        tiempoTransicion.setVisible(false);
        texto6.setVisible(false);
        switch (this.seleccion) {
            case 0:
                ningunoRadio.setSelected(true);
                tiempoTransicion.setEnabled(false);
                setLimites(false, false, false, false, false, false);
                break;
            case 1:
                aireRadio.setSelected(true);
                tiempoTransicion.setEnabled(false);
                linferiorAire.setText(""+this.liAire);
                lsuperiorAire.setText(""+this.lsAire);
                setLimites(false, false, true, true, false, false);
                break;    
            case 2:
                o2Radio.setSelected(true);
                tiempoTransicion.setEnabled(false);
                linferiorO2.setText(""+this.liO2);
                lsuperiorO2.setText(""+this.lsO2);
                setLimites(false, false, false, false, true, true);
                break;
            case 3:
                agitacionRadio.setSelected(true);
                tiempoTransicion.setEnabled(false);
                linferiorAgitacion.setText(""+this.liAgit);
                lsuperiorAgitacion.setText(""+this.lsAgit);
                setLimites(true, true, false, false, false, false);
                break;    
            case 4:
                agitacionAireRadio.setSelected(true);
                tiempoTransicion.setEnabled(true);
                linferiorAgitacion.setText(""+this.liAgit);
                lsuperiorAgitacion.setText(""+this.lsAgit);
                linferiorAire.setText(""+this.liAire);
                lsuperiorAire.setText(""+this.lsAire);
                tiempoTransicion.setText(""+this.tiempo);
                setLimites(true, true, true, true, false, false);
                break;
            case 5:
                agitacionO2Radio.setSelected(true);
                tiempoTransicion.setEnabled(true);
                linferiorAgitacion.setText(""+this.liAgit);
                lsuperiorAgitacion.setText(""+this.lsAgit);
                linferiorO2.setText(""+this.liO2);
                lsuperiorO2.setText(""+this.lsO2);
                tiempoTransicion.setText(""+this.tiempo);
                setLimites(true, true, false, false, true, true);
                break;
            case 6:
                aireO2Radio.setSelected(true);
                tiempoTransicion.setEnabled(true);
                linferiorAire.setText(""+this.liAire);
                lsuperiorAire.setText(""+this.lsAire);
                linferiorO2.setText(""+this.liO2);
                lsuperiorO2.setText(""+this.lsO2);
                tiempoTransicion.setText(""+this.tiempo);
                setLimites(false, false, true, true, true, true);
                break;
        }
    }

    private void setLimites(boolean liAg,boolean lsAg,boolean liAi,boolean lsAi,boolean liO2,boolean lsO2){
        linferiorAgitacion.setEnabled(liAg);
        lsuperiorAgitacion.setEnabled(lsAg);
        if(!liAg)
            linferiorAgitacion.setText("");
        if(!lsAg)
            lsuperiorAgitacion.setText("");
        linferiorAire.setEnabled(liAi);
        lsuperiorAire.setEnabled(lsAi);
        if(!liAi)
            linferiorAire.setText("");
        if(!lsAi)
            lsuperiorAire.setText("");
        linferiorO2.setEnabled(liO2);
        lsuperiorO2.setEnabled(lsO2);
        if(!liO2)
            linferiorO2.setText("");
        if(!lsO2)
            lsuperiorO2.setText("");
    }
    
    private void creaPop(JTextField f){
        if(pop!=null){
            if(pop.isVisible()){
                pop.setVisible(false);
            }else{
                pop = new JPopupMenu();
                tecladoNumerico=new JNumBoardPane(f);
                pop.add(tecladoNumerico);
                pop.setVisible(true);
                pop.setLocation(f.getLocationOnScreen().x+f.getSize().width, f.getLocationOnScreen().y);
            }                
        }else{
            pop = new JPopupMenu();
            tecladoNumerico=new JNumBoardPane(f);
            pop.add(tecladoNumerico);
            pop.setVisible(true);
            pop.setLocation(f.getLocationOnScreen().x+f.getSize().width, f.getLocationOnScreen().y);
        }
    }
    
    public int ocultaPop(JTextField f,int l,int minimo, int maximo){
        pop.setVisible(false);
        int t=0;
        try {
            t=Integer.parseInt(f.getText());
        } catch (Exception e) {
            f.setText(""+l);
            t=l;
        }
        if(t>maximo || t<minimo){
            JOptionPane.showMessageDialog(null, "Valor fuera de rango\n El valor debe estar entre "+minimo+" a "+maximo, "Error", JOptionPane.ERROR_MESSAGE); 
            f.setText(""+minimo);
            return minimo;
        }else{
            return Integer.parseInt(f.getText());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelSeleccion = new javax.swing.JPanel();
        ningunoRadio = new javax.swing.JRadioButton();
        aireRadio = new javax.swing.JRadioButton();
        o2Radio = new javax.swing.JRadioButton();
        agitacionRadio = new javax.swing.JRadioButton();
        agitacionAireRadio = new javax.swing.JRadioButton();
        agitacionO2Radio = new javax.swing.JRadioButton();
        aireO2Radio = new javax.swing.JRadioButton();
        panelParametros = new javax.swing.JPanel();
        texto = new javax.swing.JLabel();
        linferiorAgitacion = new javax.swing.JTextField();
        texto1 = new javax.swing.JLabel();
        lsuperiorAgitacion = new javax.swing.JTextField();
        texto2 = new javax.swing.JLabel();
        texto3 = new javax.swing.JLabel();
        linferiorAire = new javax.swing.JTextField();
        lsuperiorAire = new javax.swing.JTextField();
        texto4 = new javax.swing.JLabel();
        texto5 = new javax.swing.JLabel();
        linferiorO2 = new javax.swing.JTextField();
        lsuperiorO2 = new javax.swing.JTextField();
        texto6 = new javax.swing.JLabel();
        tiempoTransicion = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1024, 470));
        setMinimumSize(new java.awt.Dimension(1024, 470));
        setPreferredSize(new java.awt.Dimension(1024, 470));

        panelSeleccion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cascada OD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        buttonGroup1.add(ningunoRadio);
        ningunoRadio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ningunoRadio.setSelected(true);
        ningunoRadio.setText("Ninguno");
        ningunoRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ningunoRadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(aireRadio);
        aireRadio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aireRadio.setText("Aire");
        aireRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                aireRadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(o2Radio);
        o2Radio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        o2Radio.setText("O2");
        o2Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                o2RadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(agitacionRadio);
        agitacionRadio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        agitacionRadio.setText("Agitación");
        agitacionRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                agitacionRadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(agitacionAireRadio);
        agitacionAireRadio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        agitacionAireRadio.setText("Agitación / Aire");
        agitacionAireRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                agitacionAireRadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(agitacionO2Radio);
        agitacionO2Radio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        agitacionO2Radio.setText("Agitación / O2");
        agitacionO2Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                agitacionO2RadioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(aireO2Radio);
        aireO2Radio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aireO2Radio.setText("Aire / O2");
        aireO2Radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                aireO2RadioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelSeleccionLayout = new javax.swing.GroupLayout(panelSeleccion);
        panelSeleccion.setLayout(panelSeleccionLayout);
        panelSeleccionLayout.setHorizontalGroup(
            panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ningunoRadio)
                    .addComponent(aireRadio)
                    .addComponent(o2Radio)
                    .addComponent(agitacionRadio)
                    .addComponent(agitacionAireRadio)
                    .addComponent(agitacionO2Radio)
                    .addComponent(aireO2Radio))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        panelSeleccionLayout.setVerticalGroup(
            panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(ningunoRadio)
                .addGap(18, 18, 18)
                .addComponent(aireRadio)
                .addGap(18, 18, 18)
                .addComponent(o2Radio)
                .addGap(18, 18, 18)
                .addComponent(agitacionRadio)
                .addGap(18, 18, 18)
                .addComponent(agitacionAireRadio)
                .addGap(18, 18, 18)
                .addComponent(agitacionO2Radio)
                .addGap(18, 18, 18)
                .addComponent(aireO2Radio)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        panelParametros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parametros de Cascada", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        texto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto.setText("Limite Inferior Cascada de Agitación:");

        linferiorAgitacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        linferiorAgitacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        linferiorAgitacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                linferiorAgitacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                linferiorAgitacionFocusLost(evt);
            }
        });

        texto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto1.setText("Limite Superior Cascada de Agitación:");

        lsuperiorAgitacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lsuperiorAgitacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lsuperiorAgitacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lsuperiorAgitacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lsuperiorAgitacionFocusLost(evt);
            }
        });

        texto2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto2.setText("Limite Superior Cascada de Aire:");

        texto3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto3.setText("Limite Inferior Cascada de Aire:");

        linferiorAire.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        linferiorAire.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        linferiorAire.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                linferiorAireFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                linferiorAireFocusLost(evt);
            }
        });

        lsuperiorAire.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lsuperiorAire.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lsuperiorAire.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lsuperiorAireFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lsuperiorAireFocusLost(evt);
            }
        });

        texto4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto4.setText("Limite Superior Cascada de O2:");

        texto5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto5.setText("Limite Inferior Cascada de O2:");

        linferiorO2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        linferiorO2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        linferiorO2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                linferiorO2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                linferiorO2FocusLost(evt);
            }
        });

        lsuperiorO2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lsuperiorO2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lsuperiorO2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lsuperiorO2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lsuperiorO2FocusLost(evt);
            }
        });

        texto6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        texto6.setText("Histeresis:");

        tiempoTransicion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tiempoTransicion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tiempoTransicion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tiempoTransicionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tiempoTransicionFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelParametrosLayout = new javax.swing.GroupLayout(panelParametros);
        panelParametros.setLayout(panelParametrosLayout);
        panelParametrosLayout.setHorizontalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelParametrosLayout.createSequentialGroup()
                            .addComponent(texto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(linferiorAgitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelParametrosLayout.createSequentialGroup()
                            .addComponent(texto1)
                            .addGap(31, 31, 31)
                            .addComponent(lsuperiorAgitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelParametrosLayout.createSequentialGroup()
                            .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(texto3)
                                .addComponent(texto2))
                            .addGap(64, 64, 64)
                            .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lsuperiorAire, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(linferiorAire, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(linferiorO2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lsuperiorO2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(texto5)
                    .addComponent(texto4)
                    .addGroup(panelParametrosLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(tiempoTransicion, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(texto6))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        panelParametrosLayout.setVerticalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelParametrosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto)
                    .addComponent(linferiorAgitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1)
                    .addComponent(lsuperiorAgitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto3)
                    .addComponent(linferiorAire, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2)
                    .addComponent(lsuperiorAire, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto5)
                    .addComponent(linferiorO2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto4)
                    .addComponent(lsuperiorO2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto6)
                    .addComponent(tiempoTransicion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelParametros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelParametros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSeleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ningunoRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ningunoRadioItemStateChanged
        if(ningunoRadio.isSelected()){
            this.seleccion=0;
            tiempoTransicion.setEnabled(false);
            setLimites(false, false, false, false, false, false);
        }
    }//GEN-LAST:event_ningunoRadioItemStateChanged

    private void aireRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aireRadioItemStateChanged
        if(aireRadio.isSelected()){
            this.seleccion=1;
            tiempoTransicion.setEnabled(false);
            linferiorAire.setText(""+this.liAire);
            lsuperiorAire.setText(""+this.lsAire);
            setLimites(false, false, true, true, false, false);
        }
    }//GEN-LAST:event_aireRadioItemStateChanged

    private void o2RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_o2RadioItemStateChanged
        if(o2Radio.isSelected()){
            this.seleccion=2;
            tiempoTransicion.setEnabled(false);
            linferiorO2.setText(""+this.liO2);
            lsuperiorO2.setText(""+this.lsO2);
            setLimites(false, false, false, false, true, true);
        }
    }//GEN-LAST:event_o2RadioItemStateChanged

    private void agitacionRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_agitacionRadioItemStateChanged
        if(agitacionRadio.isSelected()){
            this.seleccion=3;
            tiempoTransicion.setEnabled(false);
            linferiorAgitacion.setText(""+this.liAgit);
            lsuperiorAgitacion.setText(""+this.lsAgit);
            setLimites(true, true, false, false, false, false);
        }
    }//GEN-LAST:event_agitacionRadioItemStateChanged

    private void agitacionAireRadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_agitacionAireRadioItemStateChanged
        if(agitacionAireRadio.isSelected()){
            this.seleccion=4;
            tiempoTransicion.setEnabled(true);
            linferiorAgitacion.setText(""+this.liAgit);
            lsuperiorAgitacion.setText(""+this.lsAgit);
            linferiorAire.setText(""+this.liAire);
            lsuperiorAire.setText(""+this.lsAire);
            tiempoTransicion.setText(""+this.tiempo);
            setLimites(true, true, true, true, false, false);
        }
    }//GEN-LAST:event_agitacionAireRadioItemStateChanged

    private void agitacionO2RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_agitacionO2RadioItemStateChanged
        if(agitacionO2Radio.isSelected()){
            this.seleccion=5;
            tiempoTransicion.setEnabled(true);
            linferiorAgitacion.setText(""+this.liAgit);
            lsuperiorAgitacion.setText(""+this.lsAgit);
            linferiorO2.setText(""+this.liO2);
            lsuperiorO2.setText(""+this.lsO2);
            tiempoTransicion.setText(""+this.tiempo);
            setLimites(true, true, false, false, true, true);
        }
    }//GEN-LAST:event_agitacionO2RadioItemStateChanged

    private void aireO2RadioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aireO2RadioItemStateChanged
        if(aireO2Radio.isSelected()){
            this.seleccion=6;
            tiempoTransicion.setEnabled(true);
            linferiorAire.setText(""+this.liAire);
            lsuperiorAire.setText(""+this.lsAire);
            linferiorO2.setText(""+this.liO2);
            lsuperiorO2.setText(""+this.lsO2);
            tiempoTransicion.setText(""+this.tiempo);
            setLimites(false, false, true, true, true, true);
        }
    }//GEN-LAST:event_aireO2RadioItemStateChanged

    private void linferiorAgitacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorAgitacionFocusGained
        creaPop(linferiorAgitacion);
    }//GEN-LAST:event_linferiorAgitacionFocusGained

    private void linferiorAgitacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorAgitacionFocusLost
        this.liAgit=ocultaPop(linferiorAgitacion, this.liAgit, 50, 1200);
    }//GEN-LAST:event_linferiorAgitacionFocusLost

    private void lsuperiorAgitacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorAgitacionFocusGained
        creaPop(lsuperiorAgitacion);
    }//GEN-LAST:event_lsuperiorAgitacionFocusGained

    private void lsuperiorAgitacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorAgitacionFocusLost
        this.lsAgit=ocultaPop(lsuperiorAgitacion, this.lsAgit, 50, 1200);
    }//GEN-LAST:event_lsuperiorAgitacionFocusLost

    private void linferiorAireFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorAireFocusGained
        creaPop(linferiorAire);
    }//GEN-LAST:event_linferiorAireFocusGained

    private void linferiorAireFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorAireFocusLost
        this.liAire=ocultaPop(linferiorAire, this.liAire, 0, 100);
    }//GEN-LAST:event_linferiorAireFocusLost

    private void lsuperiorAireFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorAireFocusGained
        creaPop(lsuperiorAire);
    }//GEN-LAST:event_lsuperiorAireFocusGained

    private void lsuperiorAireFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorAireFocusLost
        this.lsAire=ocultaPop(lsuperiorAire, this.lsAire, 0, 100);
    }//GEN-LAST:event_lsuperiorAireFocusLost

    private void linferiorO2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorO2FocusGained
        creaPop(linferiorO2);
    }//GEN-LAST:event_linferiorO2FocusGained

    private void linferiorO2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_linferiorO2FocusLost
        this.liO2=ocultaPop(linferiorO2, this.liO2, 0, 100);
    }//GEN-LAST:event_linferiorO2FocusLost

    private void lsuperiorO2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorO2FocusGained
        creaPop(lsuperiorO2);
    }//GEN-LAST:event_lsuperiorO2FocusGained

    private void lsuperiorO2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lsuperiorO2FocusLost
        this.lsO2=ocultaPop(lsuperiorO2, this.lsO2, 0, 100);
    }//GEN-LAST:event_lsuperiorO2FocusLost

    private void tiempoTransicionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tiempoTransicionFocusGained
        creaPop(tiempoTransicion);
    }//GEN-LAST:event_tiempoTransicionFocusGained

    private void tiempoTransicionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tiempoTransicionFocusLost
        this.tiempo=ocultaPop(tiempoTransicion, this.tiempo, 0, 10000);
    }//GEN-LAST:event_tiempoTransicionFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton agitacionAireRadio;
    private javax.swing.JRadioButton agitacionO2Radio;
    private javax.swing.JRadioButton agitacionRadio;
    private javax.swing.JRadioButton aireO2Radio;
    private javax.swing.JRadioButton aireRadio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField linferiorAgitacion;
    private javax.swing.JTextField linferiorAire;
    private javax.swing.JTextField linferiorO2;
    private javax.swing.JTextField lsuperiorAgitacion;
    private javax.swing.JTextField lsuperiorAire;
    private javax.swing.JTextField lsuperiorO2;
    private javax.swing.JRadioButton ningunoRadio;
    private javax.swing.JRadioButton o2Radio;
    private javax.swing.JPanel panelParametros;
    private javax.swing.JPanel panelSeleccion;
    private javax.swing.JLabel texto;
    private javax.swing.JLabel texto1;
    private javax.swing.JLabel texto2;
    private javax.swing.JLabel texto3;
    private javax.swing.JLabel texto4;
    private javax.swing.JLabel texto5;
    private javax.swing.JLabel texto6;
    private javax.swing.JTextField tiempoTransicion;
    // End of variables declaration//GEN-END:variables
}

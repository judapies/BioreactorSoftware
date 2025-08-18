/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keyboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PopUpAsignacion extends JPopupMenu {

    private static final long serialVersionUID = 01;
    private JPopupMenu popup;
    private JButton ninguno;
    private JButton acido;
    private JButton base;
    private JButton nivelAlto;
    private JButton nivelMedio;
    private JButton nivelBajo;
    private Color fondo;
    public static Object objeto;
    private JTextField texto;
    private JTextField t;

    public PopUpAsignacion(JTextField t) {
        texto = (JTextField) objeto;
        this.t = t;
        popup = new JPopupMenu(); //creamos el menu saliente
        ninguno = new JButton("Ninguno");
        ninguno.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ningunoActionPerformed(evt);
            }
        });
        acido = new JButton("Acido");
        acido.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acidoActionPerformed(evt);
            }
        });
        base = new JButton("Base");
        base.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseActionPerformed(evt);
            }
        });
        nivelAlto = new JButton("Nivel Alto");
        nivelAlto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelAltoActionPerformed(evt);
            }
        });
        nivelMedio = new JButton("Nivel Medio");
        nivelMedio.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelMedioActionPerformed(evt);
            }
        });
        nivelBajo = new JButton("Nivel Bajo");
        nivelBajo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelBajoActionPerformed(evt);
            }
        });

        this.setLayout(new GridLayout(3, 2));
        ninguno.setFont(new Font("Arial", Font.PLAIN, 16));
        acido.setFont(new Font("Arial", Font.PLAIN, 16));
        base.setFont(new Font("Arial", Font.PLAIN, 16));
        nivelAlto.setFont(new Font("Arial", Font.PLAIN, 16));
        nivelBajo.setFont(new Font("Arial", Font.PLAIN, 16));
        nivelMedio.setFont(new Font("Arial", Font.PLAIN, 16));

        add(ninguno);
        add(acido);
        add(base);
        add(nivelAlto);
        add(nivelMedio);
        add(nivelBajo);
    }

    private void ningunoActionPerformed(ActionEvent evt) {
        t.setText("Ninguno");
        t.transferFocus();
        this.setVisible(false);
    }

    private void acidoActionPerformed(ActionEvent evt) {
        t.setText("Acido");
        t.transferFocus();
        this.setVisible(false);
    }

    private void baseActionPerformed(ActionEvent evt) {
        t.setText("Base");
        t.transferFocus();
        this.setVisible(false);
    }

    private void nivelAltoActionPerformed(ActionEvent evt) {
        t.setText("Nivel Alto");
        t.transferFocus();
        this.setVisible(false);
    }

    private void nivelMedioActionPerformed(ActionEvent evt) {
        t.setText("Nivel Medio");
        t.transferFocus();
        this.setVisible(false);
    }

    private void nivelBajoActionPerformed(ActionEvent evt) {
        t.setText("Nivel Bajo");
        t.transferFocus();
        this.setVisible(false);
    }
}

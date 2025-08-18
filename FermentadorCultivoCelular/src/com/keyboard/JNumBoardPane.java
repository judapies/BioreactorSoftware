/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keyboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 *
 * @author Fermentador 7 Litros
 */
public class JNumBoardPane extends JPanel {

    private static final long serialVersionUID = 01;
    JTextField txt;
    String teclas[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "-"};
    ArrayList<JButton> botones = new ArrayList<JButton>();
    JPanel pletras, pespacio, pclear;
    int punto = 0;

    public JNumBoardPane(JTextField t) {

        txt = t;
        txt.setText(null);
        pletras = new JPanel();
        setLayout(new BorderLayout());
        pletras.setLayout(new GridLayout(5, 3, 0, 0));

        ActionListener accion = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                if (punto == 0) {
                    if (!b.getText().equalsIgnoreCase(" ")) {
                        txt.setText("" + txt.getText() + b.getText());
                    } else {
                        txt.setText(txt.getText() + " ");
                    }
                }

                if (punto == 1) {
                    if (!b.getText().equalsIgnoreCase(" ")) {
                        txt.setText("" + txt.getText() + b.getText());
                    }
                }

                if (".".equals(b.getText())) {
                    punto++;
                    if (punto > 1) {
                        punto = 0;
                        txt.setText(null);
                    }
                }

                if ("CLEAR".equals(b.getText())) {
                    txt.setText(null);
                }
                
            }
        };

        for (int i = 0; i < 12; i++) {
            if (teclas[i].equalsIgnoreCase("Z")) {
                JLabel l = new JLabel();
                pletras.add(l);
            }
            JButton b = new JButton(teclas[i]);
            b.addActionListener(accion);
            b.setPreferredSize(new Dimension(50, 50));
            pletras.add(b);
            botones.add(b);
        }

        pespacio = new JPanel(new GridLayout(1, 3));
        JButton bespacio = new JButton(" ");
        bespacio.addActionListener(accion);
        pespacio.add(new JLabel());
        pespacio.add(bespacio);
        pespacio.add(new JLabel());
        add(pletras);

        pclear = new JPanel(new GridLayout(1, 2));
        JButton bclear = new JButton("CLEAR");
        bclear.addActionListener(accion);
        pclear.add(new JLabel());
        pclear.add(bclear);
        pclear.add(new JLabel());
        pletras.add(bclear);
        //add(pespacio,BorderLayout.SOUTH);
    }

}

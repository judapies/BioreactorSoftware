/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.graphs;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
// Clase de soporte para la UI de cada bioreactor
public class BioreactorUIState {
    public final DefaultTableModel tableModel;
    public final BioreactorChart chart;

    public BioreactorUIState(DefaultTableModel tableModel, BioreactorChart chart) {
        this.tableModel = tableModel;
        this.chart = chart;
    }
}

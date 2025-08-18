/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.Serializable;

/**
 *
 * @author judapies
 */
public class AlphaFilter implements Serializable{
    private static final long serialVersionUID = 1L;
    private double alpha;
    private double lastOutput;
    private boolean initialized;

    public AlphaFilter(double alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("Alpha debe estar entre 0 y 1");
        }
        this.alpha = alpha;
        this.initialized = false;
    }

    public double filter(double input) {
        if (!initialized) {
            lastOutput = input;  // Inicializar con el primer valor recibido
            initialized = true;
        } else {
            lastOutput = alpha * input + (1 - alpha) * lastOutput;
        }
        return lastOutput;
    }

    public void reset() {
        initialized = false;
    }

    public void setAlpha(double alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("Alpha debe estar entre 0 y 1");
        }
        this.alpha = alpha;
    }

    public double getLastOutput() {
        return lastOutput;
    }
}

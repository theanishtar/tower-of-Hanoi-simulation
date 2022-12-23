/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.pile;

import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class Pile {
    int index;
    JPanel pn;

    public Pile(int index, JPanel pn) {
        this.index = index;
        this.pn = pn;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }
    
    
    
}

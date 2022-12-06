/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.raven.model.Model_Card;
import com.raven.utils.Counters;

/**
 *
 * @author garcia_d
 */
public class Form_Stats extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    Counters counters = new Counters();
    
    public Form_Stats() {
        initComponents();
        counters.allCounters();
        addCards();
    }
    
    /**
     * Afegim el model Card on visualitzem les dades que ens retornen els contadors
     */
    public void addCards() {
        card1.setData(new Model_Card("Usuaris", Integer.toString(counters.valorContadorUsers)));
        card2.setData(new Model_Card("Tècnics", Integer.toString(counters.valorContadorTecnics)));
        card3.setData(new Model_Card("Administradors", Integer.toString(counters.valorContadorAdmins)));
        card4.setData(new Model_Card("TOTAL USUARIS", Integer.toString(counters.valorContadorTotalUsers)));
        card5.setData(new Model_Card("DEPARTAMENTS", Integer.toString(counters.valorContadorDept)));
        card6.setData(new Model_Card("ROLS", Integer.toString(counters.valorContadorRole)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        card4 = new com.raven.component.Card();
        panel1 = new javax.swing.JLayeredPane();
        card5 = new com.raven.component.Card();
        card6 = new com.raven.component.Card();

        setBackground(new java.awt.Color(51, 51, 51));

        panel.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        card1.setColor1(new java.awt.Color(211, 184, 61));
        card1.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(211, 184, 61));
        card2.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(211, 184, 61));
        card3.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card3);

        card4.setColor1(new java.awt.Color(211, 184, 61));
        card4.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card4);

        panel1.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        card5.setColor1(new java.awt.Color(211, 184, 61));
        card5.setColor2(new java.awt.Color(73, 181, 172));
        panel1.add(card5);

        card6.setColor1(new java.awt.Color(211, 184, 61));
        card6.setColor2(new java.awt.Color(73, 181, 172));
        panel1.add(card6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(530, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.raven.component.Card card1;
    public com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private com.raven.component.Card card4;
    private com.raven.component.Card card5;
    private com.raven.component.Card card6;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    // End of variables declaration//GEN-END:variables
}

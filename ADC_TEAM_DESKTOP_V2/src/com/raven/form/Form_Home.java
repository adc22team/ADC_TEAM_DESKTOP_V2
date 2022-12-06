package com.raven.form;

import com.raven.model.Model_Card;
import com.raven.utils.Counters;

/**
 * 
 * @author garcia_d
 */
public class Form_Home extends javax.swing.JPanel {

    Counters counters = new Counters();

    public Form_Home() {
        initComponents();
        counters.allCounters();
        addCards();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();

        setBackground(new java.awt.Color(51, 51, 51));

        panel.setLayout(new java.awt.GridLayout(1, 0, 75, 0));

        card1.setColor1(new java.awt.Color(211, 184, 61));
        card1.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(211, 184, 61));
        card2.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(211, 184, 61));
        card3.setColor2(new java.awt.Color(73, 181, 172));
        panel.add(card3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Afegim el model Card on visualitzem les dades que ens retornen els contadors
     */
    public void addCards() {
        card1.setData(new Model_Card("USUARIS", Integer.toString(counters.valorContadorTotalUsers)));
        card2.setData(new Model_Card("DEPARTAMENTS", Integer.toString(counters.valorContadorDept)));
        card3.setData(new Model_Card("ROLS", Integer.toString(counters.valorContadorRole)));
//        card4.setData(new Model_Card("USUARIS", Integer.toString(counters.valorContadorTotalUsers)));
//        card5.setData(new Model_Card("DEPARTAMENTS", Integer.toString(counters.valorContadorDept)));
//        card6.setData(new Model_Card("ROLS", Integer.toString(counters.valorContadorRole)));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.raven.component.Card card1;
    public com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}

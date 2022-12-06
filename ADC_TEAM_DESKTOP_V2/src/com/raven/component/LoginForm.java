/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.component;

import com.raven.main.Login;
import com.raven.utils.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.SwingUtilities;

/**
 *
 * @author garcia_d
 */
public class LoginForm extends javax.swing.JPanel {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonClose = new javax.swing.JButton();
        loginTextIniciarFosc = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelUserIcon = new javax.swing.JLabel();
        jTextFieldUserFosc = new javax.swing.JTextField();
        jSeparatorUserFosc = new javax.swing.JSeparator();
        jLabelPassword = new javax.swing.JLabel();
        jLabelPasswordIconFosc = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jSeparatorPassword = new javax.swing.JSeparator();
        jButtonLogin = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));

        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/close.png"))); // NOI18N
        jButtonClose.setBorder(null);
        jButtonClose.setContentAreaFilled(false);
        jButtonClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClose.setFocusPainted(false);
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        loginTextIniciarFosc.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        loginTextIniciarFosc.setForeground(new java.awt.Color(204, 204, 204));
        loginTextIniciarFosc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginTextIniciarFosc.setText("Iniciar Sessió");

        jLabelUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(204, 204, 204));
        jLabelUser.setText("USUARI");

        jLabelUserIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/user.png"))); // NOI18N

        jTextFieldUserFosc.setBackground(new java.awt.Color(51, 51, 51));
        jTextFieldUserFosc.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jTextFieldUserFosc.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldUserFosc.setBorder(null);

        jSeparatorUserFosc.setBackground(new java.awt.Color(73, 181, 172));
        jSeparatorUserFosc.setForeground(new java.awt.Color(73, 181, 172));

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(204, 204, 204));
        jLabelPassword.setText("CONTRASENYA");

        jLabelPasswordIconFosc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPasswordIconFosc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/lock.png"))); // NOI18N

        jPasswordField.setBackground(new java.awt.Color(51, 51, 51));
        jPasswordField.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jPasswordField.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField.setBorder(null);
        jPasswordField.setEchoChar('\u25cf');

        jSeparatorPassword.setBackground(new java.awt.Color(73, 181, 172));
        jSeparatorPassword.setForeground(new java.awt.Color(73, 181, 172));

        jButtonLogin.setBackground(new java.awt.Color(51, 51, 51));
        jButtonLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/login.png"))); // NOI18N
        jButtonLogin.setBorder(null);
        jButtonLogin.setContentAreaFilled(false);
        jButtonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogin.setFocusPainted(false);
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonClose))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButtonLogin)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparatorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparatorUserFosc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUserIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldUserFosc, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginTextIniciarFosc)
                    .addComponent(jLabelPassword)
                    .addComponent(jLabelUser)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPasswordIconFosc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonClose)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginTextIniciarFosc, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabelUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldUserFosc)
                            .addComponent(jLabelUserIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparatorUserFosc, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabelPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPasswordIconFosc))
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jButtonLogin)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String user = jTextFieldUserFosc.getText().toString();
        String password = jPasswordField.getText().toString();

        Connection connection = new Connection();
        connection.setUser(user);
        connection.setPassword(password);        
        connection.login();
        
        Login login = (Login) SwingUtilities.getWindowAncestor(this);
        login.dispose();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#333333"), 0, getHeight(), Color.decode("#333333"));
        g2.setPaint(g);
        g2.fillRoundRect(15, 15, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPasswordIconFosc;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUserIcon;
    public static javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparatorPassword;
    private javax.swing.JSeparator jSeparatorUserFosc;
    public static javax.swing.JTextField jTextFieldUserFosc;
    private javax.swing.JLabel loginTextIniciarFosc;
    // End of variables declaration//GEN-END:variables

}

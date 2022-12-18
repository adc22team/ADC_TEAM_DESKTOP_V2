/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.raven.component.Header;
import com.raven.event.EventMenuSelected;
import com.raven.form.*;
import com.raven.utils.DeptCrud;
import com.raven.utils.RoleCrud;
import com.raven.utils.Tickets;
import com.raven.utils.UsersCrud;
import java.awt.Color;
import javax.swing.JComponent;

/**
 *
 * @author garcia_d
 */
public class MainDesktop extends javax.swing.JFrame {

    /**
     * Creates new form MainDesktop
     */
    private int id_conn;
    private String usuari;
    private String pwd;
    private int rol;
    
    public int getId_conn() {
        return id_conn;
    }

    public void setId_conn(int id_conn) {
        this.id_conn = id_conn;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Header getHeader2() {
        return header;
    }

    public void setHeader2(Header header2) {
        this.header = header2;
    }

    private Form_Home home;
    private Form_Tiquets tiquets;
    private Form_Stats estadistiques;
    private Form_UM usersM;
    private Form_DM deptM;
    private Form_RM rolM;
    private Form_Permissions_Denied denied;
    private Form_Message missatges;
    private Form_Settings settings;
    private Form_Exit exit;

    UsersCrud uc = new UsersCrud();
    DeptCrud dc = new DeptCrud();
    RoleCrud rc = new RoleCrud();
    Tickets tiq = new Tickets();

    /**
     * 
     */
    public MainDesktop() {

        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        home = new Form_Home();
        tiquets = new Form_Tiquets();
        estadistiques = new Form_Stats();
        usersM = new Form_UM();
        deptM = new Form_DM();
        rolM = new Form_RM();
        denied = new Form_Permissions_Denied();
        missatges = new Form_Message();
        settings = new Form_Settings();
        exit = new Form_Exit();

        menu.initMoving(MainDesktop.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            /**
             * Selecció de l'opció de menu
             */
            public void selected(int index) {
                if (index == 0) {
                    setForm(home);
                } else if (index == 1) {
                    setForm(tiquets);
                    tiq.listTickets(tiquets.table);
                } else if (index == 2) {
                    setForm(estadistiques);
                } else if (index == 3) {
                    if (rol == 1) {
                        setForm(usersM);
                        uc.setId_conn(id_conn);
                        uc.listUsers(usersM.table);
                        //uc.listUsersAdmin(usersM.table);
                    } else {
                        setForm(denied);
                    }
                } else if (index == 4) {
                    if (rol == 1) {
                        setForm(deptM);
                        dc.setId_conn(id_conn);
                        dc.listDept(deptM.table);
                    } else {
                        setForm(denied);
                    }
                } else if (index == 5) {
                    if (rol == 1) {
                        setForm(rolM);
                        rc.setId_conn(id_conn);
                        rc.listRole(rolM.table);
                    } else {
                        setForm(denied);
                    }
                } else if (index == 7) {
                    setForm(missatges);
                } else if (index == 8) {
                    setForm(settings);
                } else if (index == 9) {
                    setForm(exit);
                    exit.setId_conn(id_conn);
                    exit.setUser(usuari);
                }

            }

            private Object countUsersRole() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //  set when system open start with home form
        setForm(new Form_Home());
    }

    /**
     * 
     * @param com 
     */
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        menu = new com.raven.component.Menu();
        header = new com.raven.component.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setForeground(new java.awt.Color(51, 51, 51));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDesktop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.raven.component.Header header;
    public javax.swing.JPanel mainPanel;
    private com.raven.component.Menu menu;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.formadd;

import com.raven.utils.UsersCrud;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author aigardo
 */
public class Form_Add_User extends javax.swing.JPanel {

    /**
     * Creates new form Form_Add_Department
     */
    
    public Form_Add_User() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonBack = new javax.swing.JButton();
        jLabelTittle = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelPasswd = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelLastname = new javax.swing.JLabel();
        jTextFieldLastname = new javax.swing.JTextField();
        jLabelDept = new javax.swing.JLabel();
        jComboBoxDept = new javax.swing.JComboBox<>();
        jLabelRole = new javax.swing.JLabel();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jLabelState = new javax.swing.JLabel();
        jComboBoxState = new javax.swing.JComboBox<>();
        jButtonClear = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();

        setBackground(new java.awt.Color(55, 55, 55));

        jButtonBack.setBackground(new java.awt.Color(55, 55, 55));
        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/back.png"))); // NOI18N
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabelTittle.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabelTittle.setForeground(new java.awt.Color(73, 181, 172));
        jLabelTittle.setText("Afegir Usuari");

        jLabelUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(73, 181, 172));
        jLabelUser.setText("Usuari");

        jTextFieldUser.setBackground(new java.awt.Color(51, 51, 51));
        jTextFieldUser.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldUser.setBorder(null);
        jTextFieldUser.setMaximumSize(new java.awt.Dimension(190, 24));
        jTextFieldUser.setMinimumSize(new java.awt.Dimension(190, 24));
        jTextFieldUser.setPreferredSize(new java.awt.Dimension(190, 24));

        jLabelPasswd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPasswd.setForeground(new java.awt.Color(73, 181, 172));
        jLabelPasswd.setText("Contrasenya");

        jPasswordField.setBackground(new java.awt.Color(51, 51, 51));
        jPasswordField.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField.setBorder(null);
        jPasswordField.setEchoChar('\u25cf');

        jLabelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(73, 181, 172));
        jLabelName.setText("Nom");

        jTextFieldName.setBackground(new java.awt.Color(51, 51, 51));
        jTextFieldName.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldName.setBorder(null);
        jTextFieldName.setMaximumSize(new java.awt.Dimension(190, 24));
        jTextFieldName.setMinimumSize(new java.awt.Dimension(190, 24));
        jTextFieldName.setPreferredSize(new java.awt.Dimension(190, 24));

        jLabelLastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelLastname.setForeground(new java.awt.Color(73, 181, 172));
        jLabelLastname.setText("Cognom");

        jTextFieldLastname.setBackground(new java.awt.Color(51, 51, 51));
        jTextFieldLastname.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldLastname.setBorder(null);
        jTextFieldLastname.setMaximumSize(new java.awt.Dimension(190, 24));
        jTextFieldLastname.setMinimumSize(new java.awt.Dimension(190, 24));
        jTextFieldLastname.setPreferredSize(new java.awt.Dimension(190, 24));

        jLabelDept.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDept.setForeground(new java.awt.Color(73, 181, 172));
        jLabelDept.setText("Departament");

        jComboBoxDept.setBackground(new java.awt.Color(51, 51, 51));
        jComboBoxDept.setForeground(new java.awt.Color(204, 204, 204));
        jComboBoxDept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jComboBoxDept.setBorder(null);
        jComboBoxDept.setMaximumSize(new java.awt.Dimension(190, 24));
        jComboBoxDept.setMinimumSize(new java.awt.Dimension(190, 24));
        jComboBoxDept.setPreferredSize(new java.awt.Dimension(190, 24));

        jLabelRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(73, 181, 172));
        jLabelRole.setText("Rol");

        jComboBoxRole.setBackground(new java.awt.Color(51, 51, 51));
        jComboBoxRole.setForeground(new java.awt.Color(204, 204, 204));
        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        jComboBoxRole.setMaximumSize(new java.awt.Dimension(190, 24));
        jComboBoxRole.setMinimumSize(new java.awt.Dimension(190, 24));
        jComboBoxRole.setPreferredSize(new java.awt.Dimension(190, 24));

        jLabelState.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelState.setForeground(new java.awt.Color(73, 181, 172));
        jLabelState.setText("Estat");

        jComboBoxState.setBackground(new java.awt.Color(51, 51, 51));
        jComboBoxState.setForeground(new java.awt.Color(204, 204, 204));
        jComboBoxState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        jComboBoxState.setMaximumSize(new java.awt.Dimension(190, 24));
        jComboBoxState.setMinimumSize(new java.awt.Dimension(190, 24));
        jComboBoxState.setPreferredSize(new java.awt.Dimension(190, 24));

        jButtonClear.setBackground(new java.awt.Color(55, 55, 55));
        jButtonClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/clean.png"))); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonAdd.setBackground(new java.awt.Color(55, 55, 55));
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/adduser.png"))); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonEdit.setBackground(new java.awt.Color(55, 55, 55));
        jButtonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTittle)
                .addGap(351, 351, 351))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPasswd)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLastname, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldLastname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRole)
                    .addComponent(jLabelDept)
                    .addComponent(jLabelState))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxDept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonBack)
                    .addComponent(jLabelTittle))
                .addGap(152, 152, 152)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelUser)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPasswd)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelName)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDept))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelRole)
                            .addComponent(jComboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelState)
                            .addComponent(jComboBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastname)
                    .addComponent(jTextFieldLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonClear, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jButtonEdit))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        Add_Frame add_Frame = (Add_Frame) SwingUtilities.getWindowAncestor(this);
        add_Frame.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        cleanFormAdd();
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        
        String user = jTextFieldUser.getText().toString();
        String password = jPasswordField.getText().toString();
        String name = jTextFieldName.getText().toString();
        String lastname = jTextFieldLastname.getText().toString();
        String department = jComboBoxDept.getSelectedItem().toString();
        String role = jComboBoxRole.getSelectedItem().toString();
        String state = jComboBoxState.getSelectedItem().toString();
        
        //Creo instancia de la clase UsersCrud
        UsersCrud uc = new UsersCrud();
        
        uc.setUser(user);
        uc.setPassword(password);
        uc.setName(name);
        uc.setLastname(lastname);
        uc.setDepartment(department);
        uc.setRole(role);
        uc.setState(state);
        
        if (uc.addUsers()) {
            JOptionPane.showMessageDialog(this, name + " " + lastname + " ha sigut donat d'alta amb el nom d'usuari " + user);
        }
        
        cleanFormAdd();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        String user_edit = jTextFieldUser.getText().toString();
        String password_edit = jPasswordField.getText().toString();
        String name_edit = jTextFieldName.getText().toString();
        String lastname_edit = jTextFieldLastname.getText().toString();
        String department_edit = jComboBoxDept.getSelectedItem().toString();
        String role_edit = jComboBoxRole.getSelectedItem().toString();
        String state_edit = jComboBoxState.getSelectedItem().toString();
        
        //Creo instancia de la clase UsersCrud
        UsersCrud uc = new UsersCrud();
        
        uc.setUser(user_edit);
        uc.setPassword(password_edit);
        uc.setName(name_edit);
        uc.setLastname(lastname_edit);
        uc.setDepartment(department_edit);
        uc.setRole(role_edit);
        uc.setState(state_edit);
        
        if (uc.modifyUsers()) {
            JOptionPane.showMessageDialog(this, "S'ha actualitzat l'usuari " + user_edit);
        }
        
        Add_Frame form_Add = (Add_Frame) SwingUtilities.getWindowAncestor(this);
        form_Add.dispose();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void cleanFormAdd() {
        jTextFieldUser.setText(null);
        jPasswordField.setText(null);
        jTextFieldName.setText(null);
        jTextFieldLastname.setText(null);
        jComboBoxDept.setSelectedIndex(0);
        jComboBoxRole.setSelectedIndex(0);
        jComboBoxState.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonClear;
    public static javax.swing.JButton jButtonEdit;
    public static javax.swing.JComboBox<String> jComboBoxDept;
    public static javax.swing.JComboBox<String> jComboBoxRole;
    public static javax.swing.JComboBox<String> jComboBoxState;
    private javax.swing.JLabel jLabelDept;
    private javax.swing.JLabel jLabelLastname;
    private javax.swing.JLabel jLabelName;
    public static javax.swing.JLabel jLabelPasswd;
    private javax.swing.JLabel jLabelRole;
    public static javax.swing.JLabel jLabelState;
    private javax.swing.JLabel jLabelTittle;
    private javax.swing.JLabel jLabelUser;
    public static javax.swing.JPasswordField jPasswordField;
    public static javax.swing.JTextField jTextFieldLastname;
    public static javax.swing.JTextField jTextFieldName;
    public static javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}

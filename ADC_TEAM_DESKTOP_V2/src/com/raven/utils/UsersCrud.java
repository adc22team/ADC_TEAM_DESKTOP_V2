/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.form.Form_UM;
import com.raven.formadd.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author garcia_d
 */
public class UsersCrud {

    private int id_conn;
    
    private String user;
    private String password;
    private String name;
    private String lastname;
    private String department;
    private String role;
    private String state;

    public int getId_conn() {
        return id_conn;
    }

    public void setId_conn(int id_conn) {
        this.id_conn = id_conn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * Métode per llistar tots els usuaris
     * @param tableListUsers 
     */
    public void listUsers(JTable tableListUsers) {

        DefaultTableModel model = new DefaultTableModel();
        id_conn =  UsuariConectat.id;

        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            //Executem la crida per llistar usuaris
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_GRID,2,usuari", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Usuari", "Nom", "Cognoms", "Departament", "Rol"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][6];

            model.setColumnIdentifiers(nomColumnes);

            for (int i = 0; i < registres_trobats; i++) {

                String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                camps = registre.split(",");

                for (int j = 0; j < 0; j++) {
                    registresGrid[i][j] = camps[j];
                }

                model.addRow(camps);
            }

            tableListUsers.setModel(model);

        } catch (Exception e) {
        }

    }

    /**
     * Métode per llistar tots els usuaris amb rol Administrador
     * @param tableListUsers 
     */
    public void listUsersAdmin(JTable tableListUsers) {

        DefaultTableModel model = new DefaultTableModel();
        id_conn =  UsuariConectat.id;

        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            //Executem la crida per llistar usuaris
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_GRID,3,rol = 'Administrador',usuari", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Usuari", "Nom", "Cognoms", "Departament", "Rol"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][6];

            model.setColumnIdentifiers(nomColumnes);

            for (int i = 0; i < registres_trobats; i++) {

                String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                camps = registre.split(",");

                for (int j = 0; j < 0; j++) {
                    registresGrid[i][j] = camps[j];
                }

                model.addRow(camps);
            }

            tableListUsers.setModel(model);

        } catch (Exception e) {
        }
        
    }

    /**
     * Métode per llistar tots els usuaris amb rol Técnic
     * @param tableListUsers 
     */
    public void listUsersTecnics(JTable tableListUsers) {
        
        DefaultTableModel model = new DefaultTableModel();
        id_conn =  UsuariConectat.id;

        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            //Executem la crida per llistar usuaris
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_GRID,3,rol = 'Tècnic',usuari", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Usuari", "Nom", "Cognoms", "Departament", "Rol"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][6];

            model.setColumnIdentifiers(nomColumnes);

            for (int i = 0; i < registres_trobats; i++) {

                String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                camps = registre.split(",");

                for (int j = 0; j < 0; j++) {
                    registresGrid[i][j] = camps[j];
                }

                model.addRow(camps);
            }

            tableListUsers.setModel(model);

        } catch (Exception e) {
        }

    }

    /**
     * Métode per llistar tots els usuaris amb rol Usuari
     * @param tableListUsers 
     */
    public void listUsersUsuaris(JTable tableListUsers) {

        DefaultTableModel model = new DefaultTableModel();
        id_conn =  UsuariConectat.id;

        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            //Executem la crida per llistar usuaris
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_GRID,3,rol = 'Usuari',usuari", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Usuari", "Nom", "Cognoms", "Departament", "Rol"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][6];

            model.setColumnIdentifiers(nomColumnes);

            for (int i = 0; i < registres_trobats; i++) {

                String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                camps = registre.split(",");

                for (int j = 0; j < 0; j++) {
                    registresGrid[i][j] = camps[j];
                }

                model.addRow(camps);
            }

            tableListUsers.setModel(model);

        } catch (Exception e) {
        }
        
    }

    /**
     * Métode que escolleix que llistar segons l'opció del jComboBox a Form_UM
     * @param tableListUsers 
     */
    public void filtraryRefrescar(JTable tableListUsers) {

        int rolUsuari = com.raven.form.Form_UM.jComboBoxUsers.getSelectedIndex();
        
        switch (rolUsuari) {
            //Administradors
            case 1:
                if (rolUsuari == 1) {
                    listUsersAdmin(Form_UM.table);
                }
                com.raven.form.Form_UM.jComboBoxUsers.setSelectedIndex(1);
                break;
            //Tècnics
            case 2:
                if (rolUsuari == 2) {
                    listUsersTecnics(Form_UM.table);
                }
                com.raven.form.Form_UM.jComboBoxUsers.setSelectedIndex(2);
                break;
            //Usuaris
            case 3:
                if (rolUsuari == 3) {
                    listUsersUsuaris(Form_UM.table);
                }
                com.raven.form.Form_UM.jComboBoxUsers.setSelectedIndex(3);
                break;
            default:
                listUsers(Form_UM.table);
                com.raven.form.Form_UM.jComboBoxUsers.setSelectedIndex(0);
        }
        
    }

    /**
     * Métode per eliminar usuari
     */
    public void deleteUsers() {
        id_conn =  UsuariConectat.id;
        
        int fila = Form_UM.table.getSelectedRow();
        String valor = Form_UM.table.getValueAt(fila, 0).toString();
        
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            //Executem la crida per eliminar usuaris
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_DELETE," + valor, shared_secret.toByteArray()));
            
            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_eliminar == 1) {
                filtraryRefrescar(Form_UM.table);
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public boolean addUsers() {        
        id_conn =  UsuariConectat.id;
        boolean resultat = false;
        
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            
            //Executem la crida per donar d’alta un nou usuari
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_NEW," + user + "," + password + "," + name + "," + lastname + "," + department + "," + role + "," + state, shared_secret.toByteArray()));
            
            int resposta_alta = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_alta == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }
    
    public boolean modifyUsers() {
        id_conn =  UsuariConectat.id;
        boolean resultat = false;
        
        int fila = Form_UM.table.getSelectedRow();
        int id_user = Integer.parseInt(Form_UM.table.getValueAt(fila, 0).toString());
        
        Form_Add_User.jTextFieldUser.setText(Form_UM.table.getValueAt(fila, 1).toString());
        //Form_Add_User.jPasswordField.setText(Form_UM.table.getValueAt(fila, 2).toString());
        Form_Add_User.jTextFieldName.setText(Form_UM.table.getValueAt(fila, 2).toString());
        Form_Add_User.jTextFieldLastname.setText(Form_UM.table.getValueAt(fila, 3).toString());
        Form_Add_User.jComboBoxDept.setSelectedItem(Form_UM.table.getValueAt(fila, 4).toString());
        Form_Add_User.jComboBoxRole.setSelectedItem(Form_UM.table.getValueAt(fila, 5).toString());
        //Form_Add_User.jComboBoxState.setSelectedItem(Form_UM.table.getValueAt(fila, 7).toString());
        
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            //Cálcul clau pública client
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            
            //Executem la crida per donar d’alta un nou usuari
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_MODIFI,"+ id_user + "," + user + "," + password + "," + name + "," + lastname + "," + department + "," + role + "," + state, shared_secret.toByteArray()));
            
            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_modif == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }

}

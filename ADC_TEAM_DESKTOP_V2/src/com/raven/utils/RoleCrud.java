/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.form.Form_RM;
import com.raven.formadd.Add_Frame;
import com.raven.formadd.Form_Add_Role;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author garcia_d
 */
public class RoleCrud {
    
    private int id_conn;
    
    private String role;
    private String desc;

    public int getId_conn() {
        return id_conn;
    }

    public void setId_conn(int id_conn) {
        this.id_conn = id_conn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    /**
     * Métode per llistar tots els Rols
     * @param tableListUsers 
     */
    public void listRole(JTable tableListUsers) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_QUERY,2,rol", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Rol", "Descripcio"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][3];

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
    
    public void refrescarRole (JTable tableListUsers){
        listRole(Form_RM.table);
    }
    
    public void deleteRole() {
        id_conn =  UsuariConectat.id;
        
        int fila = Form_RM.table.getSelectedRow();
        String valor = Form_RM.table.getValueAt(fila, 0).toString();
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_DELETE," + valor, shared_secret.toByteArray()));
            
            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_eliminar == 1) {
                refrescarRole(Form_RM.table);
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public boolean addRole() {
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
            //out.writeUTF(id_conn + ",USER_NEW," + role + "," + desc);
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_NEW," + role + "," + desc, shared_secret.toByteArray()));
            
            int resposta_alta = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_alta == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }
    
    public boolean modifyRole() {
        id_conn =  UsuariConectat.id;
        boolean resultat = false;
        
        int fila = Form_RM.table.getSelectedRow();
        int id_rol = Integer.parseInt(Form_RM.table.getValueAt(fila, 0).toString());
        
        Form_Add_Role.jTextFieldRole.setText(Form_RM.table.getValueAt(fila, 1).toString());
        Form_Add_Role.jTextFieldDesc.setText(Form_RM.table.getValueAt(fila, 2).toString());
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_MODIFI,"+ id_rol + "," + role + "," + desc, shared_secret.toByteArray()));
            
            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_modif == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }
    
}

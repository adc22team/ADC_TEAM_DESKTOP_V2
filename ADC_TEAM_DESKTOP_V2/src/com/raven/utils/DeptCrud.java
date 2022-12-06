/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.form.Form_DM;
import com.raven.formadd.*;
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
public class DeptCrud {
    
    private int id_conn;
    
    private String dept;
    private String phone;
    private String desc;

    public int getId_conn() {
        return id_conn;
    }

    public void setId_conn(int id_conn) {
        this.id_conn = id_conn;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    /**
     * Métode per llistar tots els Departaments
     * @param tableListUsers 
     */
    public void listDept(JTable tableListUsers) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_QUERY,2,departament", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            String[] nomColumnes = {"ID", "Departament", "Descripcio", "Telefon"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][4];

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
    
    public void refrescarDept (JTable tableListUsers){
        listDept(tableListUsers);
    }
    
    public void deleteDept() {
        id_conn =  UsuariConectat.id;
        
        int fila = Form_DM.table.getSelectedRow();
        String valor = Form_DM.table.getValueAt(fila, 0).toString();
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_DELETE," + valor, shared_secret.toByteArray()));
            
            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_eliminar == 1) {
                refrescarDept(Form_DM.table);
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public boolean addDept() {
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
            //out.writeUTF(id_conn + ",USER_NEW," + dept + "," + desc + "," + phone);
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_NEW," + dept + "," + desc + "," + phone, shared_secret.toByteArray()));
            
            int resposta_alta = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_alta == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }
    
    public boolean modifyDept() {
        id_conn =  UsuariConectat.id;
        boolean resultat = false;
        
        int fila = Form_DM.table.getSelectedRow();
        int id_dept = Integer.parseInt(Form_DM.table.getValueAt(fila, 0).toString());
        
        Form_Add_Department_1.jTextFieldDept.setText(Form_DM.table.getValueAt(fila, 1).toString());
        Form_Add_Department_1.jTextFieldDesc.setText(Form_DM.table.getValueAt(fila, 2).toString());
        Form_Add_Department_1.jTextFieldPhone.setText(Form_DM.table.getValueAt(fila, 3).toString());
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_MODIFI,"+ id_dept + "," + dept + "," + desc + "," + phone, shared_secret.toByteArray()));
            
            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_modif == 1) {
                resultat = true;
            }
            
        } catch (Exception e) {
        }
        
        return resultat;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.component.Card;
import com.raven.form.Form_Home;
import com.raven.main.MainDesktop;
import com.raven.model.Model_Card;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;

/**
 *
 * @author garcia_d
 */
public class Counters {

    UsuariConectat usuariConectat = new UsuariConectat();

    private int id_conn;
    public static int valorContadorAdmins;
    public static int valorContadorTecnics;
    public static int valorContadorUsers;
    public static int valorContadorTotalUsers;
    public static int valorContadorDept;
    public static int valorContadorRole;
    

    /**
     * Executa tots els métodes contadors d'aquesta classe
     */
    public void allCounters() {

        id_conn = UsuariConectat.id;

        countUsersRole();
        countAdminsRole();
        countTecnicsRole();
        countUsers();
        countDept();
        countRole();
    }

    /**
     * Métode que rep la quantitat d'usuaris amb rol Usuari
     */
    public void countUsersRole() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_COUNT,1,rol = 3", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorUsers = registres_trobats;

        } catch (Exception e) {
        }

    }

    /**
     * Métode que rep la quantitat d'usuaris amb rol Tècnic
     */
    public void countTecnicsRole() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_COUNT,1,rol = 2", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorTecnics = registres_trobats;

        } catch (Exception e) {
        }

        //return String.valueOf(valorContadorTecnics);
    }

    /**
     * Métode que rep la quantitat d'usuaris amb rol Administrador
     */
    public void countAdminsRole() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_COUNT,1,rol = 1", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorAdmins = registres_trobats;

        } catch (Exception e) {
        }

    }

    /**
     * Métode que rep la quantitat d'usuaris
     */
    public void countUsers() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_COUNT,0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorTotalUsers = registres_trobats;

        } catch (Exception e) {
        }

    }
    
    /**
     * Numero de departaments que hi ha a la base de dades
     */
    public void countDept() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_QUERY_COUNT,0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorDept = registres_trobats;

        } catch (Exception e) {
        }

    }
    
    /**
     * Numero de rols que hi ha a la base de dades
     */
    public void countRole() {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_QUERY_COUNT,0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            valorContadorRole = registres_trobats;

        } catch (Exception e) {
        }

    }

}

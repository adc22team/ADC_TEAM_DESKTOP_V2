/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.main.Login;
import com.raven.main.MainDesktop;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author garcia_d
 */
public class Connection {

    Login loginWindow = new Login();

    private String user;
    private String password;
    private int id_conn;
    private int rol;

    public Connection(String user, String password, int id_conn, int rol) {
        this.user = user;
        this.password = password;
        this.id_conn = id_conn;
        this.rol = rol;
    }

    public Connection() {
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

    public int getId_conn() {
        return id_conn;
    }

    public void setId_conn(int id_conn) {
        this.id_conn = id_conn;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    /**
     * Métode de Login
     */
    public void login() {
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
            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF(SystemUtils.encryptedText("0" + ",LOGIN," + getUser() + "," + getPassword(), shared_secret.toByteArray()));

            int resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            id_conn = resposta_svr_id;

            if (resposta_svr_id != 0) {
                int rol = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

                if (rol == 1) {
                    JOptionPane.showMessageDialog(loginWindow, "Benvingut/a " + user);
                    com.raven.utils.UsuariConectat.id = id_conn;
                    MainDesktop window = new MainDesktop();
                    window.header.jLabelUserConnected.setText(getUser() + " - #" + resposta_svr_id + " - Administrador");
                    window.setId_conn(id_conn);
                    window.setUsuari(user);
                    window.setRol(rol);
                    window.setVisible(true);

                } else if (rol == 2) {
                    JOptionPane.showMessageDialog(loginWindow, "Benvingut/a " + user);
                    com.raven.utils.UsuariConectat.id = id_conn;
                    MainDesktop window = new MainDesktop();
                    window.header.jLabelUserConnected.setText(getUser() + " - #" + resposta_svr_id + " - Tècnic");
                    window.setId_conn(id_conn);
                    window.setUsuari(user);
                    window.setRol(rol);
                    window.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginWindow, "Aquest usuari no te credencials per aquest aplicatiu. Inicia sessió amb el client mòbil.");
                    logout(id_conn);
                }
            }

        } catch (IOException ex) {
        }

    }

    /**
     * Métode de Logout
     * @param idconn 
     */
    public void logout(int idconn) {
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
            //Executo la consulta de la crida per sortir
            out.writeUTF(SystemUtils.encryptedText(idconn + ",USER_EXIT", shared_secret.toByteArray()));

        } catch (IOException ex) {
        }
    }

}

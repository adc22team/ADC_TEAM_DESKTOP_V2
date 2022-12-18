/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.utils;

import com.raven.form.Form_Tiquets;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.text.Normalizer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aigardo
 */
public class Tickets {
    
    private int id_conn;
    
    public void listTickets(JTable tableListTickets) {
        
        DefaultTableModel model = new DefaultTableModel();
        id_conn = UsuariConectat.id;
        
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
            //Executem la crida per llistar tickets
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY,0",shared_secret.toByteArray()));
            
            // Llegir la resposta del servidor del nombre de registres trobats
            int registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            String[] nomColumnes = {"ID", "Incidencia", "Comentaris", "Estat", "ID Usuari", "ID Tecnic", "Data Incidencia", "Data Tancament", "Prioritat"};
            String[] camps;
            Object[][] registresGrid = new Object[registres_trobats][9];
            
            model.setColumnIdentifiers(nomColumnes);
            
            for (int i = 0; i < registres_trobats; i++) {

                String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                camps = registre.split(",");

                for (int j = 0; j < 0; j++) {
                    registresGrid[i][j] = camps[j];
                }

                model.addRow(camps);
            }
            
            tableListTickets.setModel(model);
            
        } catch (Exception e) {
        }
    }
    
    public void refrescarTickets (JTable tableListTickets) {
        listTickets(Form_Tiquets.table);
    }
    
}

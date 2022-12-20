/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Crides;

import com.raven.utils.SystemUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aigardo
 */
public class TestCridesContadors {
    
    private static int resposta_svr_id;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("#################### #######  C O N T A D O R S   ####### ################################");
        System.out.println("#################### #################################### ################################");
        System.out.println("");
        
        //Iniciem sessió amb un usuaria Administrador
        loginAdmin("aitor", "pwdaitor", 0);
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("--------------------------- TOTAL USUARIS ---------------------------");
        System.out.println("Total Usuaris: " + countUsers(resposta_svr_id));       
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("----------------------- USUARIS ADMINISTRADORS -----------------------");
        System.out.println("Administradors: " + countAdminsRole(resposta_svr_id));        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("-------------------------- USUARIS TECNICS --------------------------");
        System.out.println("Técnics: " + countTecnicsRole(resposta_svr_id));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("--------------------------- USUARIS USERS ---------------------------");
        System.out.println("Users: " + countUsersRole(resposta_svr_id));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("------------------------- TOTAL DEPARTAMENTS -------------------------");
        System.out.println("Departaments: " +countDept(resposta_svr_id));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //
        System.out.println("----------------------------- TOTAL ROLS -----------------------------");
        System.out.println("Rols: " + countRole(resposta_svr_id));        
        System.out.println("----------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("*********************************************************************");
        System.out.println("");
        
        //Tanquem la sessió del usuari
        logOut("aitor", resposta_svr_id);
        
    }
    
    /**
     * Fa l'inici de sessió d'un usuari amb rol d'administrador pasat per
     * parametre a la crida
     *
     * @param usuari
     * @param contrasenya
     * @param id
     * @throws InterruptedException
     */
    public static void loginAdmin(String usuari, String contrasenya, int id_conn) throws InterruptedException {
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            out.writeUTF(String.valueOf(claus_ps[0]));
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);

            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",LOGIN," + usuari + "," + contrasenya, shared_secret.toByteArray()));

            resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            if (resposta_svr_id != 0) {
                int rol = 1;
                System.out.println("Benvingut " + usuari);
                System.out.println(usuari + " - #" + resposta_svr_id + " - # rol: " + rol);
            }
            
        } catch (Exception ex) {
            System.out.println("No es pot establir connexió amb el servidor");
        }
    }
    
    public static void logOut(String usuari, int id_conn) {

        Socket sc;

        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            System.out.println("Fem logout del usuari " + usuari);

            String[] claus_ps = SystemUtils.clauPublicaClient().split(",");
            //Enviem la clau pública del client al servidor
            out.writeUTF(String.valueOf(claus_ps[0]));
            //llegim la clau pública del servidor
            BigInteger shared_secret = SystemUtils.calculClauCompartida(in.readUTF(), claus_ps[1]);
            
            //Executo la consulta de la crida per sortir
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_EXIT", shared_secret.toByteArray()));
            
            System.out.println("Logout realitzat");

        } catch (IOException ex) {
            Logger.getLogger(TestCridesConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int countUsers(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
    public static int countAdminsRole(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
    public static int countTecnicsRole(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
    public static int countUsersRole(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
    public static int countDept(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
    public static int countRole(int id_conn) {

        int registres_trobats = 0;
        
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
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
    }
    
}

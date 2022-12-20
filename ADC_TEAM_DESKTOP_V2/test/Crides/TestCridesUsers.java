package Crides;

import com.raven.utils.SystemUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aigardo
 */
public class TestCridesUsers {

    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("######################### S I M U L A C I O   D E  P R O V E S #####################################");
        System.out.println("#########################   C R U D    ----    U S U A R I S   #####################################");
        System.out.println("######################### #################################### #####################################");
        System.out.println("");

        //Iniciem sessió amb un usuaria Administrador
        loginAdmin("aitor", "pwdaitor", 0);
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una alta
        System.out.println("------------------------------- ALTA -------------------------------");
        addUser("marian", "pwdmarian", "Marian", "Fonseca", "1", "1", "1", resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem un llistat
        System.out.println("------------------------------ LLISTA ------------------------------");
        listUsers(resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una cerca de ID d'Usuari
        System.out.println("------------------------------- CERCA -------------------------------");
        buscarIdUsuari("marian", resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una modificació
        System.out.println("---------------------------- MODIFICACIO ----------------------------");
        modifyUsers(buscarIdUsuari("marian", resposta_svr_id), "marianEdit", "pwdmarian", "Marian2", "Fonseca Soto", "2", "2", "0", resposta_svr_id);
        listUsers(resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una baixa
        System.out.println("------------------------------- BAIXA -------------------------------");
        deleteUsers(buscarIdUsuari("marianEdit", resposta_svr_id), resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("********************************************************************");
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

    public static void addUser(String user, String password, String name, String lastname, String department, String role, String state, int id_conn) {

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
                System.out.println("----- USUARI CREAT -----");
                System.out.println("Usuari: " + user);
                System.out.println("Contrasenya: " + password);
                System.out.println("Nom: " + name);
                System.out.println("Cognom: " + lastname);
                System.out.println("Departament: " + department);
                System.out.println("Rol: " + role);
                System.out.println("Estat: " + state);
                System.out.println("------------------------");
            }

        } catch (Exception e) {
        }

    }
    
    public static int listUsers(int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_QUERY_GRID,2,usuari", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            System.out.println("El total d'usuaris és: " + registres_trobats);
            
            ArrayList registres = new ArrayList();            

            for (int i = 0; i < registres_trobats; i++) {

                //String registre = SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray());
                registres.add(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            }

            for (int i = 0; i < registres.size(); i++) {
                System.out.println(registres.get(i).toString());
            }

        } catch (Exception e) {
        }
        
        return registres_trobats;

    }

    public static int buscarIdUsuari(String user, int id_conn) {

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

            //Executem la crida per modificar l'usuari
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_FIND," + user, shared_secret.toByteArray()));

            int id_trobat = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            System.out.println("-------- ID USUARI --------");
            System.out.println("Usuari: " + user);
            System.out.println("ID: " + id_trobat);
            System.out.println("---------------------------");

            //Si troba usuari
            return id_trobat;

        } catch (Exception e) {
        }
        //Si no troba usuari
        return 0;

    }

    public static void modifyUsers(int id_user, String user, String password, String name, String lastname, String department, String role, String state, int id_conn) {

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

            //Executem la crida per modificar l'usuari
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_MODIFI," + id_user + "," + user + "," + password + "," + name + "," + lastname + "," + department + "," + role + "," + state, shared_secret.toByteArray()));

            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            if (resposta_modif == 1) {
                System.out.println("----- USUARI MODIFICAT -----");
                System.out.println("Usuari: " + user);
                System.out.println("Contrasenya: " + password);
                System.out.println("Nom: " + name);
                System.out.println("Cognom: " + lastname);
                System.out.println("Departament: " + department);
                System.out.println("Rol: " + role);
                System.out.println("Estat: " + state);
                System.out.println("----------------------------");
            }

        } catch (Exception e) {
        }

    }
    
    public static void deleteUsers(int id_user, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",USER_DELETE," + id_user, shared_secret.toByteArray()));
            
            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_eliminar == 1) {
                System.out.println("------ USUARI ELIMINAT ------");
                System.out.println("ID Usuari eliminat: " + id_user);
                System.out.println("-----------------------------");
            }
            
        } catch (Exception e) {
        }
        
    }
}

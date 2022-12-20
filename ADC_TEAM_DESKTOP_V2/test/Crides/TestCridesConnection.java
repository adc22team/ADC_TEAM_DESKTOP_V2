package Crides;

import com.raven.utils.SystemUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;

/**
 *
 * @author garci
 */
public class TestCridesConnection {

    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("");

        //Simulem login usuari rol Administrador i posterior Logout del servidor
        System.out.println("------------------------- ROL ADMINISTRADOR -------------------------");
        loginAdmin("aitor", "pwdaitor", 0);
        System.out.println("---------Valor ID Connexio: " + resposta_svr_id);
        logOut("aitor", resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login usuari rol Tècnic i posterior Logout del servidor
        System.out.println("---------------------------- ROL  TÈCNIC ----------------------------");
        loginTecnic("manel", "pwdmanel", 0);
        logOut("manel",resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login usuari rol Usuari i posterior Logout del servidor
        System.out.println("---------------------------- ROL  USUARI ----------------------------");
        loginUsuari("martina", "pwdmartina", 0);
        logOut("martina",resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login incorrecte
        System.out.println("------------------------- USUARI INCORRECTE -------------------------");
        loginIncorrecte("usuariincorrecte", "pwdincorrecte", 0);
        System.out.println("---------------------------------------------------------------------");
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
            
            System.out.println(id_conn + ",LOGIN," + usuari + "," + contrasenya);

            resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            System.out.println("resposta servidor: " + resposta_svr_id);

            if (resposta_svr_id != 0) {
                int rol = 1;
                System.out.println("Benvingut " + usuari);
                System.out.println("Obrim client d'administrador");
                System.out.println(usuari + " - #" + resposta_svr_id + " - # rol: " + rol);
                System.out.println("Test rol Admin COMPLETAT");
            }

        } catch (Exception ex) {
            System.out.println("No es pot establir connexió amb el servidor");
        }
    }

    /**
     * Fa l'inici de sessió d'un usuari amb rol de tècnic pasat per parametre a
     * la crida
     *
     * @param usuari
     * @param contrasenya
     * @param id
     * @throws InterruptedException
     */
    public static void loginTecnic(String usuari, String contrasenya, int id_conn) throws InterruptedException {
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
            
            System.out.println(id_conn + ",LOGIN," + usuari + "," + contrasenya);

            resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            System.out.println("resposta servidor: " + resposta_svr_id);

            if (resposta_svr_id != 0) {
                int rol = 2;
                System.out.println("Benvingut " + usuari);
                System.out.println("Obrim client tècnic");
                System.out.println(usuari + " - #" + resposta_svr_id + " - # rol: " + rol);
                System.out.println("Test rol Tècnic COMPLETAT");
            }

        } catch (Exception ex) {
            System.out.println("No es pot establir connexió amb el servidor");
        }
    }

    /**
     * Fa l'inici de sessió d'un usuari amb rol d'usuari pasat per parametre a
     * la crida
     *
     * @param usuari
     * @param contrasenya
     * @param id
     * @throws InterruptedException
     */
    public static void loginUsuari(String usuari, String contrasenya, int id_conn) throws InterruptedException {
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
            
            System.out.println(id_conn + ",LOGIN," + usuari + "," + contrasenya);

            resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            System.out.println("resposta servidor: " + resposta_svr_id);

            if (resposta_svr_id != 0) {
                int rol = 3;
                System.out.println("Rol: " + rol);
                System.out.println("Aquest rol d'usuari no te permisos per entrar en aquest aplicatiu");
                System.out.println("Test rol Usuari COMPLETAT");
            }

        } catch (Exception ex) {
            System.out.println("No es pot establir connexió amb el servidor");
        }
    }

    /**
     * * Fa l'inici de sessió d'un usuari incorrecte pasat per parametre a la
     * crida
     *
     * @param usuari
     * @param contrasenya
     * @param id
     * @throws InterruptedException
     */
    public static void loginIncorrecte(String usuari, String contrasenya, int id_conn) throws InterruptedException {
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
            
            System.out.println(id_conn + ",LOGIN," + usuari + "," + contrasenya);

            resposta_svr_id = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            System.out.println("resposta servidor: " + resposta_svr_id);

            if (resposta_svr_id == 0) {
                System.out.println("Error de usuari o contrasenya");
            }

        } catch (Exception ex) {
            System.out.println("No es pot establir connexió amb el servidor");
        }
    }

    /**
     * Fa el logout d'un usuari pasat per parametre a la crida
     *
     * @param usuari
     * @param contrasenya
     * @param id
     */
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

}

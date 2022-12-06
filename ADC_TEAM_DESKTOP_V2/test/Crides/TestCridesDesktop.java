package Crides;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;

/**
 *
 * @author garci
 */
public class TestCridesDesktop {

    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("");

        //Simulem login usuari rol Administrador i posterior Logout del servidor
        System.out.println("------------------------- ROL ADMINISTRADOR -------------------------");
        loginAdmin("aitor", "pwdaitor", "0");
        logOut("aitor", "pwdaitor", String.valueOf(resposta_svr_id));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login usuari rol Tècnic i posterior Logout del servidor
        System.out.println("---------------------------- ROL  TÈCNIC ----------------------------");
        loginTecnic("manel", "pwdmanel", "0");
        logOut("manel", "pwdmanel", String.valueOf(resposta_svr_id));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login usuari rol Usuari i posterior Logout del servidor
        System.out.println("---------------------------- ROL  USUARI ----------------------------");
        loginUsuari("martina", "pwdmartina", "0");
        logOut("martina", "pwdmartina", String.valueOf(resposta_svr_id));
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        //Simulem login incorrecte
        System.out.println("------------------------- USUARI INCORRECTE -------------------------");
        loginIncorrecte("usuariincorrecte", "pwdincorrecte", "0");
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
    public static void loginAdmin(String usuari, String contrasenya, String id) throws InterruptedException {
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            //Llegir la resposta del servidor al establir la connexió
            String resposta_svr = in.readUTF();
            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF("0" + ",LOGIN," + usuari + "," + contrasenya + "," + id);
            System.out.println("0" + ",LOGIN," + usuari + "," + contrasenya + "," + id);

            int resposta_svr_id = in.readInt();
            System.out.println("resposta servidor: " + resposta_svr);

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
    public static void loginTecnic(String usuari, String contrasenya, String id) throws InterruptedException {
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            //Llegir la resposta del servidor al establir la connexió
            String resposta_svr = in.readUTF();
            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF("LOGIN," + usuari + "," + contrasenya + "," + id);
            System.out.println("LOGIN," + usuari + "," + contrasenya + "," + id);

            int resposta_svr_id = in.readInt();
            System.out.println("resposta servidor: " + resposta_svr);

            if (resposta_svr_id != 0) {
                int rol = 2;
                System.out.println("Benvingut " + usuari);
                System.out.println("Obrim client tècnic");
                System.out.println(usuari + " - #" + resposta_svr_id + " - # rol: " + rol);
                System.out.println("Test rol Tècnic Completat");
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
    public static void loginUsuari(String usuari, String contrasenya, String id) throws InterruptedException {
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            //Llegir la resposta del servidor al establir la connexió
            String resposta_svr = in.readUTF();
            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF("LOGIN," + usuari + "," + contrasenya + "," + id);
            System.out.println("LOGIN," + usuari + "," + contrasenya + "," + id);

            int resposta_svr_id = in.readInt();
            System.out.println("resposta servidor: " + resposta_svr);

            if (resposta_svr_id != 0) {
                int rol = 3;
                System.out.println("Rol: " + rol);
                System.out.println("Aquest rol d'usuari no te permisos per entrar en aquest aplicatiu");
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
    public static void loginIncorrecte(String usuari, String contrasenya, String id) throws InterruptedException {
        Socket sc;
        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            //Llegir la resposta del servidor al establir la connexió
            String resposta_svr = in.readUTF();
            //Enviem resposta al servidor amb el usuari i la contrasenya INCORRECTES!!
            out.writeUTF("LOGIN," + usuari + "," + contrasenya + "," + id);
            System.out.println("LOGIN," + usuari + "," + contrasenya + "," + id);

            int resposta_svr_id = in.readInt();
            System.out.println("resposta servidor: " + resposta_svr);

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
    public static void logOut(String usuari, String contrasenya, String id) {

        Socket sc;

        try {
            sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            System.out.println("Fem logout del usuari " + usuari);

            // Llegir la resposta del servidor al establir la connexió
            String resposta_svr = in.readUTF();
            //Enviem resposta al servidor amb el usuari i la contrasenya
            out.writeUTF("LOGIN," + usuari + "," + contrasenya + "," + id);
            //Executo la consulta de la crida per sortir
            out.writeUTF("USER_EXIT");
            System.out.println("Logout realitzat");

        } catch (IOException ex) {
            Logger.getLogger(TestCridesDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

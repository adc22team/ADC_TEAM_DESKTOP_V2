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
public class TestCridesRole {

    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("####################    C R U D      ----      R O L S    ################################");
        System.out.println("#################### #################################### ################################");
        System.out.println("");

        //Iniciem sessió amb un usuaria Administrador
        loginAdmin("aitor", "pwdaitor", 0);
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una alta
        System.out.println("------------------------------- ALTA -------------------------------");
        addRole("SuperProva", "Rol de proves TEA 4", resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem un llistat
        System.out.println("------------------------------ LLISTA ------------------------------");
        listRole(resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una cerca de ID de Rol
        System.out.println("------------------------------- CERCA -------------------------------");
        buscarIdRol("SuperProva", resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una modificació
        System.out.println("---------------------------- MODIFICACIO ----------------------------");
        modifyRole(buscarIdRol("SuperProva", resposta_svr_id), "SuperProvaEdit", "Rol de proves TEA 4 EDITAT", resposta_svr_id);
        listRole(resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");

        //Simulem una baixa
        System.out.println("------------------------------- BAIXA -------------------------------");
        deleteRole(buscarIdRol("SuperProvaEdit", resposta_svr_id), resposta_svr_id);
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

    public static void addRole(String role, String desc, int id_conn) {

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
                System.out.println("-------- ROL CREAT --------");
                System.out.println("Departament: " + role);
                System.out.println("Descripcio: " + desc);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
        }

    }

    public static int listRole(int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_QUERY,2,rol", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            System.out.println("El total de rols és: " + registres_trobats);

            ArrayList registres = new ArrayList();

            for (int i = 0; i < registres_trobats; i++) {
                registres.add(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            }

            for (int i = 0; i < registres.size(); i++) {
                System.out.println(registres.get(i).toString());
            }

        } catch (Exception e) {
        }

        return registres_trobats;

    }

    public static int buscarIdRol(String rol, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_FIND," + rol, shared_secret.toByteArray()));

            int id_trobat = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            System.out.println("---------- ID ROL ----------");
            System.out.println("ROL: " + rol);
            System.out.println("ID: " + id_trobat);
            System.out.println("----------------------------");

            //Si troba usuari
            return id_trobat;

        } catch (Exception e) {
        }
        //Si no troba usuari
        return 0;

    }

    public static void modifyRole(int id_rol, String role, String desc, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_MODIFI," + id_rol + "," + role + "," + desc, shared_secret.toByteArray()));

            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            if (resposta_modif == 1) {
                System.out.println("------ ROL MODIFICAT ------");
                System.out.println("Departament: " + role);
                System.out.println("Descripcio: " + desc);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
        }

    }

    public static void deleteRole(int id_rol, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",ROLE_DELETE," + id_rol, shared_secret.toByteArray()));

            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            if (resposta_eliminar == 1) {
                System.out.println("--------- ROL ELIMINAT ---------");
                System.out.println("ID Rol eliminat: " + id_rol);
                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
        }

    }

}

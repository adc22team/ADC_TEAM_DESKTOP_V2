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
public class TestCridesDept {
    
    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("#################### C R U D   --   D E P A R T A M E N T ################################");
        System.out.println("#################### #################################### ################################");
        System.out.println("");
        
        //Iniciem sessió amb un usuaria Administrador
        loginAdmin("aitor", "pwdaitor", 0);
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem una alta
        System.out.println("------------------------------- ALTA -------------------------------");
        addDept("Proves", "Departament de proves TEA 4", "666859440", resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem un llistat
        System.out.println("------------------------------ LLISTA ------------------------------");
        listDept(resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem una cerca de ID de Departament
        System.out.println("------------------------------- CERCA -------------------------------");
        buscarIdDept("Proves", resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");

        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem una modificació
        System.out.println("---------------------------- MODIFICACIO ----------------------------");
        modifyDept(buscarIdDept("Proves", resposta_svr_id), "ProvesEdit", "Departament de proves TEA 4 EDITAT", "666999444", resposta_svr_id);
        listDept(resposta_svr_id);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem una baixa
        System.out.println("------------------------------- BAIXA -------------------------------");
        deleteDept(buscarIdDept("ProvesEdit", resposta_svr_id), resposta_svr_id);
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
    
    public static void addDept(String dept, String desc, String phone, int id_conn) {

        
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
                System.out.println("----- DEPARTAMENT CREAT -----");
                System.out.println("Departament: " + dept);
                System.out.println("Descripcio: " + desc);
                System.out.println("Telefon: " + phone);
                System.out.println("-----------------------------");
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public static int listDept(int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_QUERY,2,departament", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            System.out.println("El total de departaments és: " + registres_trobats);
            
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
    
    public static int buscarIdDept(String dept, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_FIND," + dept, shared_secret.toByteArray()));

            int id_trobat = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            System.out.println("-------- ID DEPARTAMENT --------");
            System.out.println("Departament: " + dept);
            System.out.println("ID: " + id_trobat);
            System.out.println("--------------------------------");

            //Si troba usuari
            return id_trobat;

        } catch (Exception e) {
        }
        //Si no troba departament
        return 0;
    }
    
    public static void modifyDept(int id_dept, String dept, String desc, String phone, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_MODIFI," + id_dept + "," + dept + "," + desc + "," + phone, shared_secret.toByteArray()));

            int resposta_modif = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            if (resposta_modif == 1) {
                System.out.println("---- DEPARTAMENT MODIFICAT ----");
                System.out.println("Departament: " + dept);
                System.out.println("Descripcio: " + desc);
                System.out.println("Telefon: " + phone);
                System.out.println("-------------------------------");
            }

        } catch (Exception e) {
        }

    }
    
    public static void deleteDept(int id_dept, int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",DEPA_DELETE," + id_dept, shared_secret.toByteArray()));
            
            int resposta_eliminar = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));
            
            if (resposta_eliminar == 1) {
                System.out.println("----- DEPARTAMENT ELIMINAT -----");
                System.out.println("ID Departament eliminat: " + id_dept);
                System.out.println("--------------------------------");
            }
            
        } catch (Exception e) {
        }
        
    }
    
}

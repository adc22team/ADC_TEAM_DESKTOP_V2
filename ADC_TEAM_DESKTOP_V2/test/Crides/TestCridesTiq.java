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
public class TestCridesTiq {
    
    private static int resposta_svr_id;

    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("#################### S I M U L A C I O   D E  P R O V E S ################################");
        System.out.println("#################### #########  T I Q U E T S   ######### ################################");
        System.out.println("#################### #################################### ################################");
        System.out.println("");
        
        //Iniciem sessió amb un usuaria Administrador
        loginAdmin("aitor", "pwdaitor", 0);
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem un llistat
        System.out.println("------------------------------ LLISTA ------------------------------");
        listTiq(resposta_svr_id);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("");
        
        System.out.println("********************************************************************");
        System.out.println("");
        
        //Simulem els contadors que tenim al client
        System.out.println("----------------------------- CONTADORS -----------------------------");
        System.out.println("");
        System.out.println("+++++++++++++++++");
        System.out.println("Total Tiquets: " + countAllTiq(resposta_svr_id));
        System.out.println("+++++++++++++++++");
        System.out.println("");
        System.out.println("++++++Estat++++++");
        System.out.println("En Resolució: " + countResolucioTiq(resposta_svr_id));
        System.out.println("Resolts: " + countResoltTiq(resposta_svr_id));
        System.out.println("+++++++++++++++++");
        System.out.println("++++Prioritat++++");
        System.out.println("Baixa: " + countPrioBaixa(resposta_svr_id));
        System.out.println("Mitja: " + countPrioMitja(resposta_svr_id));
        System.out.println("Alta: " + countPrioAlta(resposta_svr_id));
        System.out.println("Urgent: " + countPrioUrgent(resposta_svr_id));
        System.out.println("+++++++++++++++++");
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
    
    public static int listTiq(int id_conn) {

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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY,0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

            System.out.println("El total de tiquets és: " + registres_trobats);

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
    
    public static int countAllTiq(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countResolucioTiq(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,estat = 2", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countResoltTiq(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,estat = 3", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countPrioBaixa(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,prioritat = 0", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countPrioMitja(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,prioritat = 1", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countPrioAlta(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,prioritat = 2", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
    public static int countPrioUrgent(int id_conn) {
        
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
            out.writeUTF(SystemUtils.encryptedText(id_conn + ",TIQU_QUERY_COUNT,1,prioritat = 3", shared_secret.toByteArray()));

            // Llegir la resposta del servidor del nombre de registres trobats
            registres_trobats = Integer.parseInt(SystemUtils.decryptedText(in.readUTF(), shared_secret.toByteArray()));

        } catch (Exception e) {
        }
        
        return registres_trobats;
        
    }
    
}

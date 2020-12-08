package dist.herramientas.server;

import java.io.*;
import java.net.*;

public class SocketServer {

   public static ServerSocket server;
   public static Socket ns;
   public static DataOutputStream out;
   public static DataInputStream in;

   public static void conexion() {
       try {
           server = new ServerSocket(5001, 10);
           ns = server.accept();
           System.out.println("conectado");
           in = new DataInputStream(ns.getInputStream());
           out = new DataOutputStream(ns.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error de conexion " + e);
        }

    }


    
}

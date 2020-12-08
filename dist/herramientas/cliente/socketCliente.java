package dist.herramientas.cliente;

import java.io.*;
import java.net.*;


public class socketCliente {

    static public Socket echoSocket = null;
    static public DataOutputStream os = null;
    static public DataInputStream is = null;

    public static void conexionClient() {
        try {
            echoSocket = new Socket("localhost", 5001);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("no se puede resolver el host: localhost");
        } catch (IOException e) {
            System.err.println("No se puedo establecer conexion con el servidor, volviendo a intentar...");
        }
    }

}

package dist;

import java.io.*;
import java.net.*;


public class socketCliente {

    public Socket echoSocket = null;
    public DataOutputStream os = null;
    public DataInputStream is = null;
    public static DataOutputStream salida = null;
    public static DataInputStream entrada = null;
    // Scanner stdIn = new Scanner(System.in);

    public void conexionClient() {
        try {
            echoSocket = new Socket("localhost", 5001);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
            salida = os;
            entrada = is;
        } catch (UnknownHostException e) {
            System.err.println("no se puede resolver el host: localhost");
        } catch (IOException e) {
            System.err.println("No se puedo establecer conexion con el servidor, volviendo a intentar...");
        }
    }

}

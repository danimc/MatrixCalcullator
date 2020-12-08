import java.io.IOException;
import java.util.InputMismatchException;

import dist.herramientas.server.FormulariosServer;
import dist.herramientas.server.SocketServer;

class server {

    /*
     * ServerSocket server; Socket ns; DataOutputStream out; DataInputStream in;
     */
    int message;
    int resp;
    


 /*   public void conexion() {
        
         * try { server = new ServerSocket(5001, 10); ns = server.accept();
         * System.out.println("conectado"); in = new
         * DataInputStream(ns.getInputStream()); out = new
         * DataOutputStream(ns.getOutputStream()); } catch (IOException e) {
         * System.out.println("Error de conexion " + e); }
         
    }
*/
    public void listenSocket() {
        try {

            int opcion = SocketServer.in.readInt();
            System.out.println("Opcion elegida: " + opcion);
            switch (opcion) {
                case 1:
                    FormulariosServer.multiplicar();
                    break;
                case 2:
                    FormulariosServer.suma();
                    break;
                case 3:
                    FormulariosServer.igualdad();
                    break;
                case 4:
                    FormulariosServer.mayor();
                    break;
                case 5:
                    System.out.println("cliente se desconecto");
                    break;
                default:
                    System.out.println("Opcion del cliente invalida");
                    break;
            }

            SocketServer.out.flush();
            SocketServer.out.writeInt(message);
            SocketServer.out.writeByte('\n');
            SocketServer.out.flush();
        } catch (IOException e) {
            System.out.println("Accept failed: 5000");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: no es un numero");
        } catch (NullPointerException e) {
            System.err.println("error al intentar iniciar el servidor" + e);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

   
    protected void finalize() {
        try {
            SocketServer.in.close();
            SocketServer.out.close();
            SocketServer.server.close();

            System.out.println("cerrando conexion");
        } catch (IOException e) {
            System.out.println("no se pudo cerrar la conexion.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        System.out.println("servidor funcionando");
        server s = new server();

        do {
            SocketServer.conexion();
            s.listenSocket();
            s.finalize();
        } while (true);

    }

}

package dist.herramientas.server;

import java.io.IOException;
import java.net.SocketException;
import java.util.InputMismatchException;

public class IOServer {

    static void ingresaDatos(float[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                try {
                    matriz[i][k] = SocketServer.in.readFloat();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InputMismatchException e) {
                    System.out.println("no es un numero valido");
                } catch (NullPointerException e) {
                    System.err.println("Hubo un problema con el tamaño de la matriz");
                }

            }
        }
    }

    static void ingresaDatos(float[] v) throws IOException {
        for (int i = 0; i < v.length; i++) {
            v[i] = SocketServer.in.readFloat();
        }

    }

    public static void enviarResult(float[][] m) throws IOException {
        try {
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    SocketServer.out.writeFloat(m[i][j]);                   
                }
            }
        } catch (SocketException e) {
            System.err.println("error al enviar" + e);
        }

    }

    static void enviarResult(float[] v) throws IOException {

        for (int i = 0; i < v.length; i++) {
            SocketServer.out.writeFloat(v[i]);
        }
    }

    

 }

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

import dist.opMatrix.operaciones;

class server {

    ServerSocket server;
    Socket ns;
    DataOutputStream out;
    DataInputStream in;
    int message;
    int resp;

    operaciones op = new operaciones();

    public void conexion() {
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

    public void listenSocket() {
        try {

            int opcion = in.readInt();
            System.out.println("Opcion elegida: " + opcion);
            switch (opcion) {
                case 1:
                    datosMatrix();
                    break;
            }

            /*
             * int size = 0; int n; size = in.readInt(); int vector[] = new int[size];
             * System.out.println("esperando " + size + " valores");
             * 
             * for (int i = 0; i < size; i++) { n = in.readInt(); if (n != -1) { vector[i] =
             * n; System.out.println("valor " + i + ": " + n); } else {
             * System.out.println("ERROR EN EL CLIENTE"); }
             * 
             * }
             * 
             * message = op.greatest(vector);
             * 
             * System.out.println("+ writing to socket"); System.out.println("=> " +
             * message);
             */
            out.flush();
            out.writeInt(message);
            out.writeByte('\n');
            out.flush();
        } catch (IOException e) {
            System.out.println("Accept failed: 5000");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: no es un numero");
        }
    }

    public void datosMatrix() {
        try {

            int a = in.readInt();
            System.out.println("Columnas de la matriz 'A': " + a);
            int b = in.readInt();
            System.out.println("Renglones de la matriz 'A': " + b);
            float[][] matrixA = new float[a][b];

            System.out.println("\n\n >MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            //out.writeByte('\n');
            out.flush();
            boolean repetir = true;

            do {
                try {
                    out.writeUTF("> Desea multiplicar la matriz 'A' con \n 1.- otra matriz \n 2.- un vector");
                   // out.writeByte('\n');
                    out.flush();

                    resp = in.readInt();
                    if (resp == 1 || resp == 2) {
                        repetir = false;
                        System.out.println("bucle: " + repetir);
                        System.out.println("> opcion CORRECTA");
                        out.writeUTF("> opcion CORRECTA");
                        out.flush();
                       
                    } else {
                        System.out.println("bucle: " + repetir);
                        System.out.println("> opcion incorrecta");
                        out.writeUTF("> opcion incorrecta");
                       // out.writeByte('\n');
                        out.flush();
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Intente de nuevo");
                }
            } while (repetir);

            System.out.println("USUARIO SELECCIONO: "+ resp);
            out.writeUTF("selecciono la opcion " + resp);

        } catch (IOException e) {
            System.out.println("Accept failed al ingresar datos: 5000");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: no es un numero");
        }

    }

    protected void finalize() {
        try {
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("no se pudo cerrar la conexion.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        System.out.println("servidor funcionando");
        server s = new server();

        // int respuesta = 0;

        do {
            s.conexion();
            s.listenSocket();
            s.finalize();
        } while (true);

    }

}

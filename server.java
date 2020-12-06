import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

import dist.herramientas.operacionesServer;

class server {

    operacionesServer op = new operacionesServer();
    ServerSocket server;
    Socket ns;
    DataOutputStream out;
    DataInputStream in;
    int message;
    int resp;
    int renglones;

    float[][] matrixA;
    float[][] matrixB;
    float[] vecto1;
    float[][] Mresultado;
    float[] Vresultado;

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
                case 2:
                    suma();
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
            definir_TamMatrix();
            ingresaDatos(matrixA);
            op.imprimeMatrix(matrixA);
            boolean repetir = true;
            do {
                try {
                    out.writeUTF("> Desea multiplicar la matriz 'A' con \n 1.- otra matriz \n 2.- un vector");
                    out.flush();

                    resp = in.readInt();
                    if (resp == 1 || resp == 2) {
                        repetir = false;
                        System.out.println("> opcion CORRECTA");
                        out.writeUTF("> opcion CORRECTA");
                        out.flush();
                        out.writeInt(1);
                        out.flush();

                    } else {
                        System.out.println("> opcion incorrecta");
                        out.writeUTF("> opcion incorrecta");
                        out.flush();
                        out.writeInt(0);
                        out.flush();
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Intente de nuevo");
                }
            } while (repetir);

            System.out.println("USUARIO SELECCIONO: " + resp);
            out.writeUTF("> selecciono la opcion " + resp);
            out.flush();
            out.writeInt(resp);
            out.flush();

            if (resp == 1) {
                try {
                    definir_TamMatrix(renglones);
                    Mresultado = new float[matrixA.length][matrixB[0].length];
                    ingresaDatos(matrixB);
                    op.imprimeMatrix(matrixB);

                    System.out
                            .println("\n\n > MATRIZ 'R' DE TAMAÑO [" + matrixA.length + "][" + matrixB[0].length + "]");
                    out.writeInt(matrixA.length);
                    out.writeInt(matrixB[0].length);
                    op.multiply(matrixA, matrixB, Mresultado);
                    System.out.println("\n ENVIANDO RESULTADO:");
                    enviarResult(Mresultado);

                } catch (Exception e) {
                    System.err.println("Error de tamaños");
                    e.printStackTrace();
                }
            }
            if (resp == 2) {
                definir_TamVector(renglones);
                Vresultado = new float[renglones];
                out.writeInt(renglones);
                ingresaDatos(vecto1);
                op.multiply(matrixA, vecto1, Vresultado);
                op.imprimeVector(Vresultado);
                System.out.println("\n ENVIANDO RESULTADO:");
                enviarResult(Vresultado);

            }

        } catch (IOException e) {
            System.out.println("Accept failed al ingresar datos: 5000");
            System.exit(-1);
        } catch (InputMismatchException e) {
            System.out.println("Error: no es un numero");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void definir_TamVector(int renglones) throws Exception {
        int a = in.readInt();
        System.out.println("Tamaño del Vector " + a);
        if (a == renglones) {
            vecto1 = new float[a];
            System.out.println("\n\n >VECTOR DE TAMAÑO [" + a + "]");
            out.writeUTF("\n > SERVIDOR DICE: \n > VECTOR DE TAMAÑO [" + a + "]");
            out.flush();

        } else {
            // definir_TamMatrix(renglones);
            throw new Exception("Los tamaños no son compatibles");
        }

    }

    private void ingresaDatos(float[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                try {
                    matriz[i][k] = in.readFloat();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InputMismatchException e) {
                    System.out.println("no es un numero valido");
                }

            }
        }
    }

    private void ingresaDatos(float[] v) throws IOException {
        for (int i = 0; i < v.length; i++) {
            v[i] = in.readFloat();
        }

    }

    public void definir_TamMatrix() {
        try {
            int a = in.readInt();
            System.out.println("Columnas de la matriz 'A': " + a);
            int b = in.readInt();
            System.out.println("Renglones de la matriz 'A': " + b);
            matrixA = new float[a][b];

            System.out.println("\n\n >MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            renglones = b;
            out.flush();
        } catch (IOException e) {

        }

    }

    public void definir_TamMatrix(int renglones) throws Exception {
        try {
            int a = in.readInt();
            System.out.println("Columnas de la matriz 'B': " + a);
            int b = in.readInt();
            System.out.println("Renglones de la matriz 'B': " + b);

            if (a == renglones) {
                matrixB = new float[a][b];
                System.out.println("\n\n >MATRIZ 'B' DE TAMAÑO [" + a + "][" + b + "]");
                out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'B' DE TAMAÑO [" + a + "][" + b + "]");
                out.flush();

            } else {
                // definir_TamMatrix(renglones);
                throw new Exception("Los tamaños no son compatibles");

            }
        } catch (IOException e) {
            System.err.println("errorsito");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("problema con el tamaño de la matriz");
        }

    }

    public void enviarResult(float m[][]) throws IOException {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                out.writeFloat(m[i][j]);
            }
        }
    }

    private void enviarResult(float[] v) throws IOException {
        for (int i = 0; i < v.length; i++) {
            out.writeFloat(v[i]);
        }
    }

    private void suma() {
        definir_TamMatrix();
        matrixB = new float[matrixA.length][matrixA[0].length];
        Mresultado = new float[matrixA.length][matrixA[0].length];
        ingresaDatos(matrixA);
        op.imprimeMatrix(matrixA);
        System.out.println("valores de la segunda matrix");
        ingresaDatos(matrixB);
        op.imprimeMatrix(matrixB);
        op.addition(matrixA, matrixB, Mresultado);
        System.out.println("resultado de la suma");
        op.imprimeMatrix(Mresultado);

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

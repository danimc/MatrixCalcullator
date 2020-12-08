package dist.herramientas.server;

import java.io.IOException;
import java.util.InputMismatchException;

public class FormulariosServer {

    static float[][] matrixA;
    static float[][] matrixB;
    static float[] vecto1;
    static float[][] Mresultado;
    static float[] Vresultado;
    static int renglones;

    static operacionesServer op = new operacionesServer();

    static int resp;

    public static void mayor() throws Exception {
        boolean repetir = true;
        do {
            try {
                op.printServer(matrixA);
                SocketServer.out.writeUTF("> Desea verificar \n 1.- una matriz \n 2.- un vector");
                SocketServer.out.flush();
                resp = SocketServer.in.readInt();
                if (resp == 1 || resp == 2) {
                    repetir = false;
                    System.out.println("> opcion CORRECTA");
                    SocketServer.out.writeUTF("> opcion CORRECTA");
                    SocketServer.out.flush();
                    SocketServer.out.writeInt(1);
                    SocketServer.out.flush();

                } else {
                    System.out.println("> opcion incorrecta");
                    SocketServer.out.writeUTF("> opcion incorrecta");
                    SocketServer.out.flush();
                    SocketServer.out.writeInt(0);
                    SocketServer.out.flush();
                }

            } catch (InputMismatchException e) {
                System.err.println("Intente de nuevo");
            }
        } while (repetir);

        System.out.println("USUARIO SELECCIONO: " + resp);
        SocketServer.out.writeUTF("> selecciono la opcion " + resp);
        SocketServer.out.flush();
        SocketServer.out.writeInt(resp);
        SocketServer.out.flush();

        float big = 0;
        if (resp == 1) {
            Definiciones.definir_TamMatrix();
            IOServer.ingresaDatos(matrixA);
            op.printServer(matrixA);
            big = op.greatest(matrixA);
        }
        if (resp == 2) {
            Definiciones.definir_TamVector();
            IOServer.ingresaDatos(vecto1);
            big = op.greatest(vecto1);
        }

        SocketServer.out.writeFloat(big);

    }

    public static void igualdad() {
        try {

            boolean respuesta;
            Definiciones.definir_TamMatrix();
            IOServer.ingresaDatos(matrixA);
            System.out.println("segunda matriz ");
            Definiciones.definir_TamMatrixI();
            IOServer.ingresaDatos(matrixB);
            respuesta = op.equal(matrixA, matrixB);
            System.out.println("\n realizando comparacion...");
            System.out.println("\nson iguales: " + respuesta);
            SocketServer.out.writeUTF("> son iguales: " + respuesta);
        } catch (IOException e) {
            System.err.println("Problema de coneccion");
        }

    }

    public static void multiplicar() {
        try {
            Definiciones.definir_TamMatrix();
            IOServer.ingresaDatos(matrixA);
            op.printServer(matrixA);
            boolean repetir = true;
            do {
                try {
                    SocketServer.out
                            .writeUTF("> Desea multiplicar la matriz 'A' con \n 1.- otra matriz \n 2.- un vector");
                    SocketServer.out.flush();

                    resp = SocketServer.in.readInt();
                    if (resp == 1 || resp == 2) {
                        repetir = false;
                        System.out.println("> opcion CORRECTA");
                        SocketServer.out.writeUTF("> opcion CORRECTA");
                        SocketServer.out.flush();
                        SocketServer.out.writeInt(1);
                        SocketServer.out.flush();

                    } else {
                        System.out.println("> opcion incorrecta");
                        SocketServer.out.writeUTF("> opcion incorrecta");
                        SocketServer.out.flush();
                        SocketServer.out.writeInt(0);
                        SocketServer.out.flush();
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Intente de nuevo");
                }
            } while (repetir);

            System.out.println("USUARIO SELECCIONO: " + resp);
            SocketServer.out.writeUTF("> selecciono la opcion " + resp);
            SocketServer.out.flush();
            SocketServer.out.writeInt(resp);
            SocketServer.out.flush();

            if (resp == 1) {
                try {
                    Definiciones.definir_TamMatrix(renglones);
                    Mresultado = new float[matrixA.length][matrixB[0].length];
                    IOServer.ingresaDatos(matrixB);
                    op.printServer(matrixB);

                    System.out
                            .println("\n\n > MATRIZ 'R' DE TAMAÑO [" + matrixA.length + "][" + matrixB[0].length + "]");
                    op.multiply(matrixA, matrixB, Mresultado);
                    System.out.println("\n ENVIANDO RESULTADO:");
                    SocketServer.out.writeInt(matrixA.length);
                    SocketServer.out.writeInt(matrixB[0].length);
                    SocketServer.out.flush();
                    IOServer.enviarResult(Mresultado);

                } catch (IOException e) {
                    System.err.println("Hubo un problema al mandar el dato " + e);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (resp == 2) {
                Definiciones.definir_TamVector(renglones);
                Vresultado = new float[renglones];
                IOServer.ingresaDatos(vecto1);
                SocketServer.out.writeInt(renglones);
                op.multiply(matrixA, vecto1, Vresultado);
                op.printServer(Vresultado);
                System.out.println("\n ENVIANDO RESULTADO:");
                IOServer.enviarResult(Vresultado);

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

    public static void suma() {
        try {
            Definiciones.definir_TamMatrix();
            matrixB = new float[matrixA.length][matrixA[0].length];
            Mresultado = new float[matrixA.length][matrixA[0].length];
            IOServer.ingresaDatos(matrixA);
            op.printServer(matrixA);
            System.out.println("valores de la segunda matrix");
            IOServer.ingresaDatos(matrixB);
            op.printServer(matrixB);
            SocketServer.out.writeInt(matrixA.length);
            SocketServer.out.writeInt(matrixA[0].length);
            op.addition(matrixA, matrixB, Mresultado);
            System.out.println("resultado de la suma");
            op.printServer(Mresultado);

            IOServer.enviarResult(Mresultado);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}

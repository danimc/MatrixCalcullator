package dist.herramientas.server;

import java.io.IOException;
import java.util.InputMismatchException;

public class Definiciones {

    public static void definir_TamMatrix() {
        int b = 0;
        try {
            int a = SocketServer.in.readInt();
            System.out.println("Columnas de la matriz 'A': " + a);
            b = SocketServer.in.readInt();
            System.out.println("Renglones de la matriz 'A': " + b);
            FormulariosServer.matrixA = new float[a][b];
            FormulariosServer.renglones = b;

            System.out.println("\n\n >MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            SocketServer.out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            SocketServer.out.flush();

        } catch (IOException e) {
            System.err.println("no se recibieron datos " + e);
        } 
    }

    public static void definir_TamMatrixI() {
        try {
            int a = SocketServer.in.readInt();
            System.out.println("Columnas de la matriz 'B': " + a);
            int b = SocketServer.in.readInt();
            System.out.println("Renglones de la matriz 'B': " + b);
            FormulariosServer.matrixB = new float[a][b];
            System.out.println("\n\n >MATRIZ 'B' DE TAMAÑO [" + a + "][" + b + "]");
            SocketServer.out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            FormulariosServer.renglones = b;
            SocketServer.out.flush();
        } catch (IOException e) {
            System.err.println("no se recibieron datos " + e);
        } catch (InputMismatchException e ){
            System.err.println("DEBE INGRESAR UN VALOR VALIDO");
        }
    }

    public static void definir_TamMatrix(int renglones) throws Exception {
        try {
            int a = SocketServer.in.readInt();
            System.out.println("Columnas de la matriz 'B': " + a);
            int b = SocketServer.in.readInt();
            System.out.println("Renglones de la matriz 'B': " + b);

            if (a == renglones) {
                FormulariosServer.matrixB = new float[a][b];
                System.out.println("\n\n >MATRIZ 'B' DE TAMAÑO [" + a + "][" + b + "]");
                SocketServer.out.writeUTF("\n > SERVIDOR DICE: \n > MATRIZ 'B' DE TAMAÑO [" + a + "][" + b + "]");
                SocketServer.out.flush();

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

    public static void definir_TamVector(int renglones) throws Exception {
        int a = SocketServer.in.readInt();
        System.out.println("Tamaño del Vector " + a);
        if (a == renglones) {
            FormulariosServer.vecto1 = new float[a];
            System.out.println("\n\n >VECTOR DE TAMAÑO [" + a + "]");
            SocketServer.out.writeUTF("\n > SERVIDOR DICE: \n > VECTOR DE TAMAÑO [" + a + "]");
            SocketServer.out.flush();

        } else {
            // definir_TamMatrix(renglones);
            throw new Exception("Los tamaños no son compatibles");
        }

    }

    public static void definir_TamVector() throws Exception {
        int a = SocketServer.in.readInt();
        System.out.println("Tamaño del Vector " + a);

        FormulariosServer.vecto1 = new float[a];
        System.out.println("\n\n >VECTOR DE TAMAÑO [" + a + "]");
        SocketServer.out.writeUTF("\n > SERVIDOR DICE: \n > VECTOR DE TAMAÑO [" + a + "]");
        SocketServer.out.flush();
    }

}

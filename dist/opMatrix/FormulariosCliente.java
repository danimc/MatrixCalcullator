package dist.opMatrix;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);

    public static void DatosMultMatrix(DataOutputStream out, DataInputStream in) {
        int a, b, c, respuesta = 0;
        float[][] matriz1;
        // int[][] matriz2;
        float[][] resultado;

        try {
            System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ 'A'");
            a = reader.nextInt();
            out.writeInt(a);
            System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ 'A'");
            b = reader.nextInt();
            out.writeInt(b);

            System.out.println(in.readUTF());
            System.out.println("\n");

            boolean bucle = true;

            do {
                try {
                    System.out.println(in.readUTF());
                    respuesta = reader.nextInt();
                    out.writeInt(respuesta);
                  //  int d = in.readByte();
                   // System.out.println("valor: "+ d);
                    
                    //bucle = in.readBoolean();
                   // System.out.println(bucle);
                    //System.out.println(bucle);
                    System.out.println(in.readUTF());
                } catch (InputMismatchException e) {
                    System.err.println("o");
                }

                System.out.println("valor del bucle = "+ bucle);
            } while (bucle);

            System.out.println("opcion seleccionada correctamente");
            System.out.println(in.readUTF());
            // matriz1 = new float[a][b];

        } catch (IOException e) {
            System.err.println("comunicacion interrumpida con el servidor");
        }

        /*
         * 
         * System.out.println("matriz 1:"); matriz1 = new int[a][b];
         * imprimeMatrix(matriz1);
         * 
         * System.out.println("COLUMNAS MATRIZ 2"); c = reader.nextInt();
         * System.out.println("matriz 2:"); matriz2 = new int[b][c];
         * imprimeMatrix(matriz2);
         * 
         * resultado = new int[a][c];
         * System.out.println("ingrese los valores de la primera matriz;");
         * datosMatrix(matriz1);
         * System.out.println("ingrese los valores de la segunda matriz;");
         * datosMatrix(matriz2);
         * 
         * matrixMatrix(matriz1, matriz2, resultado);
         * System.out.println("\n \n LA MULTIPLICACION DE "); imprimeMatrix(matriz1);
         * System.out.println("X"); imprimeMatrix(matriz2);
         * System.out.println("ES IGUAL A: "); imprimeMatrix(resultado);
         */
    }

}

package dist.herramientas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import dist.socketCliente;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);
    DataOutputStream out = socketCliente.salida;
    DataInputStream in = socketCliente.entrada;
    float[][] matrixA;
    float[][] matrixB;

    public  void DatosMultMatrix() {
        int a, b, c, respuesta = 0, srvResp;

        try {
            System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ 'A'");
            a = reader.nextInt();
            out.writeInt(a);
            System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ 'A'");
            b = reader.nextInt();
            out.writeInt(b);
            System.out.println(in.readUTF());
            System.out.println("\n");
            mandarvaloresMatrix(a, b);
            boolean bucle = true;
            do {
                try {
                    System.out.println(in.readUTF());
                    respuesta = reader.nextInt();
                    out.writeInt(respuesta);
                    System.out.println(in.readUTF());
                    srvResp = in.readInt();
                    if (srvResp == 1) {
                        bucle = false;
                    }

                } catch (InputMismatchException e) {
                    System.err.println("o");
                }
            } while (bucle);
            // FIN DEL BUCLE PARA SELECCIONAR MATRIZ O VECTOR
            System.out.println(in.readUTF());
            srvResp = in.readInt();
            if (srvResp == 1) {
                System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ 'B'");
                a = reader.nextInt();
                out.writeInt(a);
                System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ 'B'");
                b = reader.nextInt();
                out.writeInt(b);
            }

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

    public void mandarvaloresMatrix(int a, int b) {
        float userInput;
        for (int i = 0; i < a; i++) {
            for (int k = 0; k < b; k++) {
                try {
                    System.out.println("ingresa el valor " + i);
                    userInput = reader.nextFloat();
                    out.writeFloat(userInput);
                     
                }catch (IOException e) {
                    e.printStackTrace();
                } catch (InputMismatchException e) {
                    System.out.println("no es un numero valido");
                }

            }
        }
    }

}

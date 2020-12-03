package dist.opMatrix;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);

    public static void DatosMultMatrix(DataOutputStream out) {
        int a, b, c, respuesta = 0;
        float[][] matriz1;
        // int[][] matriz2;
        float[][] resultado;

        try{
            System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ 'A'");
            a = reader.nextInt();
            out.writeInt(a);
            System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ 'A'");
            b = reader.nextInt();
            System.out.println("MATRIZ 'A' DE TAMAÑO [" + a + "][" + b + "]");
            matriz1 = new float[a][b];
            boolean repetir = true;
            while (repetir) {
                try {
                    System.out.println("Desea multiplicar la matriz 'A' \n con 1.- otra matriz \n 2.- un vector");
                    respuesta = reader.nextInt();
                    if (respuesta == 1 || respuesta == 2) {
                        repetir = false;
                    } else {
                        throw opcionExeption();
                    }
    
                } catch (Exception e) {
                    System.err.println("Intente de nuevo");
                }
    
            }


        }catch (IOException e){
            System.err.println("error de dato");
        }

   

        if( respuesta == 1 ){
            
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

    private static Exception opcionExeption() {
        System.err.println("\n OPCION NO VALIDA \n");
        return null;
    }

}

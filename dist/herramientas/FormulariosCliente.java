package dist.herramientas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import dist.socketCliente;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);
    impresionesCliente p = new impresionesCliente();
    DataOutputStream out = socketCliente.salida;
    DataInputStream in = socketCliente.entrada;
    float[][] matrixA;
    float[][] matrixB;
    float[][] mresult;
    float[] vResult;
    int a, b;

    public void DatosMultMatrix() {
        int respuesta = 0, srvResp;

        try {
            mandarTamMatrix("A");
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
            } while (bucle); // FIN DEL BUCLE PARA SELECCIONAR MATRIZ O VECTOR

            System.out.println(in.readUTF());
            srvResp = in.readInt();
            if (srvResp == 1) { // MULT POR MATRIZ
                mandarTamMatrix("B");
                System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
                System.out.println("Resultado:");
                p.tamMatrixResult(mresult);
                System.out.println("\n");
            }
            if (srvResp == 2) { // MULT POR VECTOR
                mandarTamVector();
                System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
                System.out.println("Resultado:");
                
                p.tamVectorResult(vResult);
                System.out.println("\n");
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

    public void DatosSumMatrix() {
        try {
            mandarTamMatrix("");
            System.out.println("INGRESE LOS VALORES PARA LA SEGUNDA MATRIZ");
            mandarValoresMatrix(a, b); // segunda matriz
            System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
            System.out.println("Resultado:");
            p.tamMatrixResult(mresult);
            System.out.println("\n");


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void mandarTamVector() throws IOException {
        int a;
        System.out.println("INGRESA EL TAMAÑO DEL VECTOR");
        a = reader.nextInt();
        out.writeInt(a);
        System.out.println(in.readUTF());
        System.out.println("\n");

        // suponeindo que todo salga bien
        mandarValoresVector(a);
    }

    private void mandarTamMatrix(String nombre) throws IOException {

        System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ '" + nombre + "'");
        a = reader.nextInt();
        out.writeInt(a);
        System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ '" + nombre + "'");
        b = reader.nextInt();
        out.writeInt(b);
        System.out.println(in.readUTF());
        System.out.println("\n");

        mandarValoresMatrix(a, b);
    }

    public void mandarValoresMatrix(int a, int b) {
        float userInput;
        for (int i = 0; i < a; i++) {
            for (int k = 0; k < b; k++) {
                try {
                    System.out.println("ingresa el valor " + i);
                    userInput = reader.nextFloat();
                    out.writeFloat(userInput);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InputMismatchException e) {
                    System.out.println("no es un numero valido");
                }

            }
        }
    }

    private void mandarValoresVector(int v) throws IOException {
        float userInput;
        for (int i = 0; i < v; i++) {
            System.out.println("ingresa el valor " + i);
            userInput = reader.nextFloat();
            out.writeFloat(userInput);
        }
    }

	public void DatosIgualdad() {
        try{
            mandarTamMatrix("A");
            System.out.println("segunda Matriz");
            mandarTamMatrix("B");
            System.out.println("\nesperando respuesta \n Servidor dice: ");
            System.out.println(in.readUTF());           
            
        }catch (Exception e){
            System.err.println("problema desconocido");
        }
	}



}

package dist.herramientas.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import dist.socketCliente;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);
    impresionesCliente p = new impresionesCliente();
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
                    System.out.println(socketCliente.is.readUTF());
                    respuesta = reader.nextInt();
                   socketCliente.os.writeInt(respuesta);
                    System.out.println(socketCliente.is.readUTF());
                    srvResp =socketCliente.is.readInt();
                    if (srvResp == 1) {
                        bucle = false;
                    }

                } catch (InputMismatchException e) {
                    System.err.println("o");
                }
            } while (bucle); // FIN DEL BUCLE PARA SELECCIONAR MATRIZ O VECTOR

            System.out.println(socketCliente.is.readUTF());
            srvResp =socketCliente.is.readInt();
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
    }

    public void DatosSumMatrix() {
        try {
            mandarTamMatrix("");
            System.out.println("INGRESE LOS VALORES PARA LA SEGUNDA MATRIZ");
            mandarValoresMatrix(a, b); // segunda matriz
            System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
            System.out.println(" Resultado:");
            p.tamMatrixResult(mresult);
            System.out.println("\n");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void DatosIgualdad() {
        try {
            mandarTamMatrix("A");
            System.out.println("segunda Matriz");
            mandarTamMatrix("B");
            System.out.println("\nesperando respuesta \n Servidor dice: ");
            System.out.println(socketCliente.is.readUTF());

        } catch (Exception e) {
            System.err.println("problema desconocido");
        }
    }

    public void DatosMayor() throws IOException {
        boolean bucle = true;
        int respuesta, srvResp;
        do {
            try {
                System.out.println(socketCliente.is.readUTF());
                respuesta = reader.nextInt();
               socketCliente.os.writeInt(respuesta);
                System.out.println(socketCliente.is.readUTF());
                srvResp =socketCliente.is.readInt();
                if (srvResp == 1) {
                    bucle = false;
                }

            } catch (InputMismatchException e) {
                System.err.println("o");
            }
        } while (bucle);
        System.out.println(socketCliente.is.readUTF());
        srvResp =socketCliente.is.readInt();
        System.out.println("opcion seleccionada: " + srvResp);
        
        if(srvResp == 1){
            mandarTamMatrix("big");          
        }
        if(srvResp ==2){
            mandarTamVector();     
        }
        System.out.println("\n \n ESPERANDO RESPUESTA DEL SERVIDOR");
        System.out.println("\n > El numero mayor es: " +socketCliente.is.readFloat() );

    }

    private void mandarTamVector() throws IOException {
        int a;
        System.out.println("INGRESA EL TAMAÑO DEL VECTOR");
        a = reader.nextInt();
       socketCliente.os.writeInt(a);
        System.out.println(socketCliente.is.readUTF());
        System.out.println("\n");

        // suponeindo que todo salga bien
        mandarValoresVector(a);
    }

    private void mandarTamMatrix(String nombre) throws IOException {

        System.out.println("INGRESA LOS RENGLONES DE LA MATRIZ '" + nombre + "'");
        a = reader.nextInt();
       socketCliente.os.writeInt(a);
        System.out.println("INGRESA LAS COLUMNAS DE LA MATRIZ '" + nombre + "'");
        b = reader.nextInt();
       socketCliente.os.writeInt(b);
        System.out.println(socketCliente.is.readUTF());
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
                   socketCliente.os.writeFloat(userInput);

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
           socketCliente.os.writeFloat(userInput);
        }
    }

}

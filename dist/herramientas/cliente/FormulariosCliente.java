package dist.herramientas.cliente;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FormulariosCliente {
    static Scanner reader = new Scanner(System.in);
    ICliente p = new ICliente();
    float[][] matrixA;
    float[][] matrixB;
    float[][] mresult;
    float[] vResult;
    int a, b;

    public void DatosMultMatrix() throws IOException {
        int respuesta = 0, srvResp;

        try {
            OCliente.mandarTamMatrix("A");
            boolean bucle = true;
            do {
                try {
                    System.out.println(socketCliente.is.readUTF());
                    respuesta = reader.nextInt();
                    socketCliente.os.writeInt(respuesta);
                    System.out.println(socketCliente.is.readUTF());
                    srvResp = socketCliente.is.readInt();
                    if (srvResp == 1) {
                        bucle = false;
                    }

                } catch (InputMismatchException e) {
                    System.err.println("o");
                }
            } while (bucle); // FIN DEL BUCLE PARA SELECCIONAR MATRIZ O VECTOR

            System.out.println(socketCliente.is.readUTF());
            srvResp = socketCliente.is.readInt();
            if (srvResp == 1) { // MULT POR MATRIZ
                OCliente.mandarTamMatrix("B");
                System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
                System.out.println("Resultado:");
                p.tamMatrixResult(mresult);
                System.out.println("\n");
            }
            if (srvResp == 2) { // MULT POR VECTOR
                OCliente.mandarTamVector();
                System.out.println("\n \n PROCESANDO, POR FAVOR ESPERE... \n\n");
                System.out.println("Resultado:");
                p.tamVectorResult(vResult);
                System.out.println("\n");
            }

        } catch (IOException e) {
            System.err.println("comunicacion interrumpida con el servidor");
        } catch (InputMismatchException e ){
            System.err.println("Error desde Formularios");
            socketCliente.os.writeInt(-1);
        }
    }

    public void DatosSumMatrix() {
        try {
            OCliente.mandarTamMatrix("");
            System.out.println("INGRESE LOS VALORES PARA LA SEGUNDA MATRIZ");
            OCliente.mandarValoresMatrix(a, b); // segunda matriz
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
            OCliente.mandarTamMatrix("A");
            System.out.println("segunda Matriz");
            OCliente.mandarTamMatrix("B");
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
                srvResp = socketCliente.is.readInt();
                if (srvResp == 1) {
                    bucle = false;
                }

            } catch (InputMismatchException e) {
                System.err.println("o");
            }
        } while (bucle);
        System.out.println(socketCliente.is.readUTF());
        srvResp = socketCliente.is.readInt();
        System.out.println("opcion seleccionada: " + srvResp);

        if (srvResp == 1) {
            OCliente.mandarTamMatrix("big");
        }
        if (srvResp == 2) {
            OCliente.mandarTamVector();
        }
        System.out.println("\n \n ESPERANDO RESPUESTA DEL SERVIDOR");
        System.out.println("\n > El numero mayor es: " + socketCliente.is.readFloat());

    }

}

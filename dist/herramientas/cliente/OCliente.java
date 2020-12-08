package dist.herramientas.cliente;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OCliente {

    static Scanner reader = new Scanner(System.in);
    static int a = 0;
    static int b = 0;

    public static void mandarTamVector() throws IOException {
        int a = 0;

        System.out.println("INGRESA EL TAMAÑO DEL VECTOR");
        a = reader.nextInt();
        socketCliente.os.writeInt(a);
        System.out.println(socketCliente.is.readUTF());
        System.out.println("\n");
        mandarValoresVector(a);

        // suponeindo que todo salga bien

    }

    public static void mandarTamMatrix(String nombre) throws IOException {

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

    public static void mandarValoresMatrix(int a, int b) throws IOException {
        float userInput = 0;

        for (int i = 0; i < a; i++) {
            for (int k = 0; k < b; k++) {
                boolean bucle = true;
                do {
                    try {
                        Scanner ready = new Scanner(System.in);

                        System.out.println("ingresa el valor [" + i + "][" + k + "]");
                        userInput = ready.nextFloat();

                        bucle = false;
                    } catch (InputMismatchException e) {
                        System.out.println("no es un numero valido");
                        bucle = true;
                    }
                } while (bucle == true);
                socketCliente.os.writeFloat(userInput);
            }
        }

    }

    private static void mandarValoresVector(int v) throws IOException {
        float userInput = 0;

        for (int i = 0; i < v; i++) {
            boolean bucle = true;
            do {
                try {
                    Scanner ready = new Scanner(System.in);
                    System.out.println("ingresa el valor " + i);
                    userInput = ready.nextFloat();
                    bucle = false;

                } catch (InputMismatchException e) {
                    System.out.println("no es un numero valido");
                    bucle = true;
                }
            } while (bucle);
            socketCliente.os.writeFloat(userInput);
        }
    }

}

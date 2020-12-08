package dist.herramientas.cliente;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OCliente {

    static Scanner reader = new Scanner(System.in);
    static int a = 0;
    static int b = 0;

    public static void mandarTamVector() throws IOException {
        int a;
        System.out.println("INGRESA EL TAMAÑO DEL VECTOR");
        a = reader.nextInt();
        socketCliente.os.writeInt(a);
        System.out.println(socketCliente.is.readUTF());
        System.out.println("\n");

        // suponeindo que todo salga bien
        mandarValoresVector(a);
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

    public static void mandarValoresMatrix(int a, int b) {
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

    private static void mandarValoresVector(int v) throws IOException {
        float userInput;
        for (int i = 0; i < v; i++) {
            System.out.println("ingresa el valor " + i);
            userInput = reader.nextFloat();
           socketCliente.os.writeFloat(userInput);
        }
    }
    
}

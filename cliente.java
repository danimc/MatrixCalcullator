import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dist.herramientas.cliente.socketCliente;
import dist.herramientas.cliente.FormulariosCliente;

public class cliente {
	Scanner stdIn = new Scanner(System.in);		
 
	public static void main(String[] args) {
		int opcion;
		System.out.println("BIENVENIDO AL CLIENTE DE LA CALCULADORA CON MATRICES Y VECTORES ");
		do {
			socketCliente.conexionClient();

			FormulariosCliente form = new FormulariosCliente();

			if (socketCliente.echoSocket != null && socketCliente.os != null && socketCliente.is != null) {
				try {

					System.out.println("POR FAVOR SELECCIONA UNA OPCION DEL SIGUENTE MENÚ");
					opcion = mostrarMenu();
					socketCliente.os.writeInt(opcion);

					switch (opcion) {
						case 1:
							form.DatosMultMatrix();
							break;
						case 2:
							form.DatosSumMatrix();
							break;
						case 3:
							form.DatosIgualdad();
							break;
						case 4:
							form.DatosMayor();
							break;
						case 5:
							System.out.println("gracias por utilizar nuestra calculadroa, vuelve pronto");
							socketCliente.is.readInt();
							System.exit(-1);
						default:
							System.out.println("Opcion invalida");
							socketCliente.is.readInt();
							break;
					}

				
					socketCliente.os.close();
					socketCliente.is.close();
					socketCliente.echoSocket.close();
				} catch (InputMismatchException e) {
					System.err.println("\nAtención, debe ingresar un numero:\n");
					
				} catch (IOException e) {
					System.err.println("Fallo la conexion con el servidor");
				}
			}

		} while (true);

	}

	public static int mostrarMenu() {
		Scanner reader = new Scanner(System.in);
		System.out.println("1.- Multiplicación");
		System.out.println("2.- Suma de matrices");
		System.out.println("3.- Igualdad de Matrices");
		System.out.println("4.- Valor mas alto");
		System.out.println("5.- Salir del Programa");
		
		return reader.nextInt();		
	}

}
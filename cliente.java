import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import dist.opMatrix.FormulariosCliente;
import dist.socketCliente;

public class cliente {
	Scanner stdIn = new Scanner(System.in);	
	// FormulariosCliente form = new FormulariosCliente();
	

	public static void main(String[] args) {
		int opcion;
		System.out.println("BIENVENIDO AL CLIENTE DE LA CALCULADORA CON MATRICES Y VECTORES ");
		do {
			socketCliente s = new socketCliente();
			s.conexionClient();

			if (s.echoSocket != null && s.os != null && s.is != null) {
				try {

					System.out.println("POR FAVOR SELECCIONA UNA OPCION DEL SIGUENTE MENÚ");
					opcion = mostrarMenu();
					s.os.writeInt(opcion);

					switch (opcion) {
						case 1:
							float m1[][]; 
							FormulariosCliente.DatosMultMatrix();
							break;
						case 2:

							break;
						case 3:

							break;
						case 4:

							break;
						default:
							System.out.println("Opcion invalida");
							break;
					}

					/*
					 * int userInput; int size = 0;
					 * System.out.println("\n  \nEscribe el tamaño del vector"); size =
					 * stdIn.nextInt(); os.writeInt(size); System.out.println("ingresa los " + size
					 * + " valores"); for (int i = 0; i < size; i++) {
					 * System.out.println("ingresa el valor " + i); userInput = stdIn.nextInt();
					 * os.writeInt(userInput); } System.out.println("el numero mas grande es " +
					 * is.readInt());
					 */
					s.os.close();
					s.is.close();
					s.echoSocket.close();
				} catch (InputMismatchException e) {
					System.err.println("Atención, debe ingresar un numero:");
					System.out.println("reiniciando aplicacion, por favor espere...");
				} catch (IOException e) {
					System.err.println("Fallo la conexion con el servidor");
				}
			}

		} while (true);

	}

	public static int mostrarMenu() {
		Scanner reader = new Scanner(System.in);
		System.out.println("1.- Multiplicar Matriz por Matriz");
		System.out.println("2.- Multiplicar Matriz por Vector");
		System.out.println("3.- Suma de los numero impares");
		System.out.println("4.- Mostrar el numero mas grande");
		System.out.println("5.- Salir del Programa");
		return reader.nextInt();
	}

}
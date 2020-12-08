package dist.herramientas;

import dist.interfaces.IprintMatrix;
import java.io.DataInputStream;
import java.io.IOException;
import dist.socketCliente;

public class impresionesCliente implements IprintMatrix {

    static DataInputStream in = socketCliente.entrada;
    float[][] mResult;

    public void print(float[] v) {
        System.out.print("[");
        for (int i = 0; i < v.length; i++) {
            System.out.print(" " + v[i] + " ");
        }
        System.out.println("]");

    }

    public void print(float[][] m) {
        try {

            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    if (m[i][j] != 0) {
                        System.out.print("" + m[i][j] + ", ");
                    }
                }
                System.out.println("");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("ocurrio un problema al mostrar el resultado" + e);
        } catch (NullPointerException e) {
            System.err.println("no se enconotro dato");
        }
    }

    public void tamMatrixResult(float[][] m) throws IOException {
   
            System.out.println("Obteniendo valores del servidor");
            int a = in.readInt();
            int b = in.readInt();
            float value;
            System.out.println("breakpoint");
            System.out.println("tamaño de la matriz resultante = [" + a + "][" + b + "]");
            m = new float[a][b];

            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    value = in.readFloat();
                    m[i][j] = value;
                }
            }

            print(m);
    }

    public void tamVectorResult(float[] v) {
        try {
            int a = in.readInt();
            float value;
            System.out.println("tamaño dela vector resultante = [" + a + "]");
            v = new float[a];

            for (int i = 0; i < v.length; i++) {
                value = in.readFloat();
                v[i] = value;
            }

            print(v);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error en el tamaño de los datos recibidos");
        }
    }

}

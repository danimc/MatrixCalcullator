package dist.herramientas.server;

import dist.interfaces.Imatrix;

public class operacionesServer implements Imatrix {


	public void multiply(float[][] m, float[] v, float[] r) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				r[i] = r[i] + m[i][j] * v[j];

			}
		}

	}

	public void multiply(float[][] a, float[][] b, float[][] r) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++) {
					r[i][j] = r[i][j] + a[i][k] * b[k][j];
				}
			}
		}

	}

	public void addition(float[][] a, float[][] b, float[][] r) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				r[i][j] = a[i][j] + b[i][j];
			}
		}
	}

	public boolean equal(float[][] a, float[][] b) {
		int flag = 1;
		boolean match = false;

		if (a.length == b.length && a[0].length == b[0].length) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					if (a[i][j] != b[i][j])
						flag = 0;
				}
			}
		} else {
			flag = 0;
		}

		if (flag == 1) {
			match = true;
		}

		else {
			match = false;
		}

		return match;
	}

	public float greatest(float[][] m) {
		float mayor = 0;

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (m[i][j] > mayor) {
					mayor = m[i][j];
				}
			}
		}
		return mayor;
	}

	public float greatest(float[] x) {
		float mayor = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] > mayor) {
				mayor = x[i];
			}
		}
		return mayor;
	}
	
	public void printServer(float m[][]) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (m[i][j] != 0) {
					System.out.print("" + m[i][j] + ", ");
				}
			}
			System.out.println("\n");
		}
	}

	public void printServer(float[] vresultado) {
		for (int i = 0; i < vresultado.length; i++) {
			if (vresultado[i] != 0) {
				System.out.print(" " + vresultado[i] + ", ");
			}
		}
	}
}

package dist.opMatrix;

import dist.interfaces.Imatrix;

public class operaciones implements Imatrix {

	public int greatest(int[] x) {
		int mayor = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] > mayor) {
				mayor = x[i];
			}
		}
		return mayor;
	}
}

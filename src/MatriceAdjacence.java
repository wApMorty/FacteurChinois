@SuppressWarnings("static-access")
public class MatriceAdjacence extends Matrix {

	//Constructeur de matrice d'adjacence
	public MatriceAdjacence(Graph g) {
		super(g.getNbPoints(), g.getNbPoints());
		for (int k = 0; k<g.getNbLignes(); k++) {
			int a = g.getAretes().get(k).a;
			int b = g.getAretes().get(k).b;
			for (int i=0; i<this.n; i++) {
				for (int j=0; j<this.m; j++) {
					if (i==a-1) {
						this.data[i][b-1] = 1;
					} else if (i==b-1) {
						this.data[i][a-1] = 1;
					} else {
						this.data[i][j] = 0;
					}
				}
			}
		}
	}
}
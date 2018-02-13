@SuppressWarnings("static-access")
public class MatriceAdjacence extends Matrix {

	//Constructeur de matrice d'adjacence
	public MatriceAdjacence(Graph g) {
		super(g.nbPoints, g.nbPoints);
		//Remplissage de la matrice avec des 0
		for (int i = 0; i<this.n; i++) {
			for (int j = 0; j<this.m; j++) {
				this.data[i][j] = 0;
			}
		}
		//Remplissage des 1 dans la matrice
		for (int i=1; i<this.n+1; i++) { //Correctif +1 sur les indices car on commence a 1
			for (int j=1; j<this.m+1; j++) {
				for (int k = 0; k<g.nbLignes-1; k++) { //On mets un -1 parce qu'on ne compte pas la premiere ligne du texte
					int a = g.aretes.get(k).a; //OK
					int b = g.aretes.get(k).b; //OK
					if ((i==a)&&(j==b)) {
						this.data[i-1][j-1] = 1;
					} else if ((i==b)&&(j==a)) {
						this.data[i-1][a-1] = 1;
					}
				}
			}
		}
	}
}
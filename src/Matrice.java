import java.util.ArrayList;

public abstract class Matrice {

	protected static int taille;
	protected ArrayList<ArrayList<Integer>> matrice;
	
	public void somme (Matrice a){
		for (int i = 0; i<this.matrice.size(); i++) {
			for (int j = 0; j<this.matrice.get(i).size(); j++) {
				this.matrice.get(i).set(j, this.matrice.get(i).get(j) + a.matrice.get(i).get(j));
			}
		}
	}
	
	public void produit (Matrice b) {
		ArrayList<ArrayList<Integer>> produit = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i<b.matrice.size();i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<this.matrice.get(i).size();j++) {
				int somme = 0;
				for (int k = 0; k<this.matrice.size(); k++) {
					somme+= this.matrice.get(i).get(k)*b.matrice.get(k).get(j);
				}
				ligne.add(somme);
			}
			produit.add(ligne);
		}
		this.matrice = produit;
	}
	
}

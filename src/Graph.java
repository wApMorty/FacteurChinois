import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

	protected static ArrayList<Arete> aretes;
	protected static int nbLignes = 10; //Need to find how to fix it
	protected static int nbPoints;
	
	public Graph () {
		Graph.aretes = new ArrayList<Arete>();
	}
	
	public Graph (String texte) {
		ArrayList<Arete> aretes = new ArrayList<Arete>();
		int[][] data = lectureFichier(texte);//On cree une ArrayList a deux dimensions = matrice)
		for (int i = 1; i<data.length; i++) {
			this.add(data[i][0], data[i][1]);
		}
	}
	
	//Ajouter une arete
	public void add(Arete a) {
		aretes.add(a);
	}
	
	//Methode pour ajouter une arete sans devoir declarer les aretes
	public void add (int a, int b) {
		Arete arete = new Arete(a, b);
		aretes.add(arete);
	}
	
	//Retirer une arete
	public void remove(Arete a) {
		aretes.remove(a);
	}
	
	//Methode pour retirer une arete sans devoir la declarer
	public void remove (int a, int b) {
		Arete arete = new Arete(a, b);
		aretes.remove(arete);
	}
	
	//Methode pour lire un fichier texte et en sortir les valeurs sous forme de tableau a 2 colonnes. Utile dans matriceAdjacence
	//DONE
	public static int[][] lectureFichier(String fichier){
		int[][] output = null;
		try {
			//Initialisation de la lecture du fichier
			FileReader fr = new FileReader(fichier);
			BufferedReader br = new BufferedReader(fr);
			//Initialisation du tableau qu'on va recuperer
			//On sait que la premiere ligne contient forcement le nombre de sommets
			nbPoints = Integer.parseInt(br.readLine());
			output = new int[nbLignes][2];
			//On boucle pour remplir notre tableau a partir des donnees du .txt
			for (int i = 1; i<nbLignes; i++) {
				String[] currentLine = br.readLine().split(" ");
				for (int j = 0; j<currentLine.length; j++) {
					output[i][j] = Integer.parseInt(currentLine[j]);
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}	
	
	//Methode qui renvoie une ArrayList avec les successeurs d'un point x dans un graph g
	//DONE
	public static ArrayList<Integer> successeurs (int x) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i<aretes.size(); i++) {
			int a = aretes.get(i).a;
			int b = aretes.get(i).b;
			if (a == x) {
				s.add(b);
			} else {
				if (b == x) {
					s.add(a);	
				}	
			}
		}
		return s;
	}
	
	public boolean estUnPont(int x, int y) {
		boolean isPont = true;
		//On stocke les aretes retirees pour pouvoir les remettre apres
		ArrayList<Arete> removed = new ArrayList<Arete>();
		//On retire l'arete du graphe
		for (int i = 0; i<aretes.size(); i++) {
			int a = aretes.get(i).a;
			int b = aretes.get(i).b;
			if (((a==x)&&(b==y))||((a==y)&&(b==x))) {
				removed.add(aretes.get(i));
				remove(a,b);
			}
		}
		MatriceAdjacence a = new MatriceAdjacence(this);
		int[][] s = Matrix.identity(nbPoints);
		//On boucle pour calculer la somme S dans l'algorithme de Fleury
		for (int k = 1; k<nbPoints-1; k++) {
			s = Matrix.add(s, Matrix.power(a.data,k));
		}
		//On boucle pour tester la valeur de s_i,j et verifier que l'arete (i,j) est un pont ou non
		if (s[x][y]!=0) {
			isPont = false;
		}
		
		return isPont;
	}
}
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
		int[][] data = lectureFichier(texte);//On cree une ArrayList a deux dimensions = matrice)
		for (int i = 1; i<data.length; i++) {
			this.add(data[i][0], data[i][1]);
		}
	}
	
	//Ajouter une arete
	public void add(Arete a) {
		getAretes().add(a);
	}
	
	//Methode pour ajouter une arete sans devoir declarer les aretes
	public void add (int a, int b) {
		Arete arete = new Arete(a, b);
		getAretes().add(arete);
	}
	
	//Retirer une arete
	public void remove(Arete a) {
		getAretes().remove(a);
	}
	
	//Methode pour retirer une arete sans devoir la declarer
	public void remove (int a, int b) {
		Arete arete = new Arete(a, b);
		getAretes().remove(arete);
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

	public static ArrayList<Arete> getAretes() {
		return aretes;
	}

	public static void setAretes(ArrayList<Arete> aretes) {
		Graph.aretes = aretes;
	}

	public static int getNbLignes() {
		return nbLignes;
	}

	public static void setNbLignes(int nbLignes) {
		Graph.nbLignes = nbLignes;
	}

	public static int getNbPoints() {
		return nbPoints;
	}

	public static void setNbPoints(int nbPoints) {
		Graph.nbPoints = nbPoints;
	}
	
	
	
	
}

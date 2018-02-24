import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

	protected  ArrayList<Arete> aretes;
	protected  int nbLignes = 10; //Need to find how to fix it
	protected  int nbPoints;
	
	public Graph () {
		this.aretes = new ArrayList<Arete>();
	}
	
	public Graph (String texte) {
		this.aretes = new ArrayList<Arete>();
		int[][] data = lectureFichier(texte);//On cree une ArrayList a deux dimensions = matrice)
		for (int i = 1; i<data.length; i++) {
			Sommet a = new Sommet(data[i][0]);
			Sommet b = new Sommet(data[i][1]);
			this.add(a, b);
		}
		
	}
	
	//Ajouter une arete
	public void add(Arete a) {
		aretes.add(a);
	}
	
	//Methode pour ajouter une arete sans devoir declarer les aretes
	public void add (Sommet a, Sommet b) {
		Arete arete = new Arete(a, b);
		aretes.add(arete);
	}
	
	//Retirer une arete
	public void remove(Arete a) {
		for (int i = 0; i<aretes.size();i++) {
			if (aretes.get(i).equals(a)) {
				aretes.remove(i);
			}
		}
	}
	
	//Methode pour retirer une arete sans devoir la declarer
	public void remove (Sommet a, Sommet b) {
		Arete arete = new Arete(a, b);
		for (int i = 0; i<aretes.size();i++) {
			if ((aretes.get(i).a.num==arete.a.num)&&(aretes.get(i).b.num==arete.b.num)) {
				aretes.remove(i);
			}
		}
	}
	
	//Methode pour lire un fichier texte et en sortir les valeurs sous forme de tableau a 2 colonnes. Utile dans matriceAdjacence
	//DONE
	public int[][] lectureFichier(String fichier){
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
	public ArrayList<Sommet> successeurs (Sommet x) {
		ArrayList<Sommet> s = new ArrayList<Sommet>();
		for (int i = 0; i<aretes.size(); i++) {
			Sommet a = aretes.get(i).a;
			Sommet b = aretes.get(i).b;
			if (a.num == x.num) {
				s.add(b);
			} else {
				if (b.num == x.num) {
					s.add(a);	
				}	
			}
		}
		return s;
	}
	
	public boolean estUnPont(Sommet x, Sommet y) {
		boolean isPont = true;
		//On stocke les aretes retirees pour pouvoir les remettre apres
		ArrayList<Arete> removed = new ArrayList<Arete>();
		//On retire l'arete du graphe
		for (int i = 0; i<aretes.size(); i++) {
			Sommet a = aretes.get(i).a;
			Sommet b = aretes.get(i).b;
			if (((a.num==x.num)&&(b.num==y.num))||((a.num==y.num)&&(b.num==x.num))) {
				removed.add(aretes.get(i));
				remove(a,b);
			}
		}
		MatriceAdjacence a = new MatriceAdjacence(this);
		int[][] s = new int[nbPoints][nbPoints]; 
		s = Matrix.identity(nbPoints);
		//On boucle pour calculer la somme S dans l'algorithme de Fleury
		for (int k = 1; k<nbPoints-1; k++) {
			s = Matrix.add(s, Matrix.power(a.data,k));
		}
		//On boucle pour tester la valeur de s_i,j et verifier que l'arete (i,j) est un pont ou non
		if (s[x.num-1][y.num-1]!=0) {
			isPont = false;
		}
		
		//On remet les aretes enlevees pour le test dans le graphe
		for (int i =0; i<removed.size(); i++) {
			aretes.add(removed.get(i));
		}
		
		return isPont;
	}
	
	public ArrayList<Sommet> Fleury (Sommet x){
		ArrayList<Sommet> C = new ArrayList<Sommet>(); //Integer au lieu de int parce que les types de base marchent pas, need un wrapper
		C.add(x);
		while (!aretes.isEmpty()) { //Tant que G n'est pas vide
			//On recupere l'ensemble des successeurs qu'on stocke dans une liste
			ArrayList<Sommet> successeurs = successeurs(x);
			int i = 0;
			Sommet y = successeurs.get(i);
			while ((estUnPont(x, y))&&(successeurs.size()>1)) {
				i++;
				y = successeurs.get(i);
			}
			C.add(y);
			//On parcourt G pour retrouver l'arete (x,y) et la remove
			for (int k = 0; k<aretes.size(); k++) {
				Sommet a = aretes.get(k).a;
				Sommet b = aretes.get(k).b;
				if (((a.num==x.num)&&(b.num==y.num))||((a.num==y.num)&&(b.num==x.num))) {
					remove(a,b);
				}
			}
			x = y;
		}
		
		return C;
	}
}
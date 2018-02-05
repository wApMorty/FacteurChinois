import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Temps pour programmer cette partie: 3.5H.h
 */

public class Niveau0 {

	private static int nbPoints;
	private static int nbLignes=10;
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = drawGraph("facteur6_1.txt");
		System.out.println(graph);
		ArrayList<ArrayList<Integer>> matrice = matriceAdjacence(graph);
		System.out.println(matrice);
	}
	
	//Methode pour lire un fichier texte et en sortir les valeurs sous forme de tableau a 2 colonnes. Utile dans matriceAdjacence
	//OK : Plus toucher
	public static int[][] lectureFichier(String fichier){
		int[][] output = null;
		try {
			//Initialisation de la lecture du fichier
			FileReader fr = new FileReader(fichier);
			BufferedReader br = new BufferedReader(fr);
			//Initialisation du tableau qu'on va recuperer
			//On sait que la premiere ligne contient forcement le nombre de lignes
			nbPoints = Integer.parseInt(br.readLine());
			output = new int[nbLignes][2];
			//On boucle pour remplir notre tableau a partir des donnees du .txt
			for (int i = 1; i<nbLignes; i++) {
				String[] ligne = br.readLine().split(" ");
				for (int j = 0; j<ligne.length; j++) {
					output[i][j] = Integer.parseInt(ligne[j]);
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	//Methode qui transforme un fichier texte en matrice d'adjacence. On est passe d'un [][]int a une double ArrayList car bloquant pour la methode estUnPont
	// DONE
	public static ArrayList<ArrayList<Integer>> matriceAdjacence(String fichier){
		//Extraction des donnees
		int[][] data = lectureFichier(fichier);
		//Initialisation de la matrice resultat
		ArrayList<ArrayList<Integer>> mat = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i<nbPoints; i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<nbPoints; j++) {
				ligne.add(0);
			}
			mat.add(ligne);
		}
		//Remplissage de la matrice
		for (int i = 1; i<nbPoints+1;i++) {
			for (int j = 1; j<data.length; j++) {
				int a = data[j][0], b = data[j][1];
				if (a==i) {
					mat.get(i-1).set(b-1, 1);
				}
				if (b==i) {
					mat.get(i-1).set(a-1, 1);
				}
			}
		}
		return mat;		 
	}
	
	//Constructeur matrice d'adjacence a partir d'un graphe
	//TO DO
	public static ArrayList<ArrayList<Integer>> matriceAdjacence(ArrayList<ArrayList<Integer>> graph){
		ArrayList<ArrayList<Integer>> mat = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i<nbPoints; i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<nbPoints; j++) {
				ligne.add(0);
			}
			mat.add(ligne);
		}
		//Remplissage de la matrice
		for (int i = 1; i<nbPoints+1;i++) {
			for (int j = 1; j<graph.size(); j++) {
				int a = graph.get(j).get(0);
				int b = graph.get(j).get(1);
				if (a==i) {
					mat.get(i-1).set(b-1, 1);
				}
				if (b==i) {
					mat.get(i-1).set(a-1, 1);
				}
			}
		}
		return mat;	
	}
	
	//Methode qui permet de convertir la matrice d'adjacence en pseudo graph (passe d'un int[][] à une double ArrayList, necessaire pour l'algorithme de Fleury
	// OK : Plus toucher
	public static ArrayList<ArrayList<Integer>> drawGraph(String texte){
		int[][] data = lectureFichier(texte);
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //On cree une ArrayList a deux dimensions = matrice)
		for (int i = 1; i<data.length; i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<data[0].length; j++) {
				ligne.add(data[i][j]);
			}
			graph.add(ligne);
		}
		return graph;
	}
	
	//TO DO
	public static boolean estUnPont(ArrayList<ArrayList<Integer>> graph, int x, int y) {
		boolean isPont = true;
		//On retire l'arete du graphe
		for (int i = 0; i<graph.size(); i++) {
			int a = graph.get(i).get(0);
			int b = graph.get(i).get(1);
			if (((a==x)&&(b==y))||((a==y)&&(b==x))) {
				graph.remove(i);
			}
		}
		ArrayList<ArrayList<Integer>> matrice = 
		return isPont;
	}
	
	//TO DO
	public static ArrayList<Integer> Fleury (ArrayList<ArrayList<Integer>> G, int x){
		ArrayList<Integer> C = new ArrayList<Integer>(); //Integer au lieu de int parce que les types de base marchent pas, need un wrapper
		C.add(x);
		while (!G.isEmpty()) { //Tant que G n'est pas vide
			
		}
		
		return C;
	}

	//Calcule le produit entre une matrice a et une matrice b
	//DONE
	public static ArrayList<ArrayList<Integer>> produit (ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
		ArrayList<ArrayList<Integer>> produit = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i<b.size();i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<a.get(i).size();j++) {
				int somme = 0;
				for (int k = 0; k<a.size(); k++) {
					somme+= a.get(i).get(k)*b.get(k).get(j);
				}
				ligne.add(somme);
			}
			produit.add(ligne);
		}
		return produit;
	}
	
	//Return une matrice a a la puissance n
	//DONE
	public static ArrayList<ArrayList<Integer>> puissance (ArrayList<ArrayList<Integer>> a, int n){
		ArrayList<ArrayList<Integer>> a0 = new ArrayList<ArrayList<Integer>>();
		//Creation de la copie de a, a0
		for (int i = 0; i<a.size(); i++) {
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			for (int j = 0; j<a.get(0).size(); j++) {
				ligne.add(a.get(i).get(j));
			}
			a0.add(ligne);
		}
		//Bouclage de la montee en puissance de a^n
		for (int k = 1; k<n; k++) {
			a0 = produit(a,a0);
		}
		return a0;
	}
}

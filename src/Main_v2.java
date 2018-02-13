import java.util.ArrayList;
@SuppressWarnings("static-access")

public class Main_v2 {


	public static void main(String[] args) {

		String texte = "facteur6_1.txt";
		Graph g = new Graph(texte);
		MatriceAdjacence m = new MatriceAdjacence(g);
		ArrayList<Integer> chemin = Fleury(g, 1);
		System.out.println(chemin);

	}
	
	public static boolean estUnPont(Graph graph, int x, int y) {
		boolean isPont = true;
		//On stocke les aretes retirees pour pouvoir les remettre apres
		ArrayList<Arete> removed = new ArrayList<Arete>();
		//On retire l'arete du graphe
		for (int i = 0; i<graph.aretes.size(); i++) {
			int a = graph.aretes.get(i).a;
			int b = graph.aretes.get(i).b;
			if (((a==x)&&(b==y))||((a==y)&&(b==x))) {
				removed.add(graph.aretes.get(i));
				graph.remove(a,b);
			}
		}
		MatriceAdjacence a = new MatriceAdjacence(graph);
		int[][] s = Matrix.identity(graph.nbPoints);
		//On boucle pour calculer la somme S dans l'algorithme de Fleury
		for (int k = 1; k<graph.nbPoints-1; k++) {
			s = Matrix.add(s, Matrix.power(a.data,k));
		}
		//On boucle pour tester la valeur de s_i,j et verifier que l'arete (i,j) est un pont ou non
		if (s[x][y]!=0) {
			isPont = false;
		}
		
		//On remet les aretes retirees a l'interieur du graphe
		for (int i = 0; i<removed.size(); i++) {
			graph.add(removed.get(i));
		}
		
		return isPont;
	}
	
	//Methode qui renvoie une ArrayList avec les successeurs d'un point x dans un graph g
	//DONE
	public static ArrayList<Integer> successeurs (Graph g, int x) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i<g.aretes.size(); i++) {
			int a = g.aretes.get(i).a;
			int b = g.aretes.get(i).b;
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
	
	//NEED A TEST
	public static ArrayList<Integer> Fleury (Graph G, int x){
		ArrayList<Integer> C = new ArrayList<Integer>(); //Integer au lieu de int parce que les types de base marchent pas, need un wrapper
		C.add(x);
		//On va enlever des aretes plus tard donc on les stock pour pouvoir les remettre apres traitement
		ArrayList<Arete> removed = new ArrayList<Arete>();
		while (!G.aretes.isEmpty()) { //Tant que G n'est pas vide
			//On recupere l'ensemble des successeurs qu'on stocke dans une liste
			ArrayList<Integer> successeurs = successeurs(G, x);
			System.out.println(successeurs);
			int i = 0;
			int y = successeurs.get(i);
			while ((estUnPont(G, x, y))&&(successeurs.size()>1)) {
				i++;
				y = successeurs.get(i);
			}
			C.add(y);
			//On parcourt G pour retrouver l'arete (x,y) et la remove
			for (int k = 0; k<G.aretes.size(); k++) {
				int a = G.aretes.get(k).a;
				int b = G.aretes.get(k).b;
				if (((a==x)&&(b==y))||((a==y)&&(b==x))) {
					removed.add(G.aretes.get(k));
					G.remove(a,b);
				}
			}
			x = y;
		}
		
		return C;
	}

}

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

	//NEED A FIX
	public static ArrayList<Integer> Fleury (Graph G, int x){
		ArrayList<Integer> C = new ArrayList<Integer>(); //Integer au lieu de int parce que les types de base marchent pas, need un wrapper
		C.add(x);
		//On va enlever des aretes plus tard donc on les stock pour pouvoir les remettre apres traitement
		ArrayList<Arete> removed = new ArrayList<Arete>();
		while (!G.aretes.isEmpty()) { //Tant que G n'est pas vide
			//On recupere l'ensemble des successeurs qu'on stocke dans une liste
			ArrayList<Integer> successeurs = G.successeurs(x);
			System.out.println(successeurs);
			int i = 0;
			int y = successeurs.get(i);
			while ((G.estUnPont(x, y))&&(successeurs.size()>1)) {
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

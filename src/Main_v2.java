import java.util.ArrayList;
@SuppressWarnings("static-access")

public class Main_v2 {

	public static void main(String[] args) {

		String texte = "facteur6_1.txt";
		Graph g = new Graph(texte);
		Sommet depart = new Sommet(2);//OK JUSQUE LA
		ArrayList<Sommet> chemin = g.Fleury(depart);
		System.out.println(chemin);

	}
}
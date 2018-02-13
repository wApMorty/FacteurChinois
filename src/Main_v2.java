
public class Main_v2 {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		String texte = "facteur6_1.txt";
		Graph g = new Graph(texte);
		MatriceAdjacence m = new MatriceAdjacence(g);
		m.show();

	}

}

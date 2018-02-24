
public class Arete {
	
	protected Sommet a, b;
	
	public Arete(Sommet x, Sommet y) {
		this.a = x;
		this.b = y;
	}
	
	public String toString() {
		return (this.a.num + " - " + this.b.num);
	}
	
}

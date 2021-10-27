public class Item {

	protected static String nom;
	protected static int degat;

	// CONSTRUCTOR
	public Item(String nom, int degat) {
		this.nom = nom;
		this.degat = degat;
	}

	// GETTERS & SETTERS
	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

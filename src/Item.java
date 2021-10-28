import java.io.Serializable;

public class Item implements Serializable{

	protected static String nom;

	// CONSTRUCTOR
	public Item(String nom) {
		this.nom = nom;
	}

	// GETTERS & SETTERS

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

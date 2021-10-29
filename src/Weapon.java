import java.io.Serializable;
import java.util.Random;

public class Weapon extends Item implements Serializable{

	private static int length;
	protected String name;
	protected int damage;
	protected String categorie;

	public Weapon(String name, int damage, String categorie) {
		super(nom);
		this.name = name;
		this.damage = damage;
		this.categorie = categorie;
	}

	public static Weapon[] listWeapon() {
		Random rand = new Random();
		Weapon[] weapons = new Weapon[10];
		weapons[0] = new Weapon("Epée en bois", 10, "epee");
		weapons[1] = new Weapon("Epée en fer", 15, "epee");
		weapons[2] = new Weapon("Epée en or", 20, "epee");
			
		return weapons;
	}
			
	// getters - setters
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}

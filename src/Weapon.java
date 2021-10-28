import java.util.HashMap;

public class Weapon extends Item {

	protected int damage;

	protected String category;
	static HashMap<String, Integer> weapon = new HashMap<String, Integer>();

	public Weapon(String nom, int damage, String category) {
		super(nom);
		this.damage = damage;
		this.category = category;
	}

	public static Weapon[] listWeapon() {
		Weapon[] weapons = new Weapon[10];
		weapons[0] = new Weapon("Epée en bois", 10, "epee");
		weapons[1] = new Weapon("Epée en fer", 15, "epee");
		weapons[2] = new Weapon("Epée en or", 15, "epee");
		return weapons;

	}

	public String getCategorie() {
		return category;
	}

	public void setCategorie(String categorie) {
		this.category = categorie;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

}

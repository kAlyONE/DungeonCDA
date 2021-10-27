import java.util.HashMap;
import java.util.Map.Entry;

public class Weapon extends Item {

	protected int damage;
<<<<<<< HEAD
	protected String category;
	static HashMap<String, Integer> weapon = new HashMap<String, Integer>();

	public Weapon(String nom, int damage, String category) {
		super(nom);
		this.damage = damage;
		this.category = category;
	}

	public static void listWeapons() {

		weapon.put("Epée en bois", 10);
		weapon.put("Epée en fer", 15);
		weapon.put("Epée en or", 20);

		for (Entry<String, Integer> mapentry : weapon.entrySet()) {
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
	}
	
	public static void attackMonster() {
//		Weapon.listWeapons();
//		System.out.println(Weapon.weapon.get("Epée en or"));
=======
	protected String categorie;

	public Weapon(String name, int damage, String categorie) {
		super(nom, degat);
		this.damage = damage;
		this.categorie = categorie;
	}
	
	public static Weapon[] listWeapon() {
		Weapon[] weapons = new Weapon[10];
		weapons[0] = new Weapon("Epée en bois", 10, "epee");
		weapons[1] = new Weapon("Epée en fer", 15, "epee");
		weapons[2] = new Weapon("Epée en or", 15, "epee");
		return weapons;
>>>>>>> main
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

}

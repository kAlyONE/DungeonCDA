import java.util.HashMap;
import java.util.Map.Entry;

public class Weapon extends Item {

	protected int damage;
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
	}

	public int getDamage() {
		return damage;
	}

}

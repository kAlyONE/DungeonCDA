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

		weapon.put("Ep�e en bois", 10);
		weapon.put("Ep�e en fer", 15);
		weapon.put("Ep�e en or", 20);

		for (Entry<String, Integer> mapentry : weapon.entrySet()) {
			System.out.println("cl�: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
	}
	
	public static void attackMonster() {
//		Weapon.listWeapons();
//		System.out.println(Weapon.weapon.get("Ep�e en or"));
	}

	public int getDamage() {
		return damage;
	}

}

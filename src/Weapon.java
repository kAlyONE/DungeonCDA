public class Weapon extends Item {

	protected int damage;

	public Weapon(String name, int damage) {
		super(nom, degat);
		this.damage = damage;
	}
	
	public static Weapon[] listWeapon() {
		Weapon[] weapons = new Weapon[10];
		weapons[0] = new Weapon("Epée en bois", 10);
		weapons[1] = new Weapon("Epée en fer", 15);
		weapons[2] = new Weapon("Epée en or", 15);
		return weapons;
	}

	public int getDamage() {
		return damage;
	}

}

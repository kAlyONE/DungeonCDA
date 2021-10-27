public class Weapon extends Item {

	protected int damage;
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

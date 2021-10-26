public class Monster extends Entity {

	private String nom;
	private int strengthMonster;
	private int healthMonster;
	private int lvlMonster;

	// CONSTRUCTOR

	public Monster(String nom, int strengthMonster, int healMonster, int lvlMonster, int abs, int ord) {
		super(abs, ord);
		this.nom = nom;
		this.strengthMonster = strengthMonster;
		this.healthMonster = healMonster;
		this.lvlMonster = lvlMonster;
	}

	// GETTERS & SETTERS

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getStrengthMonster() {
		return strengthMonster;
	}

	public void setStrengthMonster(int strengthMonster) {
		this.strengthMonster = strengthMonster;
	}

	public int getHealthMonster() {
		return healthMonster;
	}

	public void setHealthMonster(int healthMonster) {
		this.healthMonster = healthMonster;
	}

	public int getLvlMonster() {
		return lvlMonster;
	}

	public void setLvlMonster(int lvlMonster) {
		this.lvlMonster = lvlMonster;
	}

	// METHODS

	public boolean isAlive() {
		return this.healthMonster > 0;
	}

	public String toString() {
		return nom;
	}
	
	public static void deguatMonster() {
		
	}
}
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class Hero extends Entity implements Serializable{
	protected int lifePoints;
	protected String nom;
	protected int attackPoints;
	protected int gold;
	private Weapon weapon;
	private List<Item> inventory;
	private int healPotions;
	private int strenghtPotions;
	private static final long serialVersionUID = 1L;
	
	public int getHealPotions() {
		return healPotions;
	}

	public void setHealPotions(int healPotions) {
		this.healPotions = healPotions;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	private Directions view;

	public Hero(String nom, int lifePoints, int attackPoints, int abs, int ord) {
		super(abs, ord);

		this.nom=nom;
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
		this.gold = 0;
		this.weapon = new Weapon("Epée en bois", 0, "epee");
		this.healPotions = 3;
		this.inventory = new ArrayList<Item>(10);
		this.strenghtPotions = 3;
		view = Directions.RIGHT;
	}

	public Directions getView() {
		return view;
	}

	public void setView(Directions view) {
		this.view = view;
	}

	public boolean isAlive() {
		return this.lifePoints > 0;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {

		this.lifePoints = lifePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void addItem(Item i) {
		inventory.add(i);
	}

	public void removeItem(Item i) {
		inventory.remove(i);
	}

	public Item getItem(int index) {
		if (index < 0 || index >= inventory.size()) {
			return null;
		}
		return inventory.get(index);
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	// METHODS

	public void usePotion() {
		healPotions--;
		lifePoints += 10;
	}
	
	public int getStrenghtPotions() {
        return strenghtPotions;
    }

    public void setStrenghtPotions(int strenghtPotions) {
        this.strenghtPotions = strenghtPotions;
    }

	public void showInventory(Hero player, String params) {

		System.out.println("Mon Inventaire :");

		List<Item> inventory = player.getInventory();

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).equals("")) {
				System.out.println("- ");
			} else {
				System.out.println(i + " " + inventory.get(i).getNom());
			}
		}

	}

	/*
	 * public void equip(Hero hero) { System.out.println("Vous equipez " + this +
	 * " (" + damage + " dommages)"); Item iNow = hero.getWeapon();
	 * hero.addItem(iNow); hero.setWeapon(this); }
	 */

}

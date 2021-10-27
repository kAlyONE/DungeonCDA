import java.util.ArrayList;
import java.util.List;

public class Hero extends Entity {
	protected int lifePoints;
	
	protected int attackPoints;
	protected int gold;
	private Weapon weapon;
	private List<Item> inventory;
	private int healPotions;

	public Hero(int lifePoints, int attackPoints, int abs, int ord) {
		super(abs, ord);

		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
		this.gold = 0;
		this.weapon = weapon;
		this.healPotions = 3;
		this.inventory = new ArrayList<Item>(10);
		
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

	public int setLifePoints() {
		// TODO Auto-generated method stub
		return 100;
	}

	/*
	 * public void equip(Hero hero) { System.out.println("Vous equipez " + this +
	 * " (" + damage + " dommages)"); Item iNow = hero.getWeapon();
	 * hero.addItem(iNow); hero.setWeapon(this); }
	 */

}

import java.io.Serializable;
import java.util.Random;

public class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int abs;
	private int ord;

	public Entity(int abs, int ord) {
		this.abs = abs;
		this.ord = ord;
	}

	public int getAbs() {
		return abs;
	}

	public void setAbs(int abs) {
		this.abs = abs;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	// method monstre en aleatoire
	public static Monster randomMonster(int abs, int ord) {

		Random rand = new Random();
		Monster[] enemies = new Monster[5];
		enemies[0] = new Monster("PIKACHU", 10, 50, 1, abs, ord);
		enemies[1] = new Monster("GENGAR", 10, 50, 1, abs, ord);
		enemies[2] = new Monster("SALAMECHE", 10, 50, 1, abs, ord);
		enemies[3] = new Monster("MEW", 10, 50, 1, abs, ord);
		enemies[4] = new Monster("TOGEPI", 10, 50, 1, abs, ord);

		Monster enemy = enemies[rand.nextInt(enemies.length)];

		return enemy;

	}

}

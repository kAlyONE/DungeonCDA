public class Room {

	private boolean wallUp;
	private boolean wallRight;
	private boolean wallLeft;
	private boolean wallDown;
	private int abs;
	private int ord;
	private boolean isChecked;
	private boolean isVisited;
	private Monster monster;

	// CONSTRUCTOR

	public Room(int x, int y) {
		abs = x;
		ord = y;
		// TODO Auto-generated constructor stub
		wallUp = true;
		wallRight = true;
		wallLeft = true;
		wallDown = true;
		isChecked = false;
		isVisited = false;

		int random = randInt(0, 100);

		if (random < 10) {
			monster = Entity.randomMonster(x, y);
		}
	}

	// GETTERS / SETTERS

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean isWallUp() {
		return wallUp;
	}

	public void setWallUp(boolean wallUp) {
		this.wallUp = wallUp;
	}

	public boolean isWallRight() {
		return wallRight;
	}

	public void setWallRight(boolean wallRight) {
		this.wallRight = wallRight;
	}

	public boolean isWallLeft() {
		return wallLeft;
	}

	public void setWallLeft(boolean wallLeft) {
		this.wallLeft = wallLeft;
	}

	public boolean isWallDown() {
		return wallDown;
	}

	public void setWallDown(boolean wallDown) {
		this.wallDown = wallDown;
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

	public static int randInt(int min, int max) {

		int x = (int) ((Math.random() * ((max - min) + 1)) + min);

		return x;

	}

}

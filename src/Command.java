public abstract class Command {

	protected Labyrinthe labyrinthe;

	public Command(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
	}

	public abstract void act(String params);

	public abstract String description();

	public static String getAction(String command) {
		if (command.isEmpty()) {
			return null;
		}
		String[] s = command.split(" ");
		if (s.length >= 1) {
			return command.split(" ")[0];
		}
		return null;
	}

	public static String getParams(String command) {
		int i = command.indexOf(" ");
		if (i == -1)
			return null;
		else
			return command.substring(i + 1, command.length());
	}
}
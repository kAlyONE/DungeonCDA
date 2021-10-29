import java.util.Random;
import java.util.Scanner;

public class Labyrinthe {

	private int x; // Longueur
	private int y; // Hauteur
	private Room[][] cells; // Cellules

	// Constructeur du labyrinthe
	public Labyrinthe(int x, int y) {

		this.x = x;
		this.y = y;

	}

	public Room[][] generate() {

		// Genere les salles du labyrinthe
		// 1 - Toutes les salles ont les 4 sideWalls booleens sur TRUE
		// 2 - Toutes les salles ont des coordonnées [abs,ord]
		// 3 - Toutes les salles ont un attribut isChecked et isVisited fixés à false

		cells = new Room[x][y];

		for (int i = 0; i < y; i++) {

			for (int j = 0; j < x; j++) {

				cells[i][j] = new Room(i, j);

			}

		}

		return cells;

	}

	public void fouiller(Hero player, Labyrinthe maze, Room[][] cells) {

		if (cells[player.getAbs()][player.getOrd()].isHiddenPotion()) {
			int rand = randInt(0, 100);
			if (rand < 50) {
				System.out.println("Vous trouvez une Potion de Vie !");
				player.setHealPotions(player.getHealPotions() + 1);
				cells[player.getAbs()][player.getOrd()].setHiddenPotion(false);

			} else {
				System.out.println("Vous trouvez une Potion de Force !");
				player.setStrenghtPotions(player.getStrenghtPotions() + 1);
				cells[player.getAbs()][player.getOrd()].setHiddenPotion(false);
			}
		} else {
			System.out.println("Vous fouillez la salle... Mais vous ne trouvez rien.");
		}
	}

	// Affichage du labyrinthe
	public void display(Hero player, Room[][] cellules, Entity sortie) {

		for (int i = 0; i < y; i++) {

			// Dessine le haut du labyrinthe

			for (int j = 0; j < x; j++) {

				if (cells[j][i].isVisited()) {
					System.out.print(cells[j][i].isWallUp() ? "+---" : "+   ");
				} else {
					System.out.print(cells[j][i].isWallUp() ? "    " : "    ");
				}

			}

			System.out.println("+");

			// Génération des barres verticales via conditions ternaires vérifiant si un mur
			// existe

			for (int j = 0; j < x; j++) {

				if (player.getAbs() == cells[j][i].getAbs() && player.getOrd() == cells[j][i].getOrd()) {

					switch (player.getView()) {
					case RIGHT:
						System.out.print(cells[j][i].isWallLeft() ? "| > " : "  > ");
						break;
					case LEFT:
						System.out.print(cells[j][i].isWallLeft() ? "| < " : "  < ");
						break;
					case UP:
						System.out.print(cells[j][i].isWallLeft() ? "| A " : "  A ");
						break;
					case DOWN:
						System.out.print(cells[j][i].isWallLeft() ? "| V " : "  V ");
						break;
					}
				}

				else if (sortie.getAbs() == cells[j][i].getAbs() && sortie.getOrd() == cells[j][i].getOrd()) {

					System.out.print(cells[j][i].isWallLeft() ? "| x " : "  x ");

				}

				else {

					if (cells[j][i].isVisited()) {

						System.out.print(cells[j][i].isWallLeft() ? "|   " : "    ");

					}

					else {
						System.out.print(cells[j][i].isWallLeft() ? "    " : "    ");
					}

				}

			}

			System.out.println("|");

		}

		// Dessine le bas du labyrinthe

		for (int j = 0; j < x; j++) {

			System.out.print("+---");

		}

		System.out.println("+");

	}

	public void moveRight(Hero player, Room[][] cells) {

		// Deplacement vers la droite si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur droit du labyrinthe

		switch (player.getView()) {

		// Avancer ( Ok )

		case RIGHT:
			if (!cells[player.getAbs()][player.getOrd()].isWallDown() && player.getOrd() < y - 1) {
				cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				player.setOrd(player.getOrd() + 1);
				player.setView(Directions.DOWN);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() < y - 1) {
					cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				}
			}
			break;
		case LEFT:
			if (!cells[player.getAbs()][player.getOrd()].isWallUp() && player.getOrd() > 0) {
				cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				player.setOrd(player.getOrd() - 1);
				player.setView(Directions.UP);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() > 0) {
					cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				}
			}
			break;
		case UP:
			if (!cells[player.getAbs()][player.getOrd()].isWallRight() && player.getAbs() < x - 1) {
				cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() + 1);
				player.setView(Directions.RIGHT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() < x - 1) {
					cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case DOWN:
			if (!cells[player.getAbs()][player.getOrd()].isWallLeft() && player.getAbs() > 0) {
				cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() - 1);
				player.setView(Directions.LEFT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() > 0) {
					cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		}
	}

	public void moveForward(Hero player, Room[][] cells) {

		// Deplacement vers le haut si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur nord du labyrinthe

		switch (player.getView()) {

		// Avancer ( Ok )

		case RIGHT:
			if (!cells[player.getAbs()][player.getOrd()].isWallRight() && player.getAbs() < x - 1) {
				cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() + 1);
				player.setView(Directions.RIGHT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() < x - 1) {
					cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case LEFT:
			if (!cells[player.getAbs()][player.getOrd()].isWallLeft() && player.getAbs() > 0) {
				cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() - 1);
				player.setView(Directions.LEFT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() > 0) {
					cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case UP:
			if (!cells[player.getAbs()][player.getOrd()].isWallUp() && player.getOrd() > 0) {
				cells[player.getOrd() - 1][player.getOrd()].setVisited(true);
				player.setOrd(player.getOrd() - 1);
				player.setView(Directions.UP);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() > 0) {
					cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				}
			}
			break;
		case DOWN:
			if (!cells[player.getAbs()][player.getOrd()].isWallDown() && player.getOrd() < y - 1) {
				cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				player.setOrd(player.getOrd() + 1);
				player.setView(Directions.DOWN);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() < y - 1) {
					cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				}
			}
			break;
		}
	}

	public void moveBack(Hero player, Room[][] cells) {

		// Deplacement vers le bas si aucun mur ne barre la route et que le joueur n'est
		// pas contre le mur sud du labyrinthe

		switch (player.getView()) {

		// Reculer

		case RIGHT:
			if (!cells[player.getAbs()][player.getOrd()].isWallLeft() && player.getAbs() > 0) {
				cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() - 1);
				player.setView(Directions.LEFT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() > 0) {
					cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case LEFT:
			if (!cells[player.getAbs()][player.getOrd()].isWallRight() && player.getAbs() < x - 1) {
				cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() + 1);
				player.setView(Directions.RIGHT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() > 0) {
					cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case UP:
			if (!cells[player.getAbs()][player.getOrd()].isWallDown() && player.getOrd() < x - 1) {
				cells[player.getOrd() + 1][player.getOrd()].setVisited(true);
				player.setOrd(player.getOrd() + 1);
				player.setView(Directions.DOWN);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() < x - 1) {
					cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				}
			}
			break;
		case DOWN:
			if (!cells[player.getAbs()][player.getOrd()].isWallUp() && player.getOrd() > 0) {
				cells[player.getOrd() - 1][player.getOrd()].setVisited(true);
				player.setOrd(player.getOrd() - 1);
				player.setView(Directions.UP);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() > 0) {
					cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				}
			}
			break;
		}
	}

	public void moveLeft(Hero player, Room[][] cells) {

		// Deplacement vers la gauche si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur gauche du labyrinthe

		// Tourner à droite

		switch (player.getView()) {

		// Reculer

		case RIGHT:
			if (!cells[player.getAbs()][player.getOrd()].isWallUp() && player.getOrd() > 0) {
				cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				player.setOrd(player.getOrd() - 1);
				player.setView(Directions.UP);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() > 0) {
					cells[player.getAbs()][player.getOrd() - 1].setVisited(true);
				}
			}
			break;
		case LEFT:
			if (!cells[player.getAbs()][player.getOrd()].isWallDown() && player.getOrd() < y - 1) {
				cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				player.setOrd(player.getOrd() + 1);
				player.setView(Directions.DOWN);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getOrd() < y - 1) {
					cells[player.getAbs()][player.getOrd() + 1].setVisited(true);
				}
			}
			break;
		case UP:
			if (!cells[player.getAbs()][player.getOrd()].isWallLeft() && player.getAbs() > 0) {
				cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() - 1);
				player.setView(Directions.LEFT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() > 0) {
					cells[player.getAbs() - 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		case DOWN:
			if (!cells[player.getAbs()][player.getOrd()].isWallRight() && player.getAbs() < x - 1) {
				cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				player.setAbs(player.getAbs() + 1);
				player.setView(Directions.RIGHT);
			} else {
				System.out.println("Il y a un mur de ce coté.");
				if (player.getAbs() < x - 1) {
					cells[player.getAbs() + 1][player.getOrd()].setVisited(true);
				}
			}
			break;
		}
	}

	public void generateRandom() throws InterruptedException {

		// On récupère le total de cases du labyrinthe
		int cases = x * y;

		// Variable qui va récupérer un entier aléatoire
		int random = 0;

		// L'entité inexistante qui va dessiner les chemins
		Entity checker = new Entity(0, 0);

		// Boucle de génération du labyrinthe, s'arrête quand toutes les cases ont été
		// parcourues
		while (cases > 0) {

			// Si le checker est sur une case qui n'a jamais été parcourue, on la notifie
			// comme parcourue en passant isChecked à TRUE

			if (!cells[checker.getAbs()][checker.getOrd()].isChecked()) {
				cases--;
				cells[checker.getAbs()][checker.getOrd()].setChecked(true);
			}

			// Randomize la variable random

			random = randInt(0, 3);

			// Switch, en fonction du chiffre de sortie, on prend une direction

			switch (random) {

			// Pour chaque direction, on vérifie que la case sur laquelle en va n'est pas
			// checkée avant de modifier les booléens des murs
			// Une fois ceci fait, on change le mur vers lequel nous nous dirigeons, et
			// celui de la salle qui vient vers nous en false
			// ( Pour briser le mur )
			// Je déplace ensuite mon checker dans la salle suivante avant de lui faire
			// prendre une nouvelle direction
			// Si la salle a déjà été checkée, il s'y déplace simplement en ignorant les
			// murs

			// HAUT
			case 0:
				if (checker.getOrd() > 0) {
					if (!cells[checker.getAbs()][checker.getOrd() - 1].isChecked()) {
						cells[checker.getAbs()][checker.getOrd() - 1].setWallDown(false);
						cells[checker.getAbs()][checker.getOrd()].setWallUp(false);
						checker.setOrd(checker.getOrd() - 1);
					} else {
						checker.setOrd(checker.getOrd() - 1);
					}
				}
				break;

			// BAS
			case 1:
				if (checker.getOrd() < y - 1) {
					if (!cells[checker.getAbs()][checker.getOrd() + 1].isChecked()) {
						cells[checker.getAbs()][checker.getOrd() + 1].setWallUp(false);
						cells[checker.getAbs()][checker.getOrd()].setWallDown(false);
						checker.setOrd(checker.getOrd() + 1);
					} else {
						checker.setOrd(checker.getOrd() + 1);
					}
				}
				break;

			// GAUCHE
			case 2:
				if (checker.getAbs() > 0) {
					if (!cells[checker.getAbs() - 1][checker.getOrd()].isChecked()) {
						cells[checker.getAbs() - 1][checker.getOrd()].setWallRight(false);
						cells[checker.getAbs()][checker.getOrd()].setWallLeft(false);
						checker.setAbs(checker.getAbs() - 1);
					} else {
						checker.setAbs(checker.getAbs() - 1);
					}
				}
				break;

			// DROITE
			case 3:
				if (checker.getAbs() < x - 1) {
					if (!cells[checker.getAbs() + 1][checker.getOrd()].isChecked()) {
						cells[checker.getAbs() + 1][checker.getOrd()].setWallLeft(false);
						cells[checker.getAbs()][checker.getOrd()].setWallRight(false);
						checker.setAbs(checker.getAbs() + 1);
					} else {
						checker.setAbs(checker.getAbs() + 1);
					}
				}
				break;
			}
		}
	}

	public static int randInt(int min, int max) {

		int x = (int) ((Math.random() * ((max - min) + 1)) + min);

		return x;
	}

	public void battle(Hero player, Monster monster, Entity lastPos) throws InterruptedException {

		int maxHpMonster = monster.getHealthMonster();

		String ascii = "    __   __            __         ____        __  __  __              __        __       __\r\n"
				+ "   / /  / / __________/ /        / __ )____ _/ /_/ /_/ /__           / /________\\ \\     / /\r\n"
				+ "  / /  / / /____/____/ /_____   / __  / __ `/ __/ __/ / _ \\   ______/ /____/____/\\ \\   / / \r\n"
				+ " /_/   \\ \\/____/____/ /_____/  / /_/ / /_/ / /_/ /_/ /  __/  /_____/ /____/____/ / /  /_/  \r\n"
				+ "(_)     \\_\\        / /        /_____/\\__,_/\\__/\\__/_/\\___/        / /           /_/  (_)   \r\n"
				+ "                  /_/                                            /_/                       ";

		System.out.println(ascii);

		Scanner in = new Scanner(System.in);

		String choix = "";

		boolean fuir = false;

		while ((player.isAlive() && monster.isAlive()) && !fuir) {

			System.out.println("");

			System.out.println("					Que souhaitez vous faire ?");

			System.out.println("");

			System.out.println("		    +-------------+ +--------------+ +---------------+ +---------+");

			System.out.println("		    | [A]Attaquer | |  [O]Observer | | [I]Inventaire | | [F]Fuir |");

			System.out.println("		    +-------------+ +--------------+ +---------------+ +---------+");

			choix = in.next();

			switch (choix) {
			case "A":

				String ascii1 = "";
				String ascii2 = "";
				String ascii3 = "";
				String ascii4 = "";
				String ascii5 = "";

				switch (player.getWeapon().getCategorie()) {
				case "epee":
					ascii1 = Constants.animSwordOne;
					ascii2 = "            @@@@@@&@@@@                                                                       \r\n"
							+ "             .,     .&@@@@@@@@@@/                                                                   \r\n"
							+ "        #@@@@@@%        .%@@@@@@@@@@%.                                                              \r\n"
							+ "    /&*                      (@@@@@@@@@@@,                                                          \r\n"
							+ "                                 /@@@@@@@@@@@@@@,                                                   \r\n"
							+ "                  ,@@@@@@@%          *&@@@@@@@@@                                                    \r\n"
							+ "    ./(#(*.    &@@@@@@@@@@@@@@*          (@@@   .@@@@@@                                             \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           .  @@@@@@@@(                                             \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   .#@@@@@@@@@@@@@/#@&.                                            \r\n"
							+ "@(        .@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@/      ,@,                                           \r\n"
							+ "           @@@@@@@@@@@@@@@@@@@@@. %@@@@%/.                                                          \r\n"
							+ "        *@@@@@@@@@@@@@@@@@@@@@@                                                                     \r\n"
							+ "      /@@@@@@@@@@@@@@@@@@@@@@,                                                                      \r\n"
							+ "     &@@@@@@@@@@@@@/  .                                                                             \r\n"
							+ "    /@@@@@@@@@@@                                                                                    \r\n"
							+ "    @@@@@@@@@@@                                                                                     \r\n"
							+ "    @@@@@@@@@@&                                                                                     \r\n"
							+ "    @@@@@@@@@@%                                                                                     \r\n"
							+ "    @@@@@@@@@@@                                                                                     \r\n"
							+ "   &@@@@@@@@@@@ .,/(%@@@@@@@@(                                                                      \r\n"
							+ "";
					ascii3 = "                                                                    .                               \r\n"
							+ "                                                              .. ...   /////(@@&/.   @@             \r\n"
							+ "                                               ,(&@@&%#*,.    /////////&@@@@      @#@@@              \r\n"
							+ "                                                                             .%@@@@@@#              \r\n"
							+ "                                                                          *@@@@@@@@@(               \r\n"
							+ "                  ,@@@@@@@%                                           *@@@@@@@@@@/                  \r\n"
							+ "    ./(#(*.    &@@@@@@@@@@@@@@*                                   *&@@@@@@@@@(                      \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                              .%@@@@@@@@@%.                         \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                        /@@@@@@@@@@@@,                             \r\n"
							+ "@(        .@@@@@@@@@@@@@@@@@@@@@@ /###%%%%&&&%#*,.       &@@@@@@@@/                                 \r\n"
							+ "           @@@@@@@@@@@@@@@@@@@@@..@@@@@@@@@@@@@@@@@@@@@@@*(@@@@*                                    \r\n"
							+ "        *@@@@@@@@@@@@@@@@@@@@@@  #@@@@@@@@&&&%&@@@@@@@@@@, ,%(                                      \r\n"
							+ "      /@@@@@@@@@@@@@@@@@@@@@@,                *@@@.                                                 \r\n"
							+ "     &@@@@@@@@@@@@@/  .                       *                                                     \r\n"
							+ "    /@@@@@@@@@@@                                                                                    \r\n"
							+ "    @@@@@@@@@@@                                                                                     \r\n"
							+ "    @@@@@@@@@@&                                                                                     \r\n"
							+ "    @@@@@@@@@@%                                                                                     \r\n"
							+ "    @@@@@@@@@@@                                                                                     \r\n"
							+ "   &@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                                                   \r\n"
							+ "";

					ascii4 = "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                          #&.       &%         /@&.                 \r\n"
							+ "                                                              %@.       &&.       (@@               \r\n"
							+ "                  ,@@@@@@@%                                      %@*       %@,      (@%             \r\n"
							+ "    ./(#(*.    &@@@@@@@@@@@@@@*                                     @@       #@(     /@@,           \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                         /@@@@@@@.    #@@(          \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                    .%@@@@@@@@@.      #@@*         \r\n"
							+ "@(        .@@@@@@@@@@@@@@@@@@@@@@                                .#@@@@@@@@@&.          @@@         \r\n"
							+ "           @@@@@@@@@@@@@@@@@@@@@. .                           #@@@@@@@@@&,              #@@*        \r\n"
							+ "        *@@@@@@@@@@@@@@@@@@@@@@ ,@@@@@@@#*                /@@@@@@@@@@*                   @@(        \r\n"
							+ "      /@@@@@@@@@@@@@@@@@@@@@@, (@@@@@@@@@@@@@@%.    ,%@@@@@@@@@@@#                       @@/        \r\n"
							+ "     &@@@@@@@@@@@@@/  .              *%@@@@@@@@@@@&**@@@@@@@@%.                          @@,        \r\n"
							+ "    /@@@@@@@@@@@                            #@@@@@@@@@@@@@&                              @@         \r\n"
							+ "    @@@@@@@@@@@                              (% *@@@@@/##,                              /@%         \r\n"
							+ "    @@@@@@@@@@&                            @@/                                          @%          \r\n"
							+ "    @@@@@@@@@@%                                                                        ,            \r\n"
							+ "    @@@@@@@@@@@                                                                                     \r\n"
							+ "   &@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                                                   \r\n"
							+ "";
					ascii5 = "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                      %/            \r\n"
							+ "                                                                                        %@,         \r\n"
							+ "                  ,@@@@@@@%                                                              ,@@        \r\n"
							+ "    ./(#(*.    &@@@@@@@@@@@@@@*                                                           ,@&       \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                                           @@/      \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                       /.                 @@@      \r\n"
							+ "@(        .@@@@@@@@@@@@@@@@@@@@@@                                        @.         #      #@@,     \r\n"
							+ "           @@@@@@@@@@@@@@@@@@@@@. .                                      .@         @      %@@,     \r\n"
							+ "        *@@@@@@@@@@@@@@@@@@@@@@ ,@@@@@@@#*                                ##        @      @@@      \r\n"
							+ "      /@@@@@@@@@@@@@@@@@@@@@@, (@@@@@@@@@@@@@@%.                          ,@        @     .@@%      \r\n"
							+ "     &@@@@@@@@@@@@@/  .              *%@@@@@@@@@@@&*                       @*      ,@     #@@       \r\n"
							+ "    /@@@@@@@@@@@                            @@@@@@@@@@(#@@@@#*.            %(      @#    ,@@.       \r\n"
							+ "    @@@@@@@@@@@                                 *@@@@@%@@@@@@@@@@@@@@%(,.         @@    .@@.        \r\n"
							+ "    @@@@@@@@@@&                                      *@@@@@@@@@@@@@@@@@@@@@@@@@%(,     *@@.         \r\n"
							+ "    @@@@@@@@@@%                                                 ./#&@@@@@@@@@@@@@@@@@*/@.           \r\n"
							+ "    @@@@@@@@@@@                                                           .*#&@@@@@@@@#             \r\n"
							+ "   &@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                                                   ";
					break;
				case "sort":
					ascii1 = "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                            &&                     /                                                \r\n"
							+ "                 &&&&&&&&&&&&&&&                    /                                               \r\n"
							+ "               &&%%%%%%%%%%%%%&&                      //////////////                                \r\n"
							+ "%%%           &%%%%%%%%%%%%%%%%%                      ///////////////                               \r\n"
							+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%              %     / ///////////////                              \r\n"
							+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  %%              %%     //////////////////                            \r\n"
							+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%% %  //////////////////                              \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%      //////////////                               \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%                         //////////////                                \r\n"
							+ "        %%%%%%%%%%%                                     //////////                                  \r\n"
							+ "     %%%%%%%%%%                                     /                                               \r\n"
							+ "   %%%%%%%%%                                      /                                                 \r\n"
							+ "  %%%%%%%%                                                                                          \r\n"
							+ "%%%%%%%%                                                                                            \r\n"
							+ "%%%%%%                                                                                              \r\n"
							+ "%%%%%                                                                                               \r\n"
							+ "%%%                                                                                                 ";

					ascii2 = "                                                                                              \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                          & &&                                                                      \r\n"
							+ "                 &&&&&&&&&&&&&&&                 /      //////////                                  \r\n"
							+ "               &&%%%%%%%%%%%%%&&                   /////////////////                                \r\n"
							+ "%%%           &%%%%%%%%%%%%%%%%%                   //////////////////                               \r\n"
							+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                      ///////////////                              \r\n"
							+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%   %%                 %         //////////////                          \r\n"
							+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%      %%         //////////////                          \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%         //////////////                           \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%              %%%%%        / //////////////                            \r\n"
							+ "        %%%%%%%%%%%                            %%      //////////////                               \r\n"
							+ "     %%%%%%%%%%                                      /////////////                                  \r\n"
							+ "   %%%%%%%%%                                    /                                                   \r\n"
							+ "  %%%%%%%%                                                                                          \r\n"
							+ "%%%%%%%%                                                                                            \r\n"
							+ "%%%%%%                                                                                              \r\n"
							+ "%%%%%                                                                                               \r\n"
							+ "%%%                                                                                                 ";

					ascii3 = "                                                                                              \r\n"
							+ "                                                                                                    \r\n"
							+ "                        &&& &&                     /       //////////                             \r\n"
							+ "                 &&&&&&&&&&&&&&&                            /////////////////                     \r\n"
							+ "               &&%%%%%%%%%%%%%&&                           ////////////////////                   \r\n"
							+ "%%%           &%%%%%%%%%%%%%%%%%                               /////////////////                  \r\n"
							+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                               //// ///////////////              \r\n"
							+ "%%%%%%%%%%%%%%%%%%%%%%%%%%   %%%%                             ////////////////////                \r\n"
							+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%%                               //////////////////                \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%%% %%%                               ///////////////                \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%% %%%%%%%                           ///////////////                 \r\n"
							+ "        %%%%%%%%%%%             %%%%%%%%%                       ///////////////                   \r\n"
							+ "     %%%%%%%%%%                    %%%%%%%                   ///////////////                      \r\n"
							+ "   %%%%%%%%%                         %%%%%%                   /////////                           \r\n"
							+ "  %%%%%%%%                            %%%%%%         /                                              \r\n"
							+ "%%%%%%%%                               %%%%%                                                        \r\n"
							+ "%%%%%%                                  %%% %%%                                                     \r\n"
							+ "%%%%%                                  %   % %%%                                                    \r\n"
							+ "%%%                                       %  %                                                      \r\n"
							+ "\r\n";

					ascii4 = "                                                                                              \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                        &&& &&                                   //////////                         \r\n"
							+ "                 &&&&&&&&&&&&&&&                                 /////////////////                 \r\n"
							+ "               &&%%%%%%%%%%%%%&&                                   /////////////////               \r\n"
							+ "%%%           &%%%%%%%%%%%%%%%%%                                   //////////////////////           \r\n"
							+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                                     //// ///////////////          \r\n"
							+ "%%%%%%%%%%%%%%%%%%%%%%%%    %%   %                                //////////////////////////        \r\n"
							+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%%                      /     /////// ///////////////////////        \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%%% a%                                          ///////////////        \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%% a%%%%%                                      ///////////////         \r\n"
							+ "        %%%%%%%%%%%             %%%%%%%%                                  ///////////////           \r\n"
							+ "     %%%%%%%%%%                   %%%%%%%                              ///////////////              \r\n"
							+ "   %%%%%%%%%                        %%%%%                            /////////                      \r\n"
							+ "  %%%%%%%%                          %%%%%                                                           \r\n"
							+ "%%%%%%%%                           % %%%%                                                          \r\n"
							+ "%%%%%%                            %% %%%%                                                           \r\n"
							+ "%%%%%                              % %%                                                             \r\n"
							+ "%%%                                  %                                                              \r\n"
							+ "\r\n";

					ascii5 = "                                                                                              \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                                                    \r\n"
							+ "                                                                      '                             \r\n"
							+ "                        &&& &&                                                ///////////           \r\n"
							+ "                 &&&&&&&&&&&&&&&                       /                         /////////////      \r\n"
							+ "               &&%%%%%%%%%%%%%&&                                                 /////////////////  \r\n"
							+ "%%%           &%%%%%%%%%%%%%%%%%                                                   ///////////////  \r\n"
							+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                                      //////////// /////////////// \r\n"
							+ "%%%%%%%%%%%%%%%%%%%%%%%    %%   %                           //////////////// /////// ////////////// \r\n"
							+ "%    %%%%%%%%%%%%%%%%%%%%%%%%%%%%            /         ///        ///// /////////////////////////// \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%%%                                        /////////////////////////// \r\n"
							+ "          %%%%%%%%%%%%%%%%%%%%% a                                                   //////////////  \r\n"
							+ "        %%%%%%%%%%%        aaaaa%                      /                    '    ///////////////    \r\n"
							+ "     %%%%%%%%%%            %%%%%%                                             //////////////        \r\n"
							+ "   %%%%%%%%%               %%%%%                                               ////////             \r\n"
							+ "  %%%%%%%%                 %%%%%                                                                    \r\n"
							+ "%%%%%%%%              %%%%%%%%%                                                                     \r\n"
							+ "%%%%%%               %  %%%%%%                                                                      \r\n"
							+ "%%%%%                %  %%%%%                                                                       \r\n"
							+ "%%%                  %  %%%%                                                                        \r\n"
							+ "";
				}

				for (int j = 0; j < 3; j++) {
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii1);
					Thread.sleep(150);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii2);
					Thread.sleep(150);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii3);
					Thread.sleep(150);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii4);
					Thread.sleep(150);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii5);
					Thread.sleep(150);
					if (j == 2) {
						break;
					}
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
				}

				System.out.println(
						"+--------------------------------------------------------------------------------------------------+");
				System.out.println("	   Vous attaquez avec votre " + player.getWeapon().getName()
						+ " ! Vous infligez " + player.attackPoints + " points de dégats !");
				System.out.println(
						"+--------------------------------------------------------------------------------------------------+");

				System.out.println(
						"+--------------------------------------------------------------------------------------------------+");
				System.out.println("                  	   " + monster.getNom() + " vous inflige "
						+ monster.getStrengthMonster() + " points de dégats	    ");
				System.out.println(
						"+--------------------------------------------------------------------------------------------------+");

				monster.setHealthMonster(monster.getHealthMonster() - player.attackPoints);

				if (monster.isAlive()) {

					player.setLifePoints(player.getLifePoints() - monster.getStrengthMonster());
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");
					System.out
							.println("	       		        Il vous reste " + player.lifePoints + " points de vie.");
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");

				}

				break;
			case "O":
				if (monster.getHealthMonster() > maxHpMonster * 0.75) {
					System.out.println("Le " + monster.getNom() + " a l'air en pleine forme.");
				} else if (monster.getHealthMonster() > maxHpMonster * 0.50
						&& monster.getHealthMonster() < maxHpMonster * 0.75) {
					System.out.println("Le " + monster.getNom() + "  est blessé.");
				} else if (monster.getHealthMonster() > maxHpMonster * 0.20
						&& monster.getHealthMonster() < maxHpMonster * 0.50) {
					System.out.println("Le " + monster.getNom() + "  a l'air mal en point.");
				} else {
					System.out.println("Le " + monster.getNom() + "  est titubant.");
				}
				break;
			case "I":

				choix = "";

				openInventory(player, choix);

				break;
			case "F":
				int fuite = randInt(0, 100);
				if (fuite > 50) {
					System.out.println("	                         +--------------------------+");
					System.out.println("	                         |  Vous prenez la fuite !! |");
					System.out.println("	                         +--------------------------+");
					player.setAbs(lastPos.getAbs());
					player.setOrd(lastPos.getOrd());
					fuir = true;
				} else {
					player.setLifePoints(player.getLifePoints() - monster.getStrengthMonster());
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");
					System.out.println("        		     Le " + monster.getNom() + "  vous barre la route..");
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");
					System.out.println("        		     Vous subissez " + monster.getStrengthMonster()
							+ " points de dégats !");
					System.out.println(
							"+--------------------------------------------------------------------------------------------------+");

					break;
				}
				break;
			}
		}
		
		if (!player.isAlive()) {
			String defeat = "    __   __            __         ____       ____           __           __        __       __\r\n"
					+ "   / /  / / __________/ /        / __ \\___  / __/__  ____ _/ /_         / /________\\ \\     / /\r\n"
					+ "  / /  / / /____/____/ /_____   / / / / _ \\/ /_/ _ \\/ __ `/ __/  ______/ /____/____/\\ \\   / / \r\n"
					+ " /_/   \\ \\/____/____/ /_____/  / /_/ /  __/ __/  __/ /_/ / /_   /_____/ /____/____/ / /  /_/  \r\n"
					+ "(_)     \\_\\        / /        /_____/\\___/_/  \\___/\\__,_/\\__/        / /           /_/  (_)   \r\n"
					+ "                  /_/                                               /_/                       ";
			System.out.println(defeat);
			System.out.println("	      +-------------------------------------------------------------------+");
			System.out.println("	      |                          Vous êtes mort...                        |");
			System.out.println("  	      +-------------------------------------------------------------------+");
			
		} else {
			String victory = "    __   __            __         _    ___      __                            __        __       __\r\n"
					+ "   / /  / / __________/ /        | |  / (_)____/ /_____  _______  __         / /________\\ \\     / /\r\n"
					+ "  / /  / / /____/____/ /_____    | | / / / ___/ __/ __ \\/ ___/ / / /  ______/ /____/____/\\ \\   / / \r\n"
					+ " /_/   \\ \\/____/____/ /_____/    | |/ / / /__/ /_/ /_/ / /  / /_/ /  /_____/ /____/____/ / /  /_/  \r\n"
					+ "(_)     \\_\\        / /           |___/_/\\___/\\__/\\____/_/   \\__, /        / /           /_/  (_)   \r\n"
					+ "                  /_/                                      /____/        /_/                       ";

			System.out.println(victory);
			System.out.println("	      +-------------------------------------------------------------------+");
			System.out.println("	      |                    Vous avez vaincu le monstre                    |");
			System.out.println("  	      +-------------------------------------------------------------------+");

			int recompense = randInt(0, 500);
									
			System.out.println("	      +-------------------------------------------------------------------+");
			System.out.println("	                   	Vous gagnez " + recompense + " pièces d'or");
			System.out.println("  	      +-------------------------------------------------------------------+");

			player.setGold(player.getGold() + recompense);
		}
	}
	
	public void openInventory(Hero player, String choix) {

		choix = "";

		Scanner in = new Scanner(System.in);

		System.out.println(
				"+--------------------------------------------------------------------------------------------------+");
		System.out.println("                                     	Inventaire ");
		System.out.println(
				"+--------------------------------------------------------------------------------------------------+\n");

		System.out.println("Or : " + player.getGold());

		System.out.println("Potion de Vie - " + player.getHealPotions() + " restantes.");

		System.out.println("Potion de Force - " + player.getStrenghtPotions() + " restantes.\n");

		player.showInventory(player, "");

		System.out.println(
				"+--------------------------------------------------------------------------------------------------+");
		System.out.println("                                    Quel objet Utiliser ?");
		System.out.println(
				"+--------------------------------------------------------------------------------------------------+");

		while (choix.equals("")) {
			choix = in.nextLine();
		}

		switch (choix) {

		case "Potion de Vie":
			if (player.getHealPotions() > 0) {
				player.lifePoints += 20;
				System.out.println("Vous récupérez 20 points de vie.");
				player.setHealPotions(player.getHealPotions() - 1);
			} else {
				System.out.println("Vous n'avez plus de potions de vie..");
			}
			break;
		case "Potion de Force":
			if (player.getStrenghtPotions() > 0) {
				player.attackPoints += 5;
				System.out.println("Votre Force augmente de 5 !");
				player.setStrenghtPotions(player.getStrenghtPotions() - 1);
			} else {
				System.out.println("Vous n'avez plus de potions de force..");
				System.out.println("Potion de Force - " + player.getStrenghtPotions() + " restantes.");
			}
			break;
		}
	}
}
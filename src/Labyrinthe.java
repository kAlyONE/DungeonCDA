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
		// 2 - Toutes les salles ont des coordonn�es [abs,ord]
		// 3 - Toutes les salles ont un attribut isChecked et isVisited fix�s � false

		cells = new Room[x][y];

		for (int i = 0; i < y; i++) {

			for (int j = 0; j < x; j++) {

				cells[i][j] = new Room(i, j);

			}

		}

		return cells;

	}

	// Affichage du labyrinthe

	public void display(Entity player, Room[][] cellules, Entity sortie) {

		System.out.println("Ou souhaitez vous aller ?\n\n[Z] : Haut - [Q] : Gauche - [S] : Bas - [D] : Droite\n");

		for (int i = 0; i < y; i++) {

			// Dessine le haut du labyrinthe

			for (int j = 0; j < x; j++) {

				System.out.print(cells[j][i].isWallUp() ? "+---" : "+   ");

			}

			System.out.println("+");

			// G�n�ration des barres verticales via conditions ternaires v�rifiant si un mur
			// existe

			for (int j = 0; j < x; j++) {

				if (player.getAbs() == cells[j][i].getAbs() && player.getOrd() == cells[j][i].getOrd()) {

					System.out.print(cells[j][i].isWallLeft() ? "| P " : "  P ");

				}

				else if (sortie.getAbs() == cells[j][i].getAbs() && sortie.getOrd() == cells[j][i].getOrd()) {

					System.out.print(cells[j][i].isWallLeft() ? "| x " : "  x ");

				}

				else {

					System.out.print(cells[j][i].isWallLeft() ? "|   " : "    ");

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

	public void moveRight(Entity player, Room[][] cells) {

		// Deplacement vers la droite si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur droit du labyrinthe

		if (!cells[player.getAbs()][player.getOrd()].isWallRight() && player.getAbs() < x - 1) {
			player.setAbs(player.getAbs() + 1);
		} else {
			System.out.println("Il y a un mur de ce cot�.");
		}
	}

	public void moveUp(Entity player, Room[][] cells) {

		// Deplacement vers le haut si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur nord du labyrinthe

		if (!cells[player.getAbs()][player.getOrd()].isWallUp() && player.getOrd() > 0) {
			player.setOrd(player.getOrd() - 1);
		} else {
			System.out.println("Il y a un mur de ce cot�.");
		}
	}

	public void moveDown(Entity player, Room[][] cells) {

		// Deplacement vers le bas si aucun mur ne barre la route et que le joueur n'est
		// pas contre le mur sud du labyrinthe

		if (!cells[player.getAbs()][player.getOrd()].isWallDown() && player.getOrd() < y - 1) {
			player.setOrd(player.getOrd() + 1);
		} else {
			System.out.println("Il y a un mur de ce cot�.");
		}
	}

	public void moveLeft(Entity player, Room[][] cells) {

		// Deplacement vers la gauche si aucun mur ne barre la route et que le joueur
		// n'est pas contre le mur gauche du labyrinthe

		if (!cells[player.getAbs()][player.getOrd()].isWallLeft() && player.getAbs() > 0) {
			player.setAbs(player.getAbs() - 1);
		} else {
			System.out.println("Il y a un mur de ce cot�.");
		}
	}

	public void generateRandom() throws InterruptedException {

		// On r�cup�re le total de cases du labyrinthe

		int cases = x * y;

		// Variable qui va r�cup�rer un entier al�atoire

		int random = 0;

		// L'entit� inexistante qui va dessiner les chemins

		Entity checker = new Entity(0, 0);

		// Boucle de g�n�ration du labyrinthe, s'arr�te quand toutes les cases ont �t�
		// parcourues

		while (cases > 0) {

			// Si le checker est sur une case qui n'a jamais �t� parcourue, on la notifie
			// comme parcourue en passant isChecked � TRUE

			if (!cells[checker.getAbs()][checker.getOrd()].isChecked()) {
				cases--;
				cells[checker.getAbs()][checker.getOrd()].setChecked(true);
			}

			// Randomize la variable random

			random = randInt(0, 3);

			// Switch, en fonction du chiffre de sortie, on prend une direction

			switch (random) {

			// Pour chaque direction, on v�rifie que la case sur laquelle en va n'est pas
			// check�e avant de modifier les bool�ens des murs
			// Une fois ceci fait, on change le mur vers lequel nous nous dirigeons, et
			// celui de la salle qui vient vers nous en false
			// ( Pour briser le mur )
			// Je d�place ensuite mon checker dans la salle suivante avant de lui faire
			// prendre une nouvelle direction
			// Si la salle a d�j� �t� check�e, il s'y d�place simplement en ignorant les
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

			System.out.println("				Que souhaitez vous faire ?");

			System.out.println("");

			System.out.println("		    +----------+ +-----------+ +------------+ +------+");

			System.out.println("		    | Attaquer | |  Observer | | Inventaire | | Fuir |");

			System.out.println("		    +----------+ +-----------+ +------------+ +------+");

			choix = in.next();

			switch (choix) {
			case "Attaquer":
				String ascii1 = "                                                                                                    \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                                                    \r\n"
						+ "                            &&                    ///////////                                       \r\n"
						+ "                 &&&&&&&&&&&&&&&                 /////////////////                                  \r\n"
						+ "               &&%%%%%%%%%%%%%&&            //     /////////////////                                \r\n"
						+ "%%%           &%%%%%%%%%%%%%%%%%             ///// /  ///////////////                               \r\n"
						+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%              %     / ///////////////                              \r\n"
						+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  %%              %%         //////////////                            \r\n"
						+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%% %      //////////////                              \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%      //////////////                               \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%                       / //////////////                                \r\n"
						+ "        %%%%%%%%%%%                                 //////////////                                  \r\n"
						+ "     %%%%%%%%%%                         //       /////////////                                      \r\n"
						+ "   %%%%%%%%%                                       //////                                           \r\n"
						+ "  %%%%%%%%                                                                                          \r\n"
						+ "%%%%%%%%                                                                                            \r\n"
						+ "%%%%%%                                                                                              \r\n"
						+ "%%%%%                                                                                               \r\n"
						+ "%%%                                                                                                 ";

				String ascii2 = "                                                                                              \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                                                    \r\n"
						+ "                          & &&                    ///////////                                       \r\n"
						+ "                 &&&&&&&&&&&&&&&                 /////////////////                                  \r\n"
						+ "               &&%%%%%%%%%%%%%&&            //     /////////////////                                \r\n"
						+ "%%%           &%%%%%%%%%%%%%%%%%             ///// /  ///////////////                               \r\n"
						+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                    / ///////////////                              \r\n"
						+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%   %%                 %         //////////////                          \r\n"
						+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%      %%         //////////////                          \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%         //////////////                           \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%              %%%%%        / //////////////                            \r\n"
						+ "        %%%%%%%%%%%                            %%      //////////////                               \r\n"
						+ "     %%%%%%%%%%                                      /////////////                                  \r\n"
						+ "   %%%%%%%%%                                           //////                                       \r\n"
						+ "  %%%%%%%%                                                                                          \r\n"
						+ "%%%%%%%%                                                                                            \r\n"
						+ "%%%%%%                                                                                              \r\n"
						+ "%%%%%                                                                                               \r\n"
						+ "%%%                                                                                                 ";

				String ascii3 = "                                                                                              \r\n"
						+ "                                                                                                    \r\n"
						+ "                        &&& &&                            /  //////////                             \r\n"
						+ "                 &&&&&&&&&&&&&&&                              /////////////////                     \r\n"
						+ "               &&%%%%%%%%%%%%%&&            //                  /////////////////                   \r\n"
						+ "%%%           &%%%%%%%%%%%%%%%%%             ///// /               ///////////////                  \r\n"
						+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                                    / ///////////////              \r\n"
						+ "%%%%%%%%%%%%%%%%%%%%%%%%%%   %%%%                                     //////////////                \r\n"
						+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%%                                     //////////////                \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%% %%%                         //      ///////////////                \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%% %%%%%%%                             ///////////////                 \r\n"
						+ "        %%%%%%%%%%%             %%%%%%%%%                         ///////////////                   \r\n"
						+ "     %%%%%%%%%%                    %%%%%%%                     ///////////////                      \r\n"
						+ "   %%%%%%%%%                         %%%%%%                     /////////                           \r\n"
						+ "  %%%%%%%%                            %%%%%%                                                        \r\n"
						+ "%%%%%%%%                               %%%%%                                                        \r\n"
						+ "%%%%%%                                  %%% %%%                                                     \r\n"
						+ "%%%%%                                  %   % %%%                                                    \r\n"
						+ "%%%                                       %  %                                                      \r\n"
						+ "\r\n";

				String ascii4 = "                                                                                              \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                      /                             \r\n"
						+ "                        &&& &&                            /  //////////                             \r\n"
						+ "                 &&&&&&&&&&&&&&&                              /////////////////                     \r\n"
						+ "               &&%%%%%%%%%%%%%&&            //                  /////////////////                   \r\n"
						+ "%%%           &%%%%%%%%%%%%%%%%%             ///// /               ///////////////                  \r\n"
						+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                                    / ///////////////              \r\n"
						+ "%%%%%%%%%%%%%%%%%%%%%%%%    %%  %                                         //////////////            \r\n"
						+ "     %%%%%%%%%%%%%%%%%%%%%%%%%%%%                             //          //////////////            \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%% %%                                      ///////////////            \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%% %%%%%%                                  ///////////////             \r\n"
						+ "        %%%%%%%%%%%             %%%%%%%%                              ///////////////               \r\n"
						+ "     %%%%%%%%%%                   %%%%%%%%                         ///////////////                  \r\n"
						+ "   %%%%%%%%%                        %%%%%%%                         /////////                       \r\n"
						+ "  %%%%%%%%                          %%%%%%%                                                         \r\n"
						+ "%%%%%%%%                           % %%%%%                                                         \r\n"
						+ "%%%%%%                               %%%                                                            \r\n"
						+ "%%%%%                                                                                               \r\n"
						+ "%%%                                                                                                 \r\n"
						+ "\r\n";

				String ascii5 = "                                                                                              \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                                                    \r\n"
						+ "                                                                      /                             \r\n"
						+ "                        &&& &&                                                /  //////////         \r\n"
						+ "                 &&&&&&&&&&&&&&&                                                 /////////////      \r\n"
						+ "               &&%%%%%%%%%%%%%&&            //                                   /////////////////  \r\n"
						+ "%%%           &%%%%%%%%%%%%%%%%%             ///// /                               ///////////////  \r\n"
						+ "%%%%%%%%%%   &%%%%%%%%%%%%%%%%%%%                                                 / /////////////// \r\n"
						+ "%%%%%%%%%%%%%%%%%%%%%%%%    %%  %                                                    ////////////// \r\n"
						+ "%    %%%%%%%%%%%%%%%%%%%%%%%%%%%%                      ///                           ////////////// \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%%                                                    /////////////// \r\n"
						+ "          %%%%%%%%%%%%%%%%%%%%%%                                                    //////////////  \r\n"
						+ "        %%%%%%%%%%%       %%%%%%%                                                ///////////////    \r\n"
						+ "     %%%%%%%%%%           %%%%%%%                                             //////////////        \r\n"
						+ "   %%%%%%%%%              %%%%%%        //                                     ////////             \r\n"
						+ "  %%%%%%%%            %% %%%%%%%                                                                    \r\n"
						+ "%%%%%%%%               %%%%%%%%                                                                     \r\n"
						+ "%%%%%%                %%%%%%%%                                                                      \r\n"
						+ "%%%%%                   %%%%                                                                        \r\n"
						+ "%%%                                                                                                 \r\n"
						+ "";

				for (int j = 0; j < 3; j++) {
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii1);
					Thread.sleep(250);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii2);
					Thread.sleep(250);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii3);
					Thread.sleep(250);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii4);
					Thread.sleep(250);
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
					System.out.println(ascii5);
					Thread.sleep(250);
					if (j == 2) {
						break;
					}
					for (int i = 0; i < 40; i++) {
						System.out.println("");
					}
				}
				System.out.println("	      +-------------------------------------------------------------------+");
				System.out.println("	      |  Vous lancez une boule de feu ! Vous infligez " + player.attackPoints
						+ " points de d�gats  |");
				System.out.println("  	      +-------------------------------------------------------------------+");
				monster.setHealthMonster(monster.getHealthMonster() - player.attackPoints);
				break;
			case "Observer":
				if (monster.getHealthMonster() > maxHpMonster * 0.75) {
					System.out.println("Le " + monster.getNom() + " a l'air en pleine forme.");
				} else if (monster.getHealthMonster() > maxHpMonster * 0.50
						&& monster.getHealthMonster() < maxHpMonster * 0.75) {
					System.out.println("Le " + monster.getNom() + "  est bless�.");
				} else if (monster.getHealthMonster() > maxHpMonster * 0.20
						&& monster.getHealthMonster() < maxHpMonster * 0.50) {
					System.out.println("Le " + monster.getNom() + "  a l'air mal en point.");
				} else {
					System.out.println("Le " + monster.getNom() + "  est titubant.");
				}
				break;
			case "Inventaire":
				player.showInventory(player, "");
				break;
			case "Fuir":
				int fuite = randInt(0, 100);
				if (fuite > 50) {
					System.out.println("Vous prenez la fuite !");
					player.setAbs(lastPos.getAbs());
					player.setAbs(lastPos.getOrd());
					fuir = true;
				} else {
					System.out.println("Le " + monster.getNom() + " vous barre la route..");
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
			System.out.println("	      |                          Vous �tes mort..                         |");
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
		}
	}

}
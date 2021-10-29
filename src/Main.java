import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner hero = new Scanner(System.in);
		
		for (int i = 0; i < 25; i++) {
			System.out.println("");
		}

		// Creation d'un joueur ( Prochainement héritier de Entity )

		System.out.println("Comment vous appelez vous ?");
		
		String name = hero.next();
		
		Hero player = new Hero(name, 100, 10, 0, 0);
		
//		File scores = new File("C:/Users/alexa/Desktop/scores.ser");
		File scores = new File("D:\\DEV\\Java\\Projets Eclipse\\DungeonCDA\\score.txt"); 
		
		
		HashMap<Integer,Hero> scoredHeroes = new HashMap<>();
		

		scoredHeroes.put(0,player);

		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(scores));
		
		try {
			
			oos.writeObject(scoredHeroes);
			oos.close();
			
		}
		
		catch(Exception e) {
			
		}
		
		finally {
		      try {
		    	  oos.flush();
		          oos.close();
		        } 
		      catch (final IOException ex) {
		          ex.printStackTrace();
		        }
	      }
		
		ObjectInputStream inS = new ObjectInputStream(new FileInputStream(scores));
		
		try {

			HashMap<Integer,Hero> scoredHeroes2 = (HashMap<Integer,Hero>) inS.readObject();
			System.out.println(scoredHeroes2.get(0).getNom());
			inS.close();
			
		    } 
		catch (final java.io.IOException e) {
		      e.printStackTrace();
		    } 
		catch (final ClassNotFoundException e) {
		      e.printStackTrace();
		    } 
		finally {
		      try {
		          inS.close();
		      } catch (final IOException ex) {
		        ex.printStackTrace();
		      }
		    }
		

		
		Entity sortie = new Entity(randInt(0, 9), 9);

		// Creation du labyrinthe avec ses coordonnées

		Labyrinthe maze = new Labyrinthe(10, 10);

		// Création des cellules du labyrinthe

		Room[][] cellules = maze.generate();

		// Génération aléatoire des chemins du labyrinthe

		maze.generateRandom();

		// Affichage du labyrinthe dans la console

		cellules[player.getAbs()][player.getOrd()].setVisited(true);

		maze.display(player, cellules, sortie);

		Scanner in = new Scanner(System.in);

		String choix = "";

		Entity lastPos = new Entity(0, 0);

		// Boucle de jeu

		while (!choix.equals("End")) {

			System.out.println(
					"\n+---------------------------------------------------------------------------------------------------+\n");

			System.out.println("   " + "				   Ou souhaitez vous aller ? \n"
					+ " +--------------------------------------------------------------------------------------------------+\n"
					+ " | Déplacements : | [Z] : Avancer        | [Q] : Gauche   | [S] : Demi-Tour    | [D] : Droite       |\n"
					+ " +--------------------------------------------------------------------------------------------------+\n"
					+ " | Actions :      | [M] Ouvrir la carte  | [F] Fouiller   | [I] Inventaire     |                    |\n"
					+ " +--------------------------------------------------------------------------------------------------+");

			System.out.println(
					"\n+---------------------------------------------------------------------------------------------------+\n");

			cellules[player.getAbs()][player.getOrd()].getView(player);

			choix = in.next();

			switch (choix.toUpperCase()) {
			case "D":
				lastPos.setAbs(player.getAbs());
				lastPos.setOrd(player.getOrd());
				for (int i = 0; i < 35; i++) {
					System.out.println("");
				}
				maze.moveRight(player, cellules);
				maze.display(player, cellules, sortie);
				break;
			case "Z":
				lastPos.setAbs(player.getAbs());
				lastPos.setOrd(player.getOrd());
				for (int i = 0; i < 35; i++) {
					System.out.println("");
				}
				maze.moveForward(player, cellules);
				maze.display(player, cellules, sortie);
				break;
			case "Q":
				lastPos.setAbs(player.getAbs());
				lastPos.setOrd(player.getOrd());
				for (int i = 0; i < 35; i++) {
					System.out.println("");
				}
				maze.moveLeft(player, cellules);
				maze.display(player, cellules, sortie);
				break;
			case "S":
				lastPos.setAbs(player.getAbs());
				lastPos.setOrd(player.getOrd());
				for (int i = 0; i < 35; i++) {
					System.out.println("");
				}
				maze.moveBack(player, cellules);
				maze.display(player, cellules, sortie);
				break;
			case "F":
				maze.fouiller(player, maze, cellules);
				break;
			case "I":
				maze.openInventory(player, choix);
				break;
			}
			if (player.getAbs() == sortie.getAbs() && player.getOrd() == sortie.getOrd()) {
				choix = "End";
			}
			if (cellules[player.getAbs()][player.getOrd()].getMonster() != null
					&& cellules[player.getAbs()][player.getOrd()].getMonster().isAlive()) {
				maze.battle(player, cellules[player.getAbs()][player.getOrd()].getMonster(), lastPos);
				maze.display(player, cellules, sortie);
			}
		}
		if (player.isAlive()) {
			String victory = "    __   __            __         _    ___      __                            __        __       __\r\n"
					+ "   / /  / / __________/ /        | |  / (_)____/ /_____  _______  __         / /________\\ \\     / /\r\n"
					+ "  / /  / / /____/____/ /_____    | | / / / ___/ __/ __ \\/ ___/ / / /  ______/ /____/____/\\ \\   / / \r\n"
					+ " /_/   \\ \\/____/____/ /_____/    | |/ / / /__/ /_/ /_/ / /  / /_/ /  /_____/ /____/____/ / /  /_/  \r\n"
					+ "(_)     \\_\\        / /           |___/_/\\___/\\__/\\____/_/   \\__, /        / /           /_/  (_)   \r\n"
					+ "                  /_/                                      /____/        /_/                       ";

			System.out.println(victory);
		} else {
			String defeat = "    __   __            __         ____       ____           __           __        __       __\r\n"
					+ "   / /  / / __________/ /        / __ \\___  / __/__  ____ _/ /_         / /________\\ \\     / /\r\n"
					+ "  / /  / / /____/____/ /_____   / / / / _ \\/ /_/ _ \\/ __ `/ __/  ______/ /____/____/\\ \\   / / \r\n"
					+ " /_/   \\ \\/____/____/ /_____/  / /_/ /  __/ __/  __/ /_/ / /_   /_____/ /____/____/ / /  /_/  \r\n"
					+ "(_)     \\_\\        / /        /_____/\\___/_/  \\___/\\__,_/\\__/        / /           /_/  (_)   \r\n"
					+ "                  /_/                                               /_/                       ";
			System.out.println(defeat);
		}
	}

	public static int randInt(int min, int max) {

		int x = (int) ((Math.random() * ((max - min) + 1)) + min);

		return x;

	}
}
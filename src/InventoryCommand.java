/*
 * public class InventoryCommand extends Command {
 * 
 * 
 * public InventoryCommand(Labyrinthe labyrinthe) { super(labyrinthe); }
 * 
 * @Override public void act(String params) {
 * System.out.println("\nInventaire du joueur : "); Hero hero =
 * labyrinthe.getHero(); if(hero.getInventory().isEmpty()){
 * System.out.println(" - vide\n"); return; } for(Item i : hero.getInventory()){
 * System.out.println(" - " + i); } System.out.println(); }
 * 
 * @Override public String description() { return
 * "inventaire : montre l'inventaire du joueur."; }
 * 
 * }
 */
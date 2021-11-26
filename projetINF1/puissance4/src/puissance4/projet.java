package puissance4;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class projet {
	public static int entierAleatoire(int a, int b){
		//Retourne un entier aléatoire entre a (inclus) et b (inclus)
		return ThreadLocalRandom.current().nextInt(a, b + 1);	
	}
	public static void afficheTab2D(int [][] tab ){
		System.out.println("\n");
        	for(int i=0;i<tab.length;i++) {
            		for(int j=0;j<tab[i].length;j++) {
                		System.out.print(tab[i][j]+" ");
            		}
            		System.out.println();
        	}
	}
	static int [][] grille;
	public static int[][] initialisationGrille (){
		grille = new int[6][7];
		for(int i= 0; i<grille.length; i++){
			for(int j = 0; j< grille[i].length; j++){
				grille[i][j] = 0;
			}
		}
		return grille;
	}
	public static void jouer (int joueur, int colonne) {
		for(int i = 0; i<grille.length; i++) {
			if(i==5) {
				grille[i][colonne]=joueur;
			}
			else if (grille[i][colonne]!=0){
				grille[i-1][colonne]=joueur;
			}
		}
	}
	public static void main(String[] args) {

	}
}

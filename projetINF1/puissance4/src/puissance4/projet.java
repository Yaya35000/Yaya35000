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
	public static void grille (int [][] tab){
		
	}
	public static void main(String[] args) {

	}
}

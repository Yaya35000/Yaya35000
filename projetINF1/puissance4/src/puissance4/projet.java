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
			else if (grille[i+1][colonne]!=0){
				grille[i][colonne]=joueur;
			}
		}
	}
	public static void afficheGrille() {
		
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(j==0) {
					if(grille[i][j]==0) System.out.print("|"+" "+"|");
					else if(grille[i][j]==1) System.out.print("|"+"X"+"|");
					else System.out.print("|"+"O"+"|");
				}
				else {
					if(grille[i][j]==0) System.out.print(" "+"|");
					else if(grille[i][j]==1) System.out.print("X"+"|");
					else System.out.print("O"+"|");
				}
			}
			System.out.println();
		}
		System.out.print("|");
		for(int k=0;k<grille[0].length;k++) {
			if(k<7) System.out.print(k +"|");
		}
		System.out.println();
	}
	public static void main(String[] args) {

	}
	public static boolean aGagneHor (int x, int y, int joueur) {
        int compteur = 0;
        for(int j=y;j<grille[x].length;j++) {
            if(caseCorrecte(x, j) == true) {
            	 if(grille[x][j]==joueur) {
                     compteur +=1;
                 }
                 else {
                     compteur = 0;
                 }
             }
             if (compteur == 4) return true;
         }     
        return false;
    }
	public static boolean aGagneVer (int x, int y, int joueur) {
        int compteur = 0;
        for(int i=x;i<grille.length;i++) {
            if(caseCorrecte(x,i) == true) {
                if(grille[x][i]==joueur) {
                    compteur +=1;
                }
                else if(grille[x][i]!=joueur) {
                    compteur = 0;
                }
            }
            if (compteur == 4) return true;
        }
        return false;
    }
	public static boolean aGagneDiagMont (int x, int y, int joueur) {
        int compteur = 0;
        int o=x;
        int a=y;
        if(!caseCorrecte(o, a)) return false;
        while(caseCorrecte(o, a)) {
        	if(grille[o][a]==joueur) {
        		compteur+=1;
        		if(compteur==4) return true;
        	}
        	else compteur=0;
        	o-=1;
        	a+=1;
        	
        }
        return false;
        
    }
	
	public static boolean aGagneDiagDesc (int x, int y, int joueur) {
        int compteur = 0;
        int o=x;
        int a=y;
        if(!caseCorrecte(o, a)) return false;
        while(caseCorrecte(o, a)) {
        	if(grille[o][a]==joueur) {
        		compteur+=1;
        		if(compteur==4) return true;
        	}
        	else compteur=0;
        	o+=1;
        	a+=1;
        	
        }
        return false;
        
    }
	public static boolean aGagne(int joueur) {
		for(int i=0;i<grille.length;i++) {
			for(int j=0;i<grille[i].length;j++) {
				if(aGagneHor(i, j, joueur)||aGagneVer(i, j, joueur)||aGagneDiagMont(i, j, joueur)||aGagneDiagDesc(i, j, joueur)) return true;
			}
		}
		return false;
	}
	public static boolean verifierPremièreLigne() {
		for(int j=0;j<grille[0].length;j++) {
			if(grille[0][j]==0) return false;
		}
		return true;
	}
	public static boolean matchNul() {
		if(verifierPremièreLigne() && !aGagne(1) && !aGagne(2)) return true;
		else return false;
	}

}

package puissance4;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class projet {
	public static void afficheTab2D(int tab [][]) {
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
        boolean check=false;
        for(int i = 0; i<grille.length; i++) {
            if(i==grille.length-1 && !check) {
                grille[grille.length-1][colonne]=joueur;
                check = true;
            }
            else if (caseCorrecte(i, i+1) && !check){
                if(grille[i+1][colonne]!=0) {
                    grille[i][colonne]=joueur;
                    check=true;
                }
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
    }
	public static boolean caseCorrecte(int i,int j) {
		return (grille.length>i && i>=0 && j>=0 && j<grille[0].length);
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
			for(int j=0;j<grille[i].length;j++) {
				if(aGagneHor(i, j, joueur)||aGagneVer(i, j, joueur)||aGagneDiagMont(i, j, joueur)||aGagneDiagDesc(i, j, joueur) )return true;
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
		if(verifierPremièreLigne() == true && !aGagne(1)== true && !aGagne(2)== true) return true;
		else return false;
	}
	static int joueur=1;
	public static void boucle() {
		Scanner sc = new Scanner(System.in);
		while((!aGagne(1) && !aGagne(2)) && !matchNul()){
			afficheGrille();
			boolean check=false;
			int c=0;
			while(!check) {
				System.out.println("Quel coup pour le joueur "+joueur+" ?");
				c=sc.nextInt();
				if(c<0||c>6) {
					System.out.println("Coup invalide");
				}
				else check=true;
			}
			jouer(joueur, c);
			joueur=3-joueur;
		}
		sc.close();
	}
	public static void jeu() {
		initialisationGrille();
		boucle();
		afficheGrille();
		if(aGagne(1) == true) System.out.println("le joueur 1 a gagné !");
		else if(aGagne(2)== true) System.out.println("le joueur 2 a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	} 
	public static void main(String[] args){
		jeu();
	}
}

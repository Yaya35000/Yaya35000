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
            if(caseCorrecte(i,y) == true) {
                if(grille[i][y]==joueur) {
                    compteur +=1;
                }
                else if(grille[i][y]!=joueur) {
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
	public static void joueCoupRandom() {
		boolean check=false;
		int joue=0;
		while(!check) {
			joue=entierAleatoire(0, 6);
			if(grille[0][joue]==0) check=true;
		}
		jouer(2, joue);
	}
	
	public static void boucleIA() {
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
			joueCoupRandom();
		}
		sc.close();
	}
	public static void jeuIA() {
		initialisationGrille();
		boucleIA();
		afficheGrille();
		if(aGagne(1) == true) System.out.println("le joueur 1 a gagné !");
		else if(aGagne(2)== true) System.out.println("L'IA a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	} 
	public static boolean opportunitéeHor() {
		int compteur=0;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(grille[i][j]==2)compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i, j)) {
					if(grille[i][j+1]==0) {
						jouer(2, j+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(i, j-compteur)) {
					if(grille[i][j-compteur]==0) {
						jouer(2, j-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			compteur=0;
		}
		return false;
	}
	public static boolean opportunitéVer() {
		int compteur=0;
		for(int j=0;j<grille[0].length;j++) {
			for(int i=0;i<grille.length;i++) {
				if(grille[i][j]==2) compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i-compteur, j)) {
					if(grille[i-compteur][j]==0) {
						jouer(i-compteur, j);
						return true;
					}
					else compteur=0;
				}
				else compteur=0;
			}
			compteur=0;
		}
		return false;
	}
	public static boolean opportunitéDiagMont(int x, int y) {
		int a=x;
		int o=y;
		int compteur=0;
		while(o>6 || a<0) {
			if(caseCorrecte(a, o)) {
				if(grille[a][o]==2) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(a-1, o+1) && caseCorrecte(a, o+1)) {
					if(grille[a][o+1]!=0) {
						jouer(2, o+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(a+compteur, o-compteur) && caseCorrecte(a+compteur+1, o-compteur)) {
					if(grille[a+compteur+1][o-compteur]!=0) {
						jouer(2, o-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			else return false;
			o-=1;
			a+=1;
		}
		return false;
	}
	
	public static boolean opportunitéDiagDesc(int x, int y) {
		int a=x;
		int o=y;
		int compteur=0;
		while(o>6 || a>5) {
			if(caseCorrecte(a, o)) {
				if(grille[a][o]==2) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(a+1, o+1)) {
					if(caseCorrecte(a+2, o+1)) {
						if(grille[a+2][o+1]!=0) {
							jouer(2, a+1);
							return true;
						}
					}
					else if(a+1==5 && grille[a+1][o+1]==0) jouer(2, a+1);
				}
				else if(compteur==3 && caseCorrecte(a-compteur, o-compteur) && caseCorrecte(a-compteur+1, o-compteur)) {
					if(grille[a-compteur+1][o-compteur]!=0) {
						jouer(2, o-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			o+=1;
			a+=1;
		}
		return false;
	}
	public static boolean opportunitéeVictoire() {
		if(opportunitéeHor()) return true;
		else if(opportunitéVer()) return true;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(opportunitéDiagMont(i, j)) return true;
				if(opportunitéDiagDesc(i, j)) return true;
			}
		}
		return false;
	}
	public static void joueCoupRandom2() {
		if (!opportunitéeVictoire()) joueCoupRandom();
	}
	
	public static void boucleIA2() {
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
			joueCoupRandom2();
		}
		sc.close();
	}
	public static void jeuIA2() {
		initialisationGrille();
		boucleIA2();
		afficheGrille();
		if(aGagne(1) == true) System.out.println("le joueur 1 a gagné !");
		else if(aGagne(2)== true) System.out.println("L'IA a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	}
	
	public static boolean opportunitéeDefHor() {
		int compteur=0;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(grille[i][j]==1)compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i, j) && caseCorrecte(i, j+1)) {
					if(grille[i][j+1]==0) {
						jouer(2, j+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(i, j-compteur)) {
					if(grille[i][j-compteur]==0) {
						jouer(2, j-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			compteur=0;
		}
		return false;
	}
	public static boolean opportunitéDefVer() {
		int compteur=0;
		for(int j=0;j<grille[0].length;j++) {
			for(int i=0;i<grille.length;i++) {
				if(grille[i][j]==1) compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i-compteur, j)) {
					if(grille[i-compteur][j]==0) {
						jouer(i-compteur, j);
						return true;
					}
					else compteur=0;
				}
				else compteur=0;
			}
			compteur=0;
		}
		return false;
	}
	public static boolean opportunitéDefDiagMont(int x, int y) {
		int a=x;
		int o=y;
		int compteur=0;
		while(o>6 || a<0) {
			if(caseCorrecte(a, o)) {
				if(grille[a][o]==1) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(a-1, o+1) && caseCorrecte(a, o+1)) {
					if(grille[a][o+1]!=0) {
						jouer(2, o+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(a+compteur, o-compteur) && caseCorrecte(a+compteur+1, o-compteur)) {
					if(grille[a+compteur+1][o-compteur]!=0) {
						jouer(2, o-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			else return false;
			o-=1;
			a+=1;
		}
		return false;
	}
	
	public static boolean opportunitéDefDiagDesc(int x, int y) {
		int a=x;
		int o=y;
		int compteur=0;
		while(o>6 || a>5) {
			if(caseCorrecte(a, o)) {
				if(grille[a][o]==1) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(a+1, o+1)) {
					if(caseCorrecte(a+2, o+1)) {
						if(grille[a+2][o+1]!=0) {
							jouer(2, a+1);
							return true;
						}
					}
					else if(a+1==5 && grille[a+1][o+1]==0) jouer(2, a+1);
				}
				else if(compteur==3 && caseCorrecte(a-compteur, o-compteur) && caseCorrecte(a-compteur+1, o-compteur)) {
					if(grille[a-compteur+1][o-compteur]!=0) {
						jouer(2, o-compteur);
						return true;
					}
					else compteur=0;
				}
			}
			o+=1;
			a+=1;
		}
		return false;
	}
	public static boolean opportunitéeDefense() {
		if(opportunitéeDefHor()) return true;
		else if(opportunitéDefVer()) return true;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(opportunitéDefDiagMont(i, j)) return true;
				if(opportunitéDefDiagDesc(i, j)) return true;
			}
		}
		return false;
	}
	public static void joueCoupRandom3() {
		if (!opportunitéeDefense()) joueCoupRandom();
	}
	
	public static void boucleIA3() {
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
			joueCoupRandom3();
		}
		sc.close();
	}
	public static void jeuIA3() {
		initialisationGrille();
		boucleIA3();
		afficheGrille();
		if(aGagne(1) == true) System.out.println("le joueur 1 a gagné !");
		else if(aGagne(2)== true) System.out.println("L'IA a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	} 
	
	public static void main(String[] args){
		System.out.println("Puissance 4 ! (Par Cecilia Duchene et Sébastien Kinder)");
		Scanner s = new Scanner(System.in);
		System.out.println("2 Joueurs (1)");
		System.out.println("Contre l'IA (0)");
		int n=s.nextInt();
		if(n==1) jeu();
		else if(n==0) {
			System.out.println("Niveau 1 (1)");
			System.out.println("Niveau 2 (2)");
			System.out.println("Niveau 3 (3)");
			n=s.nextInt();
			if(n==1) jeuIA();
			else if(n==2) jeuIA2();
			else jeuIA3();
		}
		else System.out.print("Erreur !");
		s.close();	
	}
}

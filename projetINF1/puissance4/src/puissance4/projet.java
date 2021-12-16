package test;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
	public static int entierAleatoire(int a, int b){
		//Retourne un entier aléatoire entre a (inclus) et b (inclus)
		return ThreadLocalRandom.current().nextInt(a, b + 1);	
	}
	public static void afficheTab2D(int tab [][]) {
		System.out.println("\n");
        for(int i=0;i<tab.length;i++) {
            for(int j=0;j<tab[i].length;j++) {
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }
	static int [][] grille;			//On declare le tableau grille, il sera toujours présent bien que dans cet état il n'est pas initialisé
	public static int[][] initialisationGrille (){
		grille = new int[6][7];			//On initialise le tableau en un tableau à 6 lignes et 7 colonnes
		for(int i= 0; i<grille.length; i++){
			for(int j = 0; j< grille[i].length; j++){
				grille[i][j] = 0;		//On remplit le tableau de 0 pour symboliser une grille vide
			}
		}
		return grille;
	}
	public static void jouer (int joueur, int colonne) {
        boolean check=false;		//check est un booléen qui va servir à verouiller la fonction une fois le coup jouer
        for(int i = 0; i<grille.length; i++) { 		//on parcours les lignes de la colonne où le coup est joué
            if(i==grille.length-1 && !check) {		//si on atteint le fond de la grille et que le jeton n'a pas été posé on pose le jeton sur la dernière ligne
                grille[grille.length-1][colonne]=joueur;
                check = true;
            }
            else if (caseCorrecte(i, i+1) && !check){
                if(grille[i+1][colonne]!=0) {		//Si on tombe sur une case qui a un jeton on pose le jeton récemment joué au dessus
                    grille[i][colonne]=joueur;
                    check=true;
                }
            }
        }
    }
	public static void afficheGrille() {
		for(int i=0;i<grille.length;i++) {
            for(int j=0;j<grille[i].length;j++) {
                if(j==0) {		//On parcourt la grille si on est dans la première colonne on demande à afficher une barre, un symbole puis une barre
                    if(grille[i][j]==0) System.out.print("|"+" "+"|");
                    else if(grille[i][j]==1) System.out.print("|"+"X"+"|");
                    else System.out.print("|"+"O"+"|");
                }
                else {
                    if(grille[i][j]==0) System.out.print(" "+"|");			//Pour le reste on demande à afficher un symbole puis une barre
                    else if(grille[i][j]==1) System.out.print("X"+"|");
                    else System.out.print("O"+"|");
                }
            }
            System.out.println();
        }
        System.out.print("|");		//On affiche une barre puis pour chaque colonne on affiche son numéro puis une autre barre
        for(int k=0;k<grille[0].length;k++) {
            if(k<7) System.out.print(k +"|");
        }
        System.out.println();
    }
	public static boolean caseCorrecte(int i,int j) { //Cette fonction sert à verifier quand on parcours la grille que la case où on est appartient bien au tableau grille
		return (grille.length>i && i>=0 && j>=0 && j<grille[0].length);
	}
	public static boolean aGagneHor (int x, int y, int joueur) { //En prenant en entrée des coordonnées et le numéro du joueur on vérifie dans le tableau si on a une ligne de 4 jetons du même joueur allignés
        int compteur = 0; 		//On initialise le compteur
        for(int j=y;j<grille[x].length;j++) { //On parcours la ligne de la coordonnée x
            if(caseCorrecte(x, j) == true) {  //On vérifie que la case est bien dans notre tableau
            	 if(grille[x][j]==joueur) {   //Si la case contient un jeton du joueur on incrémente le compteur de 1 cran
                     compteur +=1;
                 }
                 else {						//Sinon on réinitialise le compteur
                     compteur = 0;
                 }
             }
             if (compteur == 4) return true; //Quand on a atteint 4 au compteur on retourne le booléen vraie
         }     
        return false;  //Sortir de la boucle signifie que dans la ligne il n'y a pas de ligne de 4 jetons allignés
    }
	public static boolean aGagneVer (int x, int y, int joueur) { 		//Sur le même concept on effectue la même fonction mais en verticale, on fige la colonne et on parcours les lignes
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
	public static boolean aGagneDiagMont (int x, int y, int joueur) { //Pour la diagonale on prend les mêmes entrées
        int compteur = 0;
        int o=x; //On établie deux variables o et a pour ordonnée et abscisse qui sont égales aux coordonnées du point de départ
        int a=y;
        if(!caseCorrecte(o, a)) return false; //On vérifie que la case appartient bien au tableau
        while(caseCorrecte(o, a)) { //Tant qu'on est encore dans la grille on reste sur le même concept que les deux fonctions précédentes
            if(grille[o][a]==joueur) {
                compteur+=1;
                if(compteur==4) return true;
            }
            else compteur=0;
            o-=1;		//L'incrémentation est différente, la ligne 0 est en haut de la grille donc pour monter il faut retrancher 1 à la variable 0
            a+=1;		//Pour les colonnes on incrémente de 1
        }
        return false;
    }
	public static boolean aGagneDiagDesc (int x, int y, int joueur) {	//Pour cette fonction on est sur le même concept que la fonction diagMont
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
            o+=1;	//Vu qu'on descend cette fois-ci on incrémente de 1 la variable o
            a+=1;
        }
        return false;
    }
	public static boolean aGagne(int joueur) { //La fonction aGagne prend en entrée le numéro du joueur pour chercher ses jetons
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) { //On parcourt la grille cherchant si le joueur a gagné, il ne suffit que l'une des quatres fonction renvoit vrai pour que le joueur gagne
				if(aGagneHor(i, j, joueur) || aGagneVer(i, j, joueur) || aGagneDiagMont(i, j, joueur) || aGagneDiagDesc(i, j, joueur))return true;
			}
		}
		return false;		//Si on sort de la boucle cela veut dire que le joueur n'a pas gagné on renvoit faux
	}
	public static boolean verifierPremièreLigne() { //Cette fonction est ici pour vérifier si la ligne 0 de la grille est remplie
		for(int j=0;j<grille[0].length;j++) {
			if(grille[0][j]==0) return false;
		}
		return true;
	}
	public static boolean matchNul() { 		//Cette fonction utilise les deux fonctions précédentes pour vérifier s'il y a match nul
		if(verifierPremièreLigne() == true && !aGagne(1)== true && !aGagne(2)== true) return true;		//Si la première ligne est pleine et que ni le joueur 1 ni le joueur 2 n'ont pas gagné il y a match nul
		else return false; //Sinon on peut encore jouer ou un des joueurs a gagné
	}
	static int joueur=1;		//Dans ce jeu le joueur 1 va toujours commencé en premier quand on lance le programme la valeure joueur est à 1 et elle sera en entré des fonctions aGagne
	public static void boucle() {		//Cette fonction est le corps du jeu
		Scanner sc = new Scanner(System.in); 	//On ouvre un scanner
		while((!aGagne(1) && !aGagne(2)) && !matchNul()){  //Tant qu'on n'a pas une condition de fin de partie vraie la boucle continue
			afficheGrille();		//On affiche la grille pour voir les coups à disposition
			boolean check=false;	//Le booléen check s'assure que le joueur joue un coup valide
			int c=0;				//c pour coup est la variable qui va prendre la valeure que le joueur entrera
			while(!check) {
				System.out.println("Quel coup pour le joueur "+joueur+" ?");
				c=sc.nextInt();			//On demande au joueur d'entrer la colonne où il souhaite placer son jeton
				if(c<0||c>6) {
					System.out.println("Coup invalide"); //Si le joueur pour une quelconque raison décide de placer un coup hors de la grille on lui demande de réessayer
				}
				else check=true;
			}
			jouer(joueur, c); //On fait jouer le coup 
			joueur=3-joueur;  //Cette fonction sert à changer le jouer 3-1=2; 3-2=1
		}
		sc.close(); //On ferme le scanner
	}
	public static void jeu() {		//La fonction est le cerveau du jeu, elle initialise la grille, lance la boucle
		initialisationGrille();
		boucle();
		afficheGrille(); //Une fois la partie fini on affiche la grille finale
		if(aGagne(1) == true) System.out.println("Le joueur 1 a gagné !");		//On affiche le vainqueur
		else if(aGagne(2)== true) System.out.println("Le joueur 2 a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	} 
	public static void joueCoupRandom() {	//Pour l'IA on veut lui faire faire un coup aléatoire, avec le générateur de numéro.
		boolean check=false; //check sert à vérifier si le coup est valide
		int joue=0;  //joue prendra la valeure que renverra la fonction entierAleatoire
		while(!check) {
			joue=entierAleatoire(0, 6);
			if(grille[0][joue]==0) check=true;  //Si la colonne n'est pas pleine alors on peut jouer le coup
		}
		jouer(2, joue);
	}
	
	public static void boucleIA() { //Cette boucle est conçue pour l'IA
		Scanner sc = new Scanner(System.in);
		while((!aGagne(1) && !aGagne(2)) && !matchNul()){
			afficheGrille();
			if(joueur==1) {		//Si c'est le tour du joueur on fait la boucle habituelle
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
			}
			else if(joueur==2) joueCoupRandom();	//Sinon c'est l'IA qui place son coup	
			joueur=3-joueur;
		}
		sc.close();
	}
	public static void jeuIA() {		//Cette fonction fait appel à la boucleIA
		initialisationGrille();
		boucleIA();
		afficheGrille();
		if(aGagne(1) == true) System.out.println("le joueur 1 a gagné !");
		else if(aGagne(2)== true) System.out.println("L'IA a gagné !");
		else {
			if(matchNul() == true) System.out.println("Match Nul !");
		}
	} 
	public static boolean opportunitéeHor() {  //Pour le niveau 2 de l'IA on veut que dès que l'IA a une opportunitée de gagnée elle joue ce coup
		int compteur=0;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) { //Le concept est le même que aGagneHor, sauf que quand le compteur est à 3 on vérifie les cases aux deux extrémités pour voir si le coup est jouable
				if(grille[i][j]==2)compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i, j) && caseCorrecte(i, j+1)) {
					if(grille[i][j+1]==0) {
						jouer(2, j+1);
						return true;		//On renvoit vrai car le coup est déjà joué
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
		return false;		//En sortant de la boucle on a pas jouer de coup, on renvoit faux
	}
	public static boolean opportunitéVer() {		//Sur le même concept que la fonction précédente on vérifie si on peut gagner en verticale
		int compteur=0;
		for(int j=0;j<grille[0].length;j++) {
			for(int i=0;i<grille.length;i++) {
				if(grille[i][j]==2) compteur+=1;
				else compteur=0;
				if(compteur==3 && caseCorrecte(i-compteur, j)) { //La seule différence on ne peut gagner qu'en jouant le coup sur l'extrémité en haut
					if(grille[i-compteur][j]==0) {
						jouer(2, j);
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
	public static boolean opportunitéDiagMont(int x, int y) {		//Sur le même concept que aGagneDiagMont à partir des coordonnées d'entrées on vérifie si on peut gagner en diagonale montante
		int o=x;
		int a=y;
		int compteur=0;
		while(a>6 || o<0) {
			if(caseCorrecte(o, a)) {
				if(grille[o][a]==2) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(o-1, a+1) && caseCorrecte(o, a+1)) { //On veut vérifier si le coup est jouable, si on peut jouer en diagonale
					if(grille[o][a+1]!=0) { //Si la case à droite du dernier jeton de la chaine est remplie on peut jouer le coup
						jouer(2, a+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(o+compteur, a-compteur) && caseCorrecte(o+compteur+1, a-compteur)) { //On fait la même chose pour l'autre extrémité
					if(grille[o+compteur+1][a-compteur]!=0) {
						jouer(2, a-compteur);
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
	
	public static boolean opportunitéDiagDesc(int x, int y) {		//Sur le même concept que la fonction précédente cette fois-ci avec la diagonale descendante
		int o=x;
		int a=y;
		int compteur=0;
		while(a>6 || o>5) {
			if(caseCorrecte(o, a)) {
				if(grille[o][a]==2) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(o+1, a+1)) {
					if(caseCorrecte(o+2, a+1)) {
						if(grille[o+2][a+1]!=0) {
							jouer(2, a+1);
							return true;
						}
					}
					else if(o+1==5 && grille[o+1][a+1]==0) jouer(2, a+1);		//Si on a une opportunité au fond à droite de la grille on demande à jouer le coup
				}
				else if(compteur==3 && caseCorrecte(o-compteur, a-compteur) && caseCorrecte(o-compteur+1, a-compteur)) {
					if(grille[o-compteur+1][a-compteur]!=0) {
						jouer(2, a-compteur);
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
	public static boolean opportunitéeVictoire() {			//Cette fonction détermine si on a un coup à jouer pour gagner en utilisant les quatres fonctions précédantes
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
	public static void joueCoupRandom2() {			//joueCoupRandom2 détermine si l'IA peut gagner en un coup sinon elle fait appelle joueCoupRandom
		if (!opportunitéeVictoire()) joueCoupRandom();
	}
	
	public static void boucleIA2() {		//Comme boucleIA on fait appelle à la fonction joueCoupRandom2
		Scanner sc = new Scanner(System.in);
		while((!aGagne(1) && !aGagne(2)) && !matchNul()){
			afficheGrille();
			if(joueur==1) {
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
			}
			else if(joueur==2) joueCoupRandom2();
			joueur=3-joueur;
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
	
	public static boolean opportunitéeDefHor() {		//En utilisant les fonctions pour le niveau 2, pour le niveau 3 on calque le jeu de l'IA sur celui du joueur pour un style défensif
		int compteur=0;
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
				if(grille[i][j]==1)compteur+=1;		//Cette fois on vérifie les jetons du joueur 1
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
						jouer(2, j);
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
		int o=x;
		int a=y;
		int compteur=0;
		while(a>6 || o<0) {
			if(caseCorrecte(o, a)) {
				if(grille[o][a]==1) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(o-1, a+1) && caseCorrecte(o, a+1)) {
					if(grille[o][a+1]!=0) {
						jouer(2, a+1);
						return true;
					}
				}
				else if(compteur==3 && caseCorrecte(o+compteur, a-compteur) && caseCorrecte(o+compteur+1, a-compteur)) {
					if(grille[o+compteur+1][a-compteur]!=0) {
						jouer(2, a-compteur);
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
		int o=x;
		int a=y;
		int compteur=0;
		while(a>6 || o>5) {
			if(caseCorrecte(o, a)) {
				if(grille[o][a]==1) compteur+=1;
				else compteur = 0;
				if(compteur==3 && caseCorrecte(o+1, a+1)) {
					if(caseCorrecte(o+2, a+1)) {
						if(grille[o+2][a+1]!=0) {
							jouer(2, a+1);
							return true;
						}
					}
					else if(a+1==5 && grille[o+1][a+1]==0) jouer(2, a+1);
				}
				else if(compteur==3 && caseCorrecte(o-compteur, a-compteur) && caseCorrecte(o-compteur+1, a-compteur)) {
					if(grille[o-compteur+1][a-compteur]!=0) {
						jouer(2, a-compteur);
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
	public static boolean opportunitéeDefense() {	//OpportunitéeDéfense possède la même fonction qu'opportunitéVictoire sauf qu'on cherche à défendre
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
	public static void joueCoupRandom3() {		//Si on n'est pas sous la menace du jouer on fait appel à la fonction joueCoupRandom
		if (!opportunitéeDefense()) joueCoupRandom();
	}
	
	public static void boucleIA3() {		//Comme les deux autres bouclesIA on fait appel à joueCoupRandom3
		Scanner sc = new Scanner(System.in);
		while((!aGagne(1) && !aGagne(2)) && !matchNul()){
			afficheGrille();
			if(joueur==1) {
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
			}
			else if(joueur==2) joueCoupRandom3();
			joueur=3-joueur;
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
	
	public static void main(String[] args){		//On est dans l'interface du jeu
		System.out.println("Puissance 4 ! (Par Cecilia Duchene et Sébastien Kinder)");
		Scanner s = new Scanner(System.in);
		System.out.println("2 Joueurs (1)");		//On demande à l'utilisateur s'il veut joueur contre un humain ou contre l'IA
		System.out.println("Contre l'IA (0)");
		int n=s.nextInt();
		if(n==1) jeu();		//Si l'utilisateur joue contre quelqu'un alors on démarre le mode 2 joueurs
		else if(n==0) {		//Sinon on lui demande contre quelle IA il souhaite se mesurer
			System.out.println("Niveau 1 (1)");
			System.out.println("Niveau 2 (2)");
			System.out.println("Niveau 3 (3)");
			n=s.nextInt();
			if(n==1) jeuIA();		//Si l'utilisateur rentre 1 on démarre le mode Humain VS IA mode aléatoire
			else if(n==2) jeuIA2();	//Si l'utilisateur rentre 2 on démarre le mode Humain VS IA mode offensif
			else {					
				if(n==3) jeuIA3();	//Si l'utilisateur rentre 3 on démarre le mode Humain VS IA mode défensif
				else System.out.println("Erreur !");
			}
		}
		else System.out.print("Erreur !");
		s.close();	
	}
}

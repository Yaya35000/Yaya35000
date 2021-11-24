package demineur;
// Pour utiliser des scanners pour lire des entrées depuis le clavier
// utilisés en questions 4.d] pour la fonction jeu()
import java.util.Scanner;

// Pour la fonction entierAleatoire(a, b) que l'on vous donne ci-dessous
import java.util.concurrent.ThreadLocalRandom;

//import javax.xml.crypto.dsig.XMLSignature.SignatureValue;

// L'unique classe de votre projet
public class projet_demineur {
	
	public static void afficheTab2D(int tab [][]) 
	{
		System.out.println("\n");
        for(int i=0;i<tab.length;i++) {
            for(int j=0;j<tab[i].length;j++) {
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }

    }

	// Donné, utile pour la question 1.b]
	public static int entierAleatoire(int a, int b){
		// Renvoie un entier aléatoire uniforme entre a (inclus) et b (inclus).
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}


	//
	// Exercice 1 : Initialisation des tableaux
	//

	// Question 1.a] déclarez les variables globales T et Tadj ici
	static int[][] T;//Voici les variables globale
	static int[][] Tadj;

	// Question 1.b] Fonction init
	public static void init(int h, int l, int n) {
		T=new int[h][l];
		Tadj=new int[h][l];
		for(int k=0;k<n;k++) {
			int i=entierAleatoire(0,h-1);
			int j=entierAleatoire(0,l-1);
			if(Tadj[i][j]==-1) {
				n+=1;
			}
			Tadj[i][j]=-1;
			
		}
	}

	// Question 1.c] Fonction caseCorrecte
	public static boolean caseCorrecte(int i,int j) {
		return (T.length>i && i>=0 && j>=0 && j<T[0].length);
	}

	// Question 1.d] Fonction calculerAdjacent
	public static void calculerAdjacent() {
		for(int i=0;i<Tadj.length;i++) {
			for(int j=0;j<Tadj[i].length;j++) {
				if(Tadj[i][j]==0) {
					if(caseCorrecte(i,j-1)==true) {
						if(Tadj[i][j-1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i-1,j-1)==true) {
						if(Tadj[i-1][j-1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i-1,j)==true) {
						if(Tadj[i-1][j]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i-1,j+1)==true) {
						if(Tadj[i-1][j+1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i,j+1)==true) {
						if(Tadj[i][j+1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i+1,j+1)==true) {
						if(Tadj[i+1][j+1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i+1,j)==true) {
						if(Tadj[i+1][j]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
					if(caseCorrecte(i+1,j-1)==true) {
						if(Tadj[i+1][j-1]==-1) {
							Tadj[i][j]=Tadj[i][j]+1;
						}
					}
						
				}
					
			}
		}
	}

	//
	// Exercice 2 : Affichage de la grille
	//

	// Question 2.a]
	public static void afficherGrille(boolean affMines) { 
		System.out.print("\n\n__" + "|");
		for(int i=0;i<T[0].length;i++) {
			if(i<26) System.out.print((char)('A'+i)+"|");
			else System.out.print((char)('A'+(i+6))+"|");
		}
		for(int j=0;j<T.length;j++) {
			System.out.println();
			if(j<10) System.out.print("0"+j+"|");
			else System.out.print(j+"|");
			for(int k=0;k<T[0].length;k++) {
				if(T[j][k]==0) {
					if(affMines && Tadj[j][k]==-1) System.out.print("!");
					else System.out.print(" ");
					}
				if(T[j][k]==1) {
					if(affMines && Tadj[j][k]==-1) System.out.print("!");
					else System.out.print(Tadj[j][k]);
					}
				if(T[j][k]==2) {
					if(affMines && Tadj[j][k]==-1) System.out.print("!");
					else System.out.print("X");
					}
				System.out.print("|");
			}
		}
	}
		
		// Note : Dans un premier temps, considérer que la grille contiendra au plus 52 colonnes
		// (une pour chaque lettre de l'alphabet en majuscule et minuscule) et au plus 100 lignes
		// (entiers de 0 à 99).


	//
	// Exercice 3 : Révéler une case
	//

	// Question 3.a]
	public static boolean caseAdjacenteZero(int i, int j) {
        if(caseCorrecte(i,j)==false)return false;
        else if(caseCorrecte(i-1,j-1)&&Tadj[i-1][j-1]==0)return true;
        else if(caseCorrecte(i-1,j)&&Tadj[i-1][j]==0)return true;
        else if(caseCorrecte(i-1,j+1)&&Tadj[i-1][j+1]==0)return true;
        else if(caseCorrecte(i,j-1)&&Tadj[i][j-1]==0)return true;
        else if(caseCorrecte(i,j+1)&&Tadj[i][j+1]==0)return true;
        else if(caseCorrecte(i+1,j-1)&&Tadj[i+1][j-1]==0)return true;
        else if(caseCorrecte(i+1,j)&&Tadj[i+1][j]==0)return true;
        else if(caseCorrecte(i+1,j+1)&&Tadj[i+1][j+1]==0)return true;
        else return false;
    }
	// Question 3.b]
	public static void revelation(int i, int j){		
		T[i][j] = 1;
		boolean b=false;
		if(Tadj[i][j] == 0){
			while(b == true){		
				for(int k=0;k<T.length;k++){
					for(int l=0;l<T[0].length;l++) {
						if(T[k][l] == 0 && caseAdjacenteZero(k,l)){
							T[k][l]=1;
							b=true;
						}	
					}
				}
			}
		}			
		
	}
        


	// Question 3.c] Optionnel
	public static void relevation2() { 
	}

	// Question 3.d]
	public static void actionDrapeau(int i,int j){
		if(Tadj[i][j] != 1 || Tadj[i][j] != 2){
			T[i][j] = 2;
		}
		if(Tadj[i][j] == 2){
			T[i][j] = 0;
		}
	}
	
	
	// Question 3.e]
	public static boolean revelerCase(int i,int j) {
		if(Tadj[i][j] == -1){
			return false;
		}
		else{
			revelation(i, j);
			return true;			
		}
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static boolean aGagne(){
		for(int i=0;i<T.length;i++){
			for(int j=0;j<T[0].length;j++){
				if(T[i][j] != 1 && Tadj[i][j]!=-1){
					return false;
				}
			}
		}
		return true;
	}

	// Question 4.b]
	public static boolean verifierFormat(String uneEntreeSaisie) {
		String ligne = uneEntreeSaisie.substring(1,3);		
		char colonne = uneEntreeSaisie.charAt(3);
		return (caseCorrecte(Integer.parseInt(ligne), colonne - 65) && uneEntreeSaisie.length() == 4 && (uneEntreeSaisie.charAt(0) == 'r' || uneEntreeSaisie.charAt(0) == 'd') && uneEntreeSaisie.charAt(3) >= 'A' && uneEntreeSaisie.charAt(3) <= 'Z' && uneEntreeSaisie.charAt(1) >= '0' && uneEntreeSaisie.charAt(1) <= '9' && uneEntreeSaisie.charAt(2) >= '0' && uneEntreeSaisie.charAt(2) <= '9');
	}

	// Question 4.c]
	public static int[] conversionCoordonnees(String input) {
		int[] coordonnees=new int[3];
		if(verifierFormat(input)) {
			if(input.charAt(0)== 'd'){
				coordonnees[2]=0;
			}
			else{
				coordonnees[2]=1;
			}
			String ligne = input.substring(1,3);
			coordonnees[0] = Integer.parseInt(ligne);
			char colonne = input.charAt(3);
			int soustraction = 0;
			if(colonne >= 'A' && colonne <= 'Z'){
				soustraction = 65;
			}
			else{
				soustraction = 71;
			}
			coordonnees[1] = colonne - soustraction;			
		}
		return(coordonnees);	
	}

	// Question 4.d]
	public static void jeu() {
		
		afficherGrille(false);
		Scanner sc = new Scanner( System.in );
		System.out.print("\n\n Saisir les coordonnees d'une case :" );
		String rep = sc.next();
		String ligne = rep.substring(1,3);
		int ligneInt = Integer.parseInt(ligne);
		char colonne = rep.charAt(3);	
		int soustraction = 0;
		if(colonne >= 'A' && colonne <= 'Z'){
			soustraction = 65;
		}
		else{
			soustraction = 71;
		}
		int colonneInt = colonne - soustraction;
		if(caseCorrecte(ligneInt, colonneInt)){
			char action = rep.charAt(0);
			if(action == 'r'){
				if(revelerCase(ligneInt, colonneInt) == false) {
					System.out.print("\nPERDU !" );
					afficherGrille(true);					
				}				
				else {					
					if(aGagne()){
						System.out.print("\nGAGNER !" );
					}
					else{
						jeu();
					}
				}	
			}
			else{
				actionDrapeau(ligneInt, colonneInt);
				jeu();
			}
		}
		else{
			System.out.print("\n MAUVAISE SAISIE, REESSAYER" );
			jeu();
		}
		sc.close();		
	}

	// Question 4.e]
	// Votre *unique* méthode main
	public static void main(String[] args) {
		int ligne = 0;
		int colonne = 0;
		int mine = 0;
		Scanner sc = new Scanner( System.in );
		System.out.print("Nombre de ligne :" );
		ligne = sc.nextInt();
		System.out.print("Nombre de colonne :" );
		colonne = sc.nextInt();
		System.out.print("Nombre de mine :" );
		mine = sc.nextInt();		
		init(ligne, colonne, mine);		
		calculerAdjacent();
		jeu();
		sc.close();
	}
}

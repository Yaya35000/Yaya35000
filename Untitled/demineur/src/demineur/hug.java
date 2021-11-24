package demineur;
// exo1

public class hug{

	public static void afficheTableauChar(char[] tab) {
		for ( int i = 0; i<tab.length; i++) {
			System.out.print(tab[i]);
		}
		System.out.println();
    }
	
	public static void afficheChaine(String str) {
		System.out.println(str);
	}
	
	public static void remplacerChar(char[] tab, char oldChar, char newChar) {
		for ( int i = 0; i<tab.length; i++) {
			if (tab[i] == oldChar) tab[i] = newChar;
		}
	}
	
	public static char[] remplacerCharCopy(char[] tab, char oldChar, char newChar) {
		char[] resultat;
		resultat = new char[tab.length];
		for ( int i = 0; i<tab.length; i++) {
			if (tab[i] == oldChar) {
				resultat[i] = newChar;
			}
			else {
				resultat[i] = tab[i];
			}
		}
		return resultat;
	}
	
	public static String remplaceCharInString(String str, char oldChar, char newChar) {
		String resultat = "";
		for ( int i = 0; i<str.length(); i++) {
			if (str.charAt(i) == oldChar) {
				resultat = resultat + newChar;
			}
			else {
				resultat = resultat + str.charAt(i);
			}
		}
		return resultat;
	}

 //exo2
	
    public static int nombreDeE(String s) {
        int resultat = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'e')
                resultat++;
            i++;
        }
        return resultat;
    }
    public static void main(String[] args) {
        String s = "le si et le la en cle de fa.";
		System.out.println(nombreDeDeuxLettres(s));
    }
  
  //exo3
    
    public static int laPlaceDeLOccurence(String str, char a, int b) {
    	int d = 0;
    	for (int i = 0; i<str.length(); i++) {
    		if (str.charAt(i) == a) {
    			d++;
    			if (d == b) return i;
    		}
    	}
    	return -1;
    }
    
    public static int nombreDe_e(String s) {
    	int resultat = 0;
    	for(int i = 0; i<s.length(); i++) {
    		if (s.charAt(i) == 'e')resultat += 1;
    	}
    	return resultat;
    }
    
    public static int nombreDeDeuxLettres(String str) {
    	int resultat = 0;
    	char[] stop = {' ',',',';',':','!','?','.'};
    	int countSpace = 0;
		
    	for (int i = 0; i<str.length(); i++) {
    		System.out.println(str.charAt(i));
    		for (int j = 0; j<stop.length; j++) {
    			System.out.println(" = " + stop[j]);
    			if (str.charAt(i) == stop[j]) {
    				if (countSpace == 2) {
    					resultat ++;
    				}
    				countSpace = -1;
    				break;
    			}
    		}
    		countSpace ++;
    	}
    	return resultat;
    }
    
    
    
    
    
    
    
    
    
	
}

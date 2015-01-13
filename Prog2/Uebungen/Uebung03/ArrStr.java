public class ArrStr {
    // Zweidimensionales Array a zeilenweise ausgeben.
    public static void print (int [] [] a) {
	for (int i = 0; i < a.length; i++) {
	    for (int j = 0; j < a[i].length; j++) {
		System.out.print(a[i][j] + " ");
	    }
	    System.out.println();
	}
    }

    // Pascalsches Dreieck bis Zeile n berechnen.
    public static int [] [] pascal (int n) {
	// Array mit n Zeilen.
	int [] [] p = new int [n] [];

	// Erste Zeile mit einer 1.
	p[0] = new int [] { 1 };

	// Weitere Zeilen.
	for (int i = 1; i < n; i++) {
	    // Zeile mit i+1 Elementen erzeugen.
	    p[i] = new int [i+1];

	    // Randwerte mit 1 initialisieren.
	    p[i][0] = p[i][i] = 1;

	    // Übrige Werte aus darüberstehenden Werten berechnen.
	    for (int j = 1; j < i; j++) {
		p[i][j] = p[i-1][j-1] + p[i-1][j];
	    }
	}

	// Dreieck zurückliefern.
	return p;
    }

    // Ist s ein Palindrom?
    public static boolean isPalindrome (String s) {
	// Sonderfall: s ist null.
	if (s == null) return true;

	// Zeichen von s paarweise vergleichen.
	// Resultatwert false, sobald eine Abweichung vorliegt.
	for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
	    if (s.charAt(i) != s.charAt(j)) return false;
	}

	// s ist ein Palindrom.
	return true;
    }

    // Großbuchstaben in s in Kleinbuchstaben umwandeln.
    public static String toLower (String s) {
	// Resultatstring (anfangs leer).
	String r = "";

	// String s zeichenweise durchlaufen.
	for (int i = 0; i < s.length(); i++) {
	    // Wenn das aktuelle Zeichen c ein Großbuchstabe ist,
	    // wird es durch den zugehörigen Kleinbuchstaben ersetzt.
	    char c = s.charAt(i);
	    if ('A' <= c && c <= 'Z') c += 'a' - 'A';

	    // Zeichen c zum Resultatstring hinzufügen.
	    r += c;
	}

	// Resultatstring zurückgeben.
	return r;
    }

    // Hauptmethode.
    public static void main (String [] args) {
	print(pascal(1));
	print(pascal(10));

	System.out.println(isPalindrome("Otto"));		// false
	System.out.println(isPalindrome("otto"));		// true

	System.out.println(toLower("TEST 12 test!"));		// test 12 test!
    }
}

// Taylorpolynome.
class Taylor {
    // Taylorpolynom einer Funktion an der Stelle x berechnen.
    // f[k] enthält den Wert der k-ten Ableitung der Funktion
    // an der Entwicklungsstelle 0.
    // Der Grad des Polynoms ist f.length - 1.
    // (Daher muss f mindestens ein Element enthalten.)
    public static double compute (double [] f, double x) {
	// Grad des Polynoms.
	int n = f.length - 1;

	// Summanden s[k] = f[k] * (x hoch k) / (Fakultät von k)
	// berechnen.
	double [] s = new double [n + 1];
	s[0] = f[0];
	double xk = 1;
	for (int k = 1; k <= n; k++) {
	    xk = xk * x / k;
	    s[k] = f[k] * xk;
	}

	// Die Summanden s[k] aufsummieren.
	// Wenn man mit den kleinen Werten beginnt,
	// erreicht man tendenziell eine höhere Genauigkeit.
	// (Wenn die Schleife andersherum laufen würde,
	// könnte man sie mit der vorigen Schleife zusammenfassen
	// und bräuchte das Hilfsarray s nicht.)
	double y = 0;
	for (int k = n; k >= 0; k--) y += s[k];
	return y;
    }

    // Taylorpolynom n-ten Grades der Exponentialfunktion
    // an der Stelle x berechnen.
    public static double exp (int n, double x) {
	// Die k-te Ableitung der Exponentialfunktion an der Stelle 0
	// ist 1 für jeden Wert von k.
	double [] f = new double [n+1];
	for (int k = 0; k <= n; k++) f[k] = 1;
	return compute(f, x);
    }

    // Taylorpolynom n-ten Grades der Sinusfunktion
    // an der Stelle x berechnen.
    public static double sin (int n, double x) {
	// Die k-te Ableitung der Sinusfunktion an der Stelle 0
	// ist 0, 1, 0, -1, ... für k = 0, 1, 2, 3, ...
	double [] f = new double [n+1];
	int sign = 1;
	for (int k = 1; k <= n; k += 2) {
	    f[k] = sign;
	    sign = -sign;
	}
	return compute(f, x);
    }

    // Test.
    // Der Grad n wird als Kommandozeilenargument übergeben.
    public static void main (String [] args) {
	int n = Integer.parseInt(args[0]);

	// Eulersche Zahl e mit maximal möglicher Genauigkeit.
	System.out.println("e =            " + Math.E);

	// Eulersche Zahl e als Näherungswert der Exponentialfunktion
	// an der Stelle 1.
	System.out.println("exp(n, 1) =    " + exp(n, 1));

	// Näherungswerte für sin(PI/2) = 1 und sin(PI) = 0.
	System.out.println("sin(n, PI/2) = " + sin(n, Math.PI/2));
	System.out.println("sin(n, PI) =   " + sin(n, Math.PI));
    }
}

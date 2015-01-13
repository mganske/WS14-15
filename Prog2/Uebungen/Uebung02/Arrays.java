class Arrays {
    // Array a elementweise in einer Zeile ausgeben.
    public static void print (double [] a) {
	for (int i = 0; i < a.length; i++) {
	    System.out.print(a[i]);
	    System.out.print(" ");
	}
	System.out.println();
    }

    // Reihenfolge der Elemente des Arrays a umdrehen.
    public static void mirror (double [] a) {
	int n = a.length;
	for (int i = 0; i < n/2; i++) {
	    double tmp = a[i];
	    a[i] = a[n-i-1];
	    a[n-i-1] = tmp;
	}
    }

    // Rotation des Arrays a um k Positionen nach rechts
    // als neues Array liefern.
    public static double [] rotate (double [] a, int k) {
	int n = a.length;
	double [] b = new double [n];
	for (int i = 0; i < n; i++) {
	    b[(i+k) % n] = a[i];
	}
	return b;
    }

    // Elemente der Arrays a und b per "Reißverschlussverfahren"
    // zu einem neuen Array zusammenfügen.
    public static double [] zip1 (double [] a, double [] b) {
	int m = a.length, n = b.length, sum = m + n;
	double [] c = new double [sum];
	for (int ai = 0, bi = 0, ci = 0; ci < sum; ) {
	    if (ai < m) c[ci++] = a[ai++];
	    if (bi < n) c[ci++] = b[bi++];
	}
	return c;
    }

    // Andere Implementierung von zip.
    public static double [] zip2 (double [] a, double [] b) {
	int m = a.length, n = b.length, min = m < n ? m : n;
	double [] c = new double [m+n];
	int ci = 0;
	for (int i = 0; i < min; i++) {
	    c[ci++] = a[i];
	    c[ci++] = b[i];
	}
	for (int i = min; i < m; i++) {
	    c[ci++] = a[i];
	}
	for (int i = min; i < n; i++) {
	    c[ci++] = b[i];
	}
	return c;
    }

    // Test.
    public static void main (String [] args) {
	// Arrays a und b durch Array-Initialisierer konstruieren.
	double [] a = { 3, 7, 9 }, b = { 2, 8, 6, 4, 10 };

	// Zwei Testaufrufe von mirror.
	System.out.println("mirror(a)");
	mirror(a); print(a);	// 9.0 7.0 3.0
	System.out.println("mirror(b)");
	mirror(b); print(b);	// 10.0 4.0 6.0 8.0 2.0

	// Zwei Testaufrufe von rotate.
	System.out.println("rotate(b, 2)");
	print(rotate(b, 2));	// 8.0 2.0 10.0 4.0 6.0
	System.out.println("rotate(b, 7)");
	print(rotate(b, 7));	// 8.0 2.0 10.0 4.0 6.0

	// Zwei Testaufrufe von zip.
	System.out.println("zip(a, b)");
	print(zip1(a, b));	// 9.0 10.0 7.0 4.0 3.0 6.0 8.0 2.0
	System.out.println("zip(b, a)");
	print(zip2(b, a));	// 10.0 9.0 4.0 7.0 6.0 3.0 8.0 2.0
    }
}

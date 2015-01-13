// Gebührenpflichtiges Konto.
class ChargedAccount extends Account {
    // Buchungszähler.
    private int count = 0;

    // Gebühr pro Buchung in Cent.
    public static int charge = 10;

    // Neues gebührenpflichtiges Konto mit Inhaber h
    // und ggf. Anfangsbetrag b initialisieren.
    public ChargedAccount (String h) {
	super(h);
    }
    public ChargedAccount (String h, int b) {
	super(h, b);
    }

    // Überschreibungen von deposit und withdraw,
    // die den Buchungszähler erhöhen.
    public void deposit (int amount) {
	super.deposit(amount);
	count++;
    }
    public void withdraw (int amount) {
	super.withdraw(amount);
	count++;
    }

    // Gebühren für ausgeführte Buchungen abbuchen
    // und Buchungszähler zurücksetzen.
    public void charge () {
	super.withdraw(count * charge);
	count = 0;
    }
}

// Test.
class ChargedAccountTest {
    public static void main (String [] args) {
	// Ein gebührenpflichtiges und ein limitiertes Konto erzeugen
	// und beide in Account-Variablen speichern.
	Account ca = new ChargedAccount("Mustermann", 5000);
	Account la = new LimitedAccount("Musterfrau", 3000, 1000);

	// Einige Buchungen ausführen.
	ca.deposit(5000);
	la.deposit(3000);
	ca.withdraw(5000);
	la.withdraw(3000);
	ca.transfer(5000, la);
	la.transfer(3000, ca);

	// Kontoführungsgebühren abbuchen.
	// (Damit der Compiler den Aufruf der Methode charge erlaubt,
	// muss das Aufrufobjekt den *statischen* Typ ChargedAccount
	// besitzen. Daher ist ein Typecast erforderlich.)
	((ChargedAccount)ca).charge();

	// Beide Kontostände ausgeben.
	System.out.println("Konto ca: " + ca.balance()); // 2960
	System.out.println("Konto la: " + la.balance()); // 5000
    }
}

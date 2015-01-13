// Klasse: Konto.
class Account {
  // Private Klassenvariable:
  // Nächste zu vergebende Kontonummer.
  private static int nextNumber = 1;

  // Private Objektvariablen:
  private final int number = nextNumber++;
				// Kontonummer (unveränderlich).
  private String holder;	// Kontoinhaber.
  private int balance = 0;	// Kontostand.

  // Öffentliche Konstruktoren: Konto mit Inhaber h, ggf.
  // Anfangsbetrag b und eindeutiger Nummer konstruieren.
  public Account (String h) {
    holder = h;
  }
  public Account (String h, int b) {
    this(h);			// Den anderen Konstruktor aufrufen.
    balance = b;
  }

  // Öffentliche Objektmethoden:
  // Kontonummer/-inhaber/-stand abfragen.
  public int number () { return number; }
  public String holder () { return holder; }
  public int balance () { return balance; }

  // Öffentliche Objektmethoden:
  // Betrag amount einzahlen/abheben/überweisen.
  public void deposit (int amount) {
    balance += amount;
  }
  public void withdraw (int amount) {
    balance -= amount;
  }
  public void transfer (int amount, Account that) {
    withdraw(amount);
    that.deposit(amount);
  }

  // Öffentliche Klassenmethode:
  // Anzahl bereits erzeugter Konten abfragen.
  public static int numberOfAccounts () { return nextNumber - 1; }
}

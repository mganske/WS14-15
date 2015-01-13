// Unterklasse von Account: Limitiertes Konto.
class LimitedAccount extends Account {
  // Zusätzliche Objektvariable:
  private int limit;		// Kreditlinie in Cent.

  // Konstruktoren:
  // Limitiertes Konto mit Inhaber h, ggf. Anfangsbetrag b,
  // Kreditlinie l und eindeutiger Nummer konstruieren.
  public LimitedAccount (String h, int b, int l) {
    super(h, b); // Konstruktor der Oberklasse Account aufrufen,
		 // um deren Objektvariablen zu initialisieren.
    limit = l;	 // Zusätzliche Objektvariable limit initialisieren.
  }
  public LimitedAccount (String h, int l) {
    // Entweder:		// Oder:
    super(h);			// this(h, 0, l);
    limit = l;			//
  }

  // Zusätzliche Objektmethode: Kreditlinie abfragen.
  public int limit () { return limit; }

  // Hilfsmethode: Kann Betrag amount abgezogen werden,
  // ohne die Kreditlinie zu überschreiten?
  private boolean check (int amount) {
    if (balance() - amount >= -limit) return true;
    System.out.println("Unzulässige Kontoüberziehung!");
    return false;
  }

  // Überschreiben geerbter Objektmethoden:
  // Betrag amount abheben/überweisen.
  public void withdraw (int amount) {
    if (check(amount)) {
      // Überschriebene Methode aufrufen.
      super.withdraw(amount);
    }
  }
  public void transfer (int amount, Account that) {
    if (check(amount)) {
      // Überschriebene Methode aufrufen.
      super.transfer(amount, that);
    }
  }
}

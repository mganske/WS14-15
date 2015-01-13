// Menge von Strings.
class StringSet {
    // Array von Elementen.
    private String [] elems;

    // Tatsächliche Anzahl der Elemente.
    // Arraypositionen mit Index >= card sind unbenutzt.
    private int card;

    // Position von s im Array elems liefern, falls es enthalten ist.
    // Andernfalls -1 liefern.
    private int search (String s) {
	for (int i = 0; i < card; i++) {
	    if (elems[i].equals(s)) return i;
	}
	return -1;
    }

    // Leere Menge mit Kapazität n >= 0 (d. h. Platz für n Elemente) erzeugen.
    public StringSet (int n) {
	elems = new String [n];
	card = 0;
    }

    // Leere Menge mit Kapazität n >= 0 erzeugen und anschließend
    // Element s einfügen, falls dies möglich ist (vgl. insert).
    public StringSet (int n, String s) {
	this(n);
	insert(s);
    }

    // Kapazität der Menge (d. h. Wert des Konstruktorparameters n) liefern.
    public int capacity () {
	return elems.length;
    }

    // Kardinalität der Menge (d. h. tatsächliche Anzahl ihrer Elemente) liefern.
    public int card () {
	return card;
    }

    // Menge in der Form "{ }", "{ a }", "{ a, b }" etc. ausgeben.
    public void print () {
	System.out.print("{");
	String comma = " ";
	for (int i = 0; i < card; i++) {
	    System.out.print(comma + elems[i]);
	    comma = ", ";
	}
	System.out.print(" }");
    }

    // Enthält die Menge das Element s?
    public boolean contains (String s) {
	return search(s) >= 0;
    }

    // Element s einfügen und true liefern, falls es noch nicht enthalten
    // und nicht null ist und die Kapazität der Menge noch nicht erschöpft ist;
    // andernfalls false liefern.
    public boolean insert (String s) {
	// Wenn das Element s null ist, soll es nicht eingefügt werden.
	if (s == null) return false;

	// Wenn die Kapazität erschöpft ist,
	// kann das Element nicht eingefügt werden,
	// egal, ob es bereits enthalten ist oder nicht.
	// Da diese Überprüfung schneller geht als die nächste,
	// wird sie zuerst durchgeführt.
	if (elems.length == card) return false;

	// Wenn das Element s bereits enthalten ist,
	// soll es nicht nochmals eingefügt werden.
	if (search(s) >= 0) return false;

	// Element an Position card speichern und Kardinalität erhöhen.
	elems[card++] = s;
	return true;
    }

    // Element s entfernen und true liefern, falls es enthalten ist;
    // andernfalls false liefern.
    public boolean remove (String s) {
	// Ein null-Element ist niemals in der Menge enthalten.
	if (s == null) return false;

	// Nach dem Element s suchen.
	int i = search(s);

	// Wenn das Element s nicht enthalten ist, ist nichts zu tun.
	if (i == -1) return false;

	// Letztes Element an Position i verschieben
	// und Kardinalität erniedrigen.
	elems[i] = elems[--card];
	elems[card] = null;
	return true;
    }

    // Schnittmenge der Mengen first und second als neue Menge mit
    // geeigneter Kapazität liefern (first und second bleiben unverändert).
    public static StringSet intersection (StringSet first, StringSet second) {
	// Leere Resultatmenge erzeugen.
	// Sie besitzt höchstens so viele Elemente
	// wie die kleinere der beiden Mengen.
	int n = first.card < second.card ? first.card : second.card;
	StringSet result = new StringSet(n);

	// Alle Elemente der Menge first, die auch in der Menge
	// second enthalten sind, zur Resultatmenge hinzufügen.
	for (int i = 0; i < first.card; i++) {
	    String s = first.elems[i];
	    if (second.contains(s)) result.insert(s);
	}
	return result;
    }
}

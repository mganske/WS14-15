#!/usr/bin/env python3
###############################################################################
## vim: et ai ts=4
##
## Bitte erst ab der Stelle im Code, die mit 'Hier beginnt Ihr Code' markiert
## ist, eigenen Code einfuegen.
## Aufgabe, Studenten und Gruppennummer müssen Sie nicht angeben.


###############################################################################
## Code fuer Aufgabe
import unittest
import uebung04

###############################################################################
## Hier beginnt Ihr Code


class MyUnitTests(unittest.TestCase):

    # Ein Beispiel Geruest fuer die TestCase Klasse
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_beispiel_aus_der_aufgabenstellung(self):
        # 50000 Euro Jahresgehalt, verheiratet, ein Kind
        (steuer, error) = uebung04.steuer(50000.0, 1, 1)

        # Teste Wert fuer steuer:
        # Sollwert 50000 * 0.25 * (1 - (0.2 + 0.1)) = 8750
        self.assertAlmostEqual(steuer, 8750.0)
        # steuer ist vom Typ float. assertAlmostEqual rundet die zu
        # vergleichenden Werte zunaechst auf 7 Nachkommastellen und
        # vergleicht dann die gerundeten Werte auf Gleichheit

        # Teste Wert fuer error:
        # Sollwert kein Fehler also 0
        self.assertEqual(error, 0)

    def test_keiner_muss_für_arbeit_druaf_zahlen(self):
        # -150000 Euro Jahresgehalt, 1 * verheiratet, drei Kinder
        (steuer, error) = uebung04.steuer(-150000.0, 1, 3)

        # Teste Wert fuer steuer:
        # Sollwert 0.0, da ungueltige Eingabe
        self.assertAlmostEqual(steuer, 0.0)

        # Teste Wert fuer error:
        # Sollwert ein Fehler also 1
        self.assertEqual(error, 1)

    def test_bigamie_ist_nicht_erlaubt(self):
        # 150000 Euro Jahresgehalt, 2 * verheiratet, drei Kinder
        (steuer, error) = uebung04.steuer(150000.0, 2, 3)

        # Teste Wert fuer steuer:
        # Sollwert 0.0, da ungueltige Eingabe
        self.assertAlmostEqual(steuer, 0.0)

        # Teste Wert fuer error:
        # Sollwert ein Fehler also 1
        self.assertEqual(error, 1)

    def test_verschiedene_gehaelter(self):
        # 10000 Euro Jahresgehalt, 0 * verheiratet, 0 Kinder
        (steuer, error) = uebung04.steuer(10000.0, 0, 0)
        self.assertAlmostEqual(steuer, 1200.0)
        self.assertEqual(error, 0)

        # 15000 Euro Jahresgehalt, 0 * verheiratet, 0 Kinder
        (steuer, error) = uebung04.steuer(15000.0, 0, 0)
        self.assertAlmostEqual(steuer, 2250.0)
        self.assertEqual(error, 0)

        # 25000 Euro Jahresgehalt, 0 * verheiratet, 0 Kinder
        (steuer, error) = uebung04.steuer(25000.0, 0, 0)
        self.assertAlmostEqual(steuer, 5000.0)
        self.assertEqual(error, 0)

        # 35000 Euro Jahresgehalt, 0 * verheiratet, 0 Kinder
        (steuer, error) = uebung04.steuer(35000.0, 0, 0)
        self.assertAlmostEqual(steuer, 8750.0)
        self.assertEqual(error, 0)

    def test_sultan(self):
        # 10000 Euro Jahresgehalt, 0 * verheiratet, 25 Kinder
        (steuer, error) = uebung04.steuer(10000.0, 0, 25)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 0)

    def test_wunsch_kinder(self):
        # 10000 Euro Jahresgehalt, 0 * verheiratet, -1 Kinder
        (steuer, error) = uebung04.steuer(10000.0, 0, -1)
        self.assertAlmostEqual(steuer, 0.0)
        self.assertEqual(error, 1)

###############################################################################

if __name__ == '__main__':

    unittest.main()


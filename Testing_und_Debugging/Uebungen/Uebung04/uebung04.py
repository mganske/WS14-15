#!/usr/bin/env python3
###############################################################################
## vim: et ai ts=4
##
## Bitte erst ab der Stelle im Code, die mit 'Hier beginnt Ihr Code' markiert
## ist, eigenen Code einfuegen.

###############################################################################

Aufgabe = 4                    # Diesen Eintrag nicht veraendern,
                               # anderenfalls wird die Aufgabe nicht gewertet!!

Studenten = []                 # Initalisierung der Studentenliste

###############################################################################
## Bitte tragen Sie in die folgenden Variablen Ihre Gruppennummer und die
## Mitglieder Ihrer Gruppe ein. Bitte verwenden Sie KEINE Umlaute!

Gruppennummer = 5
# Syntax fuer die Angabe der Namen und Matrikelnummern der einzelen
# Gruppenmitglieder:
#
Studenten.append({'matnr':49013, 'nachname':"Winterhalter", 'vorname':"Simon"})
Studenten.append({'matnr':49267, 'nachname':"Schuerle", 'vorname':"Sebastian"})
Studenten.append({'matnr':36603, 'nachname':"Ganske", 'vorname':"Marcus"})
# Studenten.append({'matnr':12348, 'nachname':"NACHNAME4", 'vorname':"VORNAME4"})
# Studenten.append({'matnr':12349, 'nachname':"NACHNAME5", 'vorname':"VORNAME5"})

###############################################################################
## Code fuer Aufgabe

###############################################################################
## Hier beginnt Ihr Code
def steuer(bruttogehalt, verheiratet, kinder):
    assert isinstance(bruttogehalt, float)
    assert isinstance(verheiratet, int)
    assert isinstance(kinder, int)
   
    if verheiratet != 0 and verheiratet != 1:
        return (0.0,1)
    if kinder < 0:
        return (0.0,1)
    if bruttogehalt < 0:
        return (0.0,1)
    if bruttogehalt <= 12000:
        steuersatz = 0.12
    elif bruttogehalt <= 20000:
        steuersatz = 0.15
    elif bruttogehalt <= 30000:
        steuersatz = 0.2
    else: 
        steuersatz = 0.25
    
    steuerlast = bruttogehalt * steuersatz * (1 - 0.2 * verheiratet - 0.1 * kinder)
    if steuerlast < 0:
        steuerlast = 0.0

    # Testwert fuer Beispiel aus der Aufgabenstellung.
    #steuerlast = 8750.0
    # ...
    
    return steuerlast, 0


###############################################################################
## Bitte erst innerhalb des folgenden if Blocks Funktionen aufrufen.
## Werden ausserhalb dieses Blocks Funktionen aufgerufen, so wird die Aufgabe
## nicht gewertet.

if __name__ == '__main__':  # pragma no cover

    ## Der folgende Funktionsaufruf prueft die Eintraege der Variablen
    ## Studenten, Gruppennummer und Aufgabe und gibt die Werte tabelarisch
    ## auf dem Bildschirm aus oder loest einen Fehler aus, falls die Form
    ## der Eintraege nicht korrekt ist.
    from Grading.Grading import *  # pragma no cover
    Grading.CheckStudents(Studenten, Gruppennummer, Aufgabe)  # pragma no cover
    
    ## Ab Hier kommen Ihre Aufrufe hin. 
    ## Schreiben Sie in jede der folgenden Zeilen den Kommentar 
    ## # pragma no cover
    ## am Ende mit dazu da Sie sonst nicht 100% Branch Coverage erreichen
    ## koennen. 
    ## In der Funktion steuer darf dieser Kommentar nicht verwendet werden!

    print(steuer(50000.0, 1, 1))  # pragma no cover

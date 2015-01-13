#!/usr/bin/env python3
###############################################################################
## vim: et ai ts=4
##
## Bitte erst ab der Stelle im Code, die mit 'Hier beginnt Ihr Code' markiert
## ist, eigenen Code einfuegen.

###############################################################################

Aufgabe = 6                    # Diesen Eintrag nicht veraendern,
                               # anderenfalls wird die Aufgabe nicht gewertet!!

Studenten = []                 # Initalisierung der Studentenliste

###############################################################################
## Bitte tragen Sie in die folgenden Variablen Ihre Gruppennummer und die
## Mitglieder Ihrer Gruppe ein. Bitte verwenden Sie KEINE Umlaute!

Gruppennummer = 5
# Syntax fuer die Angabe der Namen und Matrikelnummern der einzelen
# Gruppenmitglieder:
#
Studenten.append({'matnr': 49267, 'nachname': "Schuerle", 'vorname': "Sebastian"})
Studenten.append({'matnr': 49013, 'nachname': "Winterhalter", 'vorname': "Simon"})
Studenten.append({'matnr': 36603, 'nachname':"Ganske", 'vorname':"Marcus"})
# Studenten.append({'matnr':12348, 'nachname':"NACHNAME4", 'vorname':"VORNAME4"})
# Studenten.append({'matnr':12349, 'nachname':"NACHNAME5", 'vorname':"VORNAME5"})

###############################################################################
## Code fuer Aufgabe


# Importieren der fehlerhaften Queues
from Queue import Queue, Queue1, Queue2, Queue3, Queue4, Queue5, Queue6, Queue7, Queue8

# Importiere Funktionen um Dateien zu pruefen
import os.path

# Import random Funktionen
import random

# Import time Funktionen fuer seed
from time import time


API = ["empty", "full", "enqueue", "dequeue"]

QUEUES = ["Queue1", "Queue2", "Queue3", "Queue4", "Queue5", "Queue6", "Queue7", "Queue8"]


def create_random_api_calls(api=None, queues=None, queue_size=None, max_queue_size=2000, stream_length=None, max_stream_length=16000, seed=None):
    assert (api is None) or isinstance(api, list)
    assert (queues is None) or isinstance(queues, list)
    assert (queue_size is None) or (isinstance(queue_size, int) and queue_size > 0)
    assert isinstance(max_queue_size, int) and max_queue_size > 0
    assert (stream_length is None) or (isinstance(stream_length, int) and stream_length > 0)
    assert isinstance(max_stream_length, int) and max_stream_length > 0
    assert (seed is None) or isinstance(seed, int)

    # Set default for lists
    if api is None:
        api = API

    if queues is None:
        queues = QUEUES

    # create seed from time if not given otherwise
    if seed is None:
        seed = int(time() * 10000)

    # initialize random generator
    random.seed(seed)

    stream = []

    # store call parameter
    stream.append(["#", "api = '%s'" % ("', '".join(api))])
    stream.append(["#", "queues = '%s'" % ("', '".join(queues))])
    stream.append(["#", "queue_size = %s" % (repr(queue_size))])
    stream.append(["#", "max_queue_size = %s" % (repr(max_queue_size))])
    stream.append(["#", "stream_length = %s" % (repr(stream_length))])
    stream.append(["#", "max_stream_length = %s" % (repr(max_stream_length))])
    stream.append(["#", "seed = %s" % (repr(seed))])

    # choose queue implementation
    queue = random.choice(queues)

    # choose size of queue
    random_queue_size = random.randint(1, max_queue_size)
    if queue_size is None:
        queue_size = random_queue_size

    # store / call chosen queue and its length
    stream.append([str(queue), str(queue_size)])

    # choose stream_length
    random_stream_length = random.randint(1, max_stream_length)
    if stream_length == None:
        stream_length = random_stream_length

    # store chosen stream_length
    stream.append(["#", "chosen stream_length = %s" % (repr(stream_length))])

    for call in range(stream_length):
        api_call = random.choice(api)

        # only enqueue requires a second argument
        if api_call == "enqueue":
            value = random.randint(-2 ** 31, 2 ** 31)
            stream.append([str(api_call), str(value)])
        else:
            stream.append([str(api_call)])

    return stream


def store_stream(stream, filename):
    assert isinstance(stream, list)
    assert isinstance(filename, str)

    # allow only storing in current directory
    assert filename.find("/") == -1
    assert filename.find("\\") == -1

    try:
        with open(filename, "w") as fil:
            for line in stream:
                fil.write(" ".join(line) + "\n")
    except Exception:
        pass


def load_stream(filename):
    assert isinstance(filename, str)
    assert os.path.isfile(filename)

    print("Filename : " + filename)

    stream = []

    file = open(filename, 'r')
    for api_call in file:

        #print(api_call.rstrip())
        line = api_call.split()[0]
        #print(line)
        #print()

        streamPart = []

        if ('#' in line):
            streamPart.append(api_call.rstrip())
        elif (line in QUEUES):
            streamPart.append(str(api_call.split()[0]))
            streamPart.append(str(api_call.split()[1]))
        elif (line in API):
            if line == "enqueue":
                streamPart.append(str(line))
                streamPart.append(str(api_call.split()[1]))
            else:
                streamPart.append(str(line))

        else:
            return [["#", "ungueltiger Eintrag in der Datei"]]

        stream.append(streamPart)

    # # Enthaelt die zu ladenden Datei Eintraege, die nicht mit Elementen aus
    # # API, QUEUES oder mit "#" beginnenen oder deren API Benutzung unglueltig
    # # ist (falsche Argumente etc.) dann soll die Funktion das folgende
    # # zurueckgeben:
    # return [["#", "ungueltiger Eintrag in der Datei"]]
    # # Anderenfalls, wird der eingelesene Datenstrom zurueckgegenen.

    #print(stream)
    return stream


def run_stream(stream):
    assert isinstance(stream, list)
    for line in stream:
        assert isinstance(line, list)
        for entry in line:
            assert isinstance(entry, str)

    vgl_queue = []
    vgl_queue_size = 0

    queue = None

    try:
        for lnr in range(len(stream)):
            line = stream[lnr]
            if "#" in line [0]:
                # lines which beginns with '#' are comment lines and can be
                # skipped:
                continue

            if line[0] in QUEUES:
                # - check requirements for well formed API call
                #   (number of arguments, ...)
                # - initialize queue
                # - initialize vgl_queue
                #   (Hint: you can use the idea and code from loesung01.py)
                # - always call check_rep at the end
                ####################################
                # Hier beginnt Ihr Code fuer die initialisierung der Queue
                assert len(line) == 2
                vgl_queue = []
                vgl_queue_size = int(line[1])

                #print("Declaring - " + line[0] + " - with argument : " + line[1])
                if line[0] == "Queue1":
                    queue = Queue1(int(line[1]))
                elif line[0] == "Queue2":
                    queue = Queue2(int(line[1]))
                elif line[0] == "Queue3":
                    queue = Queue3(int(line[1]))
                elif line[0] == "Queue4":
                    queue = Queue4(int(line[1]))
                elif line[0] == "Queue5":
                    queue = Queue5(int(line[1]))
                elif line[0] == "Queue6":
                    queue = Queue6(int(line[1]))
                elif line[0] == "Queue7":
                    queue = Queue7(int(line[1]))
                elif line[0] == "Queue8":
                    queue = Queue8(int(line[1]))
                else:
                    raise Exception("None representative Queue")
                queue.check_rep()

            elif line[0] in API:
                # - check requirements for well formed API call
                #   (number of arguments, is queue initialized, ...)
                # - envoke API call on the queue and store return value
                # - envoke the same call on vgl_queue and store return value
                # - compare the return values via assert statement
                # - always call check_rep at the end
                ####################################
                # Hier beginnt Ihr Code fuer den API Call
                assert queue is not None

                #print("Executing command : " + line[0])

                if line[0] == "empty":
                    is_empty = queue.empty()
                    assert is_empty == (len(vgl_queue) == 0)
                if line[0] == "full":
                    is_full = queue.full()
                    assert is_full == (len(vgl_queue) == vgl_queue_size)
                if line[0] == "dequeue":
                    deq = queue.dequeue()
                    if len(vgl_queue) > 0:
                        vgl = vgl_queue.pop(0)
                    else:
                        vgl = None
                    assert deq == vgl
                if line[0] == "enqueue":
                    assert len(line) == 2
                    enq = queue.enqueue(int(line[1]))
                    if len(vgl_queue) < vgl_queue_size:
                        fill_ok = True
                        vgl_queue.append(int(line[1]))
                    else:
                        fill_ok = False
                    assert enq == fill_ok
                queue.check_rep()

            else:
                # Invalid API call
                return None
    except AssertionError:
        # Der Lauf dieses Streams hat einen Fehler gefunden
        stream.append(["#", "Test aborted in line: %d" % (lnr)])
        return stream
    except Exception as err:
        # Ein unerwarteter Fehler ist aufgetreten.
        print(err)
        return None
    else:
        return []

    # # Wenn der Testlauf ohne Fehler gelaufen ist:
    # return []
    # # Wenn der Testlauf mit einem Fehler abbricht, dann
    # # haengen sie an stream die folgende Zeile an
    # stream.append(["#", "Test aborted in line: %d" % (index)])
    # # wobei stream[index] den API call liefert, der abgebrochen ist.
    # return stream
    # # Wenn in der Struktur von stream etwas nicht stimmt:
    # return None

def testQueue():
    for SIZE in [1, 2, 2 ** 8 + 1]:
        q = Queue(SIZE)
        vglq = []
        for rnd in [1, 2]:
            for x in range(SIZE + 1):
                is_empty = q.empty()
                is_full = q.full()
                assert is_empty == (len(vglq) == 0), "%i. Fill: q SIZE=%i, x=%i, len(vglq)=%i, is_empty == (len(vglq)==0)" % (rnd, SIZE, x, len(vglq))
                assert is_full == (len(vglq) == SIZE), "%i. Fill: q SIZE=%i, x=%i, len(vglq)=%i, is_full == (len(vglq)==SIZE)" % (rnd, SIZE, x, len(vglq))
                if rnd == 2:
                    val = -x
                else:
                    val = x
                enq = q.enqueue(val)
                if len(vglq) < SIZE:
                    fill_ok = True
                    vglq.append(val)
                else:
                    fill_ok = False
                assert enq == fill_ok, "%i. Fill: q SIZE=%i, x=%i, len(vglq)=%i,enq=%s, fill_ok=%s, enq == fill_ok" % (rnd, SIZE, x, len(vglq), str(enq), str(fill_ok))

            for x in range(SIZE + 1):
                is_empty = q.empty()
                is_full = q.full()
                assert is_empty == (len(vglq) == 0), "%i. Clear: q SIZE=%i, x=%i, len(vglq)=%i, is_empty == (len(vglq)==0)" % (rnd, SIZE, x, len(vglq))
                assert is_full == (len(vglq) == SIZE), "%i. Clear: q SIZE=%i, x=%i, len(vglq)=%i, is_full == (len(vglq)==SIZE)" % (rnd, SIZE, x, len(vglq))
                deq = q.dequeue()
                if len(vglq) > 0:
                    vgl = vglq.pop(0)
                else:
                    vgl = None
                assert deq == vgl, "%i. Clear: q SIZE=%i, x=%i, len(vglq)=%i, deq=%s, vgl=%s, deq == vgl" % (rnd, SIZE, x, len(vglq), str(deq), str(vgl))


def main_run():
    for queue in QUEUES:
        if queue is "Queue3":
            main_run_Queue3_tweek()
        else:
            print("Create and run tests for %s: " % queue)
            for i in range(30):
                stream = create_random_api_calls(queues=[queue])
                result = run_stream(stream)
                if result is None:
                    print("X")
                    stream.append(["#", "Faulty API stream"])
                    store_stream(stream, "test_%s" % queue)
                    break
                elif len(result) == 0:
                    print(".")
                else:
                    print("F")
                    store_stream(result, "test_%s" % queue)
                    break
        print("\n")


def main_run_Queue3_tweek():
    print("Create and run tests for Queue3: ")
    for i in range(30):
        stream = create_random_api_calls(
            queues=["Queue3"],
            api=["enqueue", "enqueue", "enqueue", "full"],
            queue_size=1000,
            stream_length=5000)
        result = run_stream(stream)
        if result is None:
            print("X")
            stream.append(["#", "Faulty API stream"])
            store_stream(stream, "test_Queue3")
            break
        elif len(result) == 0:
            print(".")
        else:
            print("F")
            store_stream(result, "test_Queue3")
            break
        print("\n")


def load_all_run():
    for queue in QUEUES:
        print("Load and run test for %s: " % queue)
        if not os.path.isfile("test_%s" % queue):
            print("no testcase stored!")
            continue
        stream = load_stream("test_%s" % queue)
        result = run_stream(stream)
        if result is None:
            print("X")
        elif len(result) == 0:
            print(".")
        else:
            print("F")
        print("\n")


###############################################################################
## Bitte erst innerhalb des folgenden if Blocks Funktionen aufrufen.
## Werden ausserhalb dieses Blocks Funktionen aufgerufen, so wird die Aufgabe
## nicht gewertet.

if __name__ == '__main__':

    ## Der folgende Funktionsaufruf prueft die Eintraege der Variablen
    ## Studenten, Gruppennummer und Aufgabe und gibt die Werte tabelarisch
    ## auf dem Bildschirm aus oder loest einen Fehler aus, falls die Form
    ## der Eintraege nicht korrekt ist.
    from Grading.Grading import *
    Grading.CheckStudents(Studenten, Gruppennummer, Aufgabe)

    ## Aufruf der Testfunktion
    main_run()
    # Falls Sie fuer Queue3 keinen API Strom erzeugen koennen, dann implementiren
    # Sie, an main_run() orientiert, die Folgende Funktion, die den
    # create_random_api_calls() Aufruf aus den schriftlichen Aufgabe 2.1.3
    # enthaelt.

    load_all_run()

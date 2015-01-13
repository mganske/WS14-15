# SPEZIFIKATION:
#
# Die Queue Klasse stellt eine FIFO Warteschlange mit fester Laenge
# fuer Integes bereit.
#
# Der Konstruktor hat eine Parameter: ein Integer >0 der die maximale
# Anzahl von Elementen angibt, die die Warteschlange speichern kann.
#
# empty() gibt genau dann True zurueck, wenn die Warteschlange keine
# Elemnte enthaelt.
#
# full() gibt genau dann True zurueck, wenn die Warteschlange kein
# weiters Element speichen kann
#
# enqueue(i) versucht den Integerwert i in der Warteschlange abzulegen;
# Die Funktion gibt True zurueck, falls dieser Versuch erfolgreich war
# und False wenn die Warteschlange voll ist.
#
# dequeue() entfernt (nach dem FIFO Prinzip) eine Wert aus der
# Warteschlange und gibt diesen zurueck. Ist die Warteschlange leer,
# so wird None zuruek gegeben.

import array


class Queue:

    def __init__(self, size_max):
        assert size_max > 0
        self.max = size_max
        self.head = 0
        self.tail = 0
        self.size = 0
        self.data = array.array('l', range(size_max))

    def empty(self):
        return self.size == 0

    def full(self):
        return self.size == self.max

    def enqueue(self, store_value):
        if self.size == self.max:
            return False
        self.data[self.tail] = store_value
        self.size += 1
        self.tail += 1
        if self.tail == self.max:
            self.tail = 0
        return True

    def dequeue(self):
        if self.size == 0:
            return None
        store_value = self.data[self.head]
        self.size -= 1
        self.head += 1
        if self.head == self.max:
            self.head = 0
        return store_value

    def check_rep(self):
        assert self.tail >= 0
        assert self.tail < self.max
        assert self.head >= 0
        assert self.head < self.max
        if self.tail > self.head:
            assert (self.tail - self.head) == self.size
        if self.tail < self.head:
            assert (self.head - self.tail) == (self.max - self.size)
        if self.tail == self.head:
            assert (self.size == 0) or (self.size == self.max)


class Queue1(Queue):
    def __init__(self, size_max):
        assert size_max > 0
        self.max = size_max - 1                   # Fehler 1
        self.head = 0
        self.tail = 0
        self.size = 0
        self.data = array.array('i', range(size_max))


class Queue2(Queue):
    def empty(self):
        return self.size == - 1                   # Fehler 2


class Queue3(Queue):
    def full(self):
        return self.size == min(self.max, 2 ** 9)   # Fehler 3


class Queue4(Queue):
    def enqueue(self, store_value):
        if self.size == self.max:
            return False
        self.data[self.tail] = store_value
        self.size += 1
        self.tail += 1
        if self.tail == self.max:
            self.tail = min(1, self.size - 1)         # Fehler 4
        return True


class Queue5(Queue):
    def enqueue(self, store_value):
        if self.size == self.max:
            return False
        self.data[self.tail] = abs(store_value)             # Fehler 5
        self.size += 1
        self.tail += 1
        if self.tail == self.max:
            self.tail = 0
        return True


class Queue6(Queue):
    def dequeue(self):
        if self.size == 0:
            return False                          # Fehler 6
        store_value = self.data[self.head]
        self.size -= 1
        self.head += 1
        if self.head == self.max:
            self.head = 0
        return store_value


class Queue7(Queue):
    def dequeue(self):
        if self.size == 0:
            return None
        store_value = self.data[self.head]
        self.size -= 1
        self.head += 0                            # Fehler 7
        if self.head == self.max:
            self.head = 0
        return store_value


class Queue8(Queue):
    def dequeue(self):
        if self.size == self.size:                # Fehler 8
            return None
        store_value = self.data[self.head]
        self.size -= 1
        self.head += 1
        if self.head == self.max:
            self.head = 0
        return store_value

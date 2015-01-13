/*
 ============================================================================
 Name        : BankingSystem.c
 Author      : WiSe14/15
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void pause (void);
void einzahlen(int ID, int einzahlung_cent);
void test ();

const int gebuehr = 100;

int aktuellerKontostand_normalesKonto = 0;		// ID = 1
int aktuellerKontostand_limitedKonto1 = 0;		// ID = 2
int aktuellerKontostand_limitedKonto2 = 0;		// ID = 3
int aktuellerKontostand_taxedKonto = 0;			// ID = 4

enum kontos {
	normalesKonto = 1,
	limitedKonto1 = 2,
	limitedKonto2 = 3,
	taxedKonto = 4
};

int main(void) {

	test();
	return 0;
}


//-------------Limited Account 1----------------------------------------------
void einzahlen (int konto_ID, int einzahlung_cent) {

	switch (konto_ID) {
	case 1:
		aktuellerKontostand_normalesKonto += einzahlung_cent;
		printf("Aktueller Kontostand : %.2f Euro\n", aktuellerKontostand_normalesKonto / 100.0);
		break;
	case 2:
		aktuellerKontostand_limitedKonto1 += einzahlung_cent;
		printf("Aktueller Kontostand : %.2f Euro\n", aktuellerKontostand_limitedKonto1 / 100.0);
		break;
	case 3:
		aktuellerKontostand_limitedKonto2 += einzahlung_cent;
		printf("Aktueller Kontostand : %.2f Euro\n", aktuellerKontostand_limitedKonto2 / 100.0);
		break;
	case 4:
		aktuellerKontostand_taxedKonto += einzahlung_cent - gebuehr;
		printf("Aktueller Kontostand : %.2f Euro\n", aktuellerKontostand_taxedKonto / 100.0);
		break;
	default:
		printf("Konto ID unbekannt");
		break;
	}

}

// ---------------------------------------------------------------------------

//-------------Limited Account 2----------------------------------------------
void auszahlen () {

}

// ---------------------------------------------------------------------------

//-------------Taxed Account--------------------------------------------------
void taxedAccount () {

}

// ---------------------------------------------------------------------------

//-------------Test Accounting------------------------------------------------
void test () {
	/* Kontotypen: - Normales Bankkonto1
		                - Limitiertes Konto1 (Kreditlinie: 0 cent)
		                - Limitiertes Konto2 (Kreditlinie: - 20000 cent)
		                - Gebührenpflichtiges Konto1 (Gebühr: 100 cent / Transaktion)*/

	int normales_konto1 = 0,
			limitiertes_konto1 = 0,
			gebuehren_konto1 = 0,
			limitiertes_konto2 = 5000;

	/* Transaktionen: - einzahlen
		                   - abheben
		                   - überweisen (abheben konto 1 -> einzahlen konto 2)*/

	pause();
	printf("Einzahlung: 50 EUR auf normales Konto, 100 EUR auf limitiertes Konto, 250 EUR abzueglich 1 EUR Gebuehr auf Gebuehrenpflichtiges Konto\n");

	einzahlen(normalesKonto, 5015);
	einzahlen(limitedKonto1, 10000);
	einzahlen(limitedKonto2, 25000);
	//normales_konto1 += 5000;
	//limitiertes_konto1 += 10000;
	//gebuehren_konto1 += (25000 - 100);

	pause();
	printf("Ueberweisung über 24,99 EUR von gebuehrenpflichtiges Konto auf normales Konto\n");
	gebuehren_konto1 -= (2499 + 100);
	einzahlen(1, 2499);
	//normales_konto1 += 2499;

	pause();
	printf("Abhebung über 250 EUR von limitiertes Konto 2\n");
	if ((limitiertes_konto2 - 25000) >= -20000)
		limitiertes_konto2 -= 25000;
	else{
		printf("unzulaessige Kontoueberziehung!\n");
	}

	pause();
	printf("Abhebung über 105 EUR von limitertes Konto\n");
	if ((limitiertes_konto1 - 10500) >= 0){
		limitiertes_konto1 -= 10500;
	}
	else{
		printf("\t--> unzulaessige Kontoueberziehung!\n");
	}

	pause();
	gebuehren_konto1 -= (26000 + 100);
	printf("Kontostand normales Konto: %d,%d\tlimitiertes Konto1: %d,%.2d\tlimitiertes Konto2: %d,%.2d\tgebuehrenpflichtiges Konto: %d,%d\n", normales_konto1 /100, normales_konto1 % 100, limitiertes_konto1 / 100, limitiertes_konto1 % 100,limitiertes_konto2 / 100, limitiertes_konto2 % 100, gebuehren_konto1 / 100, (gebuehren_konto1 * ((gebuehren_konto1 > 0) - (gebuehren_konto1 < 0))) % 100);


}

//---------------------------------------------------------------------------














void myflush ( FILE *in )
{
	int ch;

	do
		ch = fgetc ( in );
	while ( ch != EOF && ch != '\n' );

	clearerr ( in );
}

void pause (void)
{
	printf ( "" );
	fflush ( stdout );
	getchar();
}

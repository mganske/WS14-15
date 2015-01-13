//
//  main.c
//  Hello World
//
//  Created by Ganske, Marcus on 30.10.14.
//  Copyright (c) 2014 Ganske, Marcus. All rights reserved.
//

#include <stdio.h>
#include <math.h>

int main(int argc, const char * argv[]) {
    
    /* Kontotypen: - Normales Bankkonto1
     - Limitiertes Konto1 (Kreditlinie: 0 cent)
     - Limitiertes Konto2 (Kreditlinie: - 20000 cent)
     - Gebührenpflichtiges Konto1 (Gebühr: 100 cent / Transaktion)*/
    
    int normales_konto1 = 0, limitiertes_konto1 = 0, gebuehren_konto1 = 0, limitiertes_konto2 = 5000;
    
    /* Transaktionen: - einzahlen
     - abheben
     - überweisen (abheben konto 1 -> einzahlen konto 2)*/
    
    normales_konto1 += 100;
    
    printf("Geben Sie eine Transaktion ein; einzahlen = 1, abheben = 2, Ueberweisen = 3\n");
    int gebuehr1 = 100;
    int eingabe = 0;
    int konto = 0;
    int betrag = 0;
    int empf = 0;
    
    
    
    scanf("%d", &eingabe);
    switch (eingabe) {
        case 1:
            printf("Waehlen Sie ein Konto aus: normales Konto = 1, limitiertes Konto1 = 2, limitiertes Konto2 = 3, gebuehrenpflichtiges Konto = 4\n");
            scanf("%d", &konto);
            switch (konto) {
                case 1:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    (betrag >= 0 ? normales_konto1 += betrag : printf("Dafuer gibt es die Abhebung!\n"));
                    
                    break;
                case 2:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    (betrag >= 0 ? limitiertes_konto1+= betrag : printf("Dafuer gibt es die Abhebung!\n"));
                    break;
                case 3:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    (betrag >= 0 ? limitiertes_konto2+= betrag : printf("Dafuer gibt es die Abhebung!\n"));
                    break;
                case 4:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    (betrag >= 0 ? gebuehren_konto1+= (betrag - gebuehr1) : printf("Dafuer gibt es die Abhebung!\n"));
                    break;
                default:
                    printf("DAU\n");
                    break;
            }
			break;
        case 2:
            printf("Waehlen Sie ein Konto aus: normales Konto = 1, limitiertes Konto1 = 2, limitiertes Konto2 = 3, gebuehrenpflichtiges Konto = 4\n");
            scanf("%d", &konto);
            switch (konto) {
                case 1:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    normales_konto1 -= betrag;
                    break;
                case 2:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    if ((limitiertes_konto1 - betrag) >= 0){
                    limitiertes_konto1-= betrag;
                    }
                    else{
                        printf("\tunzulaessige Kontoueberziehung!\n");
                    }
                    break;
                case 3:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    if ((limitiertes_konto2 - betrag) >= -20000){
                    limitiertes_konto2-= betrag;
                    }
                    else{
                        printf("\tunzulaessige Kontoueberziehung!\n");
                    }
                    break;
                case 4:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    gebuehren_konto1-= (betrag + gebuehr1);
                    break;
                default:
                    printf("DAU\n");
                    break;
            }
            break;
        case 3:
            printf("Geben Sie das Sender- und Empfaengerkonto ein: normales Konto = 1, limitiertes Konto1 = 2, limitiertes Konto2 = 3, gebuehrenpflichtiges Konto = 4\n");
            scanf("%d %d", &konto, &empf);
            switch (konto) {
                case 1:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    normales_konto1 -= betrag;
                    break;
                case 2:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    if ((limitiertes_konto1 - betrag) >= 0){
                        limitiertes_konto1 -= betrag;
                    }
                    else{
                        printf("\tunzulaessige Kontoueberziehung!\n");
                    }
                    break;
                case 3:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    if ((limitiertes_konto2 - betrag) >= 0){
                        limitiertes_konto2 -= betrag;
                    }
                    else{
                        printf("\tunzulaessige Kontoueberziehung!\n");
                    }
                    break;
                case 4:
                    printf("Geben Sie den Betrag ein\n");
                    scanf("%d", &betrag);
                    gebuehren_konto1 -= (betrag + gebuehr1);
                    break;
                default:
                    printf("DAU\n");
                    break;
            }
            switch (empf) {
                case 1:
                    normales_konto1 += betrag;
                    break;
                case 2:
                    limitiertes_konto1 += betrag;
                    break;
                case 3:
                    limitiertes_konto2 += betrag;
                    break;
                case 4:
                    gebuehren_konto1 += (betrag - gebuehr1);
                default:
                    printf("DAU\n");
                    break;
            }
            break;
        default:
            printf("DAU!\n");
            break;
    }
  /*  //Zinsberechnung
    double zinssatz = 0.03;
    int laufzeit = 0;
    
    printf("Geben Sie die Laufzeit der Zinsberechnung ein\n");
    scanf("%d", &laufzeit);
    
    normales_konto1 *= exp(laufzeit * zinssatz);*/
    
    printf("Kontostand normales Konto: %d,%.2d\t\tlimitiertes Konto1: %d,%.2d\tlimitiertes Konto2: %d,%.2d\tgebuehrenpflichtiges Konto: %d,%.2d\n", normales_konto1 /100, normales_konto1 % 100, limitiertes_konto1 / 100, limitiertes_konto1 % 100,limitiertes_konto2 / 100, limitiertes_konto2 % 100, gebuehren_konto1 / 100, (gebuehren_konto1 * ((gebuehren_konto1 > 0) - (gebuehren_konto1 < 0))) % 100);
    
    return 0;
}

#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(){
    struct student{
    char nachname [30];
    char vorname [30];
    unsigned int matrikelnummer;
    };

    struct student semester1 [30];

    //Student: Hans Wurst, 23456
    //Studentin: Hanna Wurster, 64758

    strcpy(semester1[0].nachname, "Wurst");
    strcpy(semester1[0].vorname, "Hans");
    semester1[0].matrikelnummer = 23456;

    // Hans [0] --> [25]
    semester1[25] = semester1[0];
    printf("%d %s %s\n", semester1[25].matrikelnummer, semester1[25].vorname, semester1[25].nachname);


    //Vorname Zeichen für Zeichen ausgeben

    int i = 0;

    while(semester1[25].vorname[i] != '\0'){
        printf("%c\n", semester1[25].vorname[i]);
        i++;
        sleep(1);
    }

    return 0;
}























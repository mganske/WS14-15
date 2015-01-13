#!/bin/bash

# Spaltenbeschriftung
echo -e "\n Code    40       41       42       43       44       45       46       47"

for FG in "39" "30" "31" "32" "33" "37" 
do
    # Zeilenbeschriftung
    echo -en " $FG "

    for BG in "40m" "41m" "42m" "43m" "44m" "45m" "46m" "47m"
    do
        echo -en "\e[$FG;${BG}e[$FG;$BG\e[0m "
    done
    echo
done
echo
exit


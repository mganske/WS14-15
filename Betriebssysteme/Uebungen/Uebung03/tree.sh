#!/bin/bash
# Aufrufsyntax:
# tree [-d] [-r "pattern"] [directory]

# Ausgabe:
# dateiname*    einfache Datei und ausführbar
# dateiname/    Directory
# dateiname/-   leeres Directory
# dateiname/-r  nicht lesbares Directory (Inhalt nicht auflistbar)
# dateiname/-x  nicht ausfuehrbares Directory (kein cd moeglich)

# usage - gibt die Hilfe aus mit dem korrekten Aufrufsyntax
usage() {
  echo "$0 [-d] [-r \"pattern\"] [directory]" >&2
  echo "     -d  nur Verzeichnisse auflisten" >&2
  echo "     -r  nur Verzeichnisse und Dateien, die durch "pattern" abgedeckt sind" >&2
}

# baum - fuer die graphische Baum-Ausgabe verantwortlich
baum() {
  if [ ! -x "$1" ]; then echo -e "-x\c";  exit; fi
  # In aktuellen Ordner wechseln
  cd $1

  set -f 
  set "" $(ls -A 2>/dev/null) || { echo -e "-r\c"; exit; }
  shift
  if [ $# -eq 0 ]; then  echo -e "-\c"; exit; fi
  args="$*"
  temp_args=""

  # Abarbeiten der angegebenen Optionen, wenn $option nicht leer ist
  if [ ! -z "$option" ]
  then
    case $option in
      *d*) for i in $args
           do
             [ -d "$i" ] && temp_args="$temp_args $i"
           done
           args="$temp_args"
           temp_args="";;
    esac
    case $option in
      *r*) for i in $args
           do
             if [ -d "$i" ] || case $i in
                                 $ra) true;;
                                 *) false;;
                               esac
             then
               temp_args="$temp_args $i"
             fi
           done
           args="$temp_args"
           temp_args="";;
    esac
  fi
    
  if [ ! -z "$args" ]
  then
    set "" $(ls -AdF $args 2>/dev/null)
  else
    set ""
  fi
  shift

  # Graphische Ausgabe des Baumteils
  while [ ! -z "$2" ]
  do
    echo -e "\n${tiefe}|-- $1\c"
    # Achtung: ( ) sind sehr wichtig. Nur in der Subshell darf die Variable tiefe
    # verlaengert werden.
    if [ -d "$1" ]; then (tiefe="${tiefe}|   "; baum $1);  fi
    shift
  done
    
  # Letzter Teil eines Teilbaums
  if [ ! -z "$1" ]
  then
    echo -e "\n${tiefe}\`-- $1\c"
    if [ -d "$1" ]; then (tiefe="${tiefe}    "; baum $1) fi
  fi
}

# Abbruchssignale wie z.B. <strg>+<c> abfangen und Skript sauber beednen
trap 'echo; exit' 0 1 2 3 15

# Abarbeiten der angegebenen Optionen
while getopts hH\?r:d opt
do
  case $opt in
    d) option="${option}d";;
    r) option="${option}r";ra="$OPTARG";;
    \?) usage; exit 1;;
    [hH]) usage; exit 1;;
  esac
done

shift $(expr $OPTIND - 1)

dir=${1:-$(pwd)}

if [ ! -d "$dir" ]
then
  echo "$0: $dir ist kein Directory" >&2
  usage
  exit 1
fi

# Wurzel anzeigen
echo -e "$dir\c"
# Baum durchlaufen (mit allen Teilbaeumen, die rekursiv erzeugt werden)
baum $dir

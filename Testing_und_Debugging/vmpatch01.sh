#!/bin/bash
# vim: ft=sh:tw=80:ts=4:sta:sw=4:si:ci
#************************************************************** SHELL SCRIPT ***
#   NAME
#       vmpatch01.sh -- Patch for the submit command
#
#   DESCRIPTION
#       github has updated its ssl stack. therefore the installed submitcommand
#       don't wortk anymore
#
#   FIRST RELEASE
#       2014-10-15  Sebastian Stigler		sebastian.stigler@htw-aalen.de
#
#   COPYRIGHT (C) 2014
#*******************************************************************************
#** function usage

usage() {
    echo "USAGE: sudo bash `basename $0` [-h|--help]"
    exit 1
}

#*******************************************************************************
#** usage verification

TEMP=$(getopt -o hb: --long b-long:,help \
       -n 'vmpatch01.sh' -- "$@")

if [ ${?} != 0 ] ; then usage; fi

eval set -- "${TEMP}"

while true ; do
    case "${1}" in
        -h|--help)   usage                            ; shift   ;;
        --)          shift                            ; break   ;;
         *)          echo "Internal error!"           ; exit 1  ;;
    esac
done

#*******************************************************************************
#** check root
check_root() {
if [ "$EUID" -ne 0 ]
  then echo -e "\e[31mYou must run this command as root (with 'sudo')!\e[0m"
  usage
  exit
fi
}

#*******************************************************************************
#** check run in vm
check_run_in_vm() {
if [ ! -f '/opt/gists/setup.py' ]
  then echo -e "\e[31mYou must call this command within the virtual machine!\e[0m"
  exit
fi
}

#*******************************************************************************
#** check internet
check_internet() {
if ! ping -c1 www.github.com &> /dev/null
  then echo -e "\e[31mYou need a working internet connection within the virtual machine!\e[0m"
  exit
fi
}

#*******************************************************************************
#** do vmpatch01
do_vmpatch01() {
cd /opt/gists

git checkout python3

git pull origin python3

python3 setup.py install
}

#*******************************************************************************
#** check success
check_success() {
cd /opt/gists
if git branch | egrep "^\* python3" -q
  then echo -e "\e[32mOK\e[0m  : git branch"
else echo -e "\e[31mFAIL\e[0m: git branch"
  exit
fi

if git log -n1 --pretty=format:%h | egrep "^adf5a30" -q
  then echo -e "\e[32mOK\e[0m  : correct commit"
else echo -e "\e[31mFAIL\e[0m: correct commit"
  exit
fi

if pip3 freeze | egrep "^requests==2\.4\.3" -q
  then echo -e "\e[32mOK\e[0m  : requests module"
else echo -e "\e[31mFAIL\e[0m: requests module"
  exit
fi

echo -e "\n\e[32mPatch was successfully applied!\e[0m"
echo -e "The \e[34msubmit\e[0m command should work now"

}
#*******************************************************************************
#** start main program
echo -e "\e[33mcheck_root\e[0m"
check_root
echo -e "\e[33mcheck_run_in_vm\e[0m"
check_run_in_vm
echo -e "\e[33mcheck_internet\e[0m"
check_internet
echo -e "\e[33mdo_vmpatch01\e[0m"
do_vmpatch01
echo -e "\e[33mcheck_success\e[0m"
check_success



#*******************************************************************************
#** clean up
#
#    rm -f /tmp/vmpatch01.sh.$$.log
#
#*********************************************************************** END ***

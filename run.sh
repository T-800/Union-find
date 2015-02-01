#!/bin/bash

ITERATIONS=10
TIMES_FILE="times"

echo -e "\e[0;36m image1bw.png 1 1\e[0m"
java Main image1bw.png 1 1
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main image1bw.png 1 1 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m image2bw.png 1 1\e[0m"
java Main image2bw.png 1 1
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main image2bw.png 1 1 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m image3bw.png 1 1\e[0m"
java Main image3bw.png 1 1
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main image3bw.png 1 1 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m image4bw.png 1 1\e[0m"
java Main image4bw.png 1 1
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main image4bw.png 1 1 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m charpetit.png 1 25 1 1\e[0m"
java Main charpetit.png 1 25
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main charpetit.png 1 25 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m cells-inv.png 1 1\e[0m"
java Main cells-inv.png 1 1
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main cells-inv.png 1 1 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

echo -e "\n\e[0;36m RBC_bw.png 2 15\e[0m"
java Main RBC_bw.png 2 15
for iter in `seq 1 $ITERATIONS`
do

    /usr/bin/time -o $TIMES_FILE -a -p java Main RBC_bw.png 2 15 >> /dev/null
done

echo -e "\e[0;31m"$(cat $TIMES_FILE | grep real | awk '{print $2}' | awk '{t+=$1} END {print t/NR}')
rm $TIMES_FILE

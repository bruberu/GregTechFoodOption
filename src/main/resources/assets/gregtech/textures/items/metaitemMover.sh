#!/bin/bash
for FILE in `ls ./metaitems -p | grep -v '/$'`
do
	FILEMANIP=`basename $FILE .png`
	FILEMANIP=${FILEMANIP//./\/}
	FILEMANIP="./metaitems/""$FILEMANIP"".png"

	FILE="./metaitems/""$FILE"
	mkdir -p ${FILEMANIP%/*}
	echo $FILE " " $FILEMANIP
	mv $FILE $FILEMANIP -i
done

#!/bin/bash
for FILE in `ls ./metaitems/`
do
	FILEMANIP=`basename $FILE .json`
	FILEMANIP=${FILEMANIP//./\/}
	FILEMANIP="./metaitems/""$FILEMANIP"".json"

	FILE="./metaitems/""$FILE"
	mkdir -p ${FILEMANIP%/*}
	echo $FILE " " $FILEMANIP
	mv $FILE $FILEMANIP -i
done

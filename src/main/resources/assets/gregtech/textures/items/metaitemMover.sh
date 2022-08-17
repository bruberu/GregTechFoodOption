#!/bin/bash
for FILE in `find ./metaitems | grep ".png$"`
do
	FILEMANIP=`basename $FILE .png`
	FILEMANIP=${FILEMANIP//./\/}
	FILEROOT=`dirname $FILE`
	FILEMANIP="$FILEROOT""/""$FILEMANIP"".png"

	FILE="$FILE"
	mkdir -p ${FILEMANIP%/*}
	echo $FILE " " $FILEMANIP
	mv $FILE $FILEMANIP -i
done

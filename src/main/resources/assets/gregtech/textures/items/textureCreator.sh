#!/bin/bash
while true; do
	echo "What file should be created? (Do not include png extension)"
	read NAME
	NAME="${NAME//./\/}"
	FILEPATH="./metaitems/"$NAME".png"
	FOLDERPATH=${FILEPATH%\/*}
	mkdir -p $FOLDERPATH
	cp "blankitem.png" $FILEPATH
	
	echo "Created " $FILEPATH
done

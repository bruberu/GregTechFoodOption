#!/bin/bash
while true; do
	echo "What file should be created? (Do not include json extension)"
	read NAME
	NAME="${NAME//./\/}"
	FILEPATH="./metaitems/"$NAME".json"
	FOLDERPATH=${FILEPATH%\/*}
	mkdir -p $FOLDERPATH
	cp "blankmodel.txt" $FILEPATH
	
	# Escapes all characters
	SAFENAME=$(printf '%s\n' "$NAME" | sed -e 's/[\/&]/\\&/g')
	SEDFUNC="s/REPLACE/"$SAFENAME"/"
	sed -i $SEDFUNC $FILEPATH
	echo "Created " $FILEPATH
done

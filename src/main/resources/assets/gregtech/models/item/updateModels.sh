#!/bin/bash
for FILE in `find ./metaitems | grep ".json$"`
do 
    sed -i "s/\./\//" $FILE
done

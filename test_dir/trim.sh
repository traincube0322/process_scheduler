#!/bin/bash

for i in {1..10}; do
  tr -s ' ' < "$i.txt" > temp.txt && mv temp.txt "$i.txt"
done

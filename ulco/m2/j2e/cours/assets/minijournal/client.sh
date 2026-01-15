#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <number_of_requests>"
  exit 1
fi

for ((i=1; i<=$1; i++))
do
  curl -s http://localhost:8081
done

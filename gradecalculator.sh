#!/bin/bash

javac -d bin "src/calculator/*.java"

java -cp bin "calculator.gradeCalculator"

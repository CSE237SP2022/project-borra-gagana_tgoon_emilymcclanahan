# Grade Calculator
## Project by Gagana Borra, Emily McClanahan, and Tom Goon

Java command line program that is fed in a text file with class assignment weights and a text file with grades and outputs the user's final grade.

## Iteration 1
- User Stories: 
 - Made sample .txt file for student's numerical grade
 - Made sample .txt file for overall class grade distribution weighting
 - Created methods necessary to read in both grades and weights .txt files in Java/Eclipse
 - Created methods to calculate and output percentage grade
 - Made unit tests for all current methods
 - Made bash script to compile and run
 
 Everything for this iteration should be functional. To run the bash script: ./gradecalculator.sh

## Iteration 2 To-do
- Create a method to output a letter grade based on a standardized grading scale and student's numerical grade
- Create a method to check and see if an extra credit assignment has been completed. If so, the student's grade will receive a +1%. If the extra credit was not completed, the student's grade does not increase. 

## Iteration 2
-User Stories:
 - Spent significant time refactoring code into object oriented design
 - Created a Bearbot, a prompter, to prompt the user for a specific day's grades inside a course and calculate final/letter grade
 - Created a Bearbot to be able to list currently available courses to the user upon starting program
 - Created a course object to output the letter grade to the user

~We currently only have CSE237 as the only available course listed. There is only one date file available to test as well. In Iteration 3, we will allow users to create more courses and date files.~

 Everything for this iteration should be functional. To run the bash script: ./gradecalculator.sh

## Iteration 3 To-do
- Create a method where BearBot should be able to allow the user to create a new course with course weights
- Create a method where BearBot should be able to allow the user to enter grades for a course and potentially save to a file

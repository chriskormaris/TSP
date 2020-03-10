:: This file is for Windows only.

if not exist bin mkdir bin
javac src\tsp\*.java src\tsp_plot\*.java -d bin -cp lib\graph.jar;lib\gral-core-0.11.jar;
pause

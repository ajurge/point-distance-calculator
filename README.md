[![Build Status](https://travis-ci.org/ajurge/point-distance-calculator.svg?branch=master)](https://travis-ci.org/ajurge/point-distance-calculator)
[![codecov](https://codecov.io/gh/ajurge/point-distance-calculator/branch/master/graph/badge.svg)](https://codecov.io/gh/ajurge/point-distance-calculator)


# Point Distance Calculator 
Calculates a specified number of Euclidean closest and furthest distances from a specified point to all the points in the given binary source file.
The source file with points must be containing an even number of points (x, y) encoded as two successive 16 bit signed integer values.
In the example file there are 10 million points.

## Build and Run
This project requires java 1.8.

1. Run: `mvn clean install` to build the project. This will build a jar file called `${build.finalName}.jar`. 
In addition, a zip file containing a bundle of the project files will be built in the project `target` directory.
2. Before running the built jar file copy the binary file named `points` to the jar directory. 
To run the jar file from command prompt execute: `java -Xmx512m -jar ${build.finalName}.jar`. 
Alternatively, run `MainApp.main` passing VM parameter `-Xmx512m` from any IDE.

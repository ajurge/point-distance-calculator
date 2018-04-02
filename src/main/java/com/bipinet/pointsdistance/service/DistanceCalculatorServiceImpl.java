package com.bipinet.pointsdistance.service;

import com.bipinet.pointsdistance.domain.PointXY;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;


public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

    private int numberOfPoints;


    DistanceCalculatorServiceImpl(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public short[] findClosestPoints(PointXY fromPoint, int numberOfClosestPoints, short[] inputPoints) {
        final double[] distances = new double[this.numberOfPoints / 2];
        //Copy of distances for searching the indexes of points of interest.
        final double[] distancesCopy = new double[distances.length];
        final int startPosition = 0;
        final double[] closestDistances = new double[numberOfClosestPoints];

        //Two entries x and y represent represent 1 point, so there will be twice as less distances
        // as there are points.
        for (int i = 0; i < inputPoints.length; i += 2) {
            distances[i / 2] = calculateEuclidianDistance(
                    fromPoint.getX(),
                    fromPoint.getY(),
                    inputPoints[i],
                    inputPoints[i + 1]);
        }

        //Make a copy of the array before sorting it.
        System.arraycopy(distances, 0,
                distancesCopy, 0, distances.length);
        //Sort distances in ascending order
        Arrays.sort(distances);
        //Return only those defined by the start and length of the array.
        System.arraycopy(distances, startPosition,
                closestDistances, 0, numberOfClosestPoints);

        //Find indexes of the closest distances by looping through the distancesCopy array
        final short[] closestPoints = getPointsByDistance(inputPoints, distancesCopy, closestDistances);

//        System.out.println(String.format("The %s closest points to %s are: %s",
//                numberOfClosestPoints, fromPoint.toString(), Arrays.toString(closestPoints)));
        printPoints(
                String.format("\n%s closest points from point %s are:", numberOfClosestPoints, fromPoint.toString()),
                closestPoints);

        return closestPoints;
    }

    @Override
    public short[] findFurthestPoints(PointXY fromPoint, int numberOfFurthestPoints, short[] inputPoints) {
        final double[] distances = new double[this.numberOfPoints / 2];
        //Copy of distances for searching the indexes of points of interest.
        final double[] distancesCopy = new double[distances.length];
        final int startPosition = this.numberOfPoints / 2 - numberOfFurthestPoints;
        final double[] furthestDistances = new double[numberOfFurthestPoints];

        for (int i = 0; i < inputPoints.length; i += 2) {
            distances[i / 2] = calculateEuclidianDistance(
                    fromPoint.getX(),
                    fromPoint.getY(),
                    inputPoints[i],
                    inputPoints[i + 1]);
        }
        //Make a copy of the array before sorting it.
        System.arraycopy(distances, 0,
                distancesCopy, 0, distances.length);
        //Sort distances in ascending order
        Arrays.sort(distances);
        //Return only those defined by the start and length of the array.
        System.arraycopy(distances, startPosition,
                furthestDistances, 0, numberOfFurthestPoints);

        //Find indexes of the closest distances by looping through the distancesCopy array
        final short[] furthestPoints = getPointsByDistance(inputPoints, distancesCopy, furthestDistances);

//        System.out.println(String.format("The %s furthest points to %s are: %s",
//                numberOfFurthestPoints, fromPoint.toString(), Arrays.toString(furthestPoints)));
        printPoints(
                String.format("\n%s furthest points from point %s are:", numberOfFurthestPoints, fromPoint.toString()),
                furthestPoints);

        return furthestPoints;
    }


    /**
     * Calculates Euclidean distance between two points represented as x1, y1 and x2, y2.
     *
     * @param x1 x point 1
     * @param y1 y point 1
     * @param x2 x point 2
     * @param y2 y point 2
     * @return distance
     */
    private double calculateEuclidianDistance(short x1, short y1, short x2, short y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Get points of interest for the corresponding distances of interest.
     *
     * @param inputPoints         all input points.
     * @param distances           all calculated distances
     * @param distancesOfInterest array with distances of interest to fetch points of interest.
     * @return array with points
     */
    private short[] getPointsByDistance(short[] inputPoints, double[] distances, double[] distancesOfInterest) {
        final short[] pointsOfInterest = new short[distancesOfInterest.length * 2];
        //Find indexes of the distances of interest by looping through the all distances array
        for (int i = 0; i < distancesOfInterest.length; i++) {
            final int indexOffset = 2;
            final int indexOfClosestDist = ArrayUtils.indexOf(distances, distancesOfInterest[i]);
            //Fetch two successive points from the inputPoints using the index of the distance in distances.
            //Multiply the index by 2 because each point is represented by x and y,
            //thus there are twice as many elements in the inputPoints array.
            pointsOfInterest[i * indexOffset] = inputPoints[indexOfClosestDist * indexOffset];
            pointsOfInterest[(i * indexOffset) + 1] = inputPoints[(indexOfClosestDist * indexOffset) + 1];
        }
        return pointsOfInterest;
    }

    private static void printPoints(String message, short[] points){
        System.out.println(message);
        for (int i=0; i < points.length; i+=2){
            System.out.println(i/2+1 + ": (" + points[i] + ", " + points[i+1] + ")");
        }
    }
}

package com.bipinet.pointsdistance.service;

import com.bipinet.pointsdistance.domain.PointXY;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public enum DistanceType {

    CLOSEST(new PointXY((short) -200, (short) 300)) {
        @Override
        public short[] findPoints(int numberOfPointsForDistanceCalculation, int numberOfPoints, short[] inputPoints) {
            return new DistanceCalculatorServiceImpl(numberOfPoints)
                    .findClosestPoints(getFromPoint(), numberOfPointsForDistanceCalculation, inputPoints);
        }
    },
    FURTHEST(new PointXY((short) 1000, (short) 25)) {
        @Override
        public short[] findPoints(int numberOfPointsForDistanceCalculation, int numberOfPoints, short[] inputPoints) {
            return new DistanceCalculatorServiceImpl(numberOfPoints)
                    .findFurthestPoints(getFromPoint(), numberOfPointsForDistanceCalculation, inputPoints);
        }
    };

    private PointXY fromPoint;


    DistanceType(PointXY fromPoint) {
        this.fromPoint = fromPoint;
    }

    public PointXY getFromPoint() {
        return fromPoint;
    }

    public static List<String> ALL() {
        List<String> result = new ArrayList<>();
        for (DistanceType distanceType : values()) {
            result.add(distanceType.name());
        }
        return result;
    }

    public String printInfo () {
        return this.name() + " from point " + this.fromPoint.toString();
    }

    public static String printAllInfo() {
        final StringJoiner result = new StringJoiner(", ");
        for (DistanceType distanceType : values()) {
            result.add(distanceType.printInfo());
        }
        return result.toString();
    }


    /**
     * Calculates, sorts and returns an array of points of interest that are specific per implementation.
     *
     * @param numberOfPointsForDistanceCalculation number of points to which distances will be calculated from the {@link #fromPoint}.
     * @param numberOfPoints                       total number of points in the input array.
     * @param inputPoints                          input points used to calculate distances and find points of interest.
     * @return array with points
     */
    public abstract short[] findPoints(int numberOfPointsForDistanceCalculation, int numberOfPoints, short[] inputPoints);
}

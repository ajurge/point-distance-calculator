package com.bipinet.pointsdistance.service;

import com.bipinet.pointsdistance.domain.DistanceCalculationParameters;

import java.util.ArrayList;
import java.util.Collection;


public enum DistanceType {
    CLOSEST() {
        @Override
        public short[] findPoints(DistanceCalculationParameters distanceCalculationParameters, short[] inputPoints) {
            return new DistanceCalculatorServiceImpl(distanceCalculationParameters.getNumberOfPoints())
                    .findClosestPoints(
                            distanceCalculationParameters.getPointForClosest(),
                            distanceCalculationParameters.getNumberOfClosestPoints(),
                            inputPoints);
        }
    },
    FURTHEST() {
        @Override
        public short[] findPoints(DistanceCalculationParameters distanceCalculationParameters, short[] inputPoints) {
            return new DistanceCalculatorServiceImpl(distanceCalculationParameters.getNumberOfPoints())
                    .findFurthestPoints(
                            distanceCalculationParameters.getPointForFurthest(),
                            distanceCalculationParameters.getNumberOfFurthestPoints(),
                            inputPoints);
        }
    };

    public static Collection<String> ALL() {
        Collection<String> result = new ArrayList();
        for (DistanceType distanceType : values()) {
            result.add(distanceType.name());
        }
        return result;
    }


    /**
     * Calculates, sorts and returns an array of points of interest that are specific per implementation.
     *
     * @param distanceCalculationParameters input parameters for the distance calculation.
     * @param inputPoints                   input points used to calculate distances and find points of interest.
     * @return
     */
    public abstract short[] findPoints(DistanceCalculationParameters distanceCalculationParameters, short[] inputPoints);
}

package com.bipinet.pointsdistance.service;

import com.bipinet.pointsdistance.domain.PointXY;

public interface DistanceCalculatorService {
    short[] findClosestPoints(PointXY fromPoint, int numberOfClosestPoints, short[] inputPoints);

    short[] findFurthestPoints(PointXY fromPoint, int numberOfFurthestPoints, short[] inputPoints);
}

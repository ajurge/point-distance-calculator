package com.bipinet.pointsdistance.domain;


public class DistanceCalculationParameters {
    private PointXY pointForClosest;
    private PointXY pointForFurthest;
    private int numberOfClosestPoints;
    private int numberOfFurthestPoints;
    private int numberOfPoints;

    public DistanceCalculationParameters(PointXY pointForClosest, PointXY pointForFurthest, int numberOfClosestPoints, int numberOfFurthestPoints, int numberOfPoints) {
        this.pointForClosest = pointForClosest;
        this.pointForFurthest = pointForFurthest;
        this.numberOfClosestPoints = numberOfClosestPoints;
        this.numberOfFurthestPoints = numberOfFurthestPoints;
        this.numberOfPoints = numberOfPoints;
    }

    public PointXY getPointForClosest() {
        return pointForClosest;
    }

    public void setPointForClosest(PointXY pointForClosest) {
        this.pointForClosest = pointForClosest;
    }

    public PointXY getPointForFurthest() {
        return pointForFurthest;
    }

    public void setPointForFurthest(PointXY pointForFurthest) {
        this.pointForFurthest = pointForFurthest;
    }

    public int getNumberOfClosestPoints() {
        return numberOfClosestPoints;
    }

    public void setNumberOfClosestPoints(int numberOfClosestPoints) {
        this.numberOfClosestPoints = numberOfClosestPoints;
    }

    public int getNumberOfFurthestPoints() {
        return numberOfFurthestPoints;
    }

    public void setNumberOfFurthestPoints(int numberOfFurthestPoints) {
        this.numberOfFurthestPoints = numberOfFurthestPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public String toString() {
        return "DistanceCalculationParameters{" +
                "pointForClosest=" + pointForClosest +
                ", pointForFurthest=" + pointForFurthest +
                ", numberOfClosestPoints=" + numberOfClosestPoints +
                ", numberOfFurthestPoints=" + numberOfFurthestPoints +
                ", numberOfPoints=" + numberOfPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistanceCalculationParameters that = (DistanceCalculationParameters) o;

        if (numberOfClosestPoints != that.numberOfClosestPoints) return false;
        if (numberOfFurthestPoints != that.numberOfFurthestPoints) return false;
        if (numberOfPoints != that.numberOfPoints) return false;
        if (pointForClosest != null ? !pointForClosest.equals(that.pointForClosest) : that.pointForClosest != null)
            return false;
        return pointForFurthest != null ? pointForFurthest.equals(that.pointForFurthest) : that.pointForFurthest == null;
    }

    @Override
    public int hashCode() {
        int result = pointForClosest != null ? pointForClosest.hashCode() : 0;
        result = 31 * result + (pointForFurthest != null ? pointForFurthest.hashCode() : 0);
        result = 31 * result + numberOfClosestPoints;
        result = 31 * result + numberOfFurthestPoints;
        result = 31 * result + numberOfPoints;
        return result;
    }
}

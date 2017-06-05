package com.bipinet.pointsdistance.domain;

/**
 * Class representing a point with x and y coordinates.
 */
public class PointXY {
    private short x;
    private short y;

    public PointXY(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public short getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointXY pointXY = (PointXY) o;

        if (x != pointXY.x) return false;
        return y == pointXY.y;
    }

    @Override
    public int hashCode() {
        int result = (int) x;
        result = 31 * result + (int) y;
        return result;
    }

}

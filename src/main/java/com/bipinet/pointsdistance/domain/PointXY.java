package com.bipinet.pointsdistance.domain;

import java.util.Objects;

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

    public short getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointXY pointXY = (PointXY) o;
        return x == pointXY.x &&
                y == pointXY.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

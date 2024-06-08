// Class that imitates array with only two numeric values: x and y
package utils;

import java.util.Objects;

public class Vector2<T extends Number> implements Comparable<Vector2<T>>, Cloneable{
    private T x; private T y;

    public Vector2(T x, T y) {
        this.x = x; this.y = y;
    }

    public T getX() { return x; }
    public T getY() { return y; }

    public void setX(T x) { this.x = x; }
    public void setY(T y) { this.y = y; }

    public void moveX(T increment) {
        x = (T) addNumbers(x, increment);
    }

    public void moveY(T increment) {
        y = (T) addNumbers(y, increment);
    }

    private Number addNumbers(Number a, Number b) {
        if (a instanceof Integer) {
            return a.intValue() + b.intValue();
        } else if (a instanceof Double) {
            return a.doubleValue() + b.doubleValue();
        } else if (a instanceof Long) {
            return a.longValue() + b.longValue();
        }
        throw new IllegalArgumentException("Unsupported number type");
    }

    public double distanceTo(Vector2<T> other) {
        double dx = other.getX().doubleValue() - this.getX().doubleValue();
        double dy = other.getY().doubleValue() - this.getY().doubleValue();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2<?> vector2 = (Vector2<?>) o;
        return Objects.equals(x, vector2.x) && Objects.equals(y, vector2.y);
    }

    @Override
    public int hashCode() { return Objects.hash(x, y); }

    @Override
    public String toString() { return String.format("Vector2{ x = %s, y = %s }", x, y); }

    @Override
    public int compareTo(Vector2<T> other) {
        // Compare based on the Euclidean distance squared (x^2 + y^2)
        double thisDistanceSquared = Math.pow(x.doubleValue(), 2) + Math.pow(y.doubleValue(), 2);
        double otherDistanceSquared = Math.pow(other.getX().doubleValue(), 2) + Math.pow(other.getY().doubleValue(), 2);

        return Double.compare(thisDistanceSquared, otherDistanceSquared);
    }

    @Override
    public Vector2<T> clone() {
        return new Vector2<>(this.getX(), this.getY());
    }
}
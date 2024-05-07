// Class that imitates array with only two numeric values: x and y
package utils;

import java.util.Objects;

public class Vector2<T extends Number>{
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

    public Vector2<T> copy() { return new Vector2<>(this.x, this.y); }

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
    public String toString() { return String.format("Vector2{x=%s, y=%s}", x, y); }
}
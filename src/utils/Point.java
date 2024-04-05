package utils;

public class Point<T> {
    private T x;
    private T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public void setX(T x) {
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void moveX(T increment) {
        if (x instanceof Integer) {
            x = (T) (Integer) ((Integer) x + (Integer) increment);
        } else if (x instanceof Double) {
            x = (T) (Double) ((Double) x + (Double) increment);
        } else if (x instanceof Long) {
            x = (T) (Long) ((Long) x + (Long) increment);
        }
    }

    public void moveY(T increment) {
        if (y instanceof Integer) {
            y = (T) (Integer) ((Integer) y + (Integer) increment);
        } else if (y instanceof Double) {
            y = (T) (Double) ((Double) y + (Double) increment);
        } else if (y instanceof Long) {
            y = (T) (Long) ((Long) y + (Long) increment);
        }
    }

    public Point<T> getCopy() {
        return new Point<>(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
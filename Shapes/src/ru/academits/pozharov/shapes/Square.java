package ru.academits.pozharov.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return Math.pow(sideLength, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "Квадрат с длиной стороны " + sideLength;
    }

    @Override
    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) shape;
        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(sideLength);
    }
}
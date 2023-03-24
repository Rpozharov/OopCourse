package ru.academits.pozharov.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double side1Length = getSideLength(x1, y1, x2, y2);
        double side2Length = getSideLength(x2, y2, x3, y3);
        double side3Length = getSideLength(x3, y3, x1, y1);
        double halfPerimeter = (side1Length + side2Length + side3Length) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - side1Length)
                * (halfPerimeter - side2Length) * (halfPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x2, y2, x3, y3) + getSideLength(x3, y3, x1, y1);
    }

    @Override
    public String toString() {
        return String.format("Треугольник c координатами (%.1f, %.1f), (%.1f, %.1f), (%.1f, %.1f)", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object shape) {
        if (shape == this) {
            return true;
        }

        if (shape == null || shape.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) shape;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2
                && x3 == triangle.x3 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    public static double getSideLength(double startX, double startY, double endX, double endY) {
        return Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
    }
}
package ru.academits.pozharov.shapes_comparators;

import ru.academits.pozharov.shapes.Shape;

import java.util.Comparator;

public class figurePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
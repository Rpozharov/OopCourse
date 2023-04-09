package ru.academits.pozharov.shapes_main;

import ru.academits.pozharov.shapes.*;
import ru.academits.pozharov.shapes_comparators.ShapeAreaComparator;
import ru.academits.pozharov.shapes_comparators.ShapePerimeterComparator;

import java.util.Arrays;

public class ShapesMain {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(3),
                new Circle(10),
                new Rectangle(8, 5),
                new Square(6),
                new Triangle(1, 2, 4, 1, 5, 3),
                new Triangle(1, 1, 6, 1, 7, 4),
                new Rectangle(7, 11),
                new Square(8),
        };

        System.out.println("Фигура с максимальной площадью: " + getShapeWithMaxArea(shapes));
        System.out.println("Фигура со вторым по величине периметром: " + getShapeWithSecondLargestPerimeter(shapes));
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }
}
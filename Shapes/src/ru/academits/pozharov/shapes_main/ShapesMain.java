package ru.academits.pozharov.shapes_main;

import ru.academits.pozharov.shapes.*;
import ru.academits.pozharov.shapes_comparators.figureAreaComparator;
import ru.academits.pozharov.shapes_comparators.figurePerimeterComparator;

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

        System.out.println("Фигура с максимальной площадью: " + getFigureWithMaxArea(shapes));

        System.out.println("Фигура со вторым по величине периметром: " + getFigureWithSecondLargestPerimeter(shapes));
    }

    public static Shape getFigureWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new figureAreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getFigureWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new figurePerimeterComparator());
        return shapes[shapes.length - 2];
    }
}
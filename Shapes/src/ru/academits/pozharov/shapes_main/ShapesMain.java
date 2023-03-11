package ru.academits.pozharov.shapes_main;

import ru.academits.pozharov.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];
        shapes[0] = new Circle(3);
        shapes[1] = new Circle(10);
        shapes[2] = new Rectangle(8, 5);
        shapes[3] = new Square(6);
        shapes[4] = new Triangle(1, 2, 4, 1, 5, 3);
        shapes[5] = new Triangle(1, 1, 6, 1, 7, 4);
        shapes[6] = new Rectangle(7, 11);
        shapes[7] = new Square(8);

        System.out.println("Фигура с максимальной площадью - " + getMaxArea(shapes));
        System.out.println("Фигура со вторым по величине периметром - " + getSecondLargestPerimeter(shapes));
    }

    public static Shape getMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new SortByArea());
        return shapes[0];
    }

    public static Shape getSecondLargestPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new SortByPerimeter());
        return shapes[1];
    }
}
package ru.academits.pozharov.range_main;

import ru.academits.pozharov.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class TwoRangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное число 1-го диапазона: ");
        double from1 = scanner.nextDouble();

        System.out.print("Введите конечное число 1-го диапазона: ");
        double to1 = scanner.nextDouble();

        System.out.print("Введите начальное число 2-го диапазона: ");
        double from2 = scanner.nextDouble();

        System.out.print("Введите конечное число 2-го диапазона: ");
        double to2 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);
        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.printf("Диапазон пересечения = %s%n", intersectionRange);
        }

        System.out.printf("Диапазон объединения = %s%n", Arrays.toString(range1.getUnion(range2)));
        System.out.printf("Диапазон разности = %s%n", Arrays.toString(range1.getDifference(range2)));
    }
}
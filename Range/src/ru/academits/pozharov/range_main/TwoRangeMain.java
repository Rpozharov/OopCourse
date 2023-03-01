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
        double to2 = scanner.nextDouble();

        System.out.print("Введите конечное число 2-го диапазона: ");
        double endNumber2 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(to2, endNumber2);
        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.printf("Диапазон пересечения = (%.1f; %.1f)%n",
                    intersectionRange.getFrom(), intersectionRange.getTo());
        }

        System.out.printf("Диапазон объединения = %s%n", Arrays.toString(range1.getUnion(range2)));
        System.out.printf("Диапазон разности = %s%n", Arrays.toString(range1.getDifference(range2)));
    }
}
package ru.academits.pozharov.range_main;

import ru.academits.pozharov.range.Range;

import java.util.Scanner;

public class MainTwoRanges {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное число 1-го диапазона: ");
        double startNumber1 = scanner.nextDouble();

        System.out.print("Введите конечное число 1-го диапазона: ");
        double endNumber1 = scanner.nextDouble();

        System.out.print("Введите начальное число 2-го диапазона: ");
        double startNumber2 = scanner.nextDouble();

        System.out.print("Введите конечное число 2-го диапазона: ");
        double endNumber2 = scanner.nextDouble();

        Range range1 = new Range(startNumber1, endNumber1);
        Range range2 = new Range(startNumber2, endNumber2);

        if (range1.getIntersection(range2) == null) {
            System.out.println("Пересечение пусто");
        } else {
            System.out.printf("Диапазон пересечения = (%.1f; %.1f)%n",
                    range1.getIntersection(range2).getFrom(), range1.getIntersection(range2).getTo());
        }

        for (Range e : range1.getUnion(range2)) {
            System.out.printf("Диапазон объединения = (%.1f; %.1f)%n", e.getFrom(), e.getTo());
        }

        if (range1.getDifference(range2) == null) {
            System.out.println("Пересечение пусто, разность не вычисляется");
        } else {
            for (Range e : range1.getDifference(range2)) {
                System.out.printf("Диапазон разности = (%.1f; %.1f)%n", e.getFrom(), e.getTo());
            }
        }
    }
}
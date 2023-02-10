package ru.academits.pozharov.range_main;

import ru.academits.pozharov.range.Range;

import java.util.Scanner;

public class MainRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное число диапазона: ");
        double startNumber = scanner.nextDouble();

        System.out.print("Введите конечное число диапазона: ");
        double endNumber = scanner.nextDouble();

        Range range = new Range(startNumber, endNumber);

        range.setFrom(startNumber);
        range.setTo(endNumber);

        System.out.println("Начальное число диапазона = " + range.getFrom());
        System.out.println("Конечное число диапазона = " + range.getTo());
        System.out.println("Длина диапазона = " + range.getLength());

        System.out.print("Введите число для проверки, принадлежит ли число диапазону: ");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону");
        }
    }
}
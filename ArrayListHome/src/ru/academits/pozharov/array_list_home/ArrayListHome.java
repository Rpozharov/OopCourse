package ru.academits.pozharov.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        }

        System.out.println("Список строк " + list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(4);
        list2.add(7);
        list2.add(6);
        list2.add(8);
        list2.add(9);

        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i) % 2 == 0) {
                list2.remove(i);
                i--;
            }
        }

        System.out.println("Список нечетных чисел " + list2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 5, 5, 1, 3, 5, 3));
        ArrayList<Integer> list4 = new ArrayList<>();

        for (Integer e : list3) {
            if (list4.contains(e)) {
                continue;
            }

            list4.add(e);
        }

        System.out.println("Список без повторений " + list4);
    }
}
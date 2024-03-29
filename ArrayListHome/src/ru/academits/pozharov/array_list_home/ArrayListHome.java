package ru.academits.pozharov.array_list_home;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }

            System.out.println("Список строк: " + linesList);
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл");
        }

        ArrayList<Integer> oddNumbersList = new ArrayList<>(Arrays.asList(5, 4, 7, 6, 8, 9));

        for (int i = 0; i < oddNumbersList.size(); i++) {
            if (oddNumbersList.get(i) % 2 == 0) {
                oddNumbersList.remove(i);
                i--;
            }
        }

        System.out.println("Список нечетных чисел: " + oddNumbersList);

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 5, 5, 1, 3, 5, 3));
        ArrayList<Integer> uniqueNumbersList = new ArrayList<>(numbersList.size());

        for (Integer e : numbersList) {
            if (!uniqueNumbersList.contains(e)) {
                uniqueNumbersList.add(e);
            }
        }

        System.out.println("Список чисел без повторений: " + uniqueNumbersList);
    }
}
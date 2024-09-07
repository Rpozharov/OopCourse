package ru.academits.pozharov.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSV {
    public static void main(String[] args) {
        ArrayList<String[]> linesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("inputCSV.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String [] cells = line.split(",");
                        linesList.add(cells);
            }

            for (String[] cells: linesList){
                System.out.println(Arrays.toString(cells));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл");
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл");
        }
    }
}

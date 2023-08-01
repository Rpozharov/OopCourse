package ru.academits.pozharov.matrix_main;

import ru.academits.pozharov.matrix.Matrix;
import ru.academits.pozharov.matrix.Vector;

public class MatrixMain {
    public static void main(String[] args) {

        double[] array1 = {4, 7, 9, 4, 3, 5};
        double[] array2 = {2, 3, 9};
        double[] array3 = {2, 3, 9};
        double[] array4 = {4, 7, 9, 4, 3, 5};
        double[][] array5 = {{7, 2, 6, 5}, {2, 3}, {1, 8, 10}};

        Vector vector1 = new Vector(7);
        Vector vector2 = new Vector(10, array1);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);
        Vector vector5 = new Vector(array2);

        System.out.println("Вектор 1 = " + vector1);
        System.out.println("Вектор 2 = " + vector2);
        System.out.println("Вектор 3 = " + vector3);
        System.out.println("Вектор 4 = " + vector4);
        System.out.println("Вектор 5 = " + vector5);

        System.out.println();

        Vector[] vectors1 = {vector1, vector2, vector3, vector4, vector5};

        Matrix matrix1 = new Matrix(3, 2);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(array5);
        Matrix matrix4 = new Matrix(vectors1);
        Matrix matrix5 = new Matrix(matrix3);

        System.out.println("Матрица 1 = " + matrix1.getVectors()[0]);
        System.out.println("Матрица 2 = " + matrix2.getVectors()[1]);
        System.out.println("Матрица 3 = " + matrix3.getVectors()[0]);
        System.out.println("Матрица 4 = " + matrix4.getVectors()[2]);
        System.out.println("Матрица 5 = " + matrix5.getVectors()[0]);
    }
}
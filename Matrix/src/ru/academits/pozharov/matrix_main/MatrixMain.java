package ru.academits.pozharov.matrix_main;

import ru.academits.pozharov.matrix.Matrix;
import ru.academits.pozharov.vector.Vector;

import java.util.Arrays;

public class MatrixMain {
    public static void main(String[] args) {

        double[] array1 = {4, 7, 9, 4, 3, 5};
        double[] array2 = {2, 3, 9, 0};
        double[] array3 = {2, 3, 9};
        double[] array4 = {4, 7, 9, 4, 3, 5};
        double[][] doubleArray1 = {{7, 2, 6, 5}, {2, 3}, {1, 8, 10}};

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

        Vector[] vectors1 = {vector3, vector4, vector5};

        Matrix matrix1 = new Matrix(3, 4);
        Matrix matrix2 = new Matrix(doubleArray1);
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix4 = new Matrix(vectors1);
        Matrix matrix5 = new Matrix(matrix4);
        Matrix matrix6 = new Matrix(1, 5);

        System.out.println("Матрица 1 = " + matrix1);
        System.out.println("Матрица 2 = " + matrix2);
        System.out.println("Матрица 3 = " + matrix3);
        System.out.println("Матрица 4 = " + matrix4);
        System.out.println("Матрица 5 = " + matrix5);
        System.out.println("Матрица 6 = " + matrix6);

        System.out.println();

        System.out.println("Размеры матрицы 3 = " + Arrays.toString(matrix3.getSize()));

        matrix3.setRowByIndex(0, vector5);

        System.out.println("Матрица 3 = " + matrix3);
        System.out.println("Строка матрицы 3 по индексу 2 = " + matrix3.getRowByIndex(2));
        System.out.println("Столбец матрицы 3 по индексу 0 = " + matrix3.getColumnByIndex(0));

        matrix3.transpose();

        System.out.println("Матрица 3 = " + matrix3);
        System.out.println("Матрица 2 = " + matrix2);
        matrix2.multiplyByScalar(5);
        System.out.println("Матрица 2 = " + matrix2);

        double[][] doubleArray3 = {{5, 4, 8, 1, 3}, {4, 15, 5, 5, 0}, {6, 8, 10, 4, 12}, {7, 4, 1, 3, 8}, {5, 7, 1, 7, 2}};
        Matrix matrix7 = new Matrix(doubleArray3);
        System.out.println("Матрица 7 = " + matrix7);
        System.out.println("Определитель матрицы 7 = " + Math.round(matrix7.getDeterminant()));

        double[][] doubleArray4 = {{4}, {2}, {6}};
        double[] array5 = {3, 5, 2, 7, 9};
        Matrix matrix8 = new Matrix(doubleArray4);
        Vector vector6 = new Vector(array5);
        System.out.println("Матрица 8 = " + matrix8);
        System.out.println("Вектор 6 = " + vector6);
        matrix8.multiplyByVector(vector6);
        System.out.println(matrix8);

        Matrix matrix9 = new Matrix(matrix2);
        matrix9.setRowByIndex(1, vector5);
        System.out.println("Матрица 2 = " + matrix2);
        System.out.println("Матрица 9 = " + matrix9);
        matrix2.add(matrix9);
        System.out.println("Матрица 2 = " + matrix2);

        matrix3.transpose();
        System.out.println("Матрица 3 = " + matrix3);
        matrix2.subtract(matrix3);
        System.out.println("Матрица 2 = " + matrix2);

        System.out.println(Matrix.getSum(matrix2, matrix3));

        System.out.println();
        System.out.println("Матрица 2 = " + matrix2);
        System.out.println("Матрица 3 = " + matrix3);

        System.out.printf("Сумма матриц 2 и 3 = %s%n", Matrix.getSum(matrix2, matrix3));
        System.out.printf("Разность матриц 3 и 2 = %s%n", Matrix.getDifference(matrix3, matrix2));

        double[][] doubleArray5 = {{-1, 2, -3, 0}, {5, 4, -2, 1}, {-8, 11, -10, -5}};
        double[][] doubleArray6 = {{-9, 3}, {6, 20}, {7, 0}, {12, -4}};
        Matrix matrix10 = new Matrix(doubleArray5);
        Matrix matrix11 = new Matrix(doubleArray6);
        System.out.println("Матрица 10 = " + matrix10);
        System.out.println("Матрица 11 = " + matrix11);
        System.out.printf("Произведение матриц 10 и 11 = %s%n", Matrix.getProduct(matrix10, matrix11));
    }
}
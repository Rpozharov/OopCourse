package ru.academits.pozharov.matrix_main;

import ru.academits.pozharov.matrix.Matrix;
import ru.academits.pozharov.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        double[] array1 = {4, 7, 9, 4, 3, 5};
        double[] array2 = {2, 3, 9};
        double[] array3 = {2, 3, 9, 8, 12};
        double[] array4 = {4, 7, 9, 4, 3, 5};
        double[] array5 = {1, 2, -1};
        double[][] matrixArray1 = {{2, 3, 7, 5}, {2, 4, 10}, {8, 12, 25, 4, 8}};
        double[][] matrixArray2 = {{1, 2, 3, 4, 3}, {2, 3, 4, 5, 5}, {3, 4, 1, 2, 9}, {4, 4, 21, 3, 12}, {5, 12, 17, 0, 14}};
        double[][] matrixArray3 = {{2, 4, 0}, {-2, 1, 3}, {-1, 0, 1}};
        double[][] matrixArray4 = {{5, 3, 8}, {7, 9, 1}, {-3, 12, 6}};
        double[][] matrixArray5 = {{10, 12, 22}, {17, 9, 5}, {25, 11, 6}};
        double[][] matrixArray6 = {{-1, 2, -3, 0}, {5, 4, -2, 1}, {-8, 11, -10, -5}};
        double[][] matrixArray7 = {{-9, 3}, {6, 20}, {7, 0}, {12, -4}};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);
        Vector vector5 = new Vector(array5);

        System.out.println("Вектор 1 = " + vector1);
        System.out.println("Вектор 2 = " + vector2);
        System.out.println("Вектор 3 = " + vector3);
        System.out.println("Вектор 4 = " + vector4);

        Vector[] vectors = {vector1, vector2, vector3, vector4};

        Matrix matrix1 = new Matrix(4, 3);
        Matrix matrix2 = new Matrix(matrixArray1);
        Matrix matrix3 = new Matrix(matrix2);
        Matrix matrix4 = new Matrix(vectors);

        System.out.println();
        System.out.println("Матрица 1 = " + matrix1);
        System.out.println("Матрица 2 = " + matrix2);
        System.out.println("Матрица 3 = " + matrix3);
        System.out.println("Матрица 4 = " + matrix4);

        matrix1.setRowByIndex(1, vector2);
        System.out.println("Матрица 1 = " + matrix1);

        matrix2.transpose();
        System.out.println("Матрица 2 = " + matrix2);

        matrix2.multiplyByScalar(2);
        System.out.println("Матрица 2 = " + matrix2);

        Matrix matrix5 = new Matrix(matrixArray2);
        System.out.println("Матрица 5 = " + matrix5);
        System.out.println("Определитель матрицы 5 = " + matrix5.getDeterminant());

        Matrix matrix6 = new Matrix(matrixArray3);
        Matrix matrix7 = new Matrix(matrixArray4);
        System.out.println("Матрица 6 = " + matrix6);
        System.out.println("Матрица 7 = " + matrix7);
        matrix6.add(matrix7);
        System.out.println("Прибавление матрицы 7 к матрице 6 = " + matrix6);

        Matrix matrix8 = new Matrix(matrixArray5);
        System.out.println("Матрица 8 = " + matrix8);
        System.out.println("Вычитание матрицы 7 из матрицы 8 = " + matrix6);
        matrix8.subtract(matrix7);
        System.out.println("Матрица 8 = " + matrix8);

        System.out.println("Матрица 6 = " + matrix6);
        System.out.println("Матрица 7 = " + matrix7);
        System.out.println("Сложение матрицы 6 и 7 = " + Matrix.getSum(matrix6, matrix7));
        System.out.println("Разность матрицы 8 и 7 = " + Matrix.getDifference(matrix8, matrix7));

        Matrix matrix9 = new Matrix(matrixArray6);
        Matrix matrix10 = new Matrix(matrixArray7);
        System.out.println("Матрица 9 = " + matrix9);
        System.out.println("Матрица 10 = " + matrix10);
        System.out.println("Произведение матриц 9 и 10 = " + Matrix.getProduct(matrix9, matrix10));

        System.out.println("Вектор строка матрицы 10 по индексу 0 = " + matrix10.getRowByIndex(0));

        System.out.println("Матрица 6 = " + matrix6);
        System.out.println("Вектор 5 = " + vector5);
        System.out.println("Матрица 6 умноженная на вектор 5 = " + matrix6.multiplyByVector(vector5));
    }
}
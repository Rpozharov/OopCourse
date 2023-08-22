package ru.academits.pozharov.matrix;

import ru.academits.pozharov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Количество строк или столбцов в матрице должно быть > 0. "
                    + "Текущие значения количества строк: " + rowsCount + ", количества столбцов: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива должна быть > 0. Текущее значение: " + array.length);
        }

        if (array[0].length == 0) {
            throw new IllegalArgumentException("Длина вложенного массива должна быть > 0. Текущее значение: " + array[0].length);
        }

        rows = new Vector[array.length];

        int maxLength = 1;

        for (double[] e : array) {
            if (e.length > maxLength) {
                maxLength = e.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Длина массива векторов должна быть > 0. Текущее значение: " + vectors.length);
        }

        rows = new Vector[vectors.length];

        int maxLength = 1;

        for (Vector vector : vectors) {
            if (vector == null) {
                throw new NullPointerException("Элементы массива векторов не должны быть null");
            }

            if (vector.getSize() > maxLength) {
                maxLength = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxLength);
            rows[i].add(vectors[i]);
        }
    }

    public int[] getSize() {
        int[] array = new int[2];
        array[0] = rows.length;
        array[1] = rows[0].getSize();
        return array;
    }

    public Vector getRowByIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < количества строк в матрице: " + rows.length
                    + ". Текущее значение индекса: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRowByIndex(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < количества строк в матрице: " + rows.length
                    + ". Текущее значение индекса: " + index);
        }

        if (vector.getSize() != rows[index].getSize()) {
            throw new IndexOutOfBoundsException("Размерность вектора должна быть = количеству столбцов в матрице: "
                    + rows[index].getSize() + ". Текущее значение размерности: " + vector.getSize());
        }

        rows[index] = vector;
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < количества столбцов в матрице: " + rows[0].getSize()
                    + ". Текущее значение индекса: " + index);
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = rows[i].getComponentByIndex(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] tempVectors = new Vector[rows[0].getSize()];

        for (int i = 0; i < tempVectors.length; i++) {
            tempVectors[i] = this.getColumnByIndex(i);
        }

        rows = tempVectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getSize()[0] != getSize()[1]) {
            throw new IllegalArgumentException("Количество строк должно быть = количеству столбцов. Текущие значения: строк = "
                    + getSize()[0] + ", столбцов = " + getSize()[1]);
        }

        if (getSize()[0] == 1) {
            return rows[0].getComponentByIndex(0);
        }

        if (getSize()[0] == 2) {
            return (rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1)) - (rows[0].getComponentByIndex(1)
                    * rows[1].getComponentByIndex(0));
        }

        if (getSize()[0] == 3) {
            return (rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1) * rows[2].getComponentByIndex(2))
                    + (rows[0].getComponentByIndex(1) * rows[1].getComponentByIndex(2) * rows[2].getComponentByIndex(0))
                    + (rows[0].getComponentByIndex(2) * rows[1].getComponentByIndex(0) * rows[2].getComponentByIndex(1))
                    - (rows[0].getComponentByIndex(2) * rows[1].getComponentByIndex(1) * rows[2].getComponentByIndex(0))
                    - (rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(2) * rows[2].getComponentByIndex(1))
                    - (rows[0].getComponentByIndex(1) * rows[1].getComponentByIndex(0) * rows[2].getComponentByIndex(2));
        }

        if (rows[0].getComponentByIndex(0) == 0) {
            return -1;
        }

        Matrix matrix = new Matrix(this);

        for (int i = 0; i < matrix.getRowByIndex(0).getSize(); i++) {
            for (int j = i; j < matrix.getRowByIndex(0).getSize() - 1; j++) {
                Vector vector = new Vector(matrix.getRowByIndex(i));
                double number = matrix.getRowByIndex(j + 1).getComponentByIndex(i) / matrix.getRowByIndex(i).getComponentByIndex(i);
                vector.multiplyByScalar(number);
                matrix.getRowByIndex(j + 1).subtract(vector);
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrix.getRowByIndex(0).getSize(); i++) {
            determinant *= matrix.getRowByIndex(i).getComponentByIndex(i);
        }

        return determinant;
    }

    public void multiplyByVector(Vector vector) {
        if (getSize()[1] != 1) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть = 1. Текущее значение: " + getSize()[1]);
        }

        Vector[] tempVectors = new Vector[getSize()[0]];

        for (int i = 0; i < tempVectors.length; i++) {
            Vector tempVector = new Vector(vector);
            tempVector.multiplyByScalar(getRowByIndex(i).getComponentByIndex(0));
            tempVectors[i] = tempVector;
        }

        rows = tempVectors;

    }

    public void add(Matrix matrix) {
        if (!Arrays.equals(getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны. Текущие значения: матрицы 1 = "
                    + Arrays.toString(getSize()) + ", матрицы 2 = " + Arrays.toString(matrix.getSize()));
        }

        for (int i = 0; i < getSize()[0]; i++) {
            rows[i].add(matrix.getRowByIndex(i));
        }
    }

    public void subtract(Matrix matrix) {
        if (!Arrays.equals(getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны. Текущие значения: матрицы 1 = "
                    + Arrays.toString(getSize()) + ", матрицы 2 = " + Arrays.toString(matrix.getSize()));
        }

        for (int i = 0; i < getSize()[0]; i++) {
            rows[i].subtract(matrix.getRowByIndex(i));
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize()[1] != matrix2.getSize()[0]) {
            throw new IllegalArgumentException("Количество столбцов матрицы 1 должно быть = количеству строк матрицы 2."
                    + " Текущие значения: матрица 1 = " + Arrays.toString(matrix1.getSize())
                    + ", матрица 2 = " + Arrays.toString(matrix2.getSize()));
        }

        Matrix tempMatrix = new Matrix(matrix2);
        tempMatrix.transpose();
        double[][] doubleArray = new double[matrix1.getSize()[0]][matrix2.getSize()[1]];

        for (int i = 0; i < matrix1.getSize()[0]; i++) {
            for (int j = 0; j < matrix2.getSize()[1]; j++) {
                doubleArray[i][j] = Vector.getScalarProduct(matrix1.getRowByIndex(i), tempMatrix.getRowByIndex(j));
            }
        }

        return new Matrix(doubleArray);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
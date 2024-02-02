package ru.academits.pozharov.matrix;

import ru.academits.pozharov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов в матрице должно быть > 0. "
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

        rows = new Vector[array.length];

        int maxLength = 0;

        for (double[] row : array) {
            if (row.length > maxLength) {
                maxLength = row.length;
            }
        }

        if (maxLength == 0) {
            throw new IllegalArgumentException("Длина одного из элементов в массиве должна быть > 0. Текущее значение: " + maxLength);
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

        int maxSize = 1;

        for (Vector vector : vectors) {
            if (vector == null) {
                throw new NullPointerException("Элементы массива векторов не должны быть null");
            }

            if (vector.getSize() > maxSize) {
                maxSize = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxSize);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
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

        if (vector.getSize() != getColumnsCount()) {
            throw new IndexOutOfBoundsException("Размерность вектора должна быть равна количеству столбцов в матрице: "
                    + getColumnsCount() + ". Текущее значение размерности: " + vector.getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0 и < количества столбцов в матрице: " + getColumnsCount()
                    + ". Текущее значение индекса: " + index);
        }

        double[] array = new double[rows.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = rows[i].getComponentByIndex(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < newRows.length; i++) {
            newRows[i] = getColumnByIndex(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Количество строк должно быть равно количеству столбцов. Текущие значения: строк = "
                    + getRowsCount() + ", столбцов = " + getColumnsCount());
        }

        if (getRowsCount() == 1) {
            return rows[0].getComponentByIndex(0);
        }

        if (getRowsCount() == 2) {
            return (rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1))
                    - (rows[0].getComponentByIndex(1) * rows[1].getComponentByIndex(0));
        }

        Matrix matrix = new Matrix(this);
        Vector[] vectors = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            if (matrix.rows[i].getComponentByIndex(i) == 0) {
                return 0;
            }

            for (int j = i; j < rows.length - 1; j++) {
                double coefficient = matrix.rows[j + 1].getComponentByIndex(i) / matrix.rows[i].getComponentByIndex(i);

                vectors[i] = new Vector(matrix.rows[i]);
                vectors[i].multiplyByScalar(coefficient);
                vectors[j + 1] = matrix.rows[j + 1];
                vectors[j + 1].subtract(vectors[i]);

                matrix.setRowByIndex(j + 1, vectors[j + 1]);
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrix.rows[0].getSize(); i++) {
            determinant *= matrix.rows[i].getComponentByIndex(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть равно длине вектора. Текущее значение:"
                    + " столбцов в матрице: " + getColumnsCount() + ", длины вектора: " + vector.getSize());
        }

        Vector resultVector = new Vector(getRowsCount());

        for (int i = 0; i < resultVector.getSize(); i++) {
            resultVector.setComponentByIndex(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }


    public void add(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы 1 должно быть равно количеству строк матрицы 2."
                    + " Текущие значения: количество столбцов в матрице 1 = " + matrix1.getColumnsCount()
                    + ", количество строк в матрице 2 = " + matrix2.getRowsCount());
        }

        Matrix transposedMatrix2 = new Matrix(matrix2);
        transposedMatrix2.transpose();
        double[][] componentsArray = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                componentsArray[i][j] = Vector.getScalarProduct(matrix1.rows[i], transposedMatrix2.rows[j]);
            }
        }

        return new Matrix(componentsArray);
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

    private static void checkSizesEquality(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны. Текущие размеры матрицы 1 = "
                    + matrix1.getRowsCount() + " на " + matrix1.getColumnsCount() + ", матрицы 2 = "
                    + matrix2.getRowsCount() + " на " + matrix2.getColumnsCount());
        }
    }
}
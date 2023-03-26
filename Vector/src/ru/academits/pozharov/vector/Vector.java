package ru.academits.pozharov.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] vectorComponents;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Рвзмерность должна быть > 0");
        }

        this.size = size;
        vectorComponents = new double[size];
    }

    public Vector(Vector vector) {
        size = vector.size;
        vectorComponents = Arrays.copyOf(vector.vectorComponents, vector.size);
    }

    public Vector(double[] array) {
        size = array.length;
        vectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Рвзмерность должна быть > 0");
        }

        this.size = size;
        vectorComponents = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return size;
    }

    public void addVector(Vector vector) {
        if (size < vector.size) {
            size = vector.size;
            vectorComponents = Arrays.copyOf(vectorComponents, vector.size);
        }

        for (int i = 0; i < vector.size; i++) {
            vectorComponents[i] += vector.vectorComponents[i];
        }
    }

    public void subtractVector(Vector vector) {
        if (size < vector.size) {
            size = vector.size;
            vectorComponents = Arrays.copyOf(vectorComponents, vector.size);
        }

        for (int i = 0; i < vector.size; i++) {
            vectorComponents[i] -= vector.vectorComponents[i];
        }
    }

    public void multipleByScalar(double scalar) {
        for (int i = 0; i < size; i++) {
            vectorComponents[i] *= scalar;
        }
    }

    public void deploy() {
        for (int i = 0; i < size; i++) {
            vectorComponents[i] *= -1;
        }
    }

    public double getLength() {
        Vector vector = new Vector(this);
        double length = 0;
        for (int i = 0; i < size; i++) {
            vector.vectorComponents[i] = vector.vectorComponents[i] * vector.vectorComponents[i];
            length += vector.vectorComponents[i];
        }

        return Math.sqrt(length);
    }

    public double getComponentOnIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть > 0 и < размерности");
        }

        return vectorComponents[index];
    }

    public void setComponentOnIndex(int index, double newComponent) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть > 0 и < размерности");
        }

        vectorComponents[index] = newComponent;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        if (resultVector.size < vector2.size) {
            resultVector.size = vector2.size;
            resultVector.vectorComponents = Arrays.copyOf(resultVector.vectorComponents, vector2.size);
        }

        for (int i = 0; i < vector2.size; i++) {
            resultVector.vectorComponents[i] += vector2.vectorComponents[i];
        }

        return resultVector;
    }

    public static Vector getVectorsDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        if (resultVector.size < vector2.size) {
            resultVector.size = vector2.size;
            resultVector.vectorComponents = Arrays.copyOf(resultVector.vectorComponents, vector2.size);
        }

        for (int i = 0; i < vector2.size; i++) {
            resultVector.vectorComponents[i] -= vector2.vectorComponents[i];
        }

        return resultVector;
    }

    public static Double getVectorsScalarProduct(Vector vector1, Vector vector2) {
        double ScalarProduct = 0;

        for (int i = 0; i < Math.min(vector1.size, vector2.size); i++) {
            ScalarProduct += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return ScalarProduct;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorComponents);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (vector.size != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (vector.vectorComponents[i] != vectorComponents[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * size + hash;
        hash = prime * hash + Arrays.hashCode(vectorComponents);
        return hash;
    }
}
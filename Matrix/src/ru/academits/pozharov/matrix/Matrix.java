package ru.academits.pozharov.matrix;

public class Matrix {
    private final Vector[] vectors;

    public Matrix(int sizeLine, int sizeColumn) {

        vectors = new Vector[sizeLine];

        for (int i = 0; i < sizeLine; i++) {
            vectors[i] = new Vector(sizeColumn);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        vectors = new Vector[array.length];

        int maxSize = 0;

        for (double[] e : array) {
            if (e.length > maxSize) {
                maxSize = e.length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(maxSize, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        vectors = new Vector[vectorsArray.length];

        int maxSize = 0;

        for (Vector vector : vectorsArray) {
            if (vector.getSize() > maxSize) {
                maxSize = vector.getSize();
            }
        }

        for (int i = 0; i < vectorsArray.length; i++) {
            double[] array = new double[vectorsArray[i].getSize()];

            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                array[j] = vectorsArray[i].getComponentByIndex(j);
            }

            vectors[i] = new Vector(maxSize, array);
        }
    }

    public Vector[] getVectors() {
        return vectors;
    }
}
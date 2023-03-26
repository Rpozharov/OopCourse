package ru.academits.pozharov.vector_main;

import ru.academits.pozharov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {4, 7, 9, 4, 3, 5};
        double[] array2 = {2, 3, 9};
        double[] array3 = {2, 3, 9};
        double[] array4 = {4, 7, 9, 4, 3, 5};

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

        vector5.deploy();
        System.out.println("Разворот вектора 5, вектор 5 = " + vector5);

        System.out.printf("Сумма векторов 3 и 4 = %s%n", Vector.getVectorsSum(vector3, vector4));
        System.out.printf("Разность векторов 3 и 4 = %s%n", Vector.getVectorsDifference(vector3, vector4));
        System.out.printf("Скалярное произведение векторов 3 и 4 = %.2f%n", Vector.getVectorsScalarProduct(vector3, vector4));
        System.out.println("Размерность вектора 1 = " + vector1.getSize());
        System.out.printf("Длина вектора 5 = %.2f%n", vector5.getLength());

        vector1.setComponentOnIndex(0, 5);
        vector1.setComponentOnIndex(1, 8);
        vector1.setComponentOnIndex(3, 12);
        System.out.println("Вектор 1 = " + vector1);
        System.out.println("Компонента по индексу 0 вектора 1 = " + vector1.getComponentOnIndex(0));

        vector3.addVector(vector4);
        System.out.println("Прибавление к вектору 3 вектора 4, вектор 3 = " + vector3);

        vector3.subtractVector(vector5);
        System.out.println("Вычитание из вектора 3 вектора 5, вектор 3 = " + vector3);

        vector4.multipleByScalar(5);
        System.out.println("Умножение вектора 4 на скаляр 5, вектор 4 = " + vector4);
    }
}
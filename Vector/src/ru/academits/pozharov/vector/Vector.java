package ru.academits.pozharov.vector;

public class Vector {
    int n;
    double [] vectorComponents;

    public Vector (int n) {
        this.n = n;
        vectorComponents = new double[n];
    }
}

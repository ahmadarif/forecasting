package com.ahmadarif.thesis.algorithm;

/**
 * Created by Arif on 27-Nov-17.
 */
public class LinearRegression {

    private double[] X;
    private double[] Y;
    private double a;
    private double b;
    private double R2;

    public LinearRegression(double[] x, double[] y) {
        X = x;
        Y = y;
    }

    public void process() {
        double n, sumX, sumY, sumX2, sumY2, sumXY;

        n = X.length;
        sumX = sumY = sumX2 = sumY2 = sumXY = 0;

        // hitung sigma masing-masing nilai
        for (int i = 0; i < n; i++) {
            sumX += X[i];
            sumY += Y[i];
            sumX2 += Math.pow(X[i], 2);
            sumY2 += Math.pow(Y[i], 2);
            sumXY += X[i] * Y[i];
        }

        a = ((sumY * sumX2) - (sumX * sumXY)) / ((n * sumX2) - Math.pow(sumX, 2));
        b = ((n * sumXY) - (sumX * sumY)) / ((n * sumX2) - Math.pow(sumX, 2));
        R2 = Math.pow((n * sumXY) - (sumX * sumY), 2) / (((n * sumX2) - Math.pow(sumX, 2)) * ((n * sumY2) - Math.pow(sumY, 2)));
    }

    public double predict(double x) {
        return a + (b * x);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getR2() {
        return R2;
    }
}
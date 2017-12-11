package com.ahmadarif.thesis.algorithm;

/**
 * Created by Arif on 27-Nov-17.
 */
public class ExponentialSmoothing {

    private double[] data;
    private double alpha;

    public ExponentialSmoothing(double[] data, double alpha) {
        this.data = data;
        this.alpha = alpha;
    }

    public double SES() {
        double[] f = new double[data.length];
        f[0] = data[0];

        System.out.println(String.format("S1 = %.2f", f[0]));
        for (int i = 1; i < data.length; i++) {
            f[i] = (alpha * data[i]) + ((1 - alpha) * f[i - 1]);
            System.out.println(String.format("S%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, data[i], (1 - alpha), f[i - 1], f[i]));
        }

        return (alpha * data[data.length - 1]) + ((1 - alpha) * f[data.length - 1]);
    }

    public double DES() {
        double[] s1 = new double[data.length];
        double[] s2 = new double[data.length];
        double[] a = new double[data.length];
        double[] b = new double[data.length];
        s1[0] = s2[0] = data[0];

        System.out.println("S't = (Alpha * Xt) + ((1 - Alpha) S't-1)");
        System.out.println(String.format("S'1 = %.2f", s1[0]));
        for (int i = 1; i < data.length; i++) {
            s1[i] = (alpha * data[i]) + ((1 - alpha) * s1[i - 1]);
            System.out.println(String.format("S'%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, data[i], (1 - alpha), s1[i - 1], s1[i]));
        }

        System.out.println();
        System.out.println("S\"t = (Alpha * S't) + ((1 - Alpha) * S\"t-1)");
        System.out.println(String.format("S\"1 = %.2f", s2[0]));
        for (int i = 1; i < data.length; i++) {
            s2[i] = (alpha * s1[i]) + ((1 - alpha) * s2[i - 1]);
            System.out.println(String.format("S\"%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, s1[i], (1 - alpha), s2[i - 1], s2[i]));
        }

        System.out.println();
        System.out.println("at = 2S't - S\"t");
        for (int i = 0; i < data.length; i++) {
            a[i] = (2 * s1[i]) - s2[i];
            System.out.println(String.format("a%d = 2(%.2f) - %.2f = %.2f", (i + 1), s1[i], s2[i], a[i]));
        }

        System.out.println();
        System.out.println("bt = (Alpha / (1 - Alpha)) * (S't - S\"t)");
        for (int i = 0; i < data.length; i++) {
            b[i] = (alpha / (1 - alpha)) * (s1[i] - s2[i]);
            System.out.println(String.format("b%d = (%.2f / %.2f) * (%.2f - %.2f) = %.2f", (i + 1), alpha, (1 - alpha), s1[i], s2[i], b[i]));
        }

        return (a[data.length - 1] + b[data.length - 1]);
    }

}
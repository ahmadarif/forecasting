package com.ahmadarif.thesis.algorithm;

/**
 * Created by Arif on 27-Nov-17.
 */
public class ExponentialSmoothing {

    private double[] data;

    public ExponentialSmoothing(double[] data) {
        this.data = data;
    }

    // Ŷ_(t+1)=αY_t+(1-α)Ŷ_t
    public double SES(double alpha, boolean debug) {
        double[] f = new double[data.length];
        f[0] = data[0];

        if (debug) System.out.println(String.format("S1 = %.2f", f[0]));
        for (int i = 1; i < data.length; i++) {
            f[i] = (alpha * data[i]) + ((1 - alpha) * f[i - 1]);
            if (debug) System.out.println(String.format("S%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, data[i], (1 - alpha), f[i - 1], f[i]));
        }

        return f[data.length - 1];
    }

    public double[] SESF(double alpha) {
        double[] f = new double[data.length];
        f[0] = data[0];

        for (int i = 1; i < data.length; i++) {
            f[i] = (alpha * data[i]) + ((1 - alpha) * f[i - 1]);
        }

        return f;
    }

    public double DES(double alpha, boolean debug) {
        double[] s1 = new double[data.length];
        double[] s2 = new double[data.length];
        double[] a = new double[data.length];
        double[] b = new double[data.length];

        s1[0] = s2[0] = data[0];

        // S't = (Alpha * Xt) + ((1 - Alpha) S't-1)
        if (debug) {
            System.out.println("S't = (Alpha * Xt) + ((1 - Alpha) S't-1)");
            System.out.println(String.format("S'1 = %.2f", s1[0]));
        }
        for (int i = 1; i < data.length; i++) {
            s1[i] = (alpha * data[i]) + ((1 - alpha) * s1[i - 1]);
            if (debug) System.out.println(String.format("S'%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, data[i], (1 - alpha), s1[i - 1], s1[i]));
        }

        // S"t = (Alpha * S't) + ((1 - Alpha) * S"t-1)
        if (debug) {
            System.out.println();
            System.out.println("S\"t = (Alpha * S't) + ((1 - Alpha) * S\"t-1)");
            System.out.println(String.format("S\"1 = %.2f", s2[0]));
        }
        for (int i = 1; i < data.length; i++) {
            s2[i] = (alpha * s1[i]) + ((1 - alpha) * s2[i - 1]);
            if (debug) System.out.println(String.format("S\"%d = %.2f(%.2f) + %.2f(%.2f) = %.2f", (i + 1), alpha, s1[i], (1 - alpha), s2[i - 1], s2[i]));
        }

        // at = 2S't - S"t
        if (debug) {
            System.out.println();
            System.out.println("at = 2S't - S\"t");
        }
        for (int i = 0; i < data.length; i++) {
            a[i] = (2 * s1[i]) - s2[i];
            if (debug) System.out.println(String.format("a%d = 2(%.2f) - %.2f = %.2f", (i + 1), s1[i], s2[i], a[i]));
        }

        // bt = (Alpha / (1 - Alpha)) * (S't - S"t)
        if (debug) {
            System.out.println();
            System.out.println("bt = (Alpha / (1 - Alpha)) * (S't - S\"t)");
        }
        for (int i = 0; i < data.length; i++) {
            b[i] = (alpha / (1 - alpha)) * (s1[i] - s2[i]);
            if (debug) System.out.println(String.format("b%d = (%.2f / %.2f) * (%.2f - %.2f) = %.2f", (i + 1), alpha, (1 - alpha), s1[i], s2[i], b[i]));
        }

        // hasil
        System.out.printf("\nF1 = %.2f\n", data[0]);
        for (int i = 1; i < data.length; i++) {
             double f = a[i] + b[i];
             if (debug) System.out.printf("F%d = %.2f + %.2f = %.2f\n", i+1, a[i], b[i], f);
        }

        return (a[data.length - 1] + b[data.length - 1]);
    }

    public double[] DESF(double alpha) {
        double[] s1 = new double[data.length];
        double[] s2 = new double[data.length];
        double[] a = new double[data.length];
        double[] b = new double[data.length];
        double[] f = new double[data.length];

        s1[0] = s2[0] = data[0];

        // S't = (Alpha * Xt) + ((1 - Alpha) S't-1)
        for (int i = 1; i < data.length; i++) {
            s1[i] = (alpha * data[i]) + ((1 - alpha) * s1[i - 1]);
        }

        // S"t = (Alpha * S't) + ((1 - Alpha) * S"t-1)
        for (int i = 1; i < data.length; i++) {
            s2[i] = (alpha * s1[i]) + ((1 - alpha) * s2[i - 1]);
        }

        // at = 2S't - S"t
        for (int i = 0; i < data.length; i++) {
            a[i] = (2 * s1[i]) - s2[i];
        }

        // bt = (Alpha / (1 - Alpha)) * (S't - S"t)
        for (int i = 0; i < data.length; i++) {
            b[i] = (alpha / (1 - alpha)) * (s1[i] - s2[i]);
        }

        // result
        for (int i = 0; i < data.length; i++) {
            f[i] = a[i] + b[i];
        }

        return f;
    }

}
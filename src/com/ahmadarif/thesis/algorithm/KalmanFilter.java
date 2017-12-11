package com.ahmadarif.thesis.algorithm;

/**
 * Created by Arif on 04-Dec-17.
 */
public class KalmanFilter {

    private double[] Zk;
    private double[] Xk;
    private double[] Pk;
    private double[] Kk;

    private boolean isDebug = false;

    public KalmanFilter(double[] data, boolean isDebug) {
        this.Zk = data;
        this.isDebug = isDebug;

        Xk = new double[data.length];
        Pk = new double[data.length];
        Kk = new double[data.length];
    }

    public double process(double R) {
        double x1, p1;

        if (isDebug) System.out.println(" #  | val \t\t| x1 \t\t| p1 \t\t| K \t\t| x2 \t\t| p2 ");
        for (int i = 0; i < Zk.length; i++) {
            // Time Update
            if (i == 0) {
                Xk[i] = 0;          // Estimasi
                Pk[i] = 1000;          // Kovariansi Error, bukan 0
            } else {
                Xk[i] = Xk[i - 1];  // Estimasi
                Pk[i] = Pk[i - 1];  // Kovariansi Error
            }

            // Save old data
            x1 = Xk[i];
            p1 = Pk[i];

            // Measurement Update
            Kk[i] = Pk[i] / (Pk[i] + R);                // Kalman Gain
            Xk[i] = Xk[i] + Kk[i] * (Zk[i] - Xk[i]);    // Estimasi
            Pk[i] = (1 - Kk[i]) * Pk[i];                // Kovariansi Error

            if (isDebug) System.out.format(" %2d  | %.4f \t| %.4f \t| %.4f \t| %.4f \t| %.4f \t| %.4f\n", i+1, Zk[i], x1, p1, Kk[i], Xk[i], Pk[i]);
        }

        return Xk[Zk.length - 1];
    }

    public void kasus311() {
        double L = 1;
        double x0 = 0;
        double p0 = 1000;
        double q = 0.0001;
        double r = 0.1;
        int tmax = 50;

        double x1, p1, x2 = 0, p2 = 0, y, K;

        System.out.println(" # | x1 | p2 | y | K | x2 | p2 ");
        for (int t = 0; t < tmax; t++) {
            if (t == 0) {
                x1 = x0;
                p1 = p0 + q;
            } else {
                x1 = x2;
                p1 = p2 + q;
            }

            y = L - r + 2 * r * Math.random();
            if (y < 0) y = 0;
            K = p1 * Math.pow(p1 + r, -1);
            x2 = x1 + K * (Zk[t] - x1);
            p2 = (1 - K) * p1;

            System.out.format(" %2d | %.4f | %.4f | %.4f | %.4f | %.4f | %.4f\n", t+1, x1, p1, y, K, x2, p2);
        }
    }

    /**
     *
     * @param R
     * @param q noise
     * @return
     */
    public double kasus311Dev(double R, double q) {
        double x0 = 0;
        double p0 = 1000;
        double x1, p1, x2 = 0, p2 = 0, K;

        if (isDebug) System.out.println(" #  | val \t\t| x1 \t\t| p1 \t\t| K \t\t| x2 \t\t| p2 ");
        for (int t = 0; t < Zk.length; t++) {
            // Time Update
            if (t == 0) {
                x1 = x0;
                p1 = p0 + q;
            } else {
                x1 = x2;
                p1 = p2 + q;
            }

            // Measurement Update
            K = p1 * Math.pow(p1 + R, -1);
            x2 = x1 + K * (Zk[t] - x1);
            p2 = (1 - K) * p1;

            if (isDebug) System.out.format(" %2d | %.4f \t| %.4f \t| %.4f \t| %.4f \t| %.4f \t| %.4f\n", t+1, Zk[t], x1, p1, K, x2, p2);
        }

        return x2;
    }
}
package com.ahmadarif.thesis.algorithm;

/**
 * Created by Arif on 24-Aug-18.
 */
public class Accuration {
    /**
     * Mean Absolute Deviation mengukur ketepatan ramalan dengan merata-rata kesalahan dugaan (nilai absolut
     * masing-masing kesalahan)
     * @param Y nilai sebenarnya
     * @param F nilai peramalan
     * @return double
     */
    public static double MAD(double[] Y, double[] F) {
        double mad = 0;
        for (int t = 1; t < Y.length; t++) {
            mad += Math.abs(Y[t] - F[t-1]);
        }
        return (mad / (Y.length - 1));
    }

    /**
     * Mean Square Error adalah metode alternatif untuk mengevaluasi teknik peramalan masing-masing kesalahan (selisih
     * data aktual terhadap data peramalan) dikuadratkan. Pendekatan ini menghasilkan kesalahan-kesalahan sedang yang
     * kemungkinan lebih baik untuk kesalahan kecil, tetapi kadang menghasilkan perbedaan yang besar.
     * @param Y nilai sebenarnya
     * @param F nilai peramalan
     * @return double
     */
    public static double MSE(double[] Y, double[] F) {
        double mse = 0;
        for (int t = 1; t < Y.length; t++) {
            mse += Math.pow(Y[t] - F[t-1], 2);
        }
        return (mse / (Y.length - 1));
    }

    public static double RMSE(double[] Y, double[] F) {
        double mse = 0;
        for (int t = 1; t < Y.length; t++) {
            mse += Math.pow(Y[t] - F[t-1], 2);
        }
        return Math.sqrt(mse / (Y.length - 1));
    }

    /**
     * Mean Absolute Percentage Error dihitung dengan menggunakan kesalahan absolut pada tiap periode dibagi dengan
     * nilai observasi yang nyata untuk periode itu, kemudian merata-rata kesalahan persentase absolut tersebut.
     * MAPE merupakan pengukuran kesalahan yang menghitung ukuran persentase penyimpangan antara data aktual dengan
     * data peramalan.
     * @param Y nilai sebenarnya
     * @param F nilai peramalan
     * @return double
     */
    public static double MAPE(double[] Y, double[] F) {
        double mape = 0;
        for (int t = 1; t < Y.length; t++) {
            mape = mape + (Math.abs(Y[t] - F[t-1]) / Math.abs(Y[t]));
        }
        return (mape * (100 / (Y.length - 1)));
    }

    public static double persen(double[] Y, double[] F, boolean debug) {
        double totalAccuration = 0;
        for (int t = 1; t < Y.length; t++) {
            double accuration = (1 - (Math.abs(Y[t] - F[t-1]) / Math.abs(Y[t]))) * 100;
            totalAccuration += accuration;
            if (debug) System.out.printf("%d) Y=%.2f, F=%.2f, Acc=%.2f\n", t, Y[t], F[t-1], accuration);
        }
        return (totalAccuration / (Y.length - 1));
    }
}

package com.ahmadarif.thesis;

import com.ahmadarif.thesis.algorithm.Accuration;
import com.ahmadarif.thesis.algorithm.ExponentialSmoothing;
import com.ahmadarif.thesis.algorithm.KalmanFilter;
import com.ahmadarif.thesis.algorithm.angle.Angle;
import com.ahmadarif.thesis.algorithm.angle.Point3;

public class Main {

//    static double[] y = {101,87,95,97,109,118,122,115,128,127,125,128};
//    static double[] y = {154,156,154,155,161,155,150,152,154};
//    static double[] y = {103,131,102,130,128,74,66,117,120,118,119,122,120,117,119,120,122,121,126,126,123,127,125,121,122,122,118,114,108,114,115,110,110,111,116,122,113,117,113};
//    static double[] y = {101,99,47,113,108,114,97,98,94,83,99,99,98,96,95,105,107,92,87,95,102,115,98,102,112,98,89,99,29,21,82};
    static double[] y = {116,109,97,98,102,97,103,97,95,102,104,99,109,117,122,113,113,106,110,111,103,106,111,112,113,110,104,97,112,111,113};

    static double[][] y2 = {
        {101,87,95,97,109,118,122,115,128,127,125,128},
        {154,156,154,155,161,155,150,152,154},
        {103,131,102,130,128,74,66,117,120,118,119,122,120,117,119,120,122,121,126,126,123,127,125,121,122,122,118,114,108,114,115,110,110,111,116,122,113,117,113},
        {101,99,47,113,108,114,97,98,94,83,99,99,98,96,95,105,107,92,87,95,102,115,98,102,112,98,89,99,29,21,82},
        {116,109,97,98,102,97,103,97,95,102,104,99,109,117,122,113,113,106,110,111,103,106,111,112,113,110,104,97,112,111,113}
    };

    public static void vectorExample() {
        Point3 a = new Point3(0, 0, 0);
        Point3 b = new Point3(10, 0, 0);
        Point3 c = new Point3(20, 0, 0);

        int degrees = (int)Angle.calculate(a, b, c);
        System.out.println(String.format("Result is %d", degrees));
        System.out.println(String.format("Is straight? %b", Angle.isStraight(a, b, c, 5)));
    }

    public static void kalmanFilterExample() {
        double[] data = {34, 40, 35, 39, 41, 36, 33, 38, 43, 40};

        KalmanFilter kf = new KalmanFilter(data);
        double result1 = kf.process(0.1, true);
        double result2 = kf.kasus311Dev(0.1, 0.1, true);

        System.out.println("\n");
        System.out.format("Result 1 = %.4f\n", result1);
        System.out.format("Result 2 = %.4f", result2);
    }

    static void hitungAkurasi() {
        System.out.println("actual :=============================================================================");
        println(y);

        double alpha = 0.9;
        double R = 0.1;

        // eksponensial
        ExponentialSmoothing es = new ExponentialSmoothing(y);

        // single eksponensial smoothing
        System.out.println("single eksponensial smoothing :=============================================================");
//        es.SES(alpha, true);
        double f[] = es.SESF(alpha);
        printAcc(y, f, false);
        println(f);

        // double eksponensial smoothing
        System.out.println("double eksponensial smoothing :=============================================================");
//        es.DES(alpha, true);
        f = es.DESF(alpha);
        printAcc(y, f, false);
        println(f);

        // kalman filter
        System.out.println("kalman filter :=============================================================================");
        KalmanFilter kf = new KalmanFilter(y);
        f = kf.processF(R);
        printAcc(y, f, false);
        println(f);
    }

    static void optimasiEksponensial() {
        for (int index = 0; index < y2.length; index++) {
            ExponentialSmoothing es = new ExponentialSmoothing(y2[index]);
            double alpha = 0.1;

            for (int i = 0; i < 9; i++) {
                double[] f = es.SESF(alpha);
                alpha += 0.1;

                printAcc(y2[index], f, false);
            }
            System.out.println();
        }
    }

    static void optimasiKalman() {
        for (int index = 0; index < y2.length; index++) {
            KalmanFilter kf = new KalmanFilter(y2[index]);
            double alpha = 0.1;

            for (int i = 0; i < 9; i++) {
                double[] f = kf.processF(alpha);
                alpha += 0.1;

                printAcc(y2[index], f, false);
            }
            System.out.println();
        }
    }

    static void print(double[] arr) {
        for (double x : arr) {
            System.out.printf("%.2f, ", x);
        }
    }

    static void println(double[] arr) {
        for (double x : arr) {
            System.out.printf("%.2f\n", x);
        }
    }

    static void printAcc(double[] Y, double[] F, boolean withText) {
        if (withText) {
            System.out.printf("MAD = %.2f", Accuration.MAD(Y, F));
            System.out.printf(", MSE = %.2f", Accuration.MSE(Y, F));
            System.out.printf(", RMSE = %.2f", Accuration.RMSE(Y, F));
            System.out.printf(", MAPE = %.2f", Accuration.MAPE(Y, F));
            System.out.printf(", ACC = %.2f\n", Accuration.persen(Y, F, false));
        } else {
            System.out.printf("%.2f\t", Accuration.MAD(Y, F));
            System.out.printf("%.2f\t", Accuration.MSE(Y, F));
            System.out.printf("%.2f\t", Accuration.RMSE(Y, F));
            System.out.printf("%.2f\t", Accuration.MAPE(Y, F));
            System.out.printf("%.2f\n", Accuration.persen(Y, F, false));
        }
    }

    public static void main(String[] args) {
//        optimasiEksponensial();
//        optimasiKalman();
        hitungAkurasi();
    }

}
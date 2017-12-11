package com.ahmadarif.thesis;

import com.ahmadarif.thesis.algorithm.ExponentialSmoothing;
import com.ahmadarif.thesis.algorithm.KalmanFilter;
import com.ahmadarif.thesis.algorithm.LinearRegression;
import com.ahmadarif.thesis.algorithm.angle.Angle;
import com.ahmadarif.thesis.algorithm.angle.Point3;

public class Main {

    public static void exponentialSoothingExample() {
        double[] data = {34, 40, 35, 39, 41, 36, 33, 38, 43, 40};
        double[] data2 = {120, 125, 129, 124, 130};
        double[] data3 = {
                815569, 1063646, 1583654, 2027675, 3771993,
                5259032, 4198209, 4679290, 2521396, 2403656,
                2242112, 2464118, 6320890, 3463067, 2298571,
                176545
        };

        ExponentialSmoothing es = new ExponentialSmoothing(data2, 0.2);
        System.out.println(String.format("\nSES = %.2f", es.SES()));
        System.out.println(String.format("\nDES = %.2f", es.DES()));
    }

    public static void linearRegressionExample() {
        double[] X = {2, 3, 2, 5, 6, 1, 4, 1};
        double[] Y = {5, 8, 8, 7, 11, 3, 10, 4};
        LinearRegression lr = new LinearRegression(X, Y);
        lr.process();

        System.out.println(String.format("a = %.2f, b = %.2f", lr.getA(), lr.getB()));
        double x = 3.5;
        System.out.println(String.format("Jika X = %.2f, maka prediksinya adalah %.2f", x, lr.predict(x)));
        System.out.println(String.format("R2 = %.2f", lr.getR2()));
    }

    public static void vectorExample() {
        //        Point3 a = new Point3(2, 1, 2);
//        Point3 b = new Point3(9, -6, 2);
//        Point3 c = new Point3(2, -4, 7);

//        Point3 a = new Point3(-1, -2, 4);
//        Point3 b = new Point3(-4, -2, 0);
//        Point3 c = new Point3(3, -2, 1);

//        Point3 a = new Point3(5, 1, 3);
//        Point3 b = new Point3(2, -1, -1);
//        Point3 c = new Point3(4, 2, -4);

        Point3 a = new Point3(0, 0, 0);
        Point3 b = new Point3(10, 0, 0);
        Point3 c = new Point3(20, 0, 0);

        int degrees = (int)Angle.calculate(a, b, c);
        System.out.println(String.format("Result is %d", degrees));
        System.out.println(String.format("Is straight? %b", Angle.isStraight(a, b, c, 5)));
    }

    public static void kalmanFilterExample() {
        double[] data = {0.39, 0.50, 0.48, 0.29, 0.25, 0.32, 0.34, 0.48, 0.41, 0.45};
        boolean isDebug = true;

        KalmanFilter kf = new KalmanFilter(data, isDebug);
        double result1 = kf.process(0.1);
        if (isDebug) System.out.println("==================================================================================");
        double result2 = kf.kasus311Dev(0.1, 0.1);

        System.out.println("\n");
        System.out.format("Result 1 = %.4f\n", result1);
        System.out.format("Result 2 = %.4f", result2);
    }

    public static void main(String[] args) {
        kalmanFilterExample();
    }

}
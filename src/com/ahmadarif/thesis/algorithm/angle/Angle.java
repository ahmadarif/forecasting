package com.ahmadarif.thesis.algorithm.angle;

/**
 * Created by Arif on 29-Nov-17.
 */
public class Angle {

    /**
     * Menghitung besar sudut tengah dari 3 titik
     * Hasil ada besar sudut di titik B (antara BA dan BC)
     * @param a Point3
     * @param b Point3
     * @param c Point3
     * @return double
     */
    public static double calculate(Point3 a, Point3 b, Point3 c) {
        Point3 ba, bc;
        double baAbs, bcAbs, cosB;

        ba = new Point3(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
        bc = new Point3(c.getX() - b.getX(), c.getY() - b.getY(), c.getZ() - b.getZ());

        baAbs = Math.sqrt(Math.pow(ba.getX(), 2) + Math.pow(ba.getY(), 2) + Math.pow(ba.getZ(), 2));
        bcAbs = Math.sqrt(Math.pow(bc.getX(), 2) + Math.pow(bc.getY(), 2) + Math.pow(bc.getZ(), 2));

        cosB = Math.toDegrees(Math.acos(((ba.getX() * bc.getX()) + (ba.getY() * bc.getY()) + (ba.getZ() * bc.getZ())) / (baAbs * bcAbs)));
        return cosB;
    }

    /**
     * Menghitung besar sudut tengah dari 3 titik
     * Hasil ada besar sudut di titik B (antara BA dan BC)
     * @param a Point2
     * @param b Point2
     * @param c Point2
     * @return double
     */
    public static double calculate(Point2 a, Point2 b, Point2 c) {
        Point2 ba, bc;
        double baAbs, bcAbs, cosB;

        ba = new Point2(a.getX() - b.getX(), a.getY() - b.getY());
        bc = new Point2(c.getX() - b.getX(), c.getY() - b.getY());

        baAbs = Math.sqrt(Math.pow(ba.getX(), 2) + Math.pow(ba.getY(), 2));
        bcAbs = Math.sqrt(Math.pow(bc.getX(), 2) + Math.pow(bc.getY(), 2));

        cosB = Math.toDegrees(Math.acos(((ba.getX() * bc.getX()) + (ba.getY() * bc.getY())) / (baAbs * bcAbs)));
        return cosB;
    }

    /**
     * Melakukan pengecekan apakah 3 titik tersebut lurus
     * Yang ditengah harus titik b
     * @param a Point3
     * @param b Point3
     * @param c Point3
     * @return boolean
     */
    public static boolean isStraight(Point3 a, Point3 b, Point3 c) {
        return calculate(a, b, c) == 180;
    }

    /**
     * Melakukan pengecekan apakah 3 titik tersebut lurus dengan nilai treshold
     * Yang ditengah harus titik b
     * @param a Point3
     * @param b Point3
     * @param c Point3
     * @param treshold int
     * @return boolean
     */
    public static boolean isStraight(Point3 a, Point3 b, Point3 c, int treshold) {
        double resultDegrees = calculate(a, b, c);
        double minDegrees = 180 - treshold;
        double maxDegrees = 180 + treshold;
        return resultDegrees >= minDegrees && resultDegrees <= maxDegrees;
    }

    /**
     * Melakukan pengecekan apakah 3 titik tersebut lurus
     * Yang ditengah harus titik b
     * @param a Point2
     * @param b Point2
     * @param c Point2
     * @return boolean
     */
    public static boolean isStraight(Point2 a, Point2 b, Point2 c) {
        return calculate(a, b, c) == 180;
    }

    /**
     * Melakukan pengecekan apakah 3 titik tersebut lurus dengan nilai treshold
     * Yang ditengah harus titik b
     * @param a Point2
     * @param b Point2
     * @param c Point2
     * @param treshold int
     * @return boolean
     */
    public static boolean isStraight(Point2 a, Point2 b, Point2 c, int treshold) {
        double resultDegrees = calculate(a, b, c);
        double minDegrees = 180 - treshold;
        double maxDegrees = 180 + treshold;
        return resultDegrees >= minDegrees && resultDegrees <= maxDegrees;
    }

}
/*
    Jarrett Philips
    Student ID: 160924936
    Computer Graphics   Fall 2016
*/

import static java.lang.Math.*;  /* Now you can use math functions without the Math. prefix */

public class Vector3D implements Cloneable {
    /*
        Variables
    */
    public double x,y,z;

    /*
        Constructors
    */
    public Vector3D(double X, double Y, double Z){
        this.x = X;
        this.y = Y;
        this.z = Z;
    }

    /*
        Functions
    */
    public Vector3D transform(Matrix m){
        double w = 1;
        double tX = (m.m[0][0] * x) + (m.m[0][1] * y) + (m.m[0][2] * z) + (m.m[0][3]);
        double tY = (m.m[1][0] * x) + (m.m[1][1] * y) + (m.m[1][2] * z) + (m.m[1][3]);
        double tZ = (m.m[2][0] * x) + (m.m[2][1] * y) + (m.m[2][2] * z) + (m.m[2][3]);
        double tW = (m.m[3][0] * x) + (m.m[3][1] * y) + (m.m[3][2] * z) + (m.m[3][3]);

        tX = tX / tW;
        tY = tY / tW;
        tZ = tZ / tW;

        Vector3D v = new Vector3D(tX, tY, tZ);
        return v;
    }

    public String toString(){
        System.out.println("x:" + x + " y:" + y + " z:" + z);
        return "Done";
    }

    public Vector3D clone() throws CloneNotSupportedException{
        return new Vector3D(-1,-1,-1);
    }

    public double L2norm(){
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public double dotProduct(Vector3D v){
        return ((this.x * v.x) + (this.y * v.y) + (this.z * v.z));
    }

    public Vector3D crossProduct(Vector3D v){
        double i = (this.y * v.z) - (v.y * this.z);
        double j = (-1) * ((this.x * v.z) - (v.x * this.z));
        double k = (this.x * v.z) - (v.x * this.z);
        return new Vector3D(i, j, k);
    }

    public void normalize(){
        double m = this.L2norm();
        this.x = this.x / m;
        this.y = this.y / m;
        this.z = this.z / m;
    }

    /*
        Testing Main
    */
    public static void main(String args[]){
        System.out.println("Vector Testing Main");

    }
}
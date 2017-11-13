/*
Jarrett Philips
Student ID: 160924936
Computer Graphics   Fall 2016
*/

import static java.lang.Math.*;

public class Point3D {
	/*
	    Variables
	*/
	public double x,y,z;
	
	/*
	    Constructors
	*/
	public Point3D(double X,double Y,double Z){
	    this.x = X;
	    this.y = Y;
	    this.z = Z;
	}
	
	/*
	    Functions
	*/
	public Point3D transform(Matrix m){
	    double w = 1;
	    double tX = (m.m[0][0] * x) + (m.m[0][1] * y) + (m.m[0][2] * z) + (m.m[0][3]);
	    double tY = (m.m[1][0] * x) + (m.m[1][1] * y) + (m.m[1][2] * z) + (m.m[1][3]);
	    double tZ = (m.m[2][0] * x) + (m.m[2][1] * y) + (m.m[2][2] * z) + (m.m[2][3]);
	    double tW = (m.m[3][0] * x) + (m.m[3][1] * y) + (m.m[3][2] * z) + (m.m[3][3]);
	
	    tX = tX / tW;
	    tY = tY / tW;
	    tZ = tZ / tW;
	
	    Point3D p = new Point3D(tX, tY, tZ);
	    return p;
	}
	
	public Vector3D vector(Point3D p){/* return the vector that is between this point and p.*/
	    return new Vector3D(p.x - this.x, p.y - this.y, p.z - this.z);
	}
	
	public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3){
	    //Takes the cross product of 2 vectors to return normal vector
	    Vector3D vA = p1.vector(p2);
	    Vector3D vB = p1.vector(p3);
	
	    double nX = (vA.y * vB.z) - (vA.z * vB.y);
	    double nY = (vA.z * vB.x) - (vA.x * vB.z);
	    double nZ = (vA.x * vB.y) - (vA.y * vB.x);
	
	    return new Vector3D(nX, nY, nZ);
	}
	
	public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn){
	    //Takes the dot product of normal of plane and vpn, returns true if positive
	    Vector3D vN = faceNormal(p1, p2, p3);
	
	    double dot = (vN.x * vpn.x) + (vN.y * vpn.y) + (vN.z * vpn.z);
	
	    if(dot < 0){
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public double distance(Point3D p){
	    double dX = Math.pow((this.x - p.x), 2);
	    double dY = Math.pow((this.y - p.y), 2);
	    double dZ = Math.pow((this.z - p.z), 2);
	
	    return Math.sqrt(dX + dY + dZ);
	}
	
	public String toString(){
	    System.out.println("x:" + x + " y:" + y + " z:" + z);
	    return "Done";
	}
	
	/*
	    Testing Main
	*/
	public static void main(String args[]){
	    System.out.println("Point Testing Main");
	    Point3D p = new Point3D(1, 1, 1);
	}
}
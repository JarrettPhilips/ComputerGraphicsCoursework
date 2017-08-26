package coursework;

/*
Jarrett Philips
Student ID: 160924936
Computer Graphics   Fall 2016
*/	

import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class GObject {
	/*
		Variables
	*/
	public Point3D[] vertex;
	public Face[] face;
	
	/*
		Constructors
	*/
	public GObject(Point3D[] v, Face[] f){
		this.vertex = v;
		this.face = f;
	}
	
	public GObject(String fileName){
		File file = new File(fileName);
	
	    try {
	        Scanner sc = new Scanner(file);
	   		vertex = new Point3D[sc.nextInt()];
	
	   		for(int i = 0; i < vertex.length; i ++){
	   			//System.out.println("i:" + i);
	   			float x = sc.nextFloat();
	   			float y = sc.nextFloat();
	   			float z = sc.nextFloat();
	
	   			vertex[i] = new Point3D(x, y, z);
	   		}
	
	   		face = new Face[sc.nextInt()];
	
	   		for(int i = 0; i < face.length; i ++){
	   			int index[] = new int[sc.nextInt()];
	
	   			for(int j = 0; j < index.length; j ++){
	   				index[j] = sc.nextInt();
	   			}
	
	   			Color c = new Color(sc.nextFloat(), sc.nextFloat(), sc.nextFloat());
	
	   			face[i] = new Face(index, c);
	   		}
	
	        sc.close();
	    } catch(FileNotFoundException e) {
	    	System.out.println("File not found: ");
	        e.printStackTrace();
	    }
	}
	
	/*
		Functions
	*/
	public void transform(Matrix m){
		Point3D[] tvertex = new Point3D[this.vertex.length];
	
		for(int i = 0; i < this.vertex.length; i ++){
			tvertex[i] = this.vertex[i].transform(m);
		}
	
		this.vertex = tvertex;
	}
	
	public String toString(){
		/* Make it look nice to save your debugging time! */
		System.out.println("=============");
		System.out.println("Vertices:");
		for(int i = 0; i < vertex.length; i ++){
			System.out.println("Vertex " + i + " | x:" + vertex[i].x + " y:" + vertex[i].y + " z:" + vertex[i].z);
		}
	
		System.out.println("===========");
		System.out.println("Faces: ");
		for(int i = 0; i < face.length; i ++){
			System.out.print("Face " + i + " | ");
	
			for(int j = 0; j < face[i].index.length; j ++){
				System.out.print(face[i].index[j] + " ");
			}
			System.out.print("\n");
	
			System.out.println("C: " + face[i].color);
		}
	
		return "Done";
	}
	
	/*
		Testing Main
	*/
	public static void main(String args[]){
		String fn = args[0];
		System.out.println("Attempting to read in file: " + fn);
		GObject gobj = new GObject(fn);
		gobj.toString();
	}
}
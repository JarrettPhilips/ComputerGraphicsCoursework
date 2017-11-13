/*
Jarrett Philips
Student ID: 160924936
Computer Graphics   Fall 2016
*/

import java.awt.*;
import java.util.Arrays;

public class Scene{
	/*
		Variables
	*/
	private GObject[] obj;
	
	/*
		Constructors 
	*/
	public Scene(String[] fileName){
		obj = new GObject[fileName.length];
	
		for(int i = 0; i < fileName.length; i ++){
			System.out.println("Loading file: " + fileName[i]);
			obj[i] = new GObject(fileName[i]);
		}
	}
	
	/*
		Functions
	*/
	public void transform(Matrix m){
		for(int i = 0; i < obj.length; i ++){
			obj[i].transform(m);
		}
	}
	
	public void draw(Camera c, Graphics g){
		//Orders the objects as to render properly
		GObject[] renderList = new GObject[obj.length];
		double[] highestZs = new double[obj.length];
	
		//Loops each obj
		for(int i = 0; i < obj.length; i ++){
			double highestZ = -10000000;
	
			//Loops each vertex
			for(int j = 0; j < obj[i].vertex.length; j ++){
				//Finds the highest z valued vertex in each object
				if(obj[i].vertex[j].z > highestZ){
					highestZ = obj[i].vertex[j].z;
					highestZs[i] = obj[i].vertex[j].z;
				}
			}
	
			//Performs an insert sort
			double temp;
			GObject tmpObj;
	        for (int k = 1; k < highestZs.length; k ++) {
	            for(int j = i ; j > 0; j --){
	                if(highestZs[j] < highestZs[j - 1]){
	                    temp = highestZs[j];
	                    tmpObj = obj[j];
	                    highestZs[j] = highestZs[j - 1];
	                    renderList[j] = renderList[j - 1];
	                    highestZs[j - 1] = temp;
	                    renderList[j - 1] = tmpObj;
	                }
	            }
	        }
		}
	
		//Loops each obj
		for(int i = 0; i <  obj.length; i++){
	
			Point3D [] vertices = obj[i].vertex;
	
			//Loops each face
			for(int j = 0; j < obj[i].face.length; j++){
				Face face = obj[i].face[j];
	
				int [] faceIndex = face.index;
	
				int [] xArr = new int[faceIndex.length];
				int [] yArr = new int[faceIndex.length];
	
				//Loops each vertex
				for(int k = 0; k < faceIndex.length; k++){
					Point3D p = vertices[faceIndex[k]];
	
					p = c.project(p);
	
					xArr[k] = (int) p.x;
					yArr[k] = (int) p.y;
				}
	
				g.setColor(face.color);
				//g.setStroke(new BasicStroke(10));
				Point3D p1 = c.projectionTransform(vertices[faceIndex[0]]);
				Point3D p2 = c.projectionTransform(vertices[faceIndex[1]]);
				Point3D p3 = c.projectionTransform(vertices[faceIndex[2]]);
				boolean isFrontFace = Point3D.isFrontFace(p1, p2, p3, c.getVPN());
	
				if(isFrontFace){
					g.fillPolygon(xArr, yArr, faceIndex.length);
				}
			}
		}
	}
	
	public String toString(){
		/* Make it look nice to save your debugging time! */
		return "Done";
	}
}

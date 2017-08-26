package coursework;

/*
Jarrett Philips
Student ID: 160924936
Computer Graphics   Fall 2016
*/

import java.awt.*;
import javax.swing.*;
import static java.lang.Math.*;

public class ParallelAnimator extends Animator {
	/*
	Variables
	*/
	private static final String[] files={"./pyramid.dat", "./cube.dat"};
	public boolean transformBoolean = true;
	
	/*
	Constructors
	*/
	public ParallelAnimator(){
	super();
	
	scene = new Scene(files);
	setupCamera();
	}
	
	/*
	Functions
	*/
	protected void setupCamera(){
	camera = new Camera(-5,5,-5,5);
	System.out.println("cufcs");
	}
	
	protected void animate(Graphics g){
	camera.setViewport(getWidth(),getHeight());
	
	if(g==null || scene==null || camera==null){
	  return;
	}
	
	Matrix mX=new Matrix(), mY=new Matrix(), mZ=new Matrix();
	if(transformBoolean){
	  mX.setRotationX(-PI/11);
	  mY.setRotationY(PI/13);
	  mZ.setRotationZ(PI/17); 
	  scene.transform(mZ.multiply(mY.multiply(mX))); 
	}
	
	//scene.transform(mY);
	scene.draw(camera, g);
	}
	
	/*
	Testing Main
	*/
	public static void main(String[] args) {
	new ParallelAnimator().loop();
	}
	
	private Scene scene;
	protected Camera camera;
}

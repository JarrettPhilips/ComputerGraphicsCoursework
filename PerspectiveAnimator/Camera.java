/*
    Jarrett Philips
    Student ID: 160924936
    Computer Graphics   Fall 2016
*/

public class Camera {

  /*
    Variables
  */
  private double xmin, xmax, ymin, ymax;
  private double fcp, bcp;  //NOT USED: front & back clippling planes
  private double ax, bx, ay, by;

  /*
    Constructors
  */
  public Camera(double xmin_, double xmax_, double ymin_, double ymax_){
    this.xmin = xmin_;
    this.xmax = xmax_;
    this.ymin = ymin_;
    this.ymax = ymax_;
  }

  /*
    Functions
  */
  public Vector3D getVPN(){
    /*return a vector that points towards the viewer. Used for face orientation*/
    return new Vector3D(0, 0, 1);
  }

  protected Point3D cameraTransform(final Point3D p){
    //p.toString();
    return new Point3D(p.x, p.y, p.z);
  }

  protected Point3D projectionTransform(final Point3D p){
    //This is where jacob had shit move
    return new Point3D(p.x, p.y, 0);
  } 

  private final Point3D viewportTransform(final Point3D p){
    return new Point3D((ax + bx*p.x), (ay + by*p.y), 0);
  }

  public final Point3D project(final Point3D p){
    Point3D temp=cameraTransform(p);
    temp=projectionTransform(temp);
    return viewportTransform(temp);
  }

  public void setViewport(int width, int height){
    //System.out.println("w:" + width + ", h:" + height);
    /*calculate ax, bx, ay, by*/
    //create new variables for vs and ws
    //vs are width and height
    //ws are given in camera
    double Vxmin = 0;
    double Vxmax = width;
    double Vymin = 0;
    double Vymax = height;
    double Wxmin = this.xmin;
    double Wxmax = this.xmax;
    double Wymin = this.ymin;
    double Wymax = this.ymax;

    double dVx = Vxmax - Vxmin;
    double dWx = Wxmax - Wxmin;
    double dVy = Vymax - Vymin;
    double dWy = Wymax - Wymin;

    //System.out.println("dVx: " + dVx + ", dVy: " + dVy + ", dWx: " + dWx + ", dWy: " + dWy);

    bx = dVx / dWx;
    ax = Vxmin - bx * Wxmin; 
    by = dVy / dWy;
    ay = Vymin - by * Wymin;

    //System.out.println("bx: " + bx + ", ax: " + ax + ", by: " + by + ", ay: " + ay);
  }

  public String toString(){/* Make it look nice to save your debugging time! */
    return "Done";
  }

  
}
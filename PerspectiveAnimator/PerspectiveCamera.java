/*
    Jarrett Philips
    Student ID: 160924936
    Computer Graphics   Fall 2016
*/

public class PerspectiveCamera extends Camera{
	
	/*
		Variables
	*/
	public Point3D cop = new Point3D(0,0,5); //centre of projection
	public Point3D vrp = new Point3D(0,0,0); //view reference point: the origin of camera coordinating system
	public Vector3D vpn = new Vector3D(0,0,1);
	public Vector3D vuv = new Vector3D(0,1,0);  //view plane normal (axis n) and the view up vector (axis v)
	public double fieldOfView = 1.5;
	public boolean usePerspectiveProjection = true;
	
	private Matrix m = new Matrix(); //camera transformation matrix
	{ m.setIdentity(); }
	
	/*
		Constructors
	*/
	public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_){
		super(xmin_, xmax_, ymin_, ymax_);
	}

	/*
		Functions
	*/
	public Vector3D getVPN(){
		return vpn;
	}

	protected Point3D projectionTransform(final Point3D p){	
		Vector3D n = new Vector3D(vpn.x / vpn.L2norm(), vpn.y / vpn.L2norm(), vpn.z / vpn.L2norm());
		Vector3D u1 = vuv.crossProduct(vpn);
		Vector3D u = new Vector3D(u1.x / u1.L2norm(), u1.y / u1.L2norm(), u1.z / u1.L2norm());
		Vector3D v = n.crossProduct(u); 

		Matrix matrix = new Matrix();
		matrix.m[0][0] = u.x;
		matrix.m[0][1] = u.y;
		matrix.m[0][2] = u.z;
		matrix.m[1][0] = v.x;
		matrix.m[1][1] = v.y;
		matrix.m[1][2] = v.z;
		matrix.m[2][0] = n.x;
		matrix.m[2][1] = n.y;
		matrix.m[2][2] = n.z;
		matrix.m[3][0] = 0;
		matrix.m[3][1] = 0;
		matrix.m[3][2] = 0;
		matrix.m[0][3] = -1 * ((u.x * vrp.x) + (u.y * vrp.y) + (u.z * vrp.z));
		matrix.m[0][3] = -1 * ((v.x * vrp.x) + (v.y * vrp.y) + (v.z * vrp.z));
		matrix.m[0][3] = -1 * ((n.x * vrp.x) + (n.y * vrp.y) + (n.z * vrp.z));
		matrix.m[3][3] = 1;

		p.transform(matrix);

		//Adds perspective distortion
		double x = p.x;
		double y = p.y;

		if(usePerspectiveProjection){
			x = p.x / ((p.z / this.cop.z) + fieldOfView);
			y = p.y / ((p.z / this.cop.z) + fieldOfView);
		}
		
		return new Point3D(x, y, 0);
	}

	public void setupCOP(Point3D cop_){
		this.cop = cop_;
	}

	protected Point3D cameraTransform(final Point3D p){
		return p;
	}

	public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vuv_){
		this.vrp = vrp_;
		this.vpn = vpn_;
		this.vuv = vuv_;
	}
}
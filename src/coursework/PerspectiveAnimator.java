package coursework;

/*
Jarrett Philips
Student ID: 160924936
Computer Graphics   Fall 2016
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PerspectiveAnimator extends ParallelAnimator{
	/*
		Constructors
	*/
	
	public PerspectiveAnimator(){
		super();
	    setUpOptionPanel();
	}
	
	/*
		Functions
	*/
	protected void setupCamera(){
		camera = new PerspectiveCamera(-5,5,-5,5);
		((PerspectiveCamera)camera).setupUVN(new Point3D(0,0,0), new Vector3D(0,0,1), new Vector3D(0,1,0));
		((PerspectiveCamera)camera).setupCOP(new Point3D(0,0,3));
	}
	
	/*
	    Sets up the option menu
	*/
	public void setUpOptionPanel(){
	    JFrame optionFrame = new JFrame();
	    optionFrame.setVisible(true);
	    optionFrame.setSize(400,100);
	    JPanel optionPanel = new JPanel();
	    JButton parallelButton = new JButton("Parallel Projection");
	    parallelButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e)
	        {
	            System.out.println("Switching to parallel projection");
	            ((PerspectiveCamera)camera).usePerspectiveProjection = false;
	        }
	    });
	    JButton perspectiveButton = new JButton("Perspective Projection");
	    perspectiveButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e)
	        {
	            System.out.println("Switching to perspective projection");
	            ((PerspectiveCamera)camera).usePerspectiveProjection = true;
	        }
	    });
	    JButton pauseButton = new JButton("Pause");
	    pauseButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e)
	        {
	            System.out.println("Pausing the animation");
	            setRunningFalse();
	        }
	    });
	    JButton playButton = new JButton("Play");
	    playButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e)
	        {
	            System.out.println("Playing the animation");
	            setRunningTrue();
	        }
	    });
	
	    optionPanel.setLayout(new GridBagLayout());
	    optionPanel.setSize(400,100);
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    optionPanel.add(parallelButton, c);
	    c.gridx ++;
	    optionPanel.add(perspectiveButton, c);
	    c.gridy ++;
	    c.gridx = 0;
	    optionPanel.add(playButton, c);
	    c.gridx ++;
	    optionPanel.add(pauseButton, c);
	
	    optionFrame.add(optionPanel);
	}
	
	/*
		Testing Main
	*/
	public static void main(String[] args){ 
		new PerspectiveAnimator().loop();
	}
}
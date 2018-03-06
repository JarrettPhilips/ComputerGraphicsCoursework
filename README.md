# Graphics Coursework
**Jarrett Philips**

### Overview
This is a basic rendering engine. It implements basic 3D objects from data files provided to it. It is built using only a library to draw polygons, so all of the translation and matrix math is done "by hand". It features clipping, movement, rotation, and most basic functions of a graphics engine. The **Perspective Animator** also runs a small window to allow the user to switch between Perspective and Parallel rendering. 

### Perspective Animator
This is a basic perspective (and parallel) rendering engine.

![Figure 1-1](https://github.com/JarrettPhilips/ComputerGraphicsCoursework/blob/master/ParallelImage.png?raw=true "Parallel Rendering")

To run, simply navigate to the directory of the java files, and use the makefile to compile the source code.
~~~~ 
>make
>java PerspectiveAnimator
~~~~

### Parallel Animator
This code is contained in the Perspective Animator, but I have included it's basic state here for completeness. This is a basic parallel rendering engine. 

![Figure 1-1](https://github.com/JarrettPhilips/ComputerGraphicsCoursework/blob/master/PerspectiveImage.png?raw=true "Parallel Rendering")


To run, simply navigate to the directory of the java files, and use the makefile to compile the source code.
~~~~ 
>make
>java ParallelAnimator
~~~~

### Dependencies
This engine is built from scratch, and only uses drawing libraries built into Java's Runtime Environment. No further dependencies are required.

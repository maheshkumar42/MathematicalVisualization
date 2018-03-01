Purpose of application:
This application demonstrates the Java capabilities to help visualize math and can be used for various students, universities, research project, companies etc.

Flow of application is as follows:
1. Application takes the input argument for the config xml file.
2. Parses the config xml file using the JAXB (Java Architecture for XML Binding) parser. In the background, xjc (binding compiler which compiles an XML schema file into fully annotated Java classes) has been used to generate the classes.
3. Once XML is parsed then it launches four scenes (one at a time) as follows:

a). Fly through object (animation):  
	* This demonstrates the visualization of the object using 3D animation.   
	* It gives the control to the user using mouse (i.e. to turn, rotate etc)  
	* Camera follows specific path.  
![alt text](https://github.com/comrench/MathematicalVisualization/blob/master/dist/sample/FlyThroughObject.jpg)

b). 2D Graph:  
	* This demonstrates the application capabilities to draw graphs in 2D.  
	* For example: Plotting the following equation	  
		y = x ( sin(x) )  
	* Once plotted, user can visualize the graph in the 2D or 3D.  
![alt text](https://github.com/comrench/MathematicalVisualization/blob/master/dist/sample/Graph2D.jpg)

c). 3D Graph:
	* This demonstrates the application capabilities to draw graph plots in 3D.  
	* For example: Plotting the following equation  
		z = x*x + y*y  
	* Once plotted, user can visualize the plotted graph in 3D.  
	* It gives the control to the user using mouse (i.e. to turn, rotate etc)  
![alt text](https://github.com/comrench/MathematicalVisualization/blob/master/dist/sample/Graph3D.jpg)

d). 3D Graph with animation:  
	* This demonstrates the application capabilities to draw complex equation with animations in 3D.  
	* For example: Plotting the following equation  
		cos(t*x)*sin(3*t*y)  
	* Once plotted, user can visualize the plotted animation in 3D.  
	* It gives the control to the user using mouse (i.e. to turn, rotate etc).  
![alt text](https://github.com/comrench/MathematicalVisualization/blob/master/dist/sample/Graph3D_Animation.jpg)


Eclipse version: Oxygen.2 Release (4.7.2)  
Java version: "1.8.0_162"  

Application uses Fxyz jar file as dependency.  

Usage:   
Usage   : java -jar <jarFile> <inputXmlFile>  
Example : java -jar mathematicalVisualization.jar etc/mathematicalVisualization.xml  
Once application is launched then press any key to change the scene.  
  
Sample execution run:  
C:\>java -jar bin/mathematicalVisualization.jar etc/mathematicalVisualization.xml  
Loading the configuation XML file: etc/mathematicalVisualization.xml  
Configuration XML loaded successfully.  
Configutation file read successfully.  
Launching the fly-through  
Completed successfully  
C:\>  
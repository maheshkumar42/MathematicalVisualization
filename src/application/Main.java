package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import support.LoadConfigXML;
import xml.MathVisualization;

/**
 * 
 * @author Mahesh Kumar
 * 
 * Purpose of application:
 * This application demonstrates the Java capabilities to help visualize math and can be used for various students, universities, research project, companies etc.
 * 
 * Flow of application is as follows:
 * 1. Application takes the input argument for the config xml file.
 * 2. Parses the config xml file using the JAXB (Java Architecture for XML Binding) parser. In the background, xjc (binding compiler which compiles an XML schema file into fully annotated Java classes) has been used to generate the classes.
 * 3. Once XML is parsed then it launches four scenes (one at a time) as follows:
 * 		a). Fly through object (animation): 
 * 			* This demonstrates the visualization of the object using 3D animation. 
 * 			* It gives the control to the user using mouse (i.e. to turn, rotate etc)
 * 			* Camera follows specific path.
 * 		b). 2D Graph:
 * 			* This demonstrates the application capabilities to draw graphs in 2D.
 * 			* For example: Plotting the following equation	
 * 				y = x ( sin(x) )
 * 			* Once plotted, user can visualize the graph in the 2D or 3D.
 * 		c). 3D Graph:
 * 			* This demonstrates the application capabilities to draw graph plots in 3D.
 * 			* For example: Plotting the following equation
 * 				z = x*x + y*y
 * 			* Once plotted, user can visualize the plotted graph in 3D.
 * 			* It gives the control to the user using mouse (i.e. to turn, rotate etc)
 * 		d). 3D Graph with animation:
 * 			* This demonstrates the application capabilities to draw complex equation with animations in 3D.
 * 			* For example: Plotting the following equation
 * 				cos(t*x)*sin(3*t*y)
 * 			* Once plotted, user can visualize the plotted animation in 3D.
 * 			* It gives the control to the user using mouse (i.e. to turn, rotate etc).
 *
 */

public class Main extends Application {
	
	public static MathVisualization mathVisualization;
	
	/*
	 * Starting point of JavaFX application. It initializes the various GUI classes and then launches the scene for fly-through class.
	 * @param primaryStage	Stage is to setup the scenes.
	 */
	@Override
	public void start(Stage primaryStage) {
		VisualMaths graph3dWithAnimations = new Graph3dWithAnimations(null);
		VisualMaths graph3d = new Graph3d(graph3dWithAnimations);
		VisualMaths graph2d = new Graph2d(graph3d);
		VisualMaths flyThroughAnimations = new FlyThroughAnimations(graph2d);
		
		System.out.println("Launching the fly-through");
		flyThroughAnimations.generateVisualMaths(primaryStage);
		System.out.println("Completed successfully");
	}
	
	/**
	 * This is the main method and also the starting point of the application
	 * @param args Initial arguments provided to the application.
	 */
	public static void main(String[] args) {
		mathVisualization = new LoadConfigXML().validateArguments(args);
		if(mathVisualization == null) {
			System.out.println("Issues parsing the XML.");
			return;
		}
		launch(args);
	}
}

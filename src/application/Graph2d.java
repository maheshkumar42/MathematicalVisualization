package application;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import support.Axis;
import support.Plot;
import xml.Argument;

/**
 * Class Graph2D
 * 
 * @author Mahesh Kumar
 * 
 * This demonstrates the application capabilities to draw graphs in 2D.
 * For example: Plotting the following equation	
 *		y = x ( sin(x) )
 * Once plotted, user can visualize the graph in the 2D or 3D.
 *
 */

public class Graph2d extends VisualMaths {
	
	/**

	 *  @param nextVisualMaths
	 */
	public Graph2d(VisualMaths nextVisualMaths) {
		super(nextVisualMaths);
		
		// Loading data from configuration XML file
		List<Argument> argumentList = mathXMLInput.getStage().getScene().get(sceneGraph2D).getArguments().getArgument();
		if(argumentList.get(0).getName().equalsIgnoreCase("axisDimensions"))
			axisDimensions = argumentList.get(0).getValue().intValue();
		if(argumentList.get(1).getName().equalsIgnoreCase("xAxisLow"))
			xAxisLow = argumentList.get(1).getValue().doubleValue();
		if(argumentList.get(2).getName().equalsIgnoreCase("xAxisHi"))
			xAxisHi = argumentList.get(2).getValue().doubleValue();
		if(argumentList.get(3).getName().equalsIgnoreCase("xAxisTickUnit"))
			xAxisTickUnit = argumentList.get(3).getValue().doubleValue();
		if(argumentList.get(4).getName().equalsIgnoreCase("yAxisLow"))
			yAxisLow = argumentList.get(4).getValue().doubleValue();
		if(argumentList.get(5).getName().equalsIgnoreCase("yAxisHi"))
			yAxisHi = argumentList.get(5).getValue().doubleValue();
		if(argumentList.get(6).getName().equalsIgnoreCase("yAxisTickUnit"))
			yAxisTickUnit = argumentList.get(6).getValue().doubleValue();
		if(argumentList.get(7).getName().equalsIgnoreCase("zAxisLow"))
			zAxisLow = argumentList.get(7).getValue().doubleValue();
		if(argumentList.get(8).getName().equalsIgnoreCase("zAxisHi"))
			zAxisHi = argumentList.get(8).getValue().doubleValue();
		if(argumentList.get(9).getName().equalsIgnoreCase("zAxisTickUnit"))
			zAxisTickUnit = argumentList.get(9).getValue().doubleValue();
		if(argumentList.get(10).getName().equalsIgnoreCase("plotLow"))
			plotLow = argumentList.get(10).getValue().doubleValue();
		if(argumentList.get(11).getName().equalsIgnoreCase("plotHi"))
			plotHi = argumentList.get(11).getValue().doubleValue();
		if(argumentList.get(12).getName().equalsIgnoreCase("xPlotIncrement"))
			xPlotIncrement = argumentList.get(12).getValue().doubleValue();
	}

	public int axisDimensions = 400;										// default value
    double xAxisLow = -8; double xAxisHi = 8; double xAxisTickUnit = 1;		// default value
    double yAxisLow = -8; double yAxisHi = 8; double yAxisTickUnit = 1;		// default value
    double zAxisLow = -8; double zAxisHi = 8; double zAxisTickUnit = 1;		// default value
    double plotLow = -8; double plotHi = 8; double xPlotIncrement = 0.1;	// default value

    @Override
    void generateVisualMaths(Stage stage) {
        Axis axis = new Axis(
                axisDimensions, axisDimensions , axisDimensions,
                xAxisLow, xAxisHi, xAxisTickUnit,
                yAxisLow, yAxisHi, yAxisTickUnit,
                zAxisLow, zAxisHi, zAxisTickUnit
        );
        Plot plot = new Plot(
                x -> x*Math.sin(x),
                plotLow, plotHi, xPlotIncrement,
                axis
        );
        plot.getTransforms().addAll(rotateX, rotateY);
        
        StackPane layout = new StackPane(plot);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgb(35, 39, 50)");

        stage.setTitle("y = x.(sin(x))");
        Scene scene = new Scene(layout, windowWidth, windowHeight, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new ParallelCamera());

        setMouseActivity(scene, stage);
		stage.setScene(scene);
        stage.show();
    }
}


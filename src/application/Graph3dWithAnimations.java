package application;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.fxyz3d.scene.paint.Patterns.CarbonPatterns;
import org.fxyz3d.shapes.primitives.SurfacePlotMesh;
import org.fxyz3d.utils.CameraTransformer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import xml.Argument;

/**
 * Class Graph3dWithAnimations
 * 
 * @author Mahesh Kumar
 * 
 * This demonstrates the application capabilities to draw complex equation with animations in 3D.
 * For example: Plotting the following equation
		cos(t*x)*sin(3*t*y)
 * Once plotted, user can visualize the plotted animation in 3D.
 * It gives the control to the user using mouse (i.e. to turn, rotate etc).
 *
 */

public class Graph3dWithAnimations extends VisualMaths{
	
    public Graph3dWithAnimations(VisualMaths nextVisualMaths) {
		super(nextVisualMaths);

		// Loading data from configuration XML file
		List<Argument> argumentList = mathXMLInput.getStage().getScene().get(sceneGraph3DAnim).getArguments().getArgument();
		if(argumentList.get(0).getName().equalsIgnoreCase("rangeX"))
			rangeX = argumentList.get(0).getValue().doubleValue();
		if(argumentList.get(1).getName().equalsIgnoreCase("rangeY"))
			rangeY = argumentList.get(1).getValue().doubleValue();
		if(argumentList.get(2).getName().equalsIgnoreCase("divisionsX"))
			divisionsX = argumentList.get(2).getValue().intValue();
		if(argumentList.get(3).getName().equalsIgnoreCase("divisionsY"))
			divisionsY =argumentList.get(3).getValue().intValue();
		if(argumentList.get(4).getName().equalsIgnoreCase("functionScale"))
			functionScale = argumentList.get(4).getValue().intValue();
	}

	private double rangeX = 1;		// default value
    private double rangeY = 2;		// default value
    private int divisionsX = 100;	// default value
    private int divisionsY = 100;	// default value
    private double functionScale= 1;// default value
    private double time = 0;
    
    private BiFunction<Double, Double, Double> functionGenerator(double time) {
        return (pX, pY) -> {
            return Math.cos(time*pX)*Math.sin(3*time*pY);
        };
    }
    private Function<Point2D, Number> generateFunction(double time) {
        return (Point2D p) -> {
            return functionGenerator(time).apply(p.getX(), p.getY());
        };
    }
    
    @Override
    void generateVisualMaths(Stage stage) {
        Group sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, windowWidth, windowHeight, true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        
        //setup camera transform for rotational support
        CameraTransformer cameraTransform = new CameraTransformer();
        cameraTransform.setTranslate(0, 0, -8);
        cameraTransform.getChildren().add(camera);
        
        //add a Point Light for better viewing of the grid coordinate system
        PointLight light = new PointLight(Color.WHITE);
        cameraTransform.getChildren().add(light);
        light.setTranslateX(camera.getTranslateX());
        light.setTranslateY(camera.getTranslateY());
        light.setTranslateZ(camera.getTranslateZ());
        scene.setCamera(camera);
        
        Group group = new Group();
        group.getChildren().add(cameraTransform);
        
        SurfacePlotMesh surface = new SurfacePlotMesh(generateFunction(0), rangeX, rangeY, divisionsX, divisionsY, functionScale);
        surface.getTransforms().addAll(new Rotate(200, Rotate.X_AXIS),
                new Rotate(60, Rotate.Y_AXIS));
        surface.setCullFace(CullFace.NONE);
        surface.setTextureModePattern(CarbonPatterns.LIGHT_CARBON, 1.0d);
        surface.getTransforms().addAll(rotateX, rotateY);
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
            surface.setFunction2D(generateFunction(time));
            time += 0.005;
        });
        Timeline timeLine = new Timeline(keyFrame);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
                
        group.getChildren().addAll(surface);
        sceneRoot.getChildren().addAll(group);
        
        setMouseActivity(scene, stage);
        stage.setTitle("z = (cos(time.x)).(sin(3.time.y))");
        stage.setScene(scene);
        stage.show();
    }
}

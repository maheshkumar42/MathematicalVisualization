package application;

import java.util.List;

import org.fxyz3d.shapes.Spheroid;
import org.fxyz3d.utils.CameraTransformer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import xml.Argument;

/**
 * Class FlyThroughAnimations
 * 
 * @author Mahesh Kumar
 * 
 * This demonstrates the visualization of the object using 3D animation. 
 * It gives the control to the user using mouse (i.e. to turn, rotate etc)
 * Camera follows specific path.
 *
 */

public class FlyThroughAnimations extends VisualMaths{
	
	/**
	 * Constructor of the class to initialize the next instance to be presented for the scene. 
	 * @param nextVisualMaths Next instance of the VisualMaths's subclass.
	 */
    public FlyThroughAnimations(VisualMaths nextVisualMaths) {
		super(nextVisualMaths);
		
		// Loading data from configuration XML file
		List<Argument> argumentList = mathXMLInput.getStage().getScene().get(sceneFlyThrough).getArguments().getArgument();
		if(argumentList.get(0).getName().equalsIgnoreCase("cameraDistance"))
			cameraDistance = argumentList.get(0).getValue().doubleValue();
		if(argumentList.get(1).getName().equalsIgnoreCase("minorRadius"))
			minorRadius =argumentList.get(1).getValue().floatValue();
		if(argumentList.get(2).getName().equalsIgnoreCase("majorRadius"))
			majorRadius = argumentList.get(2).getValue().floatValue();
		if(argumentList.get(3).getName().equalsIgnoreCase("divisions"))
			divisions = argumentList.get(3).getValue().intValue();
	}

	private double cameraDistance = -1000;	// default value	
	private float minorRadius = 57.247364f;	// default value
	private float majorRadius = 302.44724f;	// default value
	private int divisions = 61;				// default value
    private int time = 0;
    private boolean animationRunning = true;  
    
    /**
     * Providing implementation of the override method to generate the Scene.
     * @param stage Stage to present the scene.
     */
    @Override
    void generateVisualMaths(Stage stage) {
        PerspectiveCamera camera;
        final CameraTransformer cameraTransform = new CameraTransformer();
        final Group root = new Group();
    
        Spheroid spheroid = new Spheroid(divisions, minorRadius, majorRadius, Color.WHITE);               
        spheroid.setDrawMode(DrawMode.LINE);
        spheroid.getTransforms().addAll(rotateX,rotateY);
        root.getChildren().add(spheroid);
        
        camera = new PerspectiveCamera(true);
        cameraTransform.setTranslate(0, 0, 0);
        cameraTransform.getChildren().addAll(camera);
        camera.setNearClip(0.1);
        camera.setFarClip(20000.0);
        camera.setFieldOfView(42);
        camera.setTranslateZ(cameraDistance);
        cameraTransform.ry.setAngle(-45.0);
        cameraTransform.rx.setAngle(-10.0);

        //add a Point Light for better viewing of the grid coordinate system
        PointLight light = new PointLight(Color.WHITE);
        cameraTransform.getChildren().add(light);
        light.setTranslateX(camera.getTranslateX());
        light.setTranslateY(camera.getTranslateY());
        light.setTranslateZ(camera.getTranslateZ());
        root.getChildren().add(cameraTransform);
        
        camera.setRotationAxis(Rotate.Z_AXIS);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), (ActionEvent event) -> {
        	camera.setRotate(camera.getRotate()+1);
        	spheroid.setRotate(spheroid.getRotate()+2);
        	camera.setTranslateY(camera.getTranslateY()+1);
        	camera.setTranslateZ(camera.getTranslateZ()+Math.sin(camera.getTranslateZ()));
            time += 0.0005;
        });
        Timeline timeLine = new Timeline(keyFrame);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
        
        Scene scene = new Scene(new StackPane(root), 1024, 668, true, SceneAntialiasing.BALANCED);
        scene.setCamera(camera);
        scene.setFill(Color.BLACK);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	if(animationRunning) {
            		timeLine.stop();
            	}else {
            		timeLine.play();
            	}
            	animationRunning = !animationRunning;
            }
        });
        
        setMouseActivity(scene, stage);
        stage.setTitle("Spheriod - fly through object");
        stage.setScene(scene);
        stage.show();
    }
}
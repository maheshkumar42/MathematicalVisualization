package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import xml.MathVisualization;

/*
 * 
 * 
 * Purpose of this class is to provided the generic functionality to classes extending it.
 */
/**
 * Class: VisualMaths (abstract)
 * 
 * @author Mahesh Kumar
 * 
 * Purpose of this class is to provided the generic functionality to classes extending it.
 *
 */

public abstract class VisualMaths extends StackPane{
	
	// abstract method which must be implemented by extending classes
	abstract void generateVisualMaths(Stage stage);
	
	protected double mousePosX, mousePosY;
	protected double mouseOldPosX, mouseOldPosY;
	protected final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
	protected final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
	protected int windowWidth = 800; // default value
	protected int windowHeight = 600; // default value
    protected final MathVisualization mathXMLInput = Main.mathVisualization;
    protected final int sceneFlyThrough = 0;
    protected final int sceneGraph2D = 1;
    protected final int sceneGraph3D = 2;
    protected final int sceneGraph3DAnim = 3;
    
    /**
     * Constructor for the abstract super class.
     * @param nextVisualMaths
     */
	public VisualMaths(VisualMaths nextVisualMaths) {
		this.nextVisualMaths = nextVisualMaths;
		windowWidth = mathXMLInput.getGeneric().getWindowSize().getWidth();
		windowHeight = mathXMLInput.getGeneric().getWindowSize().getHeight();
	}

	/**
	 * Purpose of this method is to provide mouse control over the GUI.
	 * @param scene This is the scene of the subclass for which GUI needs to be presented.
	 * @param stage This is the stage being used for all the scenes.
	 */
    public void setMouseActivity(Scene scene, Stage stage) {
		// mouse rotation
		scene.setOnMousePressed(coordinates -> {
			mouseOldPosX = coordinates.getSceneX();
			mouseOldPosY = coordinates.getSceneY();
		});
		
		scene.setOnMouseDragged(coordinates -> {
			mousePosX = coordinates.getSceneX();
			mousePosY = coordinates.getSceneY();
			rotateX.setAngle(rotateX.getAngle() - (mousePosY - mouseOldPosY));
			rotateY.setAngle(rotateY.getAngle() - (mousePosX - mouseOldPosX));
			mouseOldPosX = mousePosX;
			mouseOldPosY = mousePosY;
		});
		
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent keyEvent) {
				if(nextVisualMaths != null)
					nextVisualMaths.generateVisualMaths(stage);
				else
					stage.hide();
			}
        });
    }
    
	public VisualMaths nextVisualMaths;
}

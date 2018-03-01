package support;

import java.util.function.Function;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

/**
 * Class Axis
 * 
 * @author Mahesh Kumar
 * 
 * This generates the Plot for the Graph2D scene
 *
 */

public class Plot extends Pane {
    public Plot(
            Function<Double, Double> function,
            double xMin, double xMax, double xInc,
            Axis axis
    ) {
        Path path = new Path();
        path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(2);

        path.setClip(
                new Rectangle(
                        0, 0, 
                        axis.getPrefWidth(), 
                        axis.getPrefHeight()
                )
        );

        double x = xMin;
        double y = function.apply(x);

        path.getElements().add(
                new MoveTo(
                        mapX(x, axis), mapY(y, axis)
                )
        );

        x += xInc;
        while (x < xMax) {
            y = function.apply(x);
            path.getElements().add(
                    new LineTo(
                            mapX(x, axis), mapY(y, axis)
                    )
            );
            x += xInc;
        }
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axis.getPrefWidth(), axis.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        getChildren().setAll(axis, path);
    }

    private double mapX(double x, Axis axis) {
        double tx = axis.getPrefWidth() / 2;
        double sx = axis.getPrefWidth() / 
           (axis.getXAxis().getUpperBound() - 
            axis.getXAxis().getLowerBound());
        return x * sx + tx;
    }

    private double mapY(double y, Axis axis) {
        double ty = axis.getPrefHeight() / 2;
        double sy = axis.getPrefHeight() / 
            (axis.getYAxis().getUpperBound() - 
             axis.getYAxis().getLowerBound());
        return -y * sy + ty;
    }
}

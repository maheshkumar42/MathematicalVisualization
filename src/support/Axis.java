package support;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class Axis extends Pane {
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private NumberAxis zAxis;

    public Axis(
            int width, int height, int depth,
            double xLow, double xHi, double xTickUnit,
            double yLow, double yHi, double yTickUnit,
            double zLow, double zHi, double zTickUnit
    ) {
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        xAxis = new NumberAxis(xLow, xHi, xTickUnit);
        xAxis.setTranslateX(0);
        xAxis.setTranslateY(0);
        xAxis.setTranslateZ(0);
        xAxis.setSide(Side.BOTTOM);
        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        xAxis.setLayoutY(height / 2);

        yAxis = new NumberAxis(yLow, yHi, yTickUnit);
        yAxis.setSide(Side.LEFT);
        yAxis.setMinorTickVisible(false);
        yAxis.setPrefHeight(height);
        yAxis.layoutXProperty().bind(
            Bindings.subtract(
                (width / 2) + 1,
                yAxis.widthProperty()
            )
        );

        zAxis = new NumberAxis(zLow, zHi, zTickUnit);
        zAxis.setSide(Side.BOTTOM);
        zAxis.setMinorTickVisible(false);
        zAxis.setPrefWidth(depth);
        zAxis.setTranslateX(width/2);
        zAxis.setTranslateY(height/2);
        zAxis.setRotationAxis(Rotate.Y_AXIS);
        zAxis.setRotate(90);
        zAxis.layoutXProperty().bind(
            Bindings.subtract(
                (depth / 2) + 1,
                zAxis.widthProperty()
            )
        );
        
        getChildren().setAll(xAxis, yAxis, zAxis);
    }

    public NumberAxis getXAxis() {
        return xAxis;
    }

    public NumberAxis getYAxis() {
        return yAxis;
    }
}


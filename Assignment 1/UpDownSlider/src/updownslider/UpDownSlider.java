/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updownslider;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author gutwin
 */
public class UpDownSlider extends Pane {
    
    private double min, max;
    private DoubleProperty lowValue = new SimpleDoubleProperty(); // value in the widget's defined range
    private DoubleProperty highValue = new SimpleDoubleProperty(); // value in the widget's defined range
    private double lowHandle, highHandle;       // stored as 0.0 - 1.0
    private double lowHandleX, highHandleX;     // actual coordinates for drawing
    double height, width;
    double insideHeight, insideWidth;   // height and width minus 10 pixel border
    double handleRadius, handleDiameter;
    double inset;   // how much space to leave around the outside
    boolean highHandleMoving, lowHandleMoving;
    GraphicsContext gc;
    

    public UpDownSlider(double newMin, double newMax, double newLow, double newHigh) {
        min = newMin;
        max = newMax;
        setLowValue(newLow);
        setHighValue(newHigh);
        lowHandle = newLow/max;
        highHandle = newHigh/max;
        width = 500;
        height = 75;
        inset = 10;
        insideWidth = width - inset * 2;
        insideHeight = height - inset * 2;
        lowHandleX = lowHandle * insideWidth;
        highHandleX = highHandle * insideWidth;
        handleRadius = 8;
        handleDiameter = handleRadius * 2;
        highHandleMoving = false;
        lowHandleMoving = false;
        
        StackPane root = new StackPane();
        Canvas widgetCanvas = new Canvas(width, height);
        root.getChildren().add(widgetCanvas);
        gc = widgetCanvas.getGraphicsContext2D();

        widgetCanvas.setOnMousePressed(this::handleMousePressed);
        widgetCanvas.setOnMouseDragged(this::handleMouseDragged);
        widgetCanvas.setOnMouseReleased(this::handleMouseReleased);

        drawShapes();
        getChildren().add(root);
    }

    public void drawShapes() {
        // blank background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        // draw widget rectangle
        gc.setFill(Color.GREEN);
        gc.fillRect(inset, inset, insideWidth, insideHeight);
        // draw three-segment line
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(4);
        // bottom-left corner to first handle
        gc.strokeLine(inset, height - inset, lowHandleX, height - inset);
        // first handle to second handle
        gc.strokeLine(lowHandleX, height - inset, highHandleX, inset);
        // second handle to top-right corner
        gc.strokeLine(highHandleX, inset, width - inset, inset);
        // draw handles
        if (highHandleMoving) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.ORANGE);
        }
        gc.fillOval(highHandleX - handleRadius, inset - handleRadius, handleDiameter, handleDiameter);
        
        if (lowHandleMoving) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.ORANGE);
        }
        gc.fillOval(lowHandleX - handleRadius, height - inset - handleRadius, handleDiameter, handleDiameter);
    }

    public void handleMousePressed(MouseEvent e) {
        // check hit on bottom handle
        if (dist(e.getX(), e.getY(), lowHandleX, height - inset) < handleRadius) {
            lowHandleMoving = true;
        }
        if (dist(e.getX(), e.getY(), highHandleX, inset) < handleRadius) {
            highHandleMoving = true;
        }
        drawShapes();
    }

    public void handleMouseDragged(MouseEvent e) {
        if (highHandleMoving) {
            // high handle can't go lower than low handle, or above right edge
            if (e.getX() > lowHandleX && e.getX() <= width - inset) {
                highHandle = (e.getX() - inset) / insideWidth;
                highHandleX = e.getX();
                setHighValue(highHandle * max);
            }
        }
        if (lowHandleMoving) {
            // low handle can't go higher than high handle, or below left edge
            if (e.getX() < highHandleX && e.getX() >= inset) {
                lowHandle = (e.getX() - inset) / insideWidth;
                lowHandleX = e.getX();
                setLowValue(lowHandle * max);
            }
        }
        drawShapes();
    }

    public void handleMouseReleased(MouseEvent e) {
        lowHandleMoving = false;
        highHandleMoving = false;
        drawShapes();
    }

    public double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public final void setLowValue(double d) {
        lowValueProperty().set(d);
    }
    
    public final double getLowValue() {
        return lowValue.get();
    }

    public final DoubleProperty lowValueProperty() {
        return lowValue;
    }
    
    public final void setHighValue(double d) {
        highValueProperty().set(d);
    }
    
    public final double getHighValue() {
        return highValue.get();
    }

    public final DoubleProperty highValueProperty() {
        return highValue;
    }
}

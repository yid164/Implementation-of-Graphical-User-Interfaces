// Name: Yinsheng Dong
// Student Number: 11148648
// NSID: yid164
// Lecutre: CMPT 381

package pkg381a1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class is the pane of the up down sliders, drawing widgets, and set the events
 * 
 */
public class UpDownSliderPane extends Pane {
    
    // the boolean of the clicked top and down actions
    boolean clickedTop;
    boolean clickedDown;
    
    // the boolean of the draged top and down actions
    boolean dragTop;
    boolean dragDown;
    
    // the position of the top and down mouse draged or clicked
    static double clickTopX;
    static double clickDownX;
    
    // the graphics contexts of top, down and mid areas
    GraphicsContext gcForTop;
    GraphicsContext gcForDown;
    GraphicsContext gcForMid;
    
    // the event handler
    EventHandler<ActionEvent> handler;
    
    // the cnavas of top, down and mid areas
    Canvas TopCanvas;
    Canvas DownCanvas;
    Canvas midLine;
    
    // the max and min value of the mouse events
    double maxValue = 500;
    double minValue = 0;
    
    // invoke the UpDownSlider, and get the top and down X posistions
    UpDownSlider upDownSlider = new UpDownSlider();
    static double downX ;
    static double topX ;
    
    /**
     *  to draw top canvas of the window
     */
    private void drawCanvasContentForTop()
    {
        // if the action is click or drag, use the top graphic context to draw the red point and stroken line
        // and use the mid graphic context to draw the line for up to down
        if(clickedTop || dragTop)
        {
            gcForMid.clearRect(0, 0, 500, 75);
            gcForMid.setStroke(Color.YELLOW);
            gcForMid.setLineWidth(4);
            gcForMid.strokeLine(clickTopX, 9,downX, 67);
            gcForTop.clearRect(0, 0, 500, 30);
            gcForTop.setStroke(Color.YELLOW);
            gcForTop.setLineWidth(4);
            gcForTop.strokeLine(500, 9, clickTopX+5,9);
            gcForTop.setFill(Color.RED);
            gcForTop.fillOval(clickTopX, 2, 15, 15);
            
        }
        
        // if the action is either not clicked or drag (or down slider value > up slider value, stop the cnavas) 
        else if(!clickedTop || !dragTop)
        {
            topX = clickTopX;
            downX = clickDownX;
            gcForMid.clearRect(0, 0, 500, 75);
            gcForMid.setStroke(Color.YELLOW);
            gcForMid.setLineWidth(4);
            gcForMid.strokeLine(topX, 9,downX, 67);
            gcForTop.clearRect(0, 0, 500, 30);
            gcForTop.setStroke(Color.YELLOW);
            gcForTop.setLineWidth(4);
            gcForTop.strokeLine(500, 9, topX+5, 9);
            gcForTop.setFill(Color.ORANGE);
            gcForTop.fillOval(topX, 2, 15, 15);
        }
        
        // if the action is from start
        else
        {
            gcForTop.clearRect(0, 0, 500, 30);
            gcForTop.setStroke(Color.YELLOW);
            gcForTop.setLineWidth(4);
            gcForTop.strokeLine(500, 9, topX+5, 9);
            gcForTop.setFill(Color.ORANGE);
            gcForTop.fillOval(topX, 2, 15, 15);
        }

    }
    
    // to drag the widets of down window
    private void drawCanvasContentForDown()
    {
        // if the action is click or drag, use the down graphic context to draw the red point and stroken line
        // and use the mid graphic context to draw the line for up to down
        if(clickedDown || dragDown)
        {
            gcForMid.clearRect(0, 0, 500, 75);
            gcForMid.setStroke(Color.YELLOW);
            gcForMid.setLineWidth(4);
            gcForMid.strokeLine(topX, 9, clickDownX, 67);
            gcForDown.clearRect(0, 0, 500, 45);
            gcForDown.setStroke(Color.YELLOW);
            gcForDown.setLineWidth(4);
            gcForDown.strokeLine(0, 37, clickDownX,37);
            gcForDown.setFill(Color.RED);
            gcForDown.fillOval(clickDownX, 30, 15, 15);
        }
        
        // if the action is either not clicked or drag (or down slider value > up slider value, stop the canvas) 
        else if(!clickedDown || !dragDown)
        {
            topX = clickTopX;
            downX = clickDownX;
            gcForMid.clearRect(0, 0, 500, 75);
            gcForMid.setStroke(Color.YELLOW);
            gcForMid.setLineWidth(4);
            gcForMid.strokeLine(topX, 9, downX, 67);
            gcForDown.clearRect(0, 0, 500, 75);
            gcForDown.setStroke(Color.YELLOW);
            gcForDown.setLineWidth(4);
            gcForDown.strokeLine(0, 37, downX,37);
            gcForDown.setFill(Color.ORANGE);
            gcForDown.fillOval(downX, 30, 15, 15);
        }
        // if the action is from start
        else
        {
            gcForDown.clearRect(0, 0, 500, 75);
            gcForDown.setStroke(Color.YELLOW);
            gcForDown.setLineWidth(4);
            gcForDown.strokeLine(0, 37, downX,37);
            gcForDown.setFill(Color.ORANGE);
            gcForDown.fillOval(downX, 30, 15, 15);
        }

    }
    
    // the handler for user drag top widgets, get mouse event
    public void handleMouseDragedForTop (MouseEvent e)
    {
        downX = clickDownX;
        if(e.getX() <= downX)
        {
            topX = downX;
            dragTop = false;
            upDownSlider.getUpSlider().setValue(topX / 500 * upDownSlider.getUpSlider().getMax());
        }
        else if(e.getX()>=maxValue)
        {
            dragTop = false;
            upDownSlider.getUpSlider().setValue(maxValue / 500 * upDownSlider.getUpSlider().getMax());
        }
        else
        {
            clickTopX = e.getX();
            topX = e.getX();
            upDownSlider.getUpSlider().setValue(clickTopX / 500 * upDownSlider.getUpSlider().getMax());
            dragTop = true;
        }

        drawCanvasContentForTop();
    }
    
    // the handler for user drag down widgets, get mouse event
    public void handleMouseDragedForDown (MouseEvent e)
    {
        topX = clickTopX;
        if(e.getX()>= topX)
        {
            dragDown = false;
            downX = clickTopX;
            upDownSlider.getDownSlider().setValue(downX / 500 * upDownSlider.getDownSlider().getMax());
        }
        else if(e.getX()>=maxValue)
        {
            dragDown = false;
            upDownSlider.getDownSlider().setValue(maxValue / 500 * upDownSlider.getDownSlider().getMax());
        }
        else
        {
            clickDownX = e.getX();
            downX = e.getX();
            upDownSlider.getDownSlider().setValue(clickDownX / 500 * upDownSlider.getDownSlider().getMax());
            dragDown = true;
        }
        drawCanvasContentForDown();
    }
    
    
    // the handler of user do the click top window widets action, get mouse event
    public void handleMouseClickedForTop(MouseEvent e)
    {
        downX = clickDownX;
        if(e.getX()<= downX)
        {
            clickedTop = false;
            topX = downX;
            upDownSlider.getUpSlider().setValue(topX / 500 * upDownSlider.getUpSlider().getMax());
        }
        else if(e.getX()>=maxValue)
        {
            clickedTop = false;
            topX = downX;
            upDownSlider.getUpSlider().setValue(maxValue / 500 * upDownSlider.getUpSlider().getMax());
        }
        else
        {
            clickTopX = e.getX();
            topX = e.getX();
            upDownSlider.getUpSlider().setValue(clickTopX / 500 * upDownSlider.getUpSlider().getMax());
            clickedTop = true;
        }
        drawCanvasContentForTop();
    }
    
    // the handler of user do the click down side window widets action, get mouse event
    public void handleMouseClickedForDown(MouseEvent e)
    {
        topX = clickTopX;
        if(e.getX()>= topX)
        {
            clickedDown = false;
            downX = clickTopX;
            upDownSlider.getDownSlider().setValue(downX / 500 * upDownSlider.getDownSlider().getMax());
        }
        else if(e.getX()>=maxValue)
        {
            clickedDown = false;
            downX = topX;
            upDownSlider.getDownSlider().setValue(maxValue / 500 * upDownSlider.getDownSlider().getMax());
        }
        else
        {
            clickDownX = e.getX();
            downX = e.getX();
            upDownSlider.getDownSlider().setValue(clickDownX / 500 * upDownSlider.getDownSlider().getMax());
            clickedDown = true;
        }
        drawCanvasContentForDown();
    }
    
    
    // the upper side of pane
    public Pane UpPane()
    {
        topX = upDownSlider.getUpSlider().getValue() / upDownSlider.getUpSlider().getMax() * 500;
        clickTopX = upDownSlider.getUpSlider().getValue() / upDownSlider.getUpSlider().getMax() * 500;
        clickedTop = false;
        dragTop = false;
        StackPane root = new StackPane();
        TopCanvas = new Canvas(500,30);
        gcForTop = TopCanvas.getGraphicsContext2D();
        root.getChildren().add(TopCanvas);
        TopCanvas.setOnMouseClicked(this::handleMouseClickedForTop);
        TopCanvas.setOnMouseDragged(this::handleMouseClickedForTop);
        
        drawCanvasContentForTop();
        getChildren().add(root);
        return root;
    }
    
    // the lower side of the pane
    public Pane DownPane()
    {
        downX = upDownSlider.getDownSlider().getValue() / upDownSlider.getDownSlider().getMax() * 500;
        clickDownX = upDownSlider.getDownSlider().getValue() / upDownSlider.getDownSlider().getMax() * 500;
        clickedDown = false;
        dragDown = false;
        StackPane root = new StackPane();
        DownCanvas = new Canvas(500,45);
        gcForDown = DownCanvas.getGraphicsContext2D();
        root.getChildren().add(DownCanvas);
        DownCanvas.setOnMouseClicked(this::handleMouseClickedForDown);
        DownCanvas.setOnMouseDragged(this::handleMouseDragedForDown);
        drawCanvasContentForDown();
        getChildren().add(root);
        return root;
    }
    
    // the totol pane, added all 3 canvas in a Vbox layout, and add it to the scence
    public UpDownSliderPane()
    {       

        midLine =new Canvas(500,75);
        gcForMid = midLine.getGraphicsContext2D();
        gcForMid.setStroke(Color.YELLOW);
        gcForMid.setLineWidth(4);
        gcForMid.strokeLine(topX,9,downX,67);
        VBox root = new VBox();
        upDownSlider.label = new Label();
        upDownSlider.label.setText(upDownSlider.getDownSlider().getValue() + " -> "+ upDownSlider.getUpSlider().getValue());
        root.getChildren().addAll(UpPane(),DownPane(), upDownSlider.label);
        getChildren().addAll(midLine,root);
    }
    
}

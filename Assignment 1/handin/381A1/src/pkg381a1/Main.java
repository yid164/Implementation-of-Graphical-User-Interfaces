/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg381a1;

import java.util.HashSet;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author yid164 (Yinsheng Dong)
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        UpDownSliderPane upDownSlider = new UpDownSliderPane();
        Canvas canvas = new Canvas(500,75);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,500,75);
        gc.setFill(Color.GREEN);
        gc.fillRect(10, 8, 480, 60);
        VBox layout1 = new VBox();
        layout1.getChildren().add(canvas);
        
        VBox layout2 = new VBox();
        layout2.getChildren().add(upDownSlider);
        
        StackPane layout3 = new StackPane();
        layout3.getChildren().addAll(layout1,layout2);
        
        
        // Slider for Q1, set a new slider, min: 150.0, max: 1000.0, value: 275.0
        Slider slider = new Slider(150.0, 1000.0,275.0);
        // set new label to show the slider value of Q1
        Label label = new Label();
        label.setText(String.format("%.1f", slider.getValue()));
        // set the slider value property to change the label text if slider changed value
        slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label.setText(String.format("%.1f",newValue));
            }
        });
        // set the button of Q1, use it to close the window
        Button button = new Button("Quit");
        button.setOnAction(e -> primaryStage.close());
        
        // use the Vbox layout to add all of Q1 item
        VBox layout = new VBox();
        layout.getChildren().addAll(slider, label, button);
        
        // use the VBox layout to add all of Q1 and Q2 layout
        VBox layoutF = new VBox();
        layoutF.getChildren().addAll(layout3, layout);
        
        
        
        
        Scene scene = new Scene(layoutF, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

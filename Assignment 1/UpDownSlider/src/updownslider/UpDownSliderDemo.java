/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updownslider;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author gutwin
 */
public class UpDownSliderDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        // Create widgets
        Button quitButton = new Button("Quit");
        Slider range = new Slider(150, 1000, 275);
        Label sliderOutput = new Label("275.0");
        sliderOutput.fontProperty().set(Font.font(24));
        UpDownSlider uds = new UpDownSlider(0, 1200, 325, 800);
        Label output = new Label("325.0 -> 800.0");
        output.fontProperty().set(Font.font(24));
        
        // Set event handlers
        quitButton.setOnAction(event -> Platform.exit());
        uds.lowValueProperty().addListener(
                (ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
                    output.setText(String.format("%.1f -> %.1f", newVal, uds.getHighValue()));
                });
        uds.highValueProperty().addListener(
                (ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
                    output.setText(String.format("%.1f -> %.1f", uds.getLowValue(), newVal));
                });
        range.valueProperty().addListener(
                (ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
                    sliderOutput.setText(String.format("%.1f", newVal));
                });

        // add to layout
        VBox root = new VBox();
        root.getChildren().add(uds);
        root.getChildren().add(output);
        root.getChildren().add(range);
        root.getChildren().add(sliderOutput);
        root.getChildren().add(quitButton);

        // set up window
        Scene scene = new Scene(root);
        primaryStage.setTitle("Up-Down Slider Demo");
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

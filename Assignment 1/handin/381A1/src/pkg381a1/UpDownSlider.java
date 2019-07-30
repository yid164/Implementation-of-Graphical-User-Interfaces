// Name: Yinsheng Dong
// Student Number: 11148648
// NSID: yid164
// Lecutre: CMPT 381
package pkg381a1;




import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * This class is only the 2 sliders and a label for to control widgets and get view
 * 
 */
public class UpDownSlider {
    
    // UPSLIDER: the slider which control the top widgets of the window
    private Slider UPSLIDER;
    // DOWNSLIDER: the slider which control the down widgets of the window
    private Slider DOWNSLIDER;
    // label: show the value of UPSLIDER AND DOWNSLIDER
    Label label;


    // interface of the label
    public Label getLabel()
    {
        return label;
    }
    
    // interface of change the label

    
    
    // interface of the UPSLIDER, and set the label
    public Slider getUpSlider()
    {
        UPSLIDER = new Slider();
        UPSLIDER.setMax(1200);
        UPSLIDER.setMin(0);
        UPSLIDER.setValue(800);
        UPSLIDER.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(String.format("%.1f", DOWNSLIDER.getValue())+" -> " + String.format("%.1f",newValue));
            if(DOWNSLIDER.getValue()>=UPSLIDER.getValue())
            {
                UPSLIDER.setValue(DOWNSLIDER.getValue());
                
            }
        });
        return UPSLIDER;
       
    }
    
    
    // interface if the DOWNSLIDER, and set the label
    public Slider getDownSlider()
    {
        DOWNSLIDER = new Slider();
        DOWNSLIDER.setMax(1200);
        DOWNSLIDER.setMin(0);
        DOWNSLIDER.setValue(325);
        DOWNSLIDER.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(String.format("%.1f", newValue) + " -> " + String.format("%.1f", UPSLIDER.getValue()));
            if (DOWNSLIDER.getValue() >= UPSLIDER.getValue())
            {
                DOWNSLIDER.setValue(UPSLIDER.getValue());
            }
        });
        
        return DOWNSLIDER;
    }
    

 
}

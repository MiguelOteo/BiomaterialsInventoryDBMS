package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ClientMainMenuController implements Initializable{
	
	 final Image image_1 = new Image("file:client-categories.png"); 
	 final Image image_0 = new Image("file:client-main.png"); 
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private ImageView image_view;
	@FXML
	private ImageView image_view_hover;
	
	// -----> ESSENTIAL METHODS <-----
	
    public ClientMainMenuController() {
    	// TODO Auto-generated constructor stub
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		KeyFrame kf1 = new KeyFrame(Duration.seconds(5), new KeyValue(image_view_hover.opacityProperty(), 0));
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5), new KeyValue(image_view_hover.opacityProperty(), 0.2));
        KeyFrame kf3 = new KeyFrame(Duration.seconds(0.5), new KeyValue(image_view_hover.opacityProperty(), 0.4));
        Timeline timelineOn = new Timeline(kf1, kf2, kf3);
        timelineOn.setCycleCount(Timeline.INDEFINITE);
        timelineOn.play();
	}	
}

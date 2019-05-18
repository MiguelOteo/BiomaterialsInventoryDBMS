package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ClientMainMenuController implements Initializable{
	
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
		
		PauseTransition wait = new PauseTransition(Duration.seconds(5));
		wait.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FadeTransition fadeTransition  = new FadeTransition(Duration.seconds(1.5), image_view);
				fadeTransition.setFromValue(1.0);
				fadeTransition.setToValue(0.0);
				fadeTransition.play();
			}
        });
		wait.play();
	}	
}

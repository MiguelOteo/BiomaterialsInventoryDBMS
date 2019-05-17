package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ClientMainMenuController implements Initializable{
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private ImageView image_view;
	
	// -----> ESSENTIAL METHODS <-----
	
    public ClientMainMenuController() {
    	// TODO Auto-generated constructor stub
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PauseTransition wait = new PauseTransition(Duration.seconds(4));
		Image image = new Image("file:src.IconPictures/client_categories.png");
		wait.setOnFinished((event_handler) -> image_view.setImage(image));
		wait.play();
	}
}

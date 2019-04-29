package db.UImenuFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FeaturesController {

    @FXML
    private AnchorPane account_window;
    @FXML
    private JFXButton done_button;
    @FXML
    private ImageView close_button;
    @FXML
    private JFXTreeTableView<?> utility_tree_view;
    @FXML
    private JFXTreeTableView<?> maintenance_tree_view;

    
    @FXML
    void close_window(MouseEvent event) {
    	Stage stage = (Stage) this.account_window.getScene().getWindow();
    	stage.close();
    }

}

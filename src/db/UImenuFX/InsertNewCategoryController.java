package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Benefits;
import db.pojos.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class InsertNewCategoryController implements Initializable{

	// -----> CLASS ATRIBUTES <-----
	
	@SuppressWarnings("unused")
	private static SQLManager SQL_manager_object;
	private static JPAManager JPA_manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Stage stage_window;
	@FXML
	private AnchorPane account_window;
	@FXML
	private Label minimum_points;
	@FXML
	private Integer minimum;
	@FXML
	private JFXButton insert_category_button;
	@FXML
	private JFXButton insert_benefits_button;
	@FXML
	private JFXTextField category_name;
	@FXML
	private JFXTextField maximum_points;
	@FXML
	private JFXTreeTableView<BenefitsListObject> benefits_tree_view;
	@FXML
	private final ObservableList<BenefitsListObject> benefits_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----
	
	public InsertNewCategoryController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(JPAManager JPA_manager, SQLManager SQL_manager) {
		JPA_manager_object = JPA_manager;
		SQL_manager_object = SQL_manager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<Category> categories_list = JPA_manager_object.List_all_categories();
		Category max_category = categories_list.get(0);
		for(Category category: categories_list) {
			if(category.getMaximum() > max_category.getMaximum()) {
				max_category = category;
			}
		}
		minimum = max_category.getMaximum() + 1;
		minimum_points.setText(minimum.toString());
		
		// Transaction list columns creation
		JFXTreeTableColumn<BenefitsListObject, String> benefits_id = new JFXTreeTableColumn<>("Benefit ID");
		benefits_id.setPrefWidth(170);
		benefits_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BenefitsListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BenefitsListObject, String> param) {
				return param.getValue().getValue().benefit_id;
			}
		});
		benefits_id.setResizable(false);
				
		JFXTreeTableColumn<BenefitsListObject, String> percentage = new JFXTreeTableColumn<>("Percentage");
		percentage.setPrefWidth(170);
		percentage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BenefitsListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BenefitsListObject, String> param) {
				return param.getValue().getValue().percentage;
			}
		});
		percentage.setResizable(false);
				
		JFXTreeTableColumn<BenefitsListObject, String> extra_units = new JFXTreeTableColumn<>("Extra units");
		extra_units.setPrefWidth(170);
		extra_units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BenefitsListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<BenefitsListObject, String> param) {
				return param.getValue().getValue().extra_units;
			}
		});
		extra_units.setResizable(false);
			
		List<Benefits> benefits_list = JPA_manager_object.List_all_benefits();
		for (Benefits benefit: benefits_list) {
			benefits_objects.add(new BenefitsListObject(benefit.getBenefits_id().toString(), benefit.getPercentage().toString()
					, benefit.getExtra_units().toString()));
		}
		TreeItem<BenefitsListObject> root = new RecursiveTreeItem<BenefitsListObject>(benefits_objects, RecursiveTreeObject::getChildren);
		benefits_tree_view.setPlaceholder(new Label("No benefits found"));
		benefits_tree_view.getColumns().setAll(benefits_id, percentage, extra_units);
		benefits_tree_view.setRoot(root);
		benefits_tree_view.setShowRoot(false);
	}
	
	// -----> BUTTON METHODS <-----
	
	public void close_window() {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void Insert_category() {
		TreeItem<BenefitsListObject> benefit_object = benefits_tree_view.getSelectionModel().getSelectedItem();
		if(benefit_object != null) {
			try {
				String name = category_name.getText();
				Integer maximum = Integer.parseInt(maximum_points.getText());
				if(!(name.equals("")) && maximum > minimum) {
					Category category = new Category();
					category.setCategory_name(name);
					category.setMinimum(minimum);
					category.setMaximum(maximum);
					category.setPenalization(minimum/4);
					System.out.println(Integer.parseInt(benefit_object.getValue().benefit_id.getValue()));
					category.setBenefits(JPA_manager_object.Search_benefits_by_id(Integer.parseInt(benefit_object.getValue().benefit_id.getValue())));
					JPA_manager_object.Insert_new_category(category);
					minimum = maximum + 1;
					minimum_points.setText(minimum.toString());
					category_name.setText("");
					maximum_points.setText("");
				} else {
					category_name.setText("");
					maximum_points.setText("");
				}
			} catch (NumberFormatException | NullPointerException insertion_error) {
				category_name.setText("");
				maximum_points.setText("");
			}
		}
	}
	
	@FXML
	public void Insert_benefit() {
		try {
			InsertNewBenefitController.setValues(JPA_manager_object);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertNewBenefitView.fxml"));
			Parent root = (Parent) loader.load();
			InsertNewBenefitController benefit_controller = new InsertNewBenefitController();
			stage_window = new Stage();
			stage_window.initStyle(StageStyle.UNDECORATED);
			stage_window.setScene(new Scene(root));
			stage_window.setAlwaysOnTop(true);				
			stage_window.setOnShowing(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					stage_window.initModality(Modality.APPLICATION_MODAL);
				}
			});
			stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
				@Override
				public void handle(WindowEvent event) {	
					benefits_objects.clear();
					List<Benefits> benefits_list = JPA_manager_object.List_all_benefits();
					for (Benefits benefit: benefits_list) {
						benefits_objects.add(new BenefitsListObject(benefit.getBenefits_id().toString(), benefit.getPercentage().toString()
								, benefit.getExtra_units().toString()));
					}
					TreeItem<BenefitsListObject> root = new RecursiveTreeItem<BenefitsListObject>(benefits_objects, RecursiveTreeObject::getChildren);
					benefits_tree_view.refresh();
				}
			});	
			stage_window.show();
		} catch (IOException charging_error) {
			
		}
	}
}

//-----> BENEFITS LIST CLASS <-----

//To insert columns into the list of transactions with all the information
class BenefitsListObject extends RecursiveTreeObject<BenefitsListObject> {
	
	StringProperty benefit_id;
	StringProperty percentage;
	StringProperty extra_units;
	
	public BenefitsListObject(String benefit_id, String percentage, String extra_units) {
		this.benefit_id = new SimpleStringProperty(benefit_id);
		this.percentage = new SimpleStringProperty(percentage);
		this.extra_units = new SimpleStringProperty(extra_units);
	}
}

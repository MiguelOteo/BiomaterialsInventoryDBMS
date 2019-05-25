package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.jpa.JPAManager;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class AddCategoryController implements Initializable{

	// -----> CLASS ATRIBUTES <-----
	
	private static JPAManager JPA_manager_object;
	private static SQLManager SQL_manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Stage stage_window;
	@FXML
	private Pane main_pane;
	@FXML
	private JFXButton deleteCategories_button;
	@FXML
	private JFXButton addCategory_button;
	@FXML
	private JFXTreeTableView<CategoryListObject> categories_tree_view;
	@FXML
	private final ObservableList<CategoryListObject> categories_objects = FXCollections.observableArrayList();

	// -----> ESSENTIAL METHODS <-----
	
	public AddCategoryController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager SQL_manager, JPAManager JPA_manager) {
		SQL_manager_object = SQL_manager;
		JPA_manager_object = JPA_manager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Transaction list columns creation
		JFXTreeTableColumn<CategoryListObject, String> category_id = new JFXTreeTableColumn<>("Category ID");
		category_id.setPrefWidth(100);
		category_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().category_id;
			}
		});
		category_id.setResizable(false);
		
		JFXTreeTableColumn<CategoryListObject, String> category_name = new JFXTreeTableColumn<>("Category");
		category_name.setPrefWidth(150);
		category_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().category_name;
			}
		});
		category_name.setResizable(false);
		
		JFXTreeTableColumn<CategoryListObject, String> minimum = new JFXTreeTableColumn<>("Minimum");
		minimum.setPrefWidth(100);
		minimum.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().minimum;
			}
		});
		minimum.setResizable(false);
		
		JFXTreeTableColumn<CategoryListObject, String> maximum = new JFXTreeTableColumn<>("Maximum");
		maximum.setPrefWidth(100);
		maximum.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().maximum;
			}
		});
		maximum.setResizable(false);
		
		JFXTreeTableColumn<CategoryListObject, String> percentage = new JFXTreeTableColumn<>("Descount %");
		percentage.setPrefWidth(120);
		percentage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().percentage;
			}
		});
		percentage.setResizable(false);
		
		JFXTreeTableColumn<CategoryListObject, String> extra_units = new JFXTreeTableColumn<>("Extra units");
		extra_units.setPrefWidth(150);
		extra_units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject, String> param) {
				return param.getValue().getValue().extra_units;
			}
		});
		extra_units.setResizable(false);
		
		List<Category> categories_list = JPA_manager_object.List_all_categories();
		for(Category category: categories_list) {
			Integer category_id_int = category.getCategory_id() - 2;
			if(category.getBenefits() == null) {
				categories_objects.add(new CategoryListObject(category_id_int.toString(), category.getCategory_name(), category.getMinimum().toString(), category.getMaximum().toString()
						, "No discount", "No extra units"));
			} else {
				categories_objects.add(new CategoryListObject(category_id_int.toString().toString(), category.getCategory_name(), category.getMinimum().toString(), category.getMaximum().toString()
						, category.getBenefits().getPercentage().toString(), category.getBenefits().getExtra_units().toString()));
			}	
		}
		
		TreeItem<CategoryListObject> root = new RecursiveTreeItem<CategoryListObject>(categories_objects, RecursiveTreeObject::getChildren);
		categories_tree_view.setPlaceholder(new Label("No categories found"));
		categories_tree_view.getColumns().setAll(category_id, category_name, minimum, maximum, percentage, extra_units);
		categories_tree_view.setRoot(root);
		categories_tree_view.setShowRoot(false);
	}
	
	// -----> REMOVE ALL CATEGORIES <-----
	
	@FXML
	public void removeAllCategories() {
		categories_objects.clear();
		categories_tree_view.refresh();
	}
	
	@FXML
	public void addCategory() {
		try {
			InsertNewCategoryController.setValues(JPA_manager_object, SQL_manager_object);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertNewCategoryView.fxml"));
			Parent root = (Parent) loader.load();
			InsertNewCategoryController category_controller = new InsertNewCategoryController();
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
				public void handle(WindowEvent arg0) {
					categories_objects.clear();
					List<Category> categories_list = JPA_manager_object.List_all_categories();
					for (Category category: categories_list) {
						Integer category_id_int = category.getCategory_id() - 2;
						if(category.getBenefits() == null) {
							categories_objects.add(new CategoryListObject(category_id_int.toString(), category.getCategory_name(), category.getMinimum().toString(), category.getMaximum().toString()
									, "No discount", "No extra units"));
						} else {
							categories_objects.add(new CategoryListObject(category_id_int.toString().toString(), category.getCategory_name(), category.getMinimum().toString(), category.getMaximum().toString()
									, category.getBenefits().getPercentage().toString(), category.getBenefits().getExtra_units().toString()));
						}	
					}
					TreeItem<CategoryListObject> root = new RecursiveTreeItem<CategoryListObject>(categories_objects, RecursiveTreeObject::getChildren);
					categories_tree_view.refresh();
				}
			});	
			stage_window.show();
		} catch (IOException charging_error) {
			charging_error.printStackTrace();
		}
	}
}

//-----> CATEGORY LIST CLASS <-----

//To insert columns into the list of categories with all the information
class CategoryListObject extends RecursiveTreeObject<CategoryListObject> {
	
	StringProperty category_id;
	StringProperty category_name;
	StringProperty minimum;
	StringProperty maximum;
	StringProperty percentage;
	StringProperty extra_units;
	
	public CategoryListObject(String category_id, String category_name, String minimum, String maximum, String percentage, String extra_units) {
		this.category_id = new SimpleStringProperty(category_id);
		this.category_name = new  SimpleStringProperty(category_name);	
		this.minimum = new SimpleStringProperty(minimum);
		this.maximum = new SimpleStringProperty(maximum);
		this.percentage = new SimpleStringProperty(percentage);
		this.extra_units = new SimpleStringProperty(extra_units);
	}
}

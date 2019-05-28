package db.UImenuFX;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;

import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class BengClubController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----
	
	@SuppressWarnings("unused")
	private static SQLManager SQL_manager_object;
	private static JPAManager JPA_manager_object;
	
	// -----> FXML ATRIBUTES <-----
	

	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<CategoryListObject2> club_tree_view;
	@FXML
	private final ObservableList<CategoryListObject2> categories_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----
	
	public BengClubController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager sqlmanager, JPAManager jpamanager) {
		SQL_manager_object = sqlmanager;
		JPA_manager_object=jpamanager;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Transaction list columns creation
		JFXTreeTableColumn<CategoryListObject2, String> category_id = new JFXTreeTableColumn<>("ID");
		category_id.setPrefWidth(70);
		category_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().category_id;
			}
		});
		category_id.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> category_name = new JFXTreeTableColumn<>("Name");
		category_name.setPrefWidth(95);
		category_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().category_name;
			}
		});
		category_name.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> min = new JFXTreeTableColumn<>("Minimum Points");
		min.setPrefWidth(150);
		min.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().min;
			}
		});
		min.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> max = new JFXTreeTableColumn<>("Maximum Points");
		max.setPrefWidth(150);
		max.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().max;
			}
		});
		max.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> penalization = new JFXTreeTableColumn<>("Penalization");
		penalization.setPrefWidth(170);
		penalization.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().penalization;
			}
		});
		penalization.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> percentage = new JFXTreeTableColumn<>("Percentage Gain");
		percentage.setPrefWidth(170);
		percentage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().percentage;
			}
		});
		percentage.setResizable(false);
		JFXTreeTableColumn<CategoryListObject2, String> extra = new JFXTreeTableColumn<>("Extra Units");
		extra.setPrefWidth(170);
		extra.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CategoryListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CategoryListObject2, String> param) {
				return param.getValue().getValue().extra;
			}
		});
		extra.setResizable(false);
		List<Category> category_list = JPA_manager_object.List_all_categories();
		for(Category category: category_list) {
			categories_objects.add(new CategoryListObject2(category.getCategory_id().toString(), category.getCategory_name(), category.getMinimum().toString()
					,category.getMaximum().toString(), category.getPenalization().toString(), category.getBenefits() ));
		}
		TreeItem<CategoryListObject2> root = new RecursiveTreeItem<CategoryListObject2>(categories_objects, RecursiveTreeObject::getChildren);
		club_tree_view.setPlaceholder(new Label("No transactions found"));
		club_tree_view.getColumns().setAll(category_id, category_name, min, max, penalization, percentage, extra);
		club_tree_view.setRoot(root);
		club_tree_view.setShowRoot(false);
		
		
	}
	
	// -----> REFRESH TRANSACTION LIST VIEW <-----
	
	public void refreshTransactionListView() {
		categories_objects.clear();
		List<Category> category_list = JPA_manager_object.List_all_categories();
		for(Category category: category_list) {
			categories_objects.add(new CategoryListObject2(category.getCategory_id().toString(), category.getCategory_name(), category.getMinimum().toString()
					,category.getMaximum().toString(), category.getPenalization().toString(), category.getBenefits() ));
		}
		TreeItem<CategoryListObject2> root = new RecursiveTreeItem<CategoryListObject2>(categories_objects, RecursiveTreeObject::getChildren);
		club_tree_view.refresh();
	}
	
	
}

//-----> CATEGORY LIST CLASS <-----

//To insert columns into the list of transactions with all the information
class CategoryListObject2 extends RecursiveTreeObject<CategoryListObject2> {
	
	StringProperty category_id;
	StringProperty category_name;
	StringProperty min;
	StringProperty max;
	StringProperty penalization;
	StringProperty percentage;
	StringProperty extra;
	
	public CategoryListObject2(String category_id, String category_name, String min, String max, String penalization, Benefits benefits) {
		this.category_id = new SimpleStringProperty(category_id);
		this.category_name= new SimpleStringProperty(category_name);
		this.min= new SimpleStringProperty(min);
		this.max= new SimpleStringProperty(max);
		this.penalization= new SimpleStringProperty(penalization);
		this.percentage= new SimpleStringProperty(String.valueOf(benefits.getPercentage()));
		this.extra= new SimpleStringProperty(String.valueOf(benefits.getExtra_units()));
		
	}
}



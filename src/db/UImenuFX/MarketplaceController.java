package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;
import javax.swing.tree.TreeNode;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;


import db.jdbc.SQLManager;
import db.jpa.JPAManager;
import db.pojos.Biomaterial;
import db.pojos.Category;
import db.pojos.Client;
import db.pojos.Transaction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class MarketplaceController implements Initializable {
	
	private static SQLManager manager_object;	
	private static JPAManager jpamanager_object;	
	private static Client client_account;
	private PurchaseConfirmationController purchase_controller;
	private UtilityInformationController information_controller;
	
	@FXML
	private Pane menu_main_pane;
	@FXML
	private Pane iteminfopane;
	@FXML
	private Label prueba;
	@FXML
	private JFXTreeTableView<BiomaterialListObject> biomaterials_tree_view;
	@FXML
	private ImageView itemimg1;
	@FXML
	private ImageView itemimg2;
	@FXML
	private JFXTextArea iteminformation;
	@FXML
	private final ObservableList<BiomaterialListObject> biomaterial_objects = FXCollections.observableArrayList();
	@FXML
	private Biomaterial biomat;
	@FXML
	private static Stage stage_window;
	private Transaction transaction;
	private float gain=0;
	private Integer total=0;
	private List<Biomaterial> biomaterial_list=new ArrayList<Biomaterial>();
	private List<Category> category_list=new ArrayList<Category>();
	 
	public static void setValues(SQLManager manager,Client client, JPAManager jpamanager) {
		manager_object=manager;
		client_account=client;
		jpamanager_object=jpamanager;
	}
	
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
			
		// Biomaterials list columns creation
		JFXTreeTableColumn<BiomaterialListObject, String> product_name = new JFXTreeTableColumn<>("Product");
		product_name.setPrefWidth(140);
		product_name.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().product_name;
					}
				});
		product_name.setResizable(false);
	
		JFXTreeTableColumn<BiomaterialListObject, String> price = new JFXTreeTableColumn<>("Price / unit ($)");
		price.setPrefWidth(90);
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().price_unit;
					}
				});
		price.setResizable(false);
		
		JFXTreeTableColumn<BiomaterialListObject, Number> id = new JFXTreeTableColumn<>("id");
		id.setPrefWidth(40);
		id.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, Number>, ObservableValue<Number>>() {
					@Override
					public ObservableValue<Number> call(CellDataFeatures<BiomaterialListObject, Number> param) {
						return param.getValue().getValue().bio_id;
					}
				});
		id.setResizable(false);
				
		/*JFXTreeTableColumn<BiomaterialListObject, JFXButton> action = new JFXTreeTableColumn<>("Quantity");
		action.setPrefWidth(200);
					
		action.setResizable(false);
		JFXTreeTableColumn<BiomaterialListObject, JFXButton> plus = new JFXTreeTableColumn<>("Plus");
		plus.setPrefWidth(100);
		plus.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, JFXButton>, ObservableValue<JFXButton>>() {
					@Override
					public ObservableValue<JFXButton> call(CellDataFeatures<BiomaterialListObject, JFXButton> param) {
						return param.getValue().getValue().plus;
					}
				});
		plus.setResizable(false);
		
		JFXTreeTableColumn<BiomaterialListObject, JFXButton> minus = new JFXTreeTableColumn<>("Minus");
		minus.setPrefWidth(100);
		minus.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, JFXButton>, ObservableValue<JFXButton>>() {
					@Override
					public ObservableValue<JFXButton> call(CellDataFeatures<BiomaterialListObject, JFXButton> param) {
						return param.getValue().getValue().minus;
					}
				});
		minus.setResizable(false);
		action.getColumns().addAll(plus, minus);
				
		JFXTreeTableColumn<BiomaterialListObject, Number> total  = new JFXTreeTableColumn<>("TOTAL");
		total.setPrefWidth(100);
		total.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, Number>, ObservableValue<Number>>() {
					@Override
					public ObservableValue<Number> call(CellDataFeatures<BiomaterialListObject, Number> param) {
						return param.getValue().getValue().total;
					}
				});
		total.setResizable(false);
		*/
		
		JFXTreeTableColumn<BiomaterialListObject, JFXTextField> tot = new JFXTreeTableColumn<>("TOTAL");
		tot.setPrefWidth(100);
		tot.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, JFXTextField>, ObservableValue<JFXTextField>>() {
					@Override
					public ObservableValue<JFXTextField> call(CellDataFeatures<BiomaterialListObject, JFXTextField> param) {
						return param.getValue().getValue().tot;
					}
				});
		tot.setResizable(false);
		JFXTreeTableColumn<BiomaterialListObject, JFXButton> info = new JFXTreeTableColumn<>("More Info");
		info.setPrefWidth(100);
		info.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, JFXButton>, ObservableValue<JFXButton>>() {
					@Override
					public ObservableValue<JFXButton> call(CellDataFeatures<BiomaterialListObject, JFXButton> param) {
						return param.getValue().getValue().info;
					}
				});
		info.setResizable(false);
		
		List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
		for(Biomaterial biomaterial: biomaterial_list) {
			biomaterial_objects.add(new BiomaterialListObject(biomaterial.getBiomaterial_id(), biomaterial.getName_product(), biomaterial.getAvailable_units(), 
					biomaterial.getPrice_unit().toString(), biomaterial.getExpiration_date().toString(), 0));
		}		
		TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
		biomaterials_tree_view.getColumns().setAll(id, product_name, price, tot, info);
		biomaterials_tree_view.setRoot(root);
		biomaterials_tree_view.setShowRoot(false);
                
				
		//Ables the selection of several biomaterials of treeTable
		//next step: associate selection's id to a variable being read by Order product controller
    }	
	
	public void refreshBiomaterialsListView(Integer total) {
		biomaterial_objects.clear();
		List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
		for (Biomaterial biomaterial: biomaterial_list) {
			biomaterial_objects.add(new BiomaterialListObject(biomaterial.getBiomaterial_id(),biomaterial.getName_product(),biomaterial.getAvailable_units(),
					biomaterial.getPrice_unit().toString(),biomaterial.getExpiration_date().toString(), total));
		}
		TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
		biomaterials_tree_view.refresh();
    }
	
	public void refreshBiomaterialsCell(Integer total,TreeItem<BiomaterialListObject> tree) {
		tree.getValue().setTotal(new SimpleIntegerProperty(total));
		Integer tot = Integer.parseInt(tree.getValue().tot.get().getText().toString());
		Integer cuenta =tot+total;
		tree.getValue().tot.get().setText(""+cuenta);
		//TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
		biomaterials_tree_view.refresh();
	}
	
	@FXML
	private void buyitem(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseConfirmationView.fxml"));
		Parent root = (Parent) loader.load();
		purchase_controller=loader.getController();
		purchase_controller.getYesButton().setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				ObservableList<BiomaterialListObject> datacolumn = FXCollections.observableArrayList();
				for(BiomaterialListObject biomat :biomaterial_objects) {
					if(!biomat.tot.get().getText().isEmpty()) {
					
					datacolumn.add(biomat);
					}		
				} 
				for(BiomaterialListObject biomat :datacolumn) {
					 Biomaterial biom=manager_object.Search_biomaterial_by_id(biomat.getId());
					 biomaterial_list.add(biom);
					 Float gains = (float) (biomat.getTot()*Float.parseFloat(biomat.getPrice()));
					 Integer totals =biomat.getTot();
					 if(!(client_account.getCategory()==null)) {
						gains= gains - (gains * ((client_account.getCategory().getBenefits().getPercentage())/100));
						totals= totals + client_account.getCategory().getBenefits().getExtra_units();
					 }
					 gain=gain+gains;
					 total=total+totals;
				}
				
				 Float percentage =(float) 0.1;
				 Integer addpoints= (int) (gain*percentage);
				
				 
				 if(client_account.getPoints()!=null) {	 
				 Integer currentpoints=client_account.getPoints();
				 Integer points= currentpoints+addpoints;
				 Boolean cont=true;
				 Integer max=0;
				 for(Category category:category_list) {
					 if(cont==true) {
						 max=category.getMaximum();
						 cont=false;
					 }
					 if(category.getMaximum()>max) {
						 max=category.getMaximum();
					 }
				 }
				 if(client_account.getPoints()<=max) {
				 client_account.setPoints(points);
				 }
				 else {
					 client_account.setPoints(max);
				 }
				 
				 
				 manager_object.Update_client_info(client_account);
				 }
				 else {
					 Integer currentpoints=0;
					 Integer points= currentpoints+addpoints;
					 client_account.setPoints(points);
					 }
				 
					 category_list=jpamanager_object.List_all_categories();
					 for(Category category:category_list) {
						 if(client_account.getPoints()>category.getMinimum() && client_account.getPoints()<category.getMaximum())
						 client_account.setCategory(category);
						 break;
					 }
					 manager_object.Update_client_info(client_account);
				     transaction = new Transaction(gain,total, biomaterial_list, client_account);
					 manager_object.Insert_new_transaction(transaction);
				
				stage_window.close();
			}
		});
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
		stage_window.show();
	}
		  
	@FXML 
	private void iteminfo (MouseEvent event) throws IOException{
		TreeItem<BiomaterialListObject> biomaterial_object = biomaterials_tree_view.getSelectionModel().getSelectedItem();
		System.out.println(biomaterial_object);
		if(biomaterial_object!=null) {
	    	//iteminformation.setText(biomaterial_object.getValue().product_name.getValue().toString());
	    	//biomaterial_object.getValue().bio_id.getValue();
	    	//biomat=manager_object.Search_biomaterial_by_id(biomaterial_object.getValue().bio_id.getValue());
	    	//iteminformation.setText(biomat.toString());
	    	for(BiomaterialListObject biomat :biomaterial_objects) {
	    		biomat.setEnable(false);
	    		
	    	}
	    	biomaterial_object.getValue().setEnable(true);
	    	
	    }
		biomaterial_object.getValue().setTree(biomaterial_object);
		
		biomaterials_tree_view.refresh();
	}
/*
	@FXML 
		private void getItem(MouseEvent event) {
			 Node node = event.getPickResult().getIntersectedNode();
			 node.getId();
			 System.out.println( node.getId());
			 TreeItem<BiomaterialListObject> tree =biomaterials_tree_view.getSelectionModel().getSelectedItem();
			System.out.println(tree);
			tree.getValue().setTree(tree);
			}*/
		
		
	

	// To insert columns into the list of biomaterials with all the information
	public class BiomaterialListObject extends RecursiveTreeObject<BiomaterialListObject> {
	
		StringProperty action;
		ObjectProperty<JFXTextField> tot;
		IntegerProperty total;
		StringProperty product_name;
		IntegerProperty available_units;
		StringProperty price_unit;
		StringProperty expiration_date;
		IntegerProperty bio_id;
		ObjectProperty<JFXButton> info;
		//ObjectProperty<JFXButton> plus;
    	//ObjectProperty<JFXButton> minus;
    	private TreeItem<BiomaterialListObject> tree;
    
		@FXML
		private ImageView add;
		@FXML
		private ImageView sub;
		@FXML
		private Image img;
		@SuppressWarnings("unused")
		private Boolean counter;
		private Boolean enable;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public BiomaterialListObject(Integer id, String product_name, Integer available_units, String price_unit,
			
			String expiration_date, Integer total) {
			this.product_name = new SimpleStringProperty(product_name);
			this.available_units = new SimpleIntegerProperty(available_units);
			this.price_unit = new SimpleStringProperty(price_unit);
			this.expiration_date = new SimpleStringProperty(expiration_date);
			this.bio_id = new SimpleIntegerProperty(id);
			if(total!=null) {
				this.total= new SimpleIntegerProperty(total);
			} else {
				this.total= new SimpleIntegerProperty(0);
			}
	        this.enable=false;
	        TextField tot = new JFXTextField();
	        Button info = new JFXButton("");
	        Image inf = new Image(getClass().getResourceAsStream("src.IconPictures/plus-black-symbol.png"));
	        ImageView add=new ImageView(inf);
			add.setFitHeight(15);
			add.setFitWidth(15);
			info.setGraphic(add);
			/*Button plus = new JFXButton("");
			Button minus = new JFXButton("");
			Image pls = new Image(getClass().getResourceAsStream("src.IconPictures/plus-black-symbol.png"));
			Image min = new Image(getClass().getResourceAsStream("src.IconPictures/minus.png"));
			ImageView add=new ImageView(pls);
			add.setFitHeight(15);
			add.setFitWidth(15);
			plus.setGraphic(add);
			ImageView sub=new ImageView(min);
			sub.setFitHeight(15);
			sub.setFitWidth(15);
			minus.setGraphic(sub);
			plus.setDisable(enable);
			minus.setDisable(enable);*/
			this.tot=new SimpleObjectProperty(tot);
			this.info=new SimpleObjectProperty(info);
			//this.plus=new SimpleObjectProperty(plus);
			//this.minus=new SimpleObjectProperty(minus);
			this.info.get().setOnAction((MouseClickEvent) -> {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilityInformationView.fxml"));
				Parent root;
				try {
					root = (Parent) loader.load();
			
				information_controller=loader.getController();
				Biomaterial biom=manager_object.Search_biomaterial_by_id(this.bio_id.intValue());
				System.out.println(biom);
				if(!(biom.getUtility()==null)|| !(biom.getMaintenance()==null)) {
					information_controller.setHeatcold_label(biom.getUtility().getHeat_cold());
					information_controller.setFlex_label(biom.getUtility().getFlexibility());
					information_controller.setPres_label(biom.getUtility().getResistance());
					information_controller.setRes_label(String.valueOf(biom.getUtility().getPressure()));
					information_controller.setStr_label(String.valueOf(biom.getUtility().getStrength()));
					information_controller.setEnpres_label(String.valueOf(biom.getMaintenance().getPressure()));
					information_controller.setO2_label(biom.getMaintenance().getO2_supply());
					information_controller.setLight_label(biom.getMaintenance().getLight());
					information_controller.setHum_label(String.valueOf(biom.getMaintenance().getHumidity()));
					information_controller.setTemp_label(String.valueOf(biom.getMaintenance().getTemperature()));
					information_controller.setComp_label(biom.getMaintenance().getCompatibility());
					information_controller.setOth_label(biom.getMaintenance().getOthers());
				}
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
				stage_window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			/*plus.setOnMouseClicked(new EventHandler<MouseEvent>() {
				
			@Override
			public void handle(MouseEvent event) {
				 Node node = event.getPickResult().getIntersectedNode();
				 TreeItem<BiomaterialListObject> tre =biomaterials_tree_view.getSelectionModel().getSelectedItem();
				System.out.println(tre);
				setTree(tre);
				}
			});
		
			this.plus.get().setOnAction((MouseClickEvent) -> {
			
				this.total=new SimpleIntegerProperty(this.total.intValue() + 1);
				refreshBiomaterialsCell(this.total.intValue(), tree);	
			});
			
			this.minus.get().setOnAction((MouseClickEvent) -> {
				if(this.total.intValue()==0) {
					System.out.println("No se puede bajar más");
				} else {
					this.total=new SimpleIntegerProperty(this.total.intValue() - 1);
				}
				refreshBiomaterialsCell(this.total.intValue(), tree);
			});*/
		}

		public void setTotal(IntegerProperty total) {
			this.total=total;
		}
		public Integer getId() {
			return this.bio_id.intValue();
		}
		public Integer getTotal() {
			return this.total.intValue();
		}
		
		public Integer getTot() {
			return Integer.parseInt(this.tot.get().getText().toString());
		}
		public String getPrice() {
			return this.price_unit.getValue().toString();
		}
		public void setTree(TreeItem<BiomaterialListObject> tree) {
			this.tree=tree;
		}
		public Boolean getEnable() {
			return this.enable;
		}
		public void setEnable(Boolean enable) {
			this.enable=enable;
		}
	}
	
}

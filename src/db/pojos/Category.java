package db.pojos;

import db.model.UtilMethods;
import java.io.Serializable;
import java.util.*;

import db.jdbc.SQLManager;

public class Category extends UtilMethods implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer category_id;
	private String category_name;
	private Integer penalization;
	private Integer maximum;
	private Integer minimum;
	private List<Category> categories_list;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String category_name, Integer penalization, Integer maximum, Integer minimum) {
		super();
		this.category_name = category_name;
		this.penalization = penalization;
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getPenalization() {
		return penalization;
	}

	public void setPenalization(Integer penalization) {
		this.penalization = penalization;
	}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + category_id;
		return result;
	}

	public List<Category> Categories_of_cients(){
		categories_list = new LinkedList<Category>();

		
		Category cat_Diamond1 = new Category("Diamond 1", 920/4, 1000, 920);
		Category cat_Diamond2 = new Category("Diamond 2", 820/4, 919, 820);
		Category cat_Platinum1 = new Category("Platinum 1", 650/4, 819, 650);
		Category cat_Platinum2 = new Category("Platinum 2", 550/4, 649, 550);
		Category cat_Gold1 = new Category("Gold 1", 450/4, 549, 450);
		Category cat_Gold2 = new Category("Gold 2", 400/4, 449, 400);
		Category cat_Silver1 = new Category("Silver 1", 280/4, 399, 280);
		Category cat_Silver2 = new Category("Silver 2", 150/4, 279, 150);
		Category cat_Bronze1 = new Category("Bronze 1", 100/4, 149, 100);
		Category cat_Bronze2 = new Category("Bronze 2", 90/4, 99, 90);
		Category cat_Bronze3 = new Category("Bronze 3", 80/4, 89, 80);
		Category none_cat = new Category("None", 0, 799, 0);
		
		categories_list.add(none_cat);
		categories_list.add(cat_Bronze3);
		categories_list.add(cat_Bronze2);
		categories_list.add(cat_Bronze1);
		categories_list.add(cat_Silver2);
		categories_list.add(cat_Silver1);
		categories_list.add(cat_Gold2);
		categories_list.add(cat_Gold1);
		categories_list.add(cat_Platinum2);
		categories_list.add(cat_Platinum1);
		categories_list.add(cat_Diamond2);
		categories_list.add(cat_Diamond1);

		return categories_list;
		
	}
	
	
	
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", penalization="
				+ penalization + ", maximum=" + maximum + ", minimum=" + minimum + ", category_list="
				+ categories_list + "]";
	}

	

	public void assign_Category_To_Client(SQLManager manager, Client client) {
		
		int sum_points = Sum_all_client_points(manager, client) ;
		
		for(Category category : categories_list) {
			
			if(sum_points <= category.getMaximum() &&  sum_points >= category.getMinimum()) {
				client.setCategory(category.getCategory_name());
			}
		}
	}

	
	/*----------------- Method penalization for client ---------------*/
	
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (category_id != other.category_id)
			return false;
		return true;
	}
	
}

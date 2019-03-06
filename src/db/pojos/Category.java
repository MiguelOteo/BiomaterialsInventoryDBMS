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
	
	// -----> CATEGORY LEVELS METHODS <-----

	public List<Category> Categories_of_cients(){
		categories_list = new LinkedList<Category>();
		
		categories_list.add(new Category("None", 0, 799, 0));
		categories_list.add(new Category("Bronze 3", 80/4, 89, 80));
		categories_list.add(new Category("Bronze 2", 90/4, 99, 90));
		categories_list.add(new Category("Bronze 1", 100/4, 149, 100));
		categories_list.add(new Category("Silver 2", 150/4, 279, 150));
		categories_list.add(new Category("Silver 1", 280/4, 399, 280));
		categories_list.add(new Category("Gold 2", 400/4, 449, 400));
		categories_list.add(new Category("Gold 1", 450/4, 549, 450));
		categories_list.add(new Category("Diamond 2", 820/4, 919, 820));
		categories_list.add(new Category("Diamond 1", 920/4, 1000, 920));
		categories_list.add(new Category("Platinum 2", 550/4, 649, 550));
		categories_list.add(new Category("Platinum 1", 650/4, 819, 650));
		return categories_list;
	}
	
	public void assign_Category_To_Client(SQLManager manager, Client client) {
		
		int sum_points = Sum_all_client_points(manager, client) ;
		
		for(Category category : categories_list) {
			
			if(sum_points <= category.getMaximum() &&  sum_points >= category.getMinimum()) {
				client.setCategory(category.getCategory_name());
			}
		}
	}

	// -----> PENALIZATION METHOD <-----

	
	
	// -----> OVERRIDE METHODS <----- 
	 
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", penalization="
				+ penalization + ", maximum=" + maximum + ", minimum=" + minimum + ", category_list="
				+ categories_list + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + category_id;
		return result;
	}
	
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

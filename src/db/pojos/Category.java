package db.pojos;

import db.jdbc.SQLManager;
import db.model.UtilMethods;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Category extends UtilMethods implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer category_id;
	private String category_name;
	private float penalization;
	private Integer maximum;
	private Integer minimum;
	private List<Category> categories_list;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String category_name, float penalization, Integer maximum, Integer minimum) {
		super();
		this.category_name = category_name;
		this.penalization = penalization;
		this.maximum = maximum;
		this.minimum = minimum;
	}

    public Integer getCategory_id() {
    	return category_id;
    }
    
    public void setCategory_id(Integer category_id) {
    	this.category_id = category_id;
    }
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public float getPenalization() {
		return penalization;
	}

	public void setPenalization(float penalization) {
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

	// -----> OVERRIDE METHODS <----- 
	 
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", penalization="
				+ penalization + ", maximum=" + maximum + ", minimum=" + minimum + "]";
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
	
	
public void assign_Category_To_Client(SQLManager manager, Client client) {
		
		int sum_points = Sum_all_client_points(manager, client) ;
		
		for(Category category : categories_list) {
			
			if(sum_points <= category.getMaximum() &&  sum_points >= category.getMinimum()) {
				client.setCategory(category.getCategory_name());
			}
		}
	}

	
	/*----------------- Method penalization for client ---------------
	
	public void check_Date(Transaction transaction) {
		
		Date currentDate = new Date();
		LocalDateTime localDate = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		
		LocalDateTime lastPurchase = LocalDateTime.now().minusMonths(3);
		
		Date dbDate = transaction.getTransaction_date();
		
		if (dbDate.before(lastPurchase)) {
			
		}
	}
	*/
	
	
public List<Category> Categories_of_client(){
	categories_list = new LinkedList<Category>();
	
	Category Diamond1 = new Category("Diamond 1", 920/4, 1000, 920);
	Category Diamond2 = new Category("Diamond 2", 820/4, 919, 820);
	Category Platinum1 = new Category("Platinum 1", 650/4, 819, 650);
	Category Platinum2 = new Category("Platinum 2", 550/4, 649, 550);
	Category Gold1 = new Category("Gold 1", 450/4, 549, 450);
	Category Gold2 = new Category("Gold 2", 400/4, 449, 400);
	Category Silver1 = new Category("Silver 1", 280/4, 399, 280);
	Category Silver2 = new Category("Silver 2", 150/4, 279, 150);
	Category Bronze1 = new Category("Bronze 1", 100/4, 149, 100);
	Category Bronze2 = new Category("Bronze 2", 90/4, 99, 90);
	Category Bronze3 = new Category("Bronze 3", 80/4, 89,80);
	Category None = new Category("None", 0, 79, 0);

	categories_list.add(None);
	categories_list.add(Diamond1);
	categories_list.add(Diamond2);
	categories_list.add(Platinum1);
	categories_list.add(Platinum2);
	categories_list.add(Gold1);
	categories_list.add(Gold2);
	categories_list.add(Silver1);
	categories_list.add(Silver2);
	categories_list.add(Bronze1);
	categories_list.add(Bronze2);
	categories_list.add(Bronze3);


	return categories_list;

}
	
	
}

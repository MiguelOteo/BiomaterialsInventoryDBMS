package db.pojos;

import db.model.UtilMethods;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "category")
public class Category extends UtilMethods implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "category")
	@TableGenerator(name = "category", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "category")
	private Integer category_id;
	private String category_name;
	private Integer minimum;
	private int penalization;
	private Integer maximum;
	@OneToOne @JoinColumn(name = "benefits_id")
	private Benefits benefits;
	@OneToMany @JoinColumn(name = "client_id")
	private List<Client> clients_list; 
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String category_name, Integer maximum, Integer minimum) {
		super();
		this.category_name = category_name;
		this.maximum = maximum;
		this.minimum = minimum;
		this.penalization = minimum/4;
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

	public int getPenalization() {
		return penalization;
	}

	public void setPenalization(Integer penalization) {
		this.penalization = penalization;
	}
	
		public Benefits getBenefits() {
			return benefits;
		}
	
		public void setBenefits(Benefits benefits) {
			this.benefits = benefits;
		}
	
		public List<Client> getClients_list() {
			return clients_list;
		}
	
		public void setClients_list(List<Client> clients_list) {
			this.clients_list = clients_list;
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
}





package db.pojos;

import java.io.Serializable;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer category_id;
	private String category_name;
	private Float penalization;
	private Integer maximum;
	private Integer minimum;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String category_name, Float penalization, Integer maximum, Integer minimum) {
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

	public Float getPenalization() {
		return penalization;
	}

	public void setPenalization(Float penalization) {
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

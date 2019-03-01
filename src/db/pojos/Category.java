package db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Category implements Serializable{


	private static final long serialVersionUID = 1L;
	private Integer Category_id;
    private String Category_name;
    private Integer Penalization;
    private Integer max;
    private Integer min;
    private Float percentage;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer category_id, String category_name, Integer penalization, Integer max, Integer min,
			Float percentage) {
		super();
		Category_id = category_id;
		Category_name = category_name;
		Penalization = penalization;
		this.max = max;
		this.min = min;
		this.percentage = percentage;
	}
	public Integer getCategory_id() {
		return Category_id;
	}
	public void setCategory_id(Integer category_id) {
		Category_id = category_id;
	}
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
	public Integer getPenalization() {
		return Penalization;
	}
	public void setPenalization(Integer penalization) {
		Penalization = penalization;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Float getPercentage() {
		return percentage;
	}
	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "Category [Category_id=" + Category_id + ", Category_name=" + Category_name + ", Penalization="
				+ Penalization + ", max=" + max + ", min=" + min + ", percentage=" + percentage + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Category_id == null) ? 0 : Category_id.hashCode());
		result = prime * result + ((Category_name == null) ? 0 : Category_name.hashCode());
		result = prime * result + ((Penalization == null) ? 0 : Penalization.hashCode());
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
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
		if (Category_id == null) {
			if (other.Category_id != null)
				return false;
		} else if (!Category_id.equals(other.Category_id))
			return false;
		if (Category_name == null) {
			if (other.Category_name != null)
				return false;
		} else if (!Category_name.equals(other.Category_name))
			return false;
		if (Penalization == null) {
			if (other.Penalization != null)
				return false;
		} else if (!Penalization.equals(other.Penalization))
			return false;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (percentage == null) {
			if (other.percentage != null)
				return false;
		} else if (!percentage.equals(other.percentage))
			return false;
		return true;
	}
	
}

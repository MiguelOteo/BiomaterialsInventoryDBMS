package db.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "benefits")
public class Benefits implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "benefits")
	@TableGenerator(name = "benefits", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "benefit")
	private Integer benefits_id;
	private Float percentage;
	//private Integer min_amount;
	private Integer extra_units;

	public Benefits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Benefits(Float percentage/*, Integer minAmount*/, Integer extraUnits) {
		super();
		this.percentage = percentage;
		//this.min_amount = minAmount;
		this.extra_units = extraUnits;
	}

	public Integer getBenefits_id() {
		return benefits_id;
	}

	public void setBenefits_id(Integer benefits_id) {
		this.benefits_id = benefits_id;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
/*
	public Integer getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(Integer minAmount) {
		this.min_amount = minAmount;
	}
*/
	public Integer getExtra_units() {
		return extra_units;
	}

	public void setExtra_units(Integer extraUnits) {
		this.extra_units = extraUnits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + benefits_id;
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
		Benefits other = (Benefits) obj;
		if (benefits_id != other.benefits_id)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Benefits [percentage=" + percentage /*+ ", minAmount=" + min_amount*/
				+ ", extraUnits=" + extra_units + "]";
	}
}

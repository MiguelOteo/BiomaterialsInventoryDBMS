package db.pojos;

import java.io.Serializable;

public class Benefits implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer benefits_id;
	private String others;
	private Float percentage;
	private Integer min_amount;
	private Integer extra_units;

	public Benefits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Benefits(String others, Float percentage, Integer minAmount, Integer extraUnits) {
		super();
		this.others = others;
		this.percentage = percentage;
		this.min_amount = minAmount;
		this.extra_units = extraUnits;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public Integer getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(Integer minAmount) {
		this.min_amount = minAmount;
	}

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
		return "Benefits [others=" + others + ", percentage=" + percentage + ", minAmount=" + min_amount
				+ ", extraUnits=" + extra_units + "]";
	}
}

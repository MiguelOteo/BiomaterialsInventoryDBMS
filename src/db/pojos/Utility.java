package db.pojos;

import java.io.Serializable;

public class Utility implements Serializable {

	private static final long serialVersionUID = 1L;
	private String heat_cold;
	private String flexibility;
	private String resistance;
	private Float pressure;
	private Float strength;
	private Integer utility_id;
	public Utility() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utility(String heat_cold, String flexibility, String resistance, Float pressure, Float strength) {
		super();
		this.heat_cold = heat_cold;
		this.flexibility = flexibility;
		this.resistance = resistance;
		this.pressure = pressure;
		this.strength = strength;
	}
	public String getHeat_cold() {
		return heat_cold;
	}
	public void setHeat_cold(String heat_cold) {
		this.heat_cold = heat_cold;
	}
	public String getFlexibility() {
		return flexibility;
	}
	public void setFlexibility(String flexibility) {
		this.flexibility = flexibility;
	}
	public String getResistance() {
		return resistance;
	}
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	public Float getPressure() {
		return pressure;
	}
	public void setPressure(Float pressure) {
		this.pressure = pressure;
	}
	public Float getStrength() {
		return strength;
	}
	public void setStrength(Float strength) {
		this.strength = strength;
	}
	public Integer getUtility_id() {
		return utility_id;
	}
	public void setUtility_id(Integer utility_id) {
		this.utility_id = utility_id;
	}
	@Override
	public String toString() {
		return "Utility [heat_cold=" + heat_cold + ", flexibility=" + flexibility + ", resistance=" + resistance
				+ ", pressure=" + pressure + ", strength=" + strength + ", utility_id=" + utility_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utility_id == null) ? 0 : utility_id.hashCode());
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
		Utility other = (Utility) obj;
		if (utility_id == null) {
			if (other.utility_id != null)
				return false;
		} else if (!utility_id.equals(other.utility_id))
			return false;
		return true;
	}
}

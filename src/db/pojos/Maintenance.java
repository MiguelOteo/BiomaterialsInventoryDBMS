package db.pojos;

import java.io.Serializable;

public class Maintenance implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer manteinance_id;
	private Float pressure;
	private String o2_supply;
	private String light;
	private Integer humidity;
	private Float temperature;
	private String compatibility;
	private String others;
	
	
	public Maintenance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Maintenance(Float pressure, String o2_supply, String light, Integer humidity,
			Float temperature, String compatibility, String others) {
		super();
		this.pressure = pressure;
		this.o2_supply = o2_supply;
		this.light = light;
		this.humidity = humidity;
		this.temperature = temperature;
		this.compatibility = compatibility;
		this.others = others;
	}


	public Integer getManteinance_id() {
		return manteinance_id;
	}


	public void setManteinance_id(Integer manteinance_id) {
		this.manteinance_id = manteinance_id;
	}


	public Float getPressure() {
		return pressure;
	}


	public void setPressure(Float pressure) {
		this.pressure = pressure;
	}


	public String getO2_supply() {
		return o2_supply;
	}


	public void setO2_supply(String o2_supply) {
		this.o2_supply = o2_supply;
	}


	public String getLight() {
		return light;
	}


	public void setLight(String light) {
		this.light = light;
	}


	public Integer getHumidity() {
		return humidity;
	}


	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}


	public Float getTemperature() {
		return temperature;
	}


	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}


	public String getCompatibility() {
		return compatibility;
	}


	public void setCompatibility(String compatibility) {
		this.compatibility = compatibility;
	}


	public String getOthers() {
		return others;
	}


	public void setOthers(String others) {
		this.others = others;
	}


	@Override
	public String toString() {
		return "Manteinance [manteinance_id=" + manteinance_id + ", pressure=" + pressure + ", o2_supply=" + o2_supply
				+ ", light=" + light + ", humidity=" + humidity + ", temperature=" + temperature + ", compatibility="
				+ compatibility + ", others=" + others + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manteinance_id == null) ? 0 : manteinance_id.hashCode());
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
		Maintenance other = (Maintenance) obj;
		if (manteinance_id == null) {
			if (other.manteinance_id != null)
				return false;
		} else if (!manteinance_id.equals(other.manteinance_id))
			return false;
		return true;
	}	    
}

package db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer transaction_id;
    private Float gain;
    private Integer client_id;
    private Integer units;
    private String product_name;
    private Date transaction_date;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(Integer transaction_id, Float gain, Integer client_id, Integer units, String product_name,
			Date transaction_date) {
		super();
		this.transaction_id = transaction_id;
		this.gain = gain;
		this.client_id = client_id;
		this.units = units;
		this.product_name = product_name;
		this.transaction_date = transaction_date;
	}
	
	public Integer getTransaction_id() {
		return transaction_id;
	}
	
	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public Float getGain() {
		return gain;
	}
	
	public void setGain(Float gain) {
		this.gain = gain;
	}
	
	public Integer getClient_id() {
		return client_id;
	}
	
	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}
	
	public Integer getUnits() {
		return units;
	}
	
	public void setUnits(Integer units) {
		this.units = units;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public Date getTransaction_date() {
		return transaction_date;
	}
	
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", gain=" + gain + ", client_id=" + client_id
				+ ", units=" + units + ", product_name=" + product_name + ", transaction_date=" + transaction_date
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transaction_id == null) ? 0 : transaction_id.hashCode());
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
		Transaction other = (Transaction) obj;
		if (transaction_id == null) {
			if (other.transaction_id != null)
				return false;
		} else if (!transaction_id.equals(other.transaction_id))
			return false;
		return true;
	}
}

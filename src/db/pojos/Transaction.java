package db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer transaction_id;
    private Float gain;
    private Integer client_id;
    private Integer units;
    private Integer product_id;
    private Date transaction_date;
    

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(Float gain, Integer client_id, Integer units, Integer product_id) {
		super();
		this.gain = gain;
		this.client_id = client_id;
		this.units = units;
		this.product_id = product_id;
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
	
	public Integer getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Date getTransaction_date() {
		return transaction_date;
	}
	
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
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
	@Override
	public String toString() {
		return " Transaction ID = " + transaction_id + "\n Gain of transaction = " + gain + "\n Client ID = " + client_id + "\n Units = "
				+ units + "\n Product ID = " + product_id + "\n Transaction date" + transaction_date;
	}

	
	
	
	
	
	
}


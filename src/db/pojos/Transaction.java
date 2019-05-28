package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer transaction_id;
    private Float gain;
    private Integer units;
    private Date transaction_date;
    private Client client;
    private List<Biomaterial> biomaterial_list;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(Float gain, Integer units,  List<Biomaterial> biomaterial_list, Client client) {
		super();
		this.gain = gain;
		this.units = units;
		this.biomaterial_list=biomaterial_list;
        this.client = client;
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
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getUnits() {
		return units;
	}
	
	public void setUnits(Integer units) {
		this.units = units;
	}
	
	public List<Biomaterial> getBiomaterial_list() {
		return biomaterial_list;
	}

	public void setBiomaterial_list(List<Biomaterial> biomaterial_list) {
		this.biomaterial_list = biomaterial_list;
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
		return "Transaction [transaction_id=" + transaction_id + ", gain=" + gain + ", client=" + client
				+ ", units=" + units + ", product=" + ", transaction_date=" + transaction_date + "]";
	}
	
	
	
	
	
	
	
}


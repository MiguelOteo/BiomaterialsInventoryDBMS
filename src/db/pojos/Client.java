package db.pojos;

import java.io.Serializable;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer client_id;
	private String name;
	private Integer telephone;
	private String bank_account;
	private String responsible;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String name, Integer telephone, String bank_account, String responsible) {
		super();
		this.name = name;
		this.telephone = telephone;
		this.bank_account = bank_account;
		this.responsible = responsible;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setId(Integer id) {
		this.client_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client_id == null) ? 0 : client_id.hashCode());
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
		Client other = (Client) obj;
		if (client_id == null) {
			if (other.client_id != null)
				return false;
		} else if (!client_id.equals(other.client_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Client name = " + name + "\n Telephone = " + telephone + "\n Bank account = "
				+ bank_account + "\n Responsible = " + responsible;
	}
}

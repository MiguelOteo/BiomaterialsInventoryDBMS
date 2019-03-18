package db.pojos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "x")
	@TableGenerator(name = "x", table="client", pkColumnName="name", valueColumnName="seq", pkColumnValue="clients")
	private Integer client_id;
	private String password;
	private String name;
	private Integer telephone;
	private String bank_account;
	private String responsible;
	@OneToMany(fetch = FetchType.LAZY)
	private String category;
	private Integer points;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String name, String password) {
		this.name = name;
		this.password = password;
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

	public void setClient_id(Integer id) {
		this.client_id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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
		return "Client [client_id=" + client_id + ", password=" + password + ", name=" + name + ", telephone="
				+ telephone + ", bank_account=" + bank_account + ", responsible=" + responsible + ", category="
				+ category + ", points=" + points + "]";
	}
}

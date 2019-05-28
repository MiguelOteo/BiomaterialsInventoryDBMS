package db.pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "client")
	@TableGenerator(name = "client", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "client")
	private Integer client_id;
	private User user; 
	private String name;
	private Integer telephone;
	private String bank_account;
	private String responsible;
	private Integer points;
	private String email;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;

	private List<Transaction> transactions_list;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String name, User user) {
		this.name = name;
		this.user = user;
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Transaction> getTransactions_list() {
		return transactions_list;
	}

	public void setTransactions_list(List<Transaction> transactions_list) {
		this.transactions_list = transactions_list;
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
		return "Client [client_id=" + client_id + ", user=" + user + ", name=" + name + ", telephone=" + telephone
				+ ", bank_account=" + bank_account + ", responsible=" + responsible + ", category=" + category
				+ ", points=" + points + ", transactions_list=" + transactions_list + "]";
	}

	
}

package db.pojos;

public class Director {

	private Integer director_id;
	private Integer user_id;
	private String director_name;
	private Integer telephone;
	private String email;
	
	public Director() {
		super();
	}

	public Director( String director_name, Integer user_id) {
		super();
		this.director_name = director_name;
		this.user_id = user_id;
	}
	
	public Integer getDirector_id() {
		return director_id;
	}
	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((director_id == null) ? 0 : director_id.hashCode());
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
		Director other = (Director) obj;
		if (director_id == null) {
			if (other.director_id != null)
				return false;
		} else if (!director_id.equals(other.director_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Director [director_id=" + director_id + ", user_id=" + user_id + ", director_name=" + director_name
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}
}

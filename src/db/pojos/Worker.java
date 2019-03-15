package db.pojos;

public class Worker {

	private Integer worker_id;
	private String worker_name;
	private String password;
	
	public Worker() {
		super();
	}

	public Worker(Integer worker_id, String worker_name, String password) {
		super();
		this.worker_id = worker_id;
		this.worker_name = worker_name;
		this.password = password;
	}

	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public String getWorker_name() {
		return worker_name;
	}

	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((worker_id == null) ? 0 : worker_id.hashCode());
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
		Worker other = (Worker) obj;
		if (worker_id == null) {
			if (other.worker_id != null)
				return false;
		} else if (!worker_id.equals(other.worker_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Worker [worker_id=" + worker_id + ", worker_name=" + worker_name + ", password=" + password + "]";
	}
}

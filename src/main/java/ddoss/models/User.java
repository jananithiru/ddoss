package ddoss.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "users")
public class User {

	// ==============
	// PRIVATE FIELDS
	// ==============

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 3, max = 200)
	private String regid;

	@NotNull
	@Size(min = 2, max = 64)
	private String ipaddr;

	// ==============
	// PUBLIC METHODS
	// ==============

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String ipaddr, String regid) {
		this.ipaddr = ipaddr;
		this.regid = regid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

} // class User

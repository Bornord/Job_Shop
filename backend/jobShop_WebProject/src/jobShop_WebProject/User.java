package jobShop_WebProject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * classe abstraite pour les différents types d'utilisateur
 * @author arenard2
 *
 */
@Entity
public abstract class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	
	private LabelRole role;
	private Date creationDate;
	
	public User() {}
	public User(String name, String surname, String login, String password, int id, LabelRole role, Date creationDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		//this.id = id;
		this.role = role;
		this.creationDate = creationDate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LabelRole getRole() {
		return role;
	}
	public void setRole(LabelRole role) {
		this.role = role;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
